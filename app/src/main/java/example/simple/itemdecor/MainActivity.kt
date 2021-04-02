package example.simple.itemdecor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import example.simple.itemdecor.grid.GridActivity
import example.simple.itemdecor.linear.LinearActivity
import example.simple.itemdecor.multitype.MultiTypeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.item_linear).setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    LinearActivity::class.java
                )
            )
        }
        findViewById<View>(R.id.item_grid).setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GridActivity::class.java
                )
            )
        }
        findViewById<View>(R.id.item_multiType).setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MultiTypeActivity::class.java
                )
            )
        }
    }
}