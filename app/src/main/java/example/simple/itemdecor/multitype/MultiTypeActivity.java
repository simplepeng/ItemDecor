package example.simple.itemdecor.multitype;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import example.simple.itemdecor.R;
import example.simple.itemdecor.ShaderItemDecor;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.simple.itemdecor.AbsItemDecor;
import me.simple.itemdecor.LinearItemDecor;
import me.simple.itemdecor.Linker;
import me.simple.itemdecor.MultiTypeItemDecor;

public class MultiTypeActivity extends AppCompatActivity {

    private Items mItems = new Items();
    private MultiTypeAdapter mAdapter = new MultiTypeAdapter(mItems);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multitype);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.register(MultiTypeHeaderBean.class, new MultiTypeHeaderItemBinder());
        mAdapter.register(MultiTypeItemBean.class, new MultiTypeItemBinder());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        final AbsItemDecor decoration1 = new LinearItemDecor()
                .setHeight(20)
                .setColor(Color.LTGRAY)
                .build();
        final AbsItemDecor decoration2 = new LinearItemDecor()
                .setColor(Color.BLACK)
                .build();
        final AbsItemDecor decoration3 = new LinearItemDecor()
                .setColor(Color.RED)
                .setMarginLeft(45)
                .build();
        final AbsItemDecor decoration4 = new LinearItemDecor()
                .setColor(Color.GREEN)
                .setHeight(3)
                .setMarginHorizontal(115)
                .build();
        final AbsItemDecor decoration5 = new ShaderItemDecor();

        RecyclerView.ItemDecoration decoration = new MultiTypeItemDecor()
//                .register(decoration1)
//                .register(decoration2)
//                .register(decoration3)
//                .register(decoration4)
//                .register(decoration5)
                .withLinker(new Linker() {
                    @Override
                    public AbsItemDecor bind(int position) {
                        switch (position) {
                            case 0:
                                return decoration3;
                            case 1:
                                return decoration2;
                            case 2:
                                return decoration4;
                            case 3:
                                return decoration5;
                        }
                        return decoration1;
                    }
                })
                .build();
        recyclerView.addItemDecoration(decoration);

        mItems.add(new MultiTypeHeaderBean());
        mItems.add(new MultiTypeItemBean());
        mItems.add(new MultiTypeItemBean());
        mItems.add(new MultiTypeItemBean());
        mItems.add(new MultiTypeItemBean());
        mItems.add(new MultiTypeItemBean());
        mAdapter.notifyDataSetChanged();
    }

}
