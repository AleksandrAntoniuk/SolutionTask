package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.RequestState
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class VendorsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedVendorItem = Vendor(
        id = 0,
        companyName = "companyName",
        coverPhoto = "coverPhoto",
        area = "area",
        favorite = true,
        categories = null,
        tags = null
    )
    private val vendors = listOf(mockedVendorItem)

    private lateinit var state: VendorsScreenUiState


    @Test
    fun should_display_content_list_when_vendorList_is_not_empty() {
        //when
        composeTestRule.setContent {
            state = VendorsScreenUiState(RequestState.Success(vendors))
            VendorsScreen(uiState = state, onValueChange = {}) {
            }
        }
        //then
        composeTestRule.onNodeWithTag(CONTENT).assertIsDisplayed()
    }

    @Test
    fun should_display_no_result_view_when_vendorList_is_empty() {
        //when
        composeTestRule.setContent {
            state = VendorsScreenUiState(RequestState.Empty)
            VendorsScreen(uiState = state, onValueChange = {}) {
            }
        }
        //then
        composeTestRule.onNodeWithTag(EMPTY).assertIsDisplayed()
    }

}