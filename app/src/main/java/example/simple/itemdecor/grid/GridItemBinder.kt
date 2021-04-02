package example.simple.itemdecor.grid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import example.simple.itemdecor.R;
import me.drakeet.multitype.ItemViewBinder;

public class GridItemBinder extends ItemViewBinder<GridItemBean, GridItemBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_grid, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final VH holder, @NonNull GridItemBean item) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getAdapter().getItems().remove(holder.getAdapterPosition());
                getAdapter().notifyItemRemoved(holder.getAdapterPosition());
                return true;
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
