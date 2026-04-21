package com.example.ejercicio2_estudio_ia_dex.presentation

import androidx.lifecycle.ViewModel
import com.example.ejercicio2_estudio_ia_dex.data.FormularioRequest
import com.example.ejercicio2_estudio_ia_dex.domain.FormUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormViewModel(

) : ViewModel() {

    private val formUseCase: FormUseCase = FormUseCase()

    private val _uiState = MutableStateFlow(
        FormUiState(
            title = "",
            description = "",
            priority = "1",
            email = "",
            buttonEnabled = false,
            loading = false,
            errors = listOf(false, false, false, false),
            categories = listOf("Trabajo", "Estudios", "Personal"),
            selectedCategory = "Trabajo"
        )
    )

    val uiState = _uiState.asStateFlow()
    val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"

    fun onButtonClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            formUseCase.uploadForm(
                FormularioRequest(
                    uiState.value.title,
                    uiState.value.description,
                    uiState.value.selectedCategory,
                    uiState.value.priority.toInt(),
                    uiState.value.email
                )
            )
        }
    }

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(
                title = title
            )
        }
        checkInputs()
    }

    fun onDescriptionChange(description: String) {
        _uiState.update {
            it.copy(
                description = description
            )
        }
        checkInputs()
    }

    fun onCategoryChange(category: String) {
        _uiState.update {
            it.copy(
                selectedCategory = category
            )
        }
    }

    fun onPriorityChange(priority: String) {
        _uiState.update {
            it.copy(
                priority = priority
            )
        }
        checkInputs()
    }

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
        checkInputs()
    }

    private fun checkInputs() {
        _uiState.update { it ->
            it.copy(
                errors = listOf(
                    uiState.value.title.length !in 5..60,
                    uiState.value.description.length !in 20..500,
                    !isValidPriority(uiState.value.priority),
                    !isValidEmail(uiState.value.email),
                ),
                buttonEnabled = uiState.value.errors.any { it }
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = EMAIL_REGEX.toRegex()
        return regex.matches(email)
    }

    private fun isValidPriority(priority: String): Boolean {
        return  "^[1-5]$".toRegex().matches(priority)
    }
}