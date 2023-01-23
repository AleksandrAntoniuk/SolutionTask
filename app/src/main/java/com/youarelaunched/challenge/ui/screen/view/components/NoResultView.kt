package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.view.EMPTY
import com.youarelaunched.challenge.ui.theme.VendorAppTheme


@Composable
fun NoResultView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(EMPTY),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.search_no_result_title),
            style = MaterialTheme.typography.h2,
            color = VendorAppTheme.colors.textDarkGreen
        )
        Text(
            text = stringResource(id = R.string.search_no_result_subtitle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = VendorAppTheme.colors.textDark
        )
    }
}