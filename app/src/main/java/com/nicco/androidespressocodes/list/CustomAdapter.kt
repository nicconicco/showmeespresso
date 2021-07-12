package com.nicco.androidespressocodes.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicco.androidespressocodes.R

/**
 * You can find the content here:
 * https://testautomationu.applitools.com/espresso-mobile-testing-tutorial/chapter4.1.html
 * this is a free course.
 */
class CustomAdapter(dataSet: MutableList<String>, applicationContext: Context) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var mDataSet: List<String>? = dataSet
    private var mContext: Context? = applicationContext

    /**
     * Provide a reference to the type of views that you are using
     * (custom [RecyclerView.ViewHolder]).
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.textView)

        // We'll use this field to showcase matching the holder from the test.
        var isInTheMiddle = false
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    fun CustomAdapter(dataSet: List<String>?, context: Context?) {
        mDataSet = dataSet
        mContext = context
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position == mDataSet!!.size / 2 /* calculate middle element position */) {
            viewHolder.isInTheMiddle = true
            viewHolder.textView.text = mContext?.resources?.getString(R.string.middle)
        } else {
            viewHolder.isInTheMiddle = false
            viewHolder.textView.text = mDataSet!![position]
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataSet!!.size
    }
}