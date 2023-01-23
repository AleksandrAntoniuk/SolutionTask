package com.youarelaunched.challenge.utils

import com.youarelaunched.challenge.data.network.models.NetworkMediaAttachment
import com.youarelaunched.challenge.data.network.models.NetworkVendor
import com.youarelaunched.challenge.data.repository.model.Vendor

object MockedObjectProvider {

     val mockedVendorItem = Vendor(
        id = 0,
        companyName = "companyName",
        coverPhoto = "coverPhoto",
        area = "area",
        favorite = true,
        categories = null,
        tags = null
    )

    val mockedNetworkVendor = NetworkVendor(
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