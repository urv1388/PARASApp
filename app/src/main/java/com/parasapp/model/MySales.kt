package com.parasapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MySales(
    val id: String? = null,
    val gross: Double? = null,
    val nett: Double? = null,
    val credit_card_payment: Double? = null,
    val lotto_lottery: Double? = null,
    val instant_lottery: Double? = null,
    val deposit_into_bank: Double? = null,
    val cash: Double? = null,
    val tax_1: Double? = null,
    val tax_2: Double? = null,
    val adjustment: Double? = null,
    val timestamp: String? = null
) : Parcelable
