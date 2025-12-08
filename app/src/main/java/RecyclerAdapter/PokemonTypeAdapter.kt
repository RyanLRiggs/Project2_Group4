package RecyclerAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project2_group4.R
import com.robertlevonyan.views.chip.Chip

class PokemonTypeAdapter(
    private val context: Context,
    private val typeList: List<String>
) : RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val type = typeList[position]
        Log.d("PokemonTypeAdapter", "Binding type at position $position: $type")
        holder.chip.text = type


    }

    override fun getItemCount(): Int = typeList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chip: Chip = itemView.findViewById(R.id.chip)

        init {
            chip.setOnClickListener {
                Toast.makeText(context, "Clicked: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

