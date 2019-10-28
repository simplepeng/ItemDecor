# ItemDecor

RecyclerView.ItemDecoration简易写法

|                      LinearDecor                       |                     GridItemDecor                      |                   MultiTypeDecorator                   |
| :----------------------------------------------------: | :----------------------------------------------------: | :----------------------------------------------------: |
| ![](https://i.loli.net/2019/10/28/QCzFwHBYVc1ZOKn.png) | ![](https://i.loli.net/2019/10/28/rYUnTsjWE7t5PXd.png) | ![](https://i.loli.net/2019/10/28/t3mfny6MTACuQjO.png) |

## 导入依赖



## LinearDecor

```java
AbsItemDecor itemDecor = new LinearItemDecor()
                .setHeight(10)
                .setColor(Color.BLACK)
                .filter(2,5)
                .retainLast()//保留最后一个ItemDecoration，默认不保留
                .setMarginHorizontal(33.5f)
                .build();
```

默认最后的`ItemDecoration`是没有画出来的，可以使用`retainLast()`方法保留最后一个ItemDecoration

## GridItemDecor

```java
AbsItemDecor spaceItemDecor = new GridItemDecor()
                .setMargin(20)
                .filter(new FilterFun() {
                    @Override
                    public boolean exclude(int position) {
                       return position == 1;
                    }
                })
                .build();
rv_grid.addItemDecoration(spaceItemDecor);
```

## MultiTypeDecorator

```java
final AbsItemDecor decoration1 = new LinearItemDecor()
                .setHeight(20)
                .setColor(Color.LTGRAY)
                .build();
final AbsItemDecor decoration2 = new LinearItemDecor()
                .setColor(Color.BLACK)
                .build();
final AbsItemDecor decoration3 = new LinearItemDecor()
                .setColor(Color.RED)
                .setMarginLeft(45)
                .build();
final AbsItemDecor decoration4 = new LinearItemDecor()
                .setColor(Color.GREEN)
                .setHeight(3)
                .setMarginHorizontal(115)
                .build();
final AbsItemDecor decoration5 = new ShaderItemDecor();

RecyclerView.ItemDecoration decoration = new MultiItemDecor()
                .register(decoration1)
                .register(decoration2)
                .register(decoration3)
                .register(decoration4)
                .register(decoration5)
                .withLinker(new Linker() {
                    @Override
                    public AbsItemDecor bind(int position) {
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
```

### 高级操作

可以使用`filter`方法排除掉某些position的`ItemDecoration`，只能使用以下方法中的其中一个，不可同时使用。

```java
.filter(new FilterFun() {
    @Override
    public boolean exclude(int position) {
        return position == 2 || position == 5;
    }
})
	//或者
.filter(2,5)
```