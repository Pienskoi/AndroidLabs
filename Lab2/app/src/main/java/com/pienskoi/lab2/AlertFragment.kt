package com.pienskoi.lab2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Будь-ласка заповніть текстове поле та оберіть всі наявні опції!")
            .setPositiveButton(getString(R.string.button_ok)) { dialog, _ -> dialog.cancel() }
            .create()

    companion object {
        const val TAG = "AlertDialog"
    }
}