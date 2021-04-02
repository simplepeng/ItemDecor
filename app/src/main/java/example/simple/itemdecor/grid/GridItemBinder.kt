package example.simple.itemdecor.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import example.simple.itemdecor.R
import me.drakeet.multitype.ItemViewBinder

class GridItemBinder : ItemViewBinder<GridItemBean, GridItemBinder.VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return VH(inflater.inflate(R.layout.item_grid, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: GridItemBean) {
        holder.itemView.setOnLongClickListener {
            adapter.items.removeAt(holder.adapterPosition)
            adapter.notifyItemRemoved(holder.adapterPosition)
            true
        }
    }

    inner class VH(itemView: View) : ViewHolder(itemView)
}