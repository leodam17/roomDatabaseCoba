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
import kotlinx.coroutines.launch
import test1a.c14220172.roomdatabasecoba.database.daftarBelanja
import test1a.c14220172.roomdatabasecoba.database.daftarBelanjaDB
import test1a.c14220172.roomdatabasecoba.helper.DataHelper.getCurrentDate

class TambahDaftar : AppCompatActivity() {

    private lateinit var btnTambah: Button
    private lateinit var btnUpdate: Button
    private lateinit var etItem: EditText
    private lateinit var etJumlah: EditText
    private lateinit var DB: daftarBelanjaDB
    private var tanggal: String = getCurrentDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)

        // Initialize views after setContentView
        btnTambah = findViewById(R.id.btnTambah)
        btnUpdate = findViewById(R.id.btnUpdate)
        etItem = findViewById(R.id.etItem)
        etJumlah = findViewById(R.id.etJumlah)

        // Initialize the database
        DB = daftarBelanjaDB.getDatabase(applicationContext)

        // Apply window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listener
        btnTambah.setOnClickListener {
            addItemToDatabase()
        }
    }

    private fun addItemToDatabase() {
        val itemText = etItem.text.toString()
        val jumlahText = etJumlah.text.toString()

        // Perform database operations in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            DB.fundaftarBelanjaDAO().insert(
                daftarBelanja(
                    tanggal = tanggal,
                    item = itemText,
                    jumlah = jumlahText
                )
            )
        }
    }
}
