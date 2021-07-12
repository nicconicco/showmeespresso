package com.nicco.androidespressocodes.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicco.androidespressocodes.R


class ListActivity : AppCompatActivity() {
    private val DATASET_COUNT = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Create a RecyclerView, a LayoutManager, a data Adapter and wire everything up.
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager

        val dataSet: MutableList<String> = ArrayList(DATASET_COUNT)
        for (i in 0 until DATASET_COUNT) {
            dataSet.add(getString(R.string.item_element_text) + i)
        }
        val adapter = CustomAdapter(dataSet, applicationContext)
        recyclerView.adapter = adapter
    }
}