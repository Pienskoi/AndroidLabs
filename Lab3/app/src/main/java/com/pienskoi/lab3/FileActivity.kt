package com.pienskoi.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.io.FileNotFoundException

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val textView: TextView = findViewById(R.id.textView_fileContents)
        val filename = "Orders.txt"
        try {
            val contents = openFileInput(filename).bufferedReader().use { it.readText() }
            textView.text = contents
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "Дані у файловому сховищі відсутні", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
