package test1a.c14220172.roomdatabasecoba

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import test1a.c14220172.roomdatabasecoba.database.daftarBelanja
import test1a.c14220172.roomdatabasecoba.database.daftarBelanjaDB
import test1a.c14220172.roomdatabasecoba.helper.DataHelper.getCurrentDate

class TambahDaftar : AppCompatActivity() {
    val btn_tambah = findViewById<Button>(R.id.btnTambah)
    val btn_update = findViewById<Button>(R.id.btnUpdate)
    val et_item = findViewById<EditText>(R.id.etItem)
    val et_jumlah = findViewById<EditText>(R.id.etItem)
    var DB = daftarBelanjaDB.getDatabase(this)
    var tanggal = getCurrentDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_tambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarBelanjaDAO().insert(
                    daftarBelanja(
                        tanggal = tanggal,
                        item = et_item.text.toString(),
                        jumlah = et_jumlah.text.toString()
                    )
                )
            }
        }
    }

}