package com.youarelaunched.challenge.data.repository

import com.youarelaunched.challenge.data.network.api.ApiVendors
import com.youarelaunched.challenge.data.network.models.NetworkMediaAttachment
import com.youarelaunched.challenge.data.network.models.NetworkVendor
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class VendorsRepositoryImplTest {


    private val api: ApiVendors = mockk()

    private val repository: VendorsRepository = VendorsRepositoryImpl(Dispatchers.Unconfined, api)

    @Test
    fun `should return at least one items of Vendors when request is success`() {
        //given
        coEvery { api.getVendors() } returns listOf(mockedNetworkVendor)

        //when
        val result = runBlocking { repository.getVendors() }
        //then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `should return exception when request is fail`() = runTest {
        //given
        coEvery { api.getVendors() } throws RuntimeException()

        //then
        assertThrows<RuntimeException> { repository.getVendors() }
    }

    companion object {
        private val mockedNetworkVendor = NetworkVendor(
            areaServed = "areaServed",
            categories = null,
            companyName = "companyName",
            coverPhoto = NetworkMediaAttachment(0,"",""),
            profilePhoto = null,
            distance = null,
            favorite = false,
            id = 0,
            shopType = "shopType",
            tags = null,
            chatId = null,
            type = null
        )
    }
}