package com.pienskoi.lab3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Lab3.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DbContract.OrderEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DbContract.OrderEntry.COLUMN_NAME} TEXT," +
                    "${DbContract.OrderEntry.COLUMN_COLOR} TEXT," +
                    "${DbContract.OrderEntry.COLUMN_PRICE} TEXT)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${DbContract.OrderEntry.TABLE_NAME}"
    }
}
