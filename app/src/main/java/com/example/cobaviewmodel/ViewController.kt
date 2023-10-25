package com.example.cobaviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cobaviewmodel.dataFolder.DataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewController : ViewModel() {
    var namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenisKlmn : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataClass())
    val uiState : StateFlow<DataClass> = _uiState.asStateFlow()

    fun insertData (nm: String, tlp: String, jk: String){
        namaUsr = nm;
        noTlp = tlp;
        jenisKlmn = jk;
    }

    fun setJenisKelamin(pilihJK : String) {
        _uiState.update { currentState -> currentState.copy(jk = pilihJK) }
    }
}