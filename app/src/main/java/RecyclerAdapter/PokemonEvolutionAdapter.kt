package RecyclerAdapter

import Common.Common
import PokiAPI.Evolution
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2_group4.R
import com.robertlevonyan.views.chip.Chip

class PokemonEvolutionAdapter(
    private val context: Context,
    private val evolutionList: List<Evolution>
) : RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val evo = evolutionList[position]

        holder.chip.text = evo.name ?: "Unknown"

    }

    override fun getItemCount(): Int = evolutionList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chip: Chip = itemView.findViewById(R.id.chip)

        init {
            chip.setOnClickListener {
                val evo = evolutionList[adapterPosition]
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(
                        Intent(Common.KEY_NUM_EVOLUTION)
                            .putExtra("num", evo.num)
                    )
            }
        }
    }
}



