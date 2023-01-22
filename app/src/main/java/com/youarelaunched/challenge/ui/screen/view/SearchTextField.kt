package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme


@Composable
fun SearchTextField(
    hint: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = VendorAppTheme.typography.subtitle2,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchIconClick()
                focusManager.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            CustomDecorationBox(
                textValue = textValue,
                hint = hint,
                innerTextField = innerTextField,
                onSearchIconClick = onSearchIconClick,
                focusManager = focusManager
            )
        }
    )
}

@Composable
private fun CustomDecorationBox(
    textValue: String?,
    hint: String,
    innerTextField: @Composable () -> Unit,
    onSearchIconClick: () -> Unit,
    focusManager: FocusManager
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp), // inner padding
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box {
                if (textValue.isNullOrBlank())
                    Text(text = hint, color = VendorAppTheme.colors.text)
                innerTextField()
            }
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = {
                    onSearchIconClick()
                    focusManager.clearFocus()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = VendorAppTheme.colors.text
                )
            }
        }
    }
}