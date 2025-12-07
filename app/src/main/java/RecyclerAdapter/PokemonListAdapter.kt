package RecyclerAdapter

import Common.Common
import Interface.IItemclickListener
import PokiAPI.Pokemon
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2_group4.R

class PokemonListAdapter(
    private val context: Context,
    private val pokemonList: List<Pokemon>
) : RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.txtPokemon.text = pokemon.name

        Glide.with(context)
            .load(pokemon.img)
            .into(holder.imgPokemon)

        holder.setItemClickListener(object : IItemclickListener {
            override fun onClick(view: View, position: Int) {
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(
                        Intent(Common.KEY_ENABLE_HOME)
                            .putExtra("position", position)
                    )
            }
        })
    }

    override fun getItemCount(): Int = pokemonList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPokemon: ImageView = itemView.findViewById(R.id.pokemon_image)
        val txtPokemon: TextView = itemView.findViewById(R.id.pokemon_name)

        private var itemClickListener: IItemclickListener? = null

        fun setItemClickListener(listener: IItemclickListener) {
            this.itemClickListener = listener
        }

        init {
            itemView.setOnClickListener { view ->
                itemClickListener?.onClick(view, adapterPosition)
            }
        }
    }
}
