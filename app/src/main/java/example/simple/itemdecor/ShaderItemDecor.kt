package example.simple.itemdecor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import me.simple.itemdecor.AbsItemDecor;

public class ShaderItemDecor extends AbsItemDecor {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mHeight = 50;

    @Override
    public void onDraw(Canvas canvas, int position,
                       Rect bounds, View itemView,
                       RecyclerView parent, RecyclerView.State state) {

        mPaint.setShader(new LinearGradient(0, 0, parent.getWidth(), 50, Color.YELLOW, Color.GREEN, Shader.TileMode.CLAMP));
        int bottom = bounds.bottom + Math.round(itemView.getTranslationY());
        int top = bottom - mHeight;
        canvas.drawRect(new Rect(0, top, parent.getWidth(), bottom), mPaint);

    }

    @Override
    public void onDrawOver(Canvas canvas, int position,
                           Rect bounds, View itemView,
                           RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void setOutRect(Rect outRect, int position,
                           View itemView,
                           RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mHeight);
    }
}
