package me.simple.itemdecor

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class GridItemDecor : IFilter<GridItemDecor> {
    /**
     *
     */
    private var mMarginLeft = 0
    private var mMarginTop = 0
    private var mMarginRight = 0
    private var mMarginBottom = 0

    /**
     *
     */
    private var mFilterFun: FilterFun? = null
    private var mExcludes: HashSet<Int>? = null
    fun setMargin(margin: Int): GridItemDecor {
        mMarginLeft = margin
        mMarginTop = margin
        mMarginRight = margin
        mMarginBottom = margin
        return this
    }

    fun setMarginLeft(margin: Int): GridItemDecor {
        mMarginLeft = margin
        return this
    }

    fun setMarginTop(margin: Int): GridItemDecor {
        mMarginTop = margin
        return this
    }

    fun setMarginRight(margin: Int): GridItemDecor {
        mMarginRight = margin
        return this
    }

    fun setMarginBottom(margin: Int): GridItemDecor {
        mMarginBottom = margin
        return this
    }

    override fun filter(func: FilterFun?): GridItemDecor {
        mFilterFun = func
        return this
    }

    override fun filter(vararg excludes: Int): GridItemDecor {
        mExcludes = HashSet()
        for (exclude in excludes) {
            mExcludes!!.add(exclude)
        }
        return this
    }

    fun build(): AbsItemDecor {
        Utils.checkFilter(mFilterFun, mExcludes)
        return object : AbsItemDecor() {
            override fun onDraw(
                canvas: Canvas, position: Int, bounds: Rect, itemView: View,
                parent: RecyclerView, state: RecyclerView.State?
            ) {
            }

            override fun onDrawOver(
                canvas: Canvas?, position: Int, bounds: Rect?, itemView: View?,
                parent: RecyclerView?, state: RecyclerView.State?
            ) {
            }

            override fun setOutRect(
                outRect: Rect, position: Int, itemView: View?,
                parent: RecyclerView, state: RecyclerView.State?
            ) {
                if (mFilterFun != null && mFilterFun!!.exclude(position)) {
                    outRect[0, 0, 0] = 0
                    return
                }
                if (mExcludes != null && mExcludes!!.contains(position)) {
                    outRect[0, 0, 0] = 0
                    return
                }
                outRect[mMarginLeft, mMarginTop, mMarginRight] = mMarginBottom
            }
        }
    }
}