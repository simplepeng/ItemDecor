# ItemDecor

RecyclerView.ItemDecoration的简易写法，轻松实现RecyclerView的Divider。

|                    LinearItemDecor                     |                     GridItemDecor                      |                     MultiItemDecor                     |
| :----------------------------------------------------: | :----------------------------------------------------: | :----------------------------------------------------: |
| ![](https://i.loli.net/2019/10/28/FEj64UdqHV5JcOf.png) | ![](https://i.loli.net/2019/10/28/vzIUPRloLmSM3ur.png) | ![](https://i.loli.net/2019/10/28/1L5UFP96wXkqbJM.png) |

## 导入依赖

[![](https://jitpack.io/v/simplepeng/ItemDecor.svg)](https://jitpack.io/#simplepeng/ItemDecor)

```groovy
maven { url 'https://jitpack.io' }
```

```groovy
implementation 'com.github.simplepeng:ItemDecor:v1.0.3'
```

## LinearItemDecor

```kotlin
val itemDecor = LinearItemDecor().apply {
    size = 10
    color = Color.BLACK
    retainLast = true//
    margin = 33.5f
}
//过滤点不需要显示的divider
itemDecor.filter(0, 1, 2)
//或
itemDecor.filter { position: Int ->
    position % 2 == 0
}
rvVertical.addItemDecoration(itemDecor)
  
//高级点，使用KT扩展函数
rvVertical.divider(Color.RED, 10, 20f, 100f)
```

默认最后的`ItemDecoration`是没有画出来的，可以使用`retainLast`属性保留最后一个。

`filter`方法可以用来过滤掉不需要显示的`ItemDecoration`。

## GridItemDecor

```kotlin
val spaceItemDecor = GridItemDecor().apply {
    margin = 10
}
rvGrid.addItemDecoration(spaceItemDecor)
//扩展函数
rvGrid.space(10)
```

## MultiTypeItemDecor

```kotlin
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

val multiTypeItemDecor = MultiTypeItemDecor { position ->
    when (position) {
        0 -> decoration3
        1 -> decoration2
        2 -> decoration4
        3 -> decoration5
        else -> decoration1
    }
}
recyclerView.addItemDecoration(multiTypeItemDecor)
//扩展函数
recyclerView.multiType { position ->
    when (position) {
        0 -> decoration3
        1 -> decoration2
        2 -> decoration4
        3 -> decoration5
        else -> decoration1
    }
}
```

## 自定义ItemDecor

```kotlin
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
```

## 赞助

如果您觉得`ItemDecor`帮助到了您，可选择精准扶贫，要是`10.24`作者就在这里🙇🙇🙇啦！

您的支持是作者继续努力创作的动力😁😁😁

萌戳下方链接精准扶贫⤵️⤵️⤵️

**[扶贫方式](https://simplepeng.github.io/merge_pay_code/)** ---- **[赞助列表](https://github.com/simplepeng/Sponsor/blob/master/README.md)**

## 问题反馈

尽量先提issue，实在无解再加QQ群：1078185041

## 版本迭代

* v1.0.3：优化
* v1.0.2：升级为KT，写法更轻松。
* v1.0.1：修复bug
* v1.0.0：首次上传