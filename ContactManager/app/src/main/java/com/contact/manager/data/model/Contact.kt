package com.contact.manager.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.contact.manager.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var id: String = "",
    @DrawableRes var photo: Int = 0,
    var name: String = "",
    var surname: String = "",
    var email: String = ""
) : Parcelable {

    fun getContacts(): List<Contact> = listOf(
        Contact(photo = R.drawable.ic_contact, name = "Name0", surname = "Surname0", email = "email0@gmail.com"),
        Contact(photo = R.drawable.ic_contact, name = "Name1", surname = "Surname1", email = "email1@gmail.com"),
        Contact(photo = R.drawable.ic_contact, name = "Name2", surname = "Surname2", email = "email2@gmail.com"),
        Contact(photo = R.drawable.ic_contact, name = "Name3", surname = "Surname3", email = "email3@gmail.com"),
        Contact(photo = R.drawable.ic_contact, name = "Name4", surname = "Surname4", email = "email4@gmail.com"),
        Contact(photo = R.drawable.ic_contact, name = "Name5", surname = "Surname5", email = "email5@gmail.com")
    )
}