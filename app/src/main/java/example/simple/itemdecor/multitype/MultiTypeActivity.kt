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
import me.simple.itemdecor.Linker
import me.simple.itemdecor.MultiTypeItemDecor

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
        decoration3.color = Color.RED
        decoration3.marginStart = 45f

        val decoration4 = LinearItemDecor()
        decoration4.color = Color.GREEN
        decoration4.size = 3
        decoration4.margin = 115f

        val decoration5: AbsItemDecor = ShaderItemDecor()
        val decoration = MultiTypeItemDecor()
            .withLinker(object : Linker {
                override fun bind(position: Int): AbsItemDecor? {
                    when (position) {
                        0 -> return decoration3
                        1 -> return decoration2
                        2 -> return decoration4
                        3 -> return decoration5
                    }
                    return decoration1
                }
            })

//        recyclerView.addItemDecoration(decoration)

        mItems.add(MultiTypeHeaderBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mItems.add(MultiTypeItemBean())
        mAdapter.notifyDataSetChanged()
    }
}