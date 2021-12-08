package com.pienskoi.lab3

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity(), FormFragment.OnFormSubmitListener {
    private val filename = "Orders.txt"
    private val dbHelper = DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileButton: Button = findViewById(R.id.button_openFile)
        fileButton.setOnClickListener { startFileActivity() }
        val dbButton: Button = findViewById(R.id.button_openDb)
        dbButton.setOnClickListener { startDbActivity() }
    }

    private fun startFileActivity() {
        val intent = Intent(this, FileActivity::class.java)
        startActivity(intent)
    }

    private fun startDbActivity() {
        val intent = Intent(this, DbActivity::class.java)
        startActivity(intent)
    }

    override fun onFormSubmitListener(order: OrderModel) {
        replaceOrderFragment(order.toString())
        val storeRadioGroup: RadioGroup = findViewById(R.id.radioGroup_store)
        when (storeRadioGroup.checkedRadioButtonId) {
            R.id.radioButton_file -> saveOrderToFile(order.toString())
            R.id.radioButton_db -> saveOrderToDatabase(order)
        }
    }

    private fun replaceOrderFragment(orderInfo: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val orderFragment = OrderFragment()
        val bundle = bundleOf("order_info" to orderInfo)
        orderFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container_view, orderFragment).commit()
    }

    private fun saveOrderToFile(orderInfo: String) {
        openFileOutput(filename, Context.MODE_APPEND).use {
            it.write(orderInfo.toByteArray())
            it.write("\n".toByteArray())
        }
    }

    private fun saveOrderToDatabase(order: OrderModel) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DbContract.OrderEntry.COLUMN_NAME, order.name)
            put(DbContract.OrderEntry.COLUMN_COLOR, order.color)
            put(DbContract.OrderEntry.COLUMN_PRICE, order.price)
        }

        db?.insert(DbContract.OrderEntry.TABLE_NAME, null, values)
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
