package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.RequestState
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.LoadingView
import com.youarelaunched.challenge.ui.screen.view.components.NoResultView
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import com.youarelaunched.challenge.utils.isScrollingUp


//testing tags
internal const val CONTENT = "content"
internal const val EMPTY = "no_content"

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(
        uiState = uiState,
        onValueChange = viewModel::onInputTextChanged,
        onSearchIconClick = viewModel::onSearchIconClick
    )
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState,
    onValueChange: (String) -> Unit,
    onSearchIconClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->

        when (uiState.requestState) {
            is RequestState.Loading -> LoadingView()
            is RequestState.Empty -> NoResultView()
            is RequestState.Success -> ContentList(
                state = lazyListState,
                paddingValues = paddings,
                vendors = uiState.requestState.vendors ?: emptyList()
            )
        }

        SearchTextField(
            textValue = uiState.searchText,
            onValueChange = onValueChange,
            onSearchIconClick = onSearchIconClick,
            focusManager = focusManager,
            hint = stringResource(R.string.search_hint),
            isCollapsed = lazyListState.isScrollingUp(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        )
    }
}

@Composable
fun ContentList(paddingValues: PaddingValues, vendors: List<Vendor>, state: LazyListState) {
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .testTag(CONTENT),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            vertical = 88.dp,
            horizontal = 16.dp
        )
    ) {
        items(vendors) { vendor ->
            VendorItem(vendor = vendor)
        }
    }
}

