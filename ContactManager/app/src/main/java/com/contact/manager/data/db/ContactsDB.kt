package com.contact.manager.data.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ContactsDB(
    @PrimaryKey
    var id: String = "",
    var photo: Int = 0,
    var name: String = "",
    var surname: String = "",
    var email: String = ""
) : RealmObject()