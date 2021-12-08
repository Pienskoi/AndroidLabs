package com.pienskoi.lab3

import android.provider.BaseColumns

object DbContract {
    object OrderEntry : BaseColumns {
        const val TABLE_NAME = "orders"
        const val COLUMN_NAME = "name"
        const val COLUMN_COLOR = "color"
        const val COLUMN_PRICE = "price"
    }
}
