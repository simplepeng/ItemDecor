package example.simple.itemdecor.multitype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import example.simple.itemdecor.R;
import me.drakeet.multitype.ItemViewBinder;

public class MultiTypeHeaderItemBinder extends ItemViewBinder<MultiTypeHeaderBean, MultiTypeHeaderItemBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_multity_header,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull MultiTypeHeaderBean item) {

    }

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
