package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.RequestState
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VendorsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private var autoSearchJob: Job? = null

    init {
        getAllVendors()
    }

    private fun getAllVendors() {
        _uiState.update { it.copy(requestState = RequestState.Loading) }
        viewModelScope.launch {
            updateList(repository.getVendors())
        }
    }

    private fun searchVendors(query: String) {
        searchJob?.cancel()
        _uiState.update { it.copy(requestState = RequestState.Loading) }
        searchJob = viewModelScope.launch {
            updateList(repository.searchVendors(query))
        }
    }

    private fun updateList(vendor: List<Vendor>) {
        _uiState.update {
            it.copy(
                requestState =
                if (vendor.isNotEmpty()) RequestState.Success(vendor)
                else RequestState.Empty
            )
        }
    }

    fun onInputTextChanged(inputText: String) {
        _uiState.update { it.copy(searchText = inputText) }
        if (inputText.isNotBlank() and (inputText.length >= AUTO_SEARCH_MIN_CHAR)) {
            autoSearchJob?.cancel()
            autoSearchJob = viewModelScope.launch {
                delay(AUTO_SEARCH_DEBOUNCE)
                searchVendors(inputText)
            }
        }
    }

    fun onSearchIconClick() = searchVendors(uiState.value.searchText)

    companion object {
        const val AUTO_SEARCH_MIN_CHAR = 3
        const val AUTO_SEARCH_DEBOUNCE = 500L
    }

}