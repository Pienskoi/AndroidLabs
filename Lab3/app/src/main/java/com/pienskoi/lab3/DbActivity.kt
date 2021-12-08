package com.pienskoi.lab3

import android.os.Bundle
import android.provider.BaseColumns
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DbActivity : AppCompatActivity() {
    private val dbHelper = DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            DbContract.OrderEntry.COLUMN_NAME,
            DbContract.OrderEntry.COLUMN_COLOR,
            DbContract.OrderEntry.COLUMN_PRICE
        )
        val cursor = db.query(
            DbContract.OrderEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            BaseColumns._ID
        )

        val orders = mutableMapOf<Long, OrderModel>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(DbContract.OrderEntry.COLUMN_NAME))
                val color = getString(getColumnIndexOrThrow(DbContract.OrderEntry.COLUMN_COLOR))
                val price = getString(getColumnIndexOrThrow(DbContract.OrderEntry.COLUMN_PRICE))
                val order = OrderModel(name, color, price)
                orders[id] = order
            }
        }
        cursor.close()

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)

        if (orders.isEmpty()) {
            Toast.makeText(this, "Дані у базі даних відсутні", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            for ((id, order) in orders) {
                val tableRow = TableRow(this)
                for (value in arrayOf(id.toString(), order.name, order.color, order.price)) {
                    val textView = TextView(this)
                    textView.textSize = 20f
                    textView.setPadding(3, 3, 3, 3)
                    textView.text = value
                    tableRow.addView(textView)
                }
                tableLayout.addView(tableRow)
            }
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
