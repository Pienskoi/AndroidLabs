package com.pienskoi.lab1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showOrderInfo(view: android.view.View) {
        val editText: EditText = findViewById(R.id.editText)
        val input = editText.text.toString()
        val colorRadioGroup: RadioGroup = findViewById(R.id.radioGroup_color)
        val checkedColorId = colorRadioGroup.checkedRadioButtonId
        val priceRadioGroup: RadioGroup = findViewById(R.id.radioGroup_price)
        val checkedPriceId = priceRadioGroup.checkedRadioButtonId

        if (input.isEmpty() || checkedColorId == -1 || checkedPriceId == -1) {
            val warningMsg = "Будь-ласка заповніть текстове поле та оберіть всі наявні опції!"
            Toast.makeText(this, warningMsg, Toast.LENGTH_SHORT).show()
        } else {
            var orderInfo = "Інформація щодо замовлення:\n$input"
            val checkedColorButton: RadioButton = colorRadioGroup.findViewById(checkedColorId)
            orderInfo += "\nКолір: ${checkedColorButton.text}"
            val checkedPriceButton: RadioButton = priceRadioGroup.findViewById(checkedPriceId)
            orderInfo += "\nДіапазон цін: ${checkedPriceButton.text}"
            val textView: TextView = findViewById(R.id.textView_orderInfo)
            textView.text = orderInfo
        }
    }
}
