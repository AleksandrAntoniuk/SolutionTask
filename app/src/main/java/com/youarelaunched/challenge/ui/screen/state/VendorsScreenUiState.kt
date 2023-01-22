package com.youarelaunched.challenge.ui.screen.state

import com.youarelaunched.challenge.data.repository.model.Vendor

data class VendorsScreenUiState(
    val requestState: RequestState = RequestState.Loading,
    val searchText: String = ""
)

sealed class RequestState {
    object Loading: RequestState()
    object Empty: RequestState()
    class Success(val vendors: List<Vendor>?): RequestState()
}