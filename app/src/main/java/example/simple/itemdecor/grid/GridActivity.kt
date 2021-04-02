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
import me.simple.itemdecor.space

class GridActivity : AppCompatActivity() {

    private val mItems = Items()
    private val mAdapter = MultiTypeAdapter(mItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        mAdapter.register(GridItemBean::class.java, GridItemBinder())

        val rvGrid = findViewById<RecyclerView>(R.id.rv_grid)
        rvGrid.layoutManager = GridLayoutManager(this, 4)
        rvGrid.adapter = mAdapter
//
//        val spaceItemDecor = GridItemDecor().apply {
//            margin = 10
//        }
//        rvGrid.addItemDecoration(spaceItemDecor)
        //扩展函数
        rvGrid.space(10)

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