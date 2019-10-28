package example.simple.itemdecor.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.simple.itemdecor.AbsItemDecor;
import me.simple.itemdecor.FilterFunc;
import me.simple.itemdecor.GridItemDecor;
import me.simple.itemdecor.demo.R;

public class GridActivity extends AppCompatActivity {

    private Items mItems = new Items();
    private MultiTypeAdapter mAdapter = new MultiTypeAdapter(mItems);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.register(GridItemBean.class, new GridItemBinder());

        RecyclerView rv_grid = findViewById(R.id.rv_grid);
        rv_grid.setLayoutManager(new GridLayoutManager(this, 4));
        rv_grid.setAdapter(mAdapter);

        AbsItemDecor spaceItemDecor = new GridItemDecor(4)
                .setMargin(20)
                .filter(new FilterFunc() {
                    @Override
                    public boolean exclude(int position) {
                        return false;
                    }
                })
                .build();
        rv_grid.addItemDecoration(spaceItemDecor);

        for (int i=0;i<5;i++) {
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
            mItems.add(new GridItemBean());
        }

        mAdapter.notifyDataSetChanged();
    }
}
