package example.simple.itemdecor.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import example.simple.itemdecor.R
import me.drakeet.multitype.ItemViewBinder

class MultiTypeItemBinder : ItemViewBinder<MultiTypeItemBean, MultiTypeItemBinder.VH>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        return VH(inflater.inflate(R.layout.item_multitype, parent, false))
    }

    override fun onBindViewHolder(holder: VH, item: MultiTypeItemBean) {}
    inner class VH(itemView: View) : ViewHolder(itemView)
}