package com.example.project07.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project07.models.Siswa


class SiswaViewModel : ViewModel() {
    private val _statusUI = MutableStateFlow(Siswa())
    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()

    fun setSiswa(ls: MutableList<String>) {
        _statusUI.update { statusSaatIni ->
            statusSaatIni.copy(nama = ls[0], gender = ls[1], alamat = ls[2])
        }
    }
}
