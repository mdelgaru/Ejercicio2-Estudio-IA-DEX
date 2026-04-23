package com.example.ejercicio2_estudio_ia_dex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio2_estudio_ia_dex.data.FormularioRequest
import com.example.ejercicio2_estudio_ia_dex.domain.FormUseCase
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
            selectedCategory = "Trabajo",
            dataSent = false,
            loadError = false
        )
    )

    val uiState = _uiState.asStateFlow()
    val EMAIL_REGEX = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}"
    val PRIORITY_REGEX = "^[1-5]$"

    fun onCloseErrorModal() {
        _uiState.update {
            it.copy(
                loadError = false
            )
        }
    }

    fun onRetrySendData() {
        onCloseErrorModal()
        onButtonClicked()
    }

    fun onButtonClicked() {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            try {
                formUseCase.uploadForm(
                    FormularioRequest(
                        uiState.value.title,
                        uiState.value.description,
                        uiState.value.selectedCategory,
                        uiState.value.priority.toInt(),
                        uiState.value.email
                    )
                )
                reset()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loadError = true,
                        loading = false
                    )
                }
            }
        }
    }

    private fun reset() {
        _uiState.update {
            it.copy(
                title = "",
                description = "",
                priority = "1",
                email = "",
                buttonEnabled = false,
                errors = listOf(false, false, false, false),
                selectedCategory = "Trabajo",
                dataSent = true,
                loading = false
            )
        }
    }

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(
                title = title
            )
        }
        val isTitleWrong = uiState.value.title.length !in 5..60
        val errors = uiState.value.errors.toMutableList()
        errors[0] = isTitleWrong
        updateErrors(errors)
    }

    fun onDescriptionChange(description: String) {
        _uiState.update {
            it.copy(
                description = description
            )
        }

        val isDescriptionWrong = uiState.value.description.length !in 20..500
        val errors = uiState.value.errors.toMutableList()
        errors[1] = isDescriptionWrong
        updateErrors(errors)
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

        val isPriorityWrong = !isValidPriority(priority)
        val errors = uiState.value.errors.toMutableList()
        errors[2] = isPriorityWrong
        updateErrors(errors)
    }

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }

        val isDescriptionWrong = !isValidEmail(email)
        val errors = uiState.value.errors.toMutableList()
        errors[3] = isDescriptionWrong
        updateErrors(errors)
    }

    private fun updateErrors(errors: List<Boolean>) {
        _uiState.update {
            it.copy(
                errors = errors
            )
        }
        enableButton()
    }

    private fun enableButton() {
        val hasErrors = uiState.value.errors.any { error -> error }
        val passedConditions = uiState.value.title.length in 5..60 &&
                uiState.value.description.length in 20..500 &&
                isValidPriority(uiState.value.priority) &&
                isValidEmail(uiState.value.email)

        _uiState.update {
            it.copy(
                buttonEnabled = !hasErrors && passedConditions
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    private fun isValidPriority(priority: String): Boolean {
        return  PRIORITY_REGEX.toRegex().matches(priority)
    }
}