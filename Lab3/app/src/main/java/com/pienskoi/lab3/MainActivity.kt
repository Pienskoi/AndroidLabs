package com.pienskoi.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity(), FormFragment.OnFormSubmitListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFormSubmitListener(orderInfo: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val orderFragment = OrderFragment()
        val bundle = bundleOf("order_info" to orderInfo)
        orderFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container_view, orderFragment).commit()
    }
}
