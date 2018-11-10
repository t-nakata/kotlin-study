package com.example.nakata.t.kotlinstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_log.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val logs = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log_recycler.apply {
            adapter = LogAdapter()
        }

        run_button.setOnClickListener{
            when (spinner.selectedItem) {
                "０から１００までの数字を出力" -> logToZeroToHundred()
                "FizzBuzz" -> logToFizzBuzz()
                "1から10のランダムな値を出力" -> logToRandom()
                "リセット" -> resetLog()
            }
        }

        spinner.apply {
            adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, getMethodList())
        }
    }

    private fun logToZeroToHundred() {
        // 0 から １００ までの数字を出力


        for (i in 0..100) {
            addLog(i.toString())
        }


        refreshLog()
    }

    private fun logToFizzBuzz() {
        // 3の倍数なら｢Fizz｣ 5の倍数なら｢Buzz｣ 両方の倍数なら｢FizzBuzz｣


            for(i in 1..100){
                if(i % 15 == 0){
                    addLog("FizzBuzz")
                }else if(i % 3 == 0){
                    addLog("Fizz")
                }else if(i % 5 == 0){
                    addLog("Buzz")
                }else{
                    addLog(i.toString())
                }
        }

        refreshLog()
    }

    private fun getRandom(): Int{
        return Random().nextInt(10) + 1
    }

    private fun logToRandom() {
        // 1から10のランダムな値を出力
        addLog (getRandom().toString())

        refreshLog()
    }


    private fun getMethodList(): List<String> {
        return listOf(
            "０から１００までの数字を出力",
            "FizzBuzz",
            "1から10のランダムな値を出力",
            "リセット"
        )
    }

    private fun addLog(log: String) {
        logs.add(String.format("%05d: %s", logs.size, log))
        refreshLog()
    }

    private fun resetLog() {
        logs.clear()
        refreshLog()
    }

    private fun refreshLog() {
        log_recycler.scrollToPosition(logs.size - 1)
        log_recycler.adapter?.notifyDataSetChanged()
    }

    inner class LogAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            return ViewHolder(LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.item_log, parent, false))
        }

        override fun getItemCount(): Int = logs.size

        override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
            if (vh is ViewHolder) {
                val item = logs[position]
                item.apply {
                    vh.log.text = item
                }
            }
        }

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)  {
            val log: TextView = view.text
        }

    }

}
