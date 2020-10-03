package com.brkr.linagora.presentation.ui.searching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brkr.linagora.databinding.ItemPurchaseBinding

typealias IntegerCallback = (Int) -> Unit

class PurchaseAdapter(
    private val purchaseItems: List<PurchaseItem>,
    private val handleOnClick: IntegerCallback
) : RecyclerView.Adapter<PurchaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.purchaseItem = purchaseItems[position]
        holder.view.executePendingBindings()
        holder.setListener(position, handleOnClick)
    }

    override fun getItemCount(): Int = purchaseItems.size

    open class ViewHolder(val view: ItemPurchaseBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun setListener(position: Int, callback: IntegerCallback) {
            view.root.setOnClickListener {
                callback(position)
            }
        }
    }
}
