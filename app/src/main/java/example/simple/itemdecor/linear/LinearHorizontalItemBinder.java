package example.simple.itemdecor.linear;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.drakeet.multitype.ItemViewBinder;
import me.simple.itemdecor.demo.R;

public class LinearHorizontalItemBinder extends ItemViewBinder<LinearItemBean, LinearHorizontalItemBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_linear_horizontal, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull LinearItemBean item) {

    }

    class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
