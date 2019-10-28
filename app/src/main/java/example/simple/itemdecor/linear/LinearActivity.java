package example.simple.itemdecor.linear;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.simple.itemdecor.AbsItemDecor;
import me.simple.itemdecor.FilterFunc;
import me.simple.itemdecor.LinearItemDecor;
import me.simple.itemdecor.demo.R;

public class LinearActivity extends AppCompatActivity {

    private Items mVerticalItems = new Items();
    private MultiTypeAdapter mVerticalAdapter = new MultiTypeAdapter(mVerticalItems);

    private Items mHorizontalItems = new Items();
    private MultiTypeAdapter mHorizontalAdapter = new MultiTypeAdapter(mHorizontalItems);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linear);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initHorizontal();
        initVertical();
    }

    private void initHorizontal() {
        mHorizontalAdapter.register(LinearItemBean.class, new LinearHorizontalItemBinder());
        RecyclerView rv_horizontal = findViewById(R.id.rv_horizontal);
        rv_horizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        AbsItemDecor itemdecor = new LinearItemDecor(LinearItemDecor.HORIZONTAL)
                .setWidth(20)
                .build();
        rv_horizontal.addItemDecoration(itemdecor);

        rv_horizontal.setAdapter(mHorizontalAdapter);

        getHorizontalData();
    }

    private void initVertical() {
        mVerticalAdapter.register(LinearItemBean.class, new LinearItemBinder());
        RecyclerView rv_vertical = findViewById(R.id.rv_vertical);
        rv_vertical.setLayoutManager(new LinearLayoutManager(this));

        AbsItemDecor itemdecor = new LinearItemDecor()
                .setHeight(10)
                .setColor(Color.BLACK)
                .filter(new FilterFunc() {
                    @Override
                    public boolean exclude(int position) {
                        return position == 2 || position == 5;
                    }
                })
                .retainLast()
                .setMarginHorizontal(33.5f)
                .build();
        rv_vertical.addItemDecoration(itemdecor);

        rv_vertical.setAdapter(mVerticalAdapter);

        getVerticalData();
    }

    private void getHorizontalData() {
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalItems.add(new LinearItemBean());
        mHorizontalAdapter.notifyItemRangeInserted(mVerticalItems.size() - 6, 6);
    }

    private void getVerticalData() {
        mVerticalItems.add(new LinearItemBean());
        mVerticalItems.add(new LinearItemBean());
        mVerticalItems.add(new LinearItemBean());
        mVerticalItems.add(new LinearItemBean());
        mVerticalItems.add(new LinearItemBean());
        mVerticalItems.add(new LinearItemBean());
        mVerticalAdapter.notifyItemRangeInserted(mVerticalItems.size() - 6, 6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                getHorizontalData();
                getVerticalData();
                break;
        }
        return true;
    }
}
