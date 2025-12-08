package com.example.project2_group4

import Common.Common
import PokiAPI.Pokemon
import RecyclerAdapter.PokemonEvolutionAdapter
import RecyclerAdapter.PokemonTypeAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Context
import android.widget.Button
import android.widget.Toast
import com.example.project2_group4.database.entities.AppDatabase
import com.example.project2_group4.database.entities.TeamData



class PokemonDetail : Fragment() {

    internal lateinit var pokemon_img: ImageView
    internal lateinit var pokemon_name: TextView
    internal lateinit var pokemon_height: TextView
    internal lateinit var pokemon_weight: TextView

    lateinit var recycler_type: RecyclerView
    lateinit var recycler_weakness: RecyclerView
    lateinit var recycler_prev_evolution: RecyclerView
    lateinit var recycler_next_evolution: RecyclerView

    private lateinit var btnToggleTeam: Button






    companion object {
        internal var instance: PokemonDetail? = null

        fun getInstance(): PokemonDetail {
            if (instance == null)
                instance = PokemonDetail()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val backImage: ImageView = itemView.findViewById(R.id.img_back)
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        val args = requireArguments()
        val pokemon: Pokemon? = if (args.getString("num") == null) {
            Common.pokemonList[args.getInt("position")]
        } else {
            Common.findPokemonByNum(args.getString("num"))
        }

        pokemon_img = itemView.findViewById(R.id.pokemon_image)
        pokemon_name = itemView.findViewById(R.id.name)
        pokemon_height = itemView.findViewById(R.id.height)
        pokemon_weight = itemView.findViewById(R.id.weight)

        recycler_next_evolution = itemView.findViewById(R.id.recycler_next_evolution)
        recycler_next_evolution.setHasFixedSize(true)
        recycler_next_evolution.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_prev_evolution = itemView.findViewById(R.id.recycler_prev_evolution)
        recycler_prev_evolution.setHasFixedSize(true)
        recycler_prev_evolution.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_type = itemView.findViewById(R.id.recycler_type)
        recycler_type.setHasFixedSize(true)
        recycler_type.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_weakness = itemView.findViewById(R.id.recycler_weakness)
        recycler_weakness.setHasFixedSize(true)
        recycler_weakness.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        btnToggleTeam = itemView.findViewById(R.id.btn_toggle_team)
        setupTeamButton(pokemon, args)

        setDetailPokemon(pokemon)

        return itemView
    }

    private fun setDetailPokemon(pokemon: Pokemon?) {
        if (pokemon == null) return

        Glide.with(requireActivity()).load(pokemon.img).into(pokemon_img)

        pokemon_name.text = pokemon.name
        pokemon_height.text = "Height: ${pokemon.height}"
        pokemon_weight.text = "Weight: ${pokemon.weight}"

        val typeAdapter = PokemonTypeAdapter(requireContext(), pokemon.type ?: emptyList())
        recycler_type.adapter = typeAdapter

        val weaknessAdapter = PokemonTypeAdapter(requireContext(), pokemon.weaknesses ?: emptyList())
        recycler_weakness.adapter = weaknessAdapter

        if (pokemon.prev_evolution != null) {
            val prevEvolution = PokemonEvolutionAdapter(requireContext(), pokemon.prev_evolution!!)
            recycler_prev_evolution.adapter = prevEvolution
        }
        if (pokemon.next_evolution != null) {
            val nextEvolution = PokemonEvolutionAdapter(requireContext(), pokemon.next_evolution!!)
            recycler_next_evolution.adapter = nextEvolution
        }
    }

    private fun getCurrentUserId(): Int {
        val prefs = requireContext().getSharedPreferences("POKEDEX_PREFERENCE", Context.MODE_PRIVATE)
        return prefs.getInt("USERID", -1)
    }

    private fun getPokemonIdForDb(pokemon: Pokemon?, args: Bundle): Int? {
        pokemon ?: return null

        pokemon.num?.toIntOrNull()?.let { return it }

        val position = args.getInt("position", -1)
        if (position >= 0) return position + 1

        return null
    }

    private fun setupTeamButton(pokemon: Pokemon?, args: Bundle) {
        if (pokemon == null) {
            btnToggleTeam.visibility = View.GONE
            return
        }

        val userId = getCurrentUserId()
        if (userId == -1) {
            btnToggleTeam.visibility = View.GONE
            return
        }

        val pokemonId = getPokemonIdForDb(pokemon, args) ?: run {
            btnToggleTeam.visibility = View.GONE
            return
        }

        val db = AppDatabase.getInstance(requireContext())

        Thread {
            val isInTeam = db.teamDAO().isPokemonInTeam(userId, pokemonId) > 0

            requireActivity().runOnUiThread {
                btnToggleTeam.text = if (isInTeam) "Remove from Team" else "Add to Team"

                btnToggleTeam.setOnClickListener {
                    Thread {
                        val currentlyInTeam =
                            db.teamDAO().isPokemonInTeam(userId, pokemonId) > 0

                        if (currentlyInTeam) {
                            db.teamDAO().removePokemonFromTeam(userId, pokemonId)
                            requireActivity().runOnUiThread {
                                btnToggleTeam.text = "Add to Team"
                                Toast.makeText(
                                    requireContext(),
                                    "Removed from your team",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            val name = pokemon.name ?: "Unknown"
                            val teamRow = TeamData(userId, pokemonId, name)
                            db.teamDAO().insertTeamMember(teamRow)

                            requireActivity().runOnUiThread {
                                btnToggleTeam.text = "Remove from Team"
                                Toast.makeText(
                                    requireContext(),
                                    "Added to your team",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }.start()
                }
            }
        }.start()
    }


}