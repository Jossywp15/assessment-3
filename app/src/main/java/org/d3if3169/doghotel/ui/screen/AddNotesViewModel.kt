package org.d3if3169.doghotel.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3169.doghotel.database.CatatanDao
import org.d3if3169.doghotel.model.Catatan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotesViewModel(private val dao: CatatanDao): ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, isi: String, lamaMenginap: String) {
        val catatan = Catatan(
            tanggal = formatter.format(Date()),
            nama = nama,
            catatan = isi,
            lamaMenginap = lamaMenginap
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(catatan)
        }
    }

    suspend fun getCatatan(id: Int): Catatan? {
        return dao.getCatatanById(id)
    }

    fun update(id: Int, nama: String, isi: String, lamaMenginap: String) {
        val catatan = Catatan(
            id = id,
            tanggal = formatter.format(Date()),
            nama = nama,
            catatan = isi,
            lamaMenginap = lamaMenginap
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(catatan)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }

}