package org.d3if3169.doghotel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3169.doghotel.database.CatatanDao
import org.d3if3169.doghotel.ui.screen.AddNotesViewModel
import org.d3if3169.doghotel.ui.screen.NotesViewModel

class ViewModelFactory(private val dao: CatatanDao): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(AddNotesViewModel::class.java)) {
            return AddNotesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}