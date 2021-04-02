package example.simple.itemdecor.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import example.simple.itemdecor.R
import me.drakeet.multitype.ItemViewBinder

class LinearHorizontalItemBinder : ItemViewBinder<LinearItemBean, LinearHorizontalItemBinder.VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return VH(inflater.inflate(R.layout.item_linear_horizontal, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: LinearItemBean) {}
    inner class VH(itemView: View) : ViewHolder(itemView)
}