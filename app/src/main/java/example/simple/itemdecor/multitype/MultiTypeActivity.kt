package example.simple.itemdecor.multitype

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.simple.itemdecor.R
import example.simple.itemdecor.ShaderItemDecor
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.simple.itemdecor.AbsItemDecor
import me.simple.itemdecor.LinearItemDecor
import me.simple.itemdecor.MultiTypeItemDecor
import me.simple.itemdecor.multiType

class MultiTypeActivity : AppCompatActivity() {

    private val mItems = Items()
    private val mAdapter = MultiTypeAdapter(mItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multitype)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        mAdapter.register(MultiTypeHeaderBean::class.java, MultiTypeHeaderItemBinder())
        mAdapter.register(MultiTypeItemBean::class.java, MultiTypeItemBinder())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter

        val decoration1 = LinearItemDecor()
        decoration1.size = 20
        decoration1.color = Color.LTGRAY

        val decoration2 = LinearItemDecor()
        decoration2.color = Color.BLACK

        val decoration3 = LinearItemDecor()
        decoration3.color = Color.BLUE
        decoration3.size = 20

        val decoration4 = LinearItemDecor()
        decoration4.color = Color.GREEN
        decoration4.size = 3
        decoration4.margin = 115f

        val decoration5: AbsItemDecor = ShaderItemDecor()

        recyclerView.multiType { position ->
            when (position) {
                0 -> decoration3
                1 -> decoration2
                2 -> decoration4
                3 -> decoration5
                else -> decoration1
            }
        }
//        val multiTypeItemDecor = MultiTypeItemDecor { position ->
//            when (position) {
//                0 -> decoration3
//                1 -> decoration2
//                2 -> decoration4
//                3 -> decoration5
//                else -> decoration1
//            }
//        }
//        recyclerView.addItemDecoration(multiTypeItemDecor)

        mItems.add(MultiTypeHeaderBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mAdapter.notifyDataSetChanged()
    }
}