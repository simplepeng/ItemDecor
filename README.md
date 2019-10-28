# ItemDecorator

## LinearDecorator

```java
AbsItemDecorator itemdecor = new LinearDecorator()
                .setHeight(10)
                .setColor(Color.BLACK)
                .retainLast()//保留最后一个ItemDecoration
                .setMarginHorizontal(33.5f)
                .build();
rv_vertical.addItemDecoration(itemdecor);
```

默认最后的`ItemDecoration`是没有画出来的，可以使用`retainLast()`方法保留最后一个ItemDecoration

### 高级操作

可以使用`filter`方法排除掉某些position的`ItemDecoration`

```java
.filter(new FilterFunc() {
              @Override
              public boolean exclude(int position) {
                 return position == 2 || position == 5;
            }
})
```

## SpaceItemDecorator

```java
AbsItemDecorator spaceItemDecor = new SpaceItemDecorator(4)
                .setMargin(20)
                .filter(new FilterFunc() {
                    @Override
                    public boolean exclude(int position) {
                        return false;
                    }
                })
                .build();
rv_grid.addItemDecoration(spaceItemDecor);
```

## MultiTypeDecorator

```java
final AbsItemDecorator decoration1 = new LinearDecorator()
                .setHeight(20)
                .setColor(Color.LTGRAY)
                .build();
final AbsItemDecorator decoration2 = new LinearDecorator()
                .setColor(Color.BLACK)
                .build();
final AbsItemDecorator decoration3 = new LinearDecorator()
                .setColor(Color.RED)
                .setMarginLeft(45)
                .build();
final AbsItemDecorator decoration4 = new LinearDecorator()
                .setColor(Color.GREEN)
                .setHeight(3)
                .setMarginHorizontal(115)
                .build();
final AbsItemDecorator decoration5 = new ShaderItemDecorator();

RecyclerView.ItemDecoration decoration = new MultiTypeDecorator()
                .register(decoration1)
                .register(decoration2)
                .register(decoration3)
                .register(decoration4)
                .register(decoration5)
                .withLinker(new Linker() {
                    @Override
                    public AbsItemDecorator bind(int position) {
                        switch (position) {
                            case 0:
                                return decoration3;
                            case 1:
                                return decoration2;
                            case 2:
                                return decoration4;
                            case 3:
                                return decoration5;
                        }
                        return decoration1;
                    }
                })
                .build();
recyclerView.addItemDecoration(decoration);
```

## 自定义

```java
public class ShaderItemDecorator extends AbsItemDecorator {

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
```