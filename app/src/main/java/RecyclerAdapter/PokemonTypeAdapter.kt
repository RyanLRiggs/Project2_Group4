package RecyclerAdapter

import android.content.Context
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
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val label = typeList[position]
        holder.chip.text = label

        // TEMP: log or toast to prove the data
        android.util.Log.d("TypeAdapter", "chip label = $label")
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

