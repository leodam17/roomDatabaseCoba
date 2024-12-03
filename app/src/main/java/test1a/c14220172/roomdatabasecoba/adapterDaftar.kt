package test1a.c14220172.roomdatabasecoba

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import test1a.c14220172.roomdatabasecoba.database.daftarBelanja

class adapterDaftar(private val daftarBelanja : MutableList<daftarBelanja>):

        RecyclerView.Adapter<adapterDaftar.ListViewHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): adapterDaftar.ListViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler, parent,
                false)
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
            var daftar = daftarBelanja[position]

            holder._tvTanggal.setText(daftar.tanggal)
            holder._tvItemBarang.setText(daftar.item)
            holder._tvJumlahBarang.setText(daftar.jumlah)

            holder._btnEdit.setOnClickListener{
                val intent = Intent(it.context, TambahDaftar::class.java)
                intent.putExtra("id", daftar.id)
                intent.putExtra("addEdit",1)
                it.context.startActivity(intent)
            }

            holder._btnDelete.setOnClickListener{
                onItemClickCallback.delData(daftar)
            }
        }

        override fun getItemCount(): Int {
            return daftarBelanja.size
        }

        interface OnItemClickCallback {
            fun delData(dtBelanja: daftarBelanja)
        }

        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
            this.onItemClickCallback = onItemClickCallback
        }

        fun isiData(daftar: List<daftarBelanja>){
            daftarBelanja.clear()
            daftarBelanja.addAll(daftar)
            notifyDataSetChanged()
        }

        class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var _tvItemBarang = itemView.findViewById<TextView>(R.id.tv_itemBarang)
            var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.tv_jumlahBarang)
            var _tvTanggal = itemView.findViewById<TextView>(R.id.tv_tanggal)

            var _btnEdit = itemView.findViewById<ImageView>(R.id.btn_edit)
            var _btnDelete = itemView.findViewById<ImageView>(R.id.btn_delete)

        }

    private lateinit var onItemClickCallback: OnItemClickCallback

}