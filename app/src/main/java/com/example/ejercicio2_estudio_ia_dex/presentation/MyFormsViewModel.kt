package com.example.ejercicio2_estudio_ia_dex.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio2_estudio_ia_dex.domain.FormUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyFormsViewModel : ViewModel() {

    private val formUseCase: FormUseCase = FormUseCase()

    private val _uiState = MutableStateFlow(
        MyFormsUiState(
            myForms = listOf(),
            loading = false,
            loadingError = false
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            try {
                val myForms = formUseCase.getMyReports()
                _uiState.update {
                    it.copy(
                        myForms = myForms
                    )
                }
                _uiState.update {
                    it.copy(
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        loadingError = true
                    )
                }
            }
        }
    }

    fun getCategoryStyle(category: String): Long {
        return when (category) {
            "Trabajo" -> {
                CategoryType.TRABAJO.color
            }
            "Estudios" -> {
                CategoryType.ESTUDIOS.color
            }
            else -> {
                CategoryType.PERSONAL.color
            }
        }
    }

    fun onCloseErrorModal() {
        _uiState.update {
            it.copy(
                loadingError = false
            )
        }
    }

    fun onRetryLoadingData() {
        _uiState.update {
            it.copy(
                loading = true,
                loadingError = false
            )
        }

        viewModelScope.launch {
            try {
                val myForms = formUseCase.getMyReports()
                _uiState.update {
                    it.copy(
                        myForms = myForms
                    )
                }
                _uiState.update {
                    it.copy(
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        loadingError = true
                    )
                }
            }
        }
    }
}

enum class CategoryType(
    val value: String,
    val color: Long
) {
    TRABAJO("Trabajo", 0xFFEE9E24),
    ESTUDIOS("Estudios", 0xFF4F7FE0),
    PERSONAL("Personal", 0xFFD12E88)
}