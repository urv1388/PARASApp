package com.parasapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Constants {

    companion object {
        private const val DATE_FORMAT = "yyyyMMdd"
        const val COLLECTION_STORES = "stores"
        const val COLLECTION_SALES_YEAR = "sales_year"
        var COLLECTION_ON_STORE = "my_store_freeburg"
        var COLLECTION_ON_YEAR = "year_2021"


        fun getDateOfCollection(date: Date): String {
            val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT);
            return dateFormat.format(date);
        }


        fun getFormattedDateFromString(date: String): String? {
            try {
                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT);
                val dateFormatNew = SimpleDateFormat("dd MMM yyyy", Locale.CANADA);
                return dateFormatNew.format(dateFormat.parse(date) ?: "");
            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }
        }

    }
}