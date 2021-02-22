package com.parasapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.parasapp.R
import com.parasapp.model.MySales
import com.parasapp.utils.Constants

class MySalesRecyclerViewAdapter(
    options: FirestoreRecyclerOptions<MySales>
) : FirestoreRecyclerAdapter<MySales, MySalesRecyclerViewAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sales, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val grossTextView: TextView = view.findViewById(R.id.grossTextView)
        val nettTextView: TextView = view.findViewById(R.id.nettTextView)
        val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        val textViewTotalLottery: TextView = view.findViewById(R.id.textViewTotalLottery)
        val textViewLottoRemit: TextView = view.findViewById(R.id.textViewLottoRemit)
        val totalTaxTextView: TextView = view.findViewById(R.id.totalTaxTextView)
        override fun toString(): String {
            return super.toString() + " '" + grossTextView.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MySales) {

        val textGross: String = holder.grossTextView.context.getString(
            R.string.string_gross_amount,
            model.gross.toString()
        )
        holder.grossTextView.text = HtmlCompat.fromHtml(textGross, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val textNett: String = holder.nettTextView.context.getString(
            R.string.string_nett_amount,
            model.nett.toString()
        )
        holder.nettTextView.text = HtmlCompat.fromHtml(textNett, HtmlCompat.FROM_HTML_MODE_LEGACY)


        val textDate: String = holder.textViewDate.context.getString(
            R.string.string_date, model.id?.let { Constants.getFormattedDateFromString(it) }
        )
        holder.textViewDate.text =
            HtmlCompat.fromHtml(textDate, HtmlCompat.FROM_HTML_MODE_LEGACY)


        val totalLottery = model.instant_lottery?.let { model.lotto_lottery?.plus(it) }
        val textLottery: String = holder.textViewTotalLottery.context.getString(
            R.string.string_total_lottery_amount,
            totalLottery.toString()
        )
        holder.textViewTotalLottery.text =
            HtmlCompat.fromHtml(textLottery, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val textLotteryPaidout: String = holder.textViewLottoRemit.context.getString(
            R.string.string_lottery_paidout_amount,
            model.lottery_remit.toString()
        )
        holder.textViewLottoRemit.text =
            HtmlCompat.fromHtml(textLotteryPaidout, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val totalTax = model.tax_2?.let { model.tax_1?.plus(it) }
        val textTotalTax: String = holder.totalTaxTextView.context.getString(
            R.string.string_total_tax,
            totalTax.toString()
        )
        holder.totalTaxTextView.text =
            HtmlCompat.fromHtml(textTotalTax, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}