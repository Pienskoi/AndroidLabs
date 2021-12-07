package com.pienskoi.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OrderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.order_fragment, container, false)
        val textView: TextView = view.findViewById(R.id.textView_orderInfo)
        textView.text = arguments?.getString("order_info")
        return view
    }
}
