package example.simple.itemdecor.grid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.simple.itemdecor.R
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.simple.itemdecor.GridItemDecor

class GridActivity : AppCompatActivity() {
    private val mItems = Items()
    private val mAdapter = MultiTypeAdapter(mItems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        mAdapter.register(GridItemBean::class.java, GridItemBinder())
        val rv_grid = findViewById<RecyclerView>(R.id.rv_grid)
        rv_grid.layoutManager = GridLayoutManager(this, 4)
        rv_grid.adapter = mAdapter
        val spaceItemDecor = GridItemDecor()
            .setMargin(20) //                .filter(new FilterFun() {
            //                    @Override
            //                    public boolean exclude(int position) {
            //                        return position == 1;
            //                    }
            //                })
            .build()
        rv_grid.addItemDecoration(spaceItemDecor)
        for (i in 0..4) {
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
            mItems.add(GridItemBean())
        }
        mAdapter.notifyDataSetChanged()
    }
}