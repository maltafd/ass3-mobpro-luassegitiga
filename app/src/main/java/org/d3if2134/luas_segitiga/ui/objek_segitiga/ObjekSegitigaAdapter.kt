package org.d3if2134.luas_segitiga.ui.objek_segitiga

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2134.luas_segitiga.R
import org.d3if2134.luas_segitiga.databinding.ItemObjekSegitigaBinding
import org.d3if2134.luas_segitiga.model.ObjekSegitiga
import org.d3if2134.luas_segitiga.network.ObjekSegitigaApi

class ObjekSegitigaAdapter : RecyclerView.Adapter<ObjekSegitigaAdapter.ViewHolder>() {
    private val data = mutableListOf<ObjekSegitiga>()
    fun updateData(newData: List<ObjekSegitiga>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemObjekSegitigaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(objekSegitiga: ObjekSegitiga) = with(binding) {
            nama.text = objekSegitiga.nama
            keterangan.text = objekSegitiga.keterangan
            Glide.with(imageView.context)
                .load(ObjekSegitigaApi.getObjekSegitigaUrl(objekSegitiga.gambar))
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemObjekSegitigaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}