package RecyclerAdapter

import PokiAPI.Pokemon
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.R
import androidx.recyclerview.widget.RecyclerView
import com.example.project2_group4.PokemonList
import res/layout/pokemon_list_item.view.*



class PokemonListAdapter (internal var context: Context, internal var pokemonList: List<Pokemon>):
    RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent:ViewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        Glide.with(context).load(pokemonList[position].img).into(holder.img_pokemon)
        holder.txt_pokemon.text=pokemonList[position].name
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var img_pokemon: ImageView
        internal var txt_pokemon: TextView
        init {
            img_pokemon = itemView.findViewById<>(R.id.pokemon_image) as ImageView
            txt_pokemon = itemView.findViewById(R.id.pokemon_name) as TextView
        }
    }
}