package com.willkernel.app.kotlindemo.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.willkernel.app.kotlindemo.NullTest
import com.willkernel.app.kotlindemo.R
import com.willkernel.app.kotlindemo.Test
import com.willkernel.app.kotlindemo.data.Artist
import com.willkernel.app.kotlindemo.data.Forecast1
import com.willkernel.app.kotlindemo.data.Person
import com.willkernel.app.kotlindemo.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.util.*

//import kotlinx.android.extensions.LayoutContainer
class MainActivity : AppCompatActivity() {
    private val tag: String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setText()

        nullSafety()

//        toastF()

        setRecyclerView()

        baseType()

        variable()

        openWeatherMap()

        copyClass()

        collectionsOperation()

        nullable()

        flowControl()

        innerClass()

        typeClass()
    }

    private fun typeClass() {
        val t1 = TypedClass(24)
        val t2 = TypedClass<String>("Hello Kotlin")
        val test: Test = Test()
        test.test()
    }

    class TypedClass<T>(parameter: T) {
        val value: T = parameter
    }

    private fun innerClass() {
        //        class Outer1{
//            private val bar:Int=1
//            class Nested{
//                fun foo()=2
//            }
//        }
//        val d1=Outer1.Nested().foo()
        class Outer2 {
            private val bar: Int = 1

            inner class Nested {
                fun foo() = bar
            }
        }

        val d2 = Outer2().Nested().foo()
    }

    sealed class Option<out T> {
        class Some<out T> : Option<T>()
        object None : Option<Nothing>()
    }

    private fun flowControl() {
        val v1 = 6
        val z1 = if (v1 in 4..6) v1 else 0
        Log.e(tag, "if z1 $z1")


    }

    private fun whenFun(x: Int, view: View) {
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> {
                print("I'm a block")
                print("x is neither 1 nor 2")
            }
        }
        val result = when (x) {
            0, 1 -> "binary"
            else -> "error"
        }
        when (view) {
            is TextView -> view.setText("I'm a TextView")
            is EditText -> toast("EditText value: ${view.getText()}")
            is ViewGroup -> toast("Number of children: ${view.childCount} ")
            else -> textView.visibility = View.GONE
        }
    }

    private fun ranges(i: Int) {
        if (i >= 0 && i <= 10)
            println(i)

        if (i in 0..10)
            println(i)

        for (i in 0..10)
            println(i)

//        Ranges 默认会自增长，所以如果像以下的代码
        for (i in 10..0)
            println(i)

//        可以使用 downTo 函数
        for (i in 10 downTo 0)
            println(i)

//        在 range 中使用 step 来定义一个从1到一个值的不同的空隙
        for (i in 1..4 step 2) println(i)
        for (i in 4 downTo 1 step 2) println(i)

//        创建一个open range（不包含最后一项，译者注：类似数学中的开区间），你可以使用 until 函数
//        使用 (i in 0 until list.size) 比 (i in 0..list.size - 1) 更加容易理解
        for (i in 0 until 4) println(i)

//        val views = (0..viewGroup.childCount - 1).map { viewGroup.getChildAt(it) }
    }

    private fun nullable() {
        val a: Int? = null
        Log.e(tag, "a!! " + (a?.toString() ?: ""))
        //"a!!.toString() throw KotlinNullPointerException

        val test = NullTest()
        val myObject: Any? = test.`object`
        Log.e(tag, "$myObject ")
    }

    private fun collectionsOperation() {
        val list = listOf(1, 1, 3, 4, 5, 6)
        Log.e(tag, "any1 " + list.any { it % 2 == 0 })
        Log.e(tag, "any2 " + list.any { it > 10 })
        Log.e(tag, "all1 " + list.all { it < 10 })
        Log.e(tag, "all2 " + list.all { it % 2 == 0 })
        Log.e(tag, "count " + list.count { it % 2 == 0 })
        Log.e(tag, "fold " + list.fold(4) { total, next -> total + next })
        Log.e(tag, "foldRight " + list.foldRight(4) { total, next -> total + next })
        list.forEach { println(it) }
        list.forEachIndexed { index, i -> println("position $index value $i") }
        Log.e(tag, "max " + list.max())
        Log.e(tag, "maxBy " + list.maxBy { -it })
        Log.e(tag, "min " + list.min())
        Log.e(tag, "minBy " + list.minBy { -it })
        Log.e(tag, "none " + list.none { it % 7 == 0 })
        Log.e(tag, "reduce " + list.reduce { total, next -> total + next })
        Log.e(tag, "reduce right " + list.reduceRight { total, next -> total + next })
        Log.e(tag, "sumBy " + list.sumBy { it % 2 })
        Log.e(tag, "drop " + list.drop(4))
        Log.e(tag, "dropWhile " + list.dropWhile { it > 2 })
        Log.e(tag, "dropLastWhile " + list.dropLastWhile { it > 2 })
        Log.e(tag, "filter " + list.filter { it > 2 })
        Log.e(tag, "filterNot " + list.filterNot { it > 2 })
        val listWithNull = listOf(1, 2, null)
        Log.e(tag, "filterNotNull " + listWithNull.filterNotNull())
        Log.e(tag, "slice " + list.slice(listOf(1, 2, 4)))
        Log.e(tag, "flatMap " + list.flatMap { listOf(it, it + 1) })
        Log.e(tag, "groupBy " + list.groupBy { if (it % 2 == 0) "even" else "odd" })
        Log.e(tag, "map " + list.map { it * 2 })
        Log.e(tag, "partition " + list.partition { it % 2 == 0 })
        Log.e(tag, "plus " + list + listOf(7, 8))
        Log.e(tag, "zip " + list.zip(listOf(7, 4, 9, 8)))
        Log.e(tag, "unzip " + listOf(Pair(5, 7), Pair(6, 8)).unzip())

    }

    private fun copyClass() {
        val f1 = Forecast1(Date(), 23f, "Sunny")
        Log.e(tag, f1.toString())
        val f2 = f1.copy(temperature = 30f)
        Log.e(tag, f2.toString())

        //映射对象到变量中,多声明
        val (date, temp, details) = f1
        Log.e(tag, "date " + date.toString())
        Log.e(tag, "temp " + temp.toString())
        Log.e(tag, "details $details")

//        上面的多声明会编译成下面的代码
//        val date = f1.component1()
//        val temperature = f1.component2()
//        val details = f1.component3()

        val map = mutableListOf(f1, f2)
        for ((key, value) in map) {
            Log.e("map", "key:$key, value:$value")
        }
    }


    private fun openWeatherMap() {
        doAsync {
            val result = RequestForecastCommand(94043).execute()
//            val result = ForecastRequest("94043").execute()
            Log.e(tag, "result " + result.toString())
            //UIThread 是可以依赖于调用者如果它是被一个Activity 调用的，
            //那么如果 activity.isFinishing() 返回true，则 uiThread 不会执行，在Activity销毁的时候避免程序崩溃
            uiThread {
                //                recyclerView.adapter = ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener {
//                    override fun invoke(forecast: Forecast) {
//                        toast(forecast.date)
//                    }
//                })
                recyclerView.adapter = ForecastListAdapter(result) { forecast -> toast(forecast.date.toString()) }
            }
        }
    }

    var TextView.text: CharSequence
        get() = getText()
        set(v) = setText(v)

    private fun variable() {
        val actionBar = supportActionBar
        Log.e(tag, actionBar.toString())
        val a: Any = 23
        Log.e(tag, a.toString())

        val c: Context = this
        Log.e(tag, c.toString())

        val p = Person("Tim", "star")
        Log.e(tag, p.toString())
    }

    private fun baseType() {
        val i1 = 7
        val d: Double = i1.toDouble()
        Log.e(tag, d.toString())

        val c = 'c'
        val i2: Int = c.toInt()
        Log.e(tag, i2.toString())

        val FLAG1 = 8L
        val FLAG2 = 16L
        //按位运算，Int Long
        val bitwiseOr = FLAG1 or FLAG2
        val bitwiseAnd = FLAG1 and FLAG2
        Log.e(tag, bitwiseOr.toString())
        Log.e(tag, bitwiseAnd.toString())

        //string 可以像数组一样访问
        val s = "Example"
        val c1 = s[2]
        Log.e(tag, c1.toString())
        for (c in s) {
            Log.e(tag, "item =$c")
        }
    }

    private fun setRecyclerView() {
//        val forecastList = findViewById<RecyclerView>(R.id.recyclerView)
//        val forecastList: RecyclerView = find(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        val items = listOf("Mon 6/23 - Sunny - 31/17",
//                "Tue 6/24 - Foggy - 21/8",
//                "Wed 6/25 - Cloudy - 22/17",
//                "Thurs 6/26 - Rainy - 18/11",
//                "Fri 6/27 - Foggy - 21/10",
//                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
//                "Sun 6/29 - Sunny - 20/7")
//        recyclerView.adapter = ForecastListAdapter(items)
    }

    private fun toastF() {
        //anko 自带扩展函数
        toast(R.string.app_name)
        longToast("longToast")
        toast1("Hello")
        toast1("Hello", "MyTag")
        toast1("Hello", "MyTag", Toast.LENGTH_SHORT)
        toast2("Hello", Toast.LENGTH_LONG)
    }

    private fun setText() {
        textView.setText(R.string.app_name)
        textView.text = getString(R.string.app_name)
        textView.setOnClickListener { toast("Kotlin") }
    }

    /**设置返回值，分号不是必须的*/
    private fun toast1(message: CharSequence, tag: String = "MainActivity", duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", duration).show()
    }

    private fun Context.toast2(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "$message", duration).show()
    }

    private fun nullSafety() {
        //        编译不通过，非空类不能为null
        //        var artist:Artist=null

        //        安全调用操作符? 明确地指定一个对象是否能为空
        var artist: Artist? = null

        //         无法编译, artist可能是null，需要进行处理
        //        artist.hashCode()

        //        在artist!=null时调用
        artist?.hashCode()

        //        判空
        if (artist != null) {
            artist.hashCode()
        }

        //        给定在null时的替代者
        val name = artist?.name ?: "empty"
        Log.e(tag, name)
//        确保artist不是null的情况下调用，否在抛异常KotlinNullPointerException
//        artist!!.hashCode()
    }

//    class ViewHolder(override val containerView: View) : ViewHolder(containerView), LayoutContainer {
//        fun setup(title: String) {
//            itemTitle.text = "Hello World!"
//        }
//    }
}

