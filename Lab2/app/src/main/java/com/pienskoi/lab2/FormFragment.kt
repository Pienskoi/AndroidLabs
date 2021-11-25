package com.pienskoi.lab2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

class FormFragment : Fragment() {
    private lateinit var mListener: OnFormSubmitListener

    interface OnFormSubmitListener {
        fun onFormSubmitListener(orderInfo: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_fragment, container, false)
        val button: Button = view.findViewById(R.id.button_ok)
        button.setOnClickListener { showOrderInfo(view) }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as OnFormSubmitListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnFormSubmitListener")
        }
    }

    private fun showOrderInfo(view: View) {
        val editText: EditText = view.findViewById(R.id.editText)
        val input = editText.text.toString()
        val colorRadioGroup: RadioGroup = view.findViewById(R.id.radioGroup_color)
        val checkedColorId = colorRadioGroup.checkedRadioButtonId
        val priceRadioGroup: RadioGroup = view.findViewById(R.id.radioGroup_price)
        val checkedPriceId = priceRadioGroup.checkedRadioButtonId

        if (input.isEmpty() || checkedColorId == -1 || checkedPriceId == -1) {
            AlertFragment().show(childFragmentManager, AlertFragment.TAG)
        } else {
            var orderInfo = "Інформація щодо замовлення:\n$input"
            val checkedColorButton: RadioButton = colorRadioGroup.findViewById(checkedColorId)
            orderInfo += "\nКолір: ${checkedColorButton.text}"
            val checkedPriceButton: RadioButton = priceRadioGroup.findViewById(checkedPriceId)
            orderInfo += "\nДіапазон цін: ${checkedPriceButton.text}"
            mListener.onFormSubmitListener(orderInfo)
        }
    }
}