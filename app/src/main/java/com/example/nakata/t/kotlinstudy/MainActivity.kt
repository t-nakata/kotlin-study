package com.example.nakata.t.kotlinstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val logs = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log.apply {
            movementMethod = ScrollingMovementMethod.getInstance()
        }

        run_button.setOnClickListener{
            when (spinner.selectedItem) {
                "０から１００までの数字を出力" -> logToZeroToHundred()
                "FizzBuzz" -> logToFizzBuzz()
                "リセット" -> resetLog()
            }
        }

        spinner.apply {
            adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, getMethodList())
        }
    }

    private fun logToZeroToHundred() {
        // 0 から １００ までの数字を出力

        refreshLog()
    }

    private fun logToFizzBuzz() {
        // 3の倍数なら｢Fizz｣ 5の倍数なら｢Buzz｣ 両方の倍数なら｢FizzBuzz｣

        refreshLog()
    }


    private fun getMethodList(): List<String> {
        return listOf(
            "０から１００までの数字を出力",
            "FizzBuzz",
            "リセット"
        )
    }

    private fun addLog(log: String) {
        logs.add(log)
        refreshLog()
    }

    private fun resetLog() {
        logs.clear()
        refreshLog()
    }

    private fun refreshLog() {
        var logText = ""
        for ((index, text) in logs.withIndex()) {
            logText += String.format("%05d: %s\n", index, text)
        }

        log.apply {
            scrollY = log.height
            text = logText

            val scrollAmount = layout.getLineTop(lineCount) - height

            if (scrollAmount > 0) {
                scrollTo(0, scrollAmount)
            } else {
                scrollTo(0,0)
            }
        }
    }

}
