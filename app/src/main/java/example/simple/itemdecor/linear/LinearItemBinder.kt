package example.simple.itemdecor.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import example.simple.itemdecor.R
import me.drakeet.multitype.ItemViewBinder

class LinearItemBinder : ItemViewBinder<LinearItemBean, LinearItemBinder.VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return VH(inflater.inflate(R.layout.item_linear, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: LinearItemBean) {
        holder.itemView.setOnLongClickListener {
            adapter.items.remove(item)
            adapter.notifyItemRemoved(holder.adapterPosition)
            true
        }
    }

    inner class VH(itemView: View) : ViewHolder(itemView)
}