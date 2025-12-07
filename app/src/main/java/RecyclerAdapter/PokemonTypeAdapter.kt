package RecyclerAdapter

import Common.Common
import Interface.IItemclickListener
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.R
import androidx.recyclerview.widget.RecyclerView
import com.robertlevonyan.views.chip.Chip

class PokemonTypeAdapter(internal var context: Context, internal var typeList: List<String>):
RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.chip.chipText = typeList[position]
        holder.chip.changeBackgroungColor(Common.getColorByType(typeList[position]))
    }

    override fun getItemCount(): Int {
        return typeList.size
   }


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var chip: Chip

        init{
            chip = itemView.findViewById(R.id.chip) as Chip
            chip.setOnChipClickListener { Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()}
        }
    }
}