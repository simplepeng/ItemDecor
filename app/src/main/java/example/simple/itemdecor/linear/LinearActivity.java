package example.simple.itemdecor.linear;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import example.simple.itemdecor.R;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.simple.itemdecor.AbsItemDecor;
import me.simple.itemdecor.FilterFunc;
import me.simple.itemdecor.LinearItemDecor;

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

        AbsItemDecor itemDecor = new LinearItemDecor(LinearItemDecor.HORIZONTAL)
                .setWidth(20)
                .build();
        rv_horizontal.addItemDecoration(itemDecor);

        rv_horizontal.setAdapter(mHorizontalAdapter);

        getHorizontalData();
    }

    private void initVertical() {
        mVerticalAdapter.register(LinearItemBean.class, new LinearItemBinder());
        RecyclerView rv_vertical = findViewById(R.id.rv_vertical);
        rv_vertical.setLayoutManager(new LinearLayoutManager(this));

        AbsItemDecor itemDecor = new LinearItemDecor()
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
        rv_vertical.addItemDecoration(itemDecor);

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
