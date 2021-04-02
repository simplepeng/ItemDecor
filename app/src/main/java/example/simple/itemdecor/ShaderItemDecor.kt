package example.simple.itemdecor

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.simple.itemdecor.AbsItemDecor

class ShaderItemDecor : AbsItemDecor() {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mHeight = 50
    override fun onDraw(
        canvas: Canvas, position: Int,
        bounds: Rect, itemView: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        mPaint.shader = LinearGradient(
            0f,
            0f,
            parent.width.toFloat(),
            50f,
            Color.YELLOW,
            Color.GREEN,
            Shader.TileMode.CLAMP
        )
        val bottom = bounds.bottom + Math.round(itemView.translationY)
        val top = bottom - mHeight
        canvas.drawRect(Rect(0, top, parent.width, bottom), mPaint)
    }

    override fun onDrawOver(
        canvas: Canvas, position: Int,
        bounds: Rect, itemView: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
    }

    override fun setOutRect(
        outRect: Rect, position: Int,
        itemView: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect[0, 0, 0] = mHeight
    }
}