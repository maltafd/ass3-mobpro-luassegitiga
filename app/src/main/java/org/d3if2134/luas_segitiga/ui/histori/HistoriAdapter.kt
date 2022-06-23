package org.d3if2134.luas_segitiga.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2134.luas_segitiga.R
import org.d3if2134.luas_segitiga.databinding.ItemHistoriBinding
import org.d3if2134.luas_segitiga.db.SegitigaEntity
import org.d3if2134.luas_segitiga.model.hitungSegitiga
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<SegitigaEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<SegitigaEntity>() {
                override fun areItemsTheSame(
                    oldData: SegitigaEntity, newData: SegitigaEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: SegitigaEntity, newData: SegitigaEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: SegitigaEntity) = with(binding) {
            val hasilSegitiga = item.hitungSegitiga()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            segitigaTextView.text = root.context.getString(R.string.value_x, hasilSegitiga.luasSegitiga)
        }
    }
}

