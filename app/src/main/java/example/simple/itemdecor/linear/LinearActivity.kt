package example.simple.itemdecor.linear

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.simple.itemdecor.R
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.simple.itemdecor.LinearItemDecor
import me.simple.itemdecor.divider

class LinearActivity : AppCompatActivity() {

    private val mVerticalItems = Items()
    private val mVerticalAdapter = MultiTypeAdapter(mVerticalItems)
    private val mHorizontalItems = Items()
    private val mHorizontalAdapter = MultiTypeAdapter(mHorizontalItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_linear)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        initHorizontal()
        initVertical()
    }

    private fun initHorizontal() {
        mHorizontalAdapter.register(LinearItemBean::class.java, LinearHorizontalItemBinder())
        val rvHorizontal = findViewById<RecyclerView>(R.id.rv_horizontal)
        rvHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val itemDecor = LinearItemDecor()
        itemDecor.orientation = LinearItemDecor.HORIZONTAL
        itemDecor.size = 20
        rvHorizontal.addItemDecoration(itemDecor)

        rvHorizontal.adapter = mHorizontalAdapter

        addHorizontalData()
    }

    private fun initVertical() {
        mVerticalAdapter.register(LinearItemBean::class.java, LinearItemBinder())
        val rvVertical = findViewById<RecyclerView>(R.id.rv_vertical)
        rvVertical.layoutManager = LinearLayoutManager(this)

        rvVertical.divider(Color.RED, 10, 20f, 100f)
//        val itemDecor = LinearItemDecor()
//        itemDecor.size = 10
//        itemDecor.color = Color.BLACK
//        itemDecor.filter { integer: Int ->
//            Log.d("LinearActivity", "position == $integer")
//            integer % 2 == 0
//        }
//        itemDecor.retainLast = true
//        itemDecor.margin = 33.5f
//        rvVertical.addItemDecoration(itemDecor)

        rvVertical.adapter = mVerticalAdapter
        addVerticalData()
    }

    private fun addHorizontalData() {
        mHorizontalItems.add(LinearItemBean())
        mHorizontalItems.add(LinearItemBean())
        mHorizontalItems.add(LinearItemBean())
        mHorizontalItems.add(LinearItemBean())
        mHorizontalItems.add(LinearItemBean())
        mHorizontalItems.add(LinearItemBean())
        mHorizontalAdapter.notifyItemRangeInserted(mVerticalItems.size - 6, 6)
    }

    private fun addVerticalData() {
        mVerticalItems.add(LinearItemBean())
        mVerticalItems.add(LinearItemBean())
        mVerticalItems.add(LinearItemBean())
        mVerticalItems.add(LinearItemBean())
        mVerticalItems.add(LinearItemBean())
        mVerticalItems.add(LinearItemBean())
        mVerticalAdapter.notifyItemRangeInserted(mVerticalItems.size - 6, 6)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> {
                addHorizontalData()
                addVerticalData()
            }
        }
        return true
    }
}