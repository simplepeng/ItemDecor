package example.simple.itemdecor.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import example.simple.itemdecor.R
import me.drakeet.multitype.ItemViewBinder

class MultiTypeHeaderItemBinder :
    ItemViewBinder<MultiTypeHeaderBean, MultiTypeHeaderItemBinder.VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return VH(inflater.inflate(R.layout.item_multity_header, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: MultiTypeHeaderBean) {}
    inner class VH(itemView: View) : ViewHolder(itemView)
}