package com.parasapp.utils

import java.text.SimpleDateFormat
import java.util.*

class Constants {

    companion object {
        private const val DATE_FORMAT = "ddMMyyyy"
        const val COLLECTION_STORES = "stores"
        const val COLLECTION_SALES_YEAR = "sales_year"
        var COLLECTION_ON_STORE = "my_store_freeburg"
        var COLLECTION_ON_YEAR = "year_2021"


        fun getDateOfCollection(date: Date): String {
            val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT);
            return dateFormat.format(date);
        }

    }
}