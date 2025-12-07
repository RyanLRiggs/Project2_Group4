package com.example.project2_group4

import Common.Common
import Common.ItemOffsetDecoration
import RecyclerAdapter.PokemonListAdapter
import Retrofit.IPokemonlList
import Retrofit.RetrofitClient
import PokiAPI.Pokedex
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonList : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var iPokemonlList: IPokemonlList

    private lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = RetrofitClient.instance
        iPokemonlList = retrofit.create(IPokemonlList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recycler_view = itemView.findViewById(R.id.pokemon_recyclerview)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(requireContext(), 2)

        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(
            iPokemonlList.getPokemonList()       // <-- note the () here
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { pokemonDex ->
                    Common.pokemonList = pokemonDex.pokemon ?: emptyList()
                    val adapter = PokemonListAdapter(requireContext(), Common.pokemonList)
                    recycler_view.adapter = adapter
                }
        )
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}