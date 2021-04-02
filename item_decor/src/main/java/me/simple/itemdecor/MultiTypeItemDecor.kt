package me.simple.itemdecor

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class MultiTypeItemDecor  {

    private var mLinker: Linker? = null
    private var mFilterFun: FilterFun? = null

    fun withLinker(linker: Linker?): MultiTypeItemDecor {
        mLinker = linker
        return this
    }

//    override fun filter(func: FilterFun): MultiTypeItemDecor {
//        mFilterFun = func
//        return this
//    }
//
//    override fun filter(vararg excludes: Int): MultiTypeItemDecor {
//        return this
//    }

    fun build(): ItemDecoration {
        return object : AbsItemDecor() {
            override fun onDraw(
                canvas: Canvas, position: Int, bounds: Rect, itemView: View,
                parent: RecyclerView, state: RecyclerView.State
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) return
                val itemDecor = getItemDecoration(position) ?: return
                itemDecor.onDraw(canvas, position, bounds, itemView, parent, state)
            }

            override fun onDrawOver(
                canvas: Canvas, position: Int, bounds: Rect, itemView: View,
                parent: RecyclerView, state: RecyclerView.State
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) return
                val itemDecor = getItemDecoration(position) ?: return
                itemDecor.onDrawOver(canvas, position, bounds, itemView, parent, state)
            }

            override fun setOutRect(
                outRect: Rect, position: Int, itemView: View,
                parent: RecyclerView, state: RecyclerView.State
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) {
                    outRect[0, 0, 0] = 0
                    return
                }
                val itemDecor = getItemDecoration(position) ?: return
                itemDecor.setOutRect(outRect, position, itemView, parent, state)
            }
        }
    }

    private fun getItemDecoration(position: Int): AbsItemDecor? {

        val itemDecor: AbsItemDecor?

        if (mLinker == null) {
            throw NullPointerException("Do You Call withLinker Method ?")
        }
        itemDecor = mLinker!!.bind(position)

//        if (itemDecor == null) {
//            throw new NullPointerException("Do You Call register or withLinker Method ?");
//        }
        return itemDecor
    }
}