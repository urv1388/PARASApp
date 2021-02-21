package com.parasapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaleDetail(
    val id: String,
    val gross: Double?,
    val nett: Double?,
    val credit_card_payment: Double?,
    val lotto_lottery: Double?,
    val instant_lottery: Double?,
    val deposit_into_bank: Double?,
    val cash: Double?,
    val tax_1: Double?,
    val tax_2: Double?,
    val adjustment: Double?,
    val timestamp: String
) : Parcelable
