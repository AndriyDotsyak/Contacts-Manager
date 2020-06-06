package com.contact.manager.screen.home

import com.contact.manager.data.db.ContactsDB
import com.contact.manager.data.model.Contact
import com.contact.manager.screen.base.BaseViewModel
import io.realm.Realm
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun addItemDB(contact: Contact) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                val contactDB = transaction.createObject(ContactsDB::class.java, UUID.randomUUID().toString())
                contactDB.photo = contact.photo
                contactDB.name = contact.name
                contactDB.surname = contact.surname
                contactDB.email = contact.email
            }
        }
    }

    fun addItemsDB(contacts: List<Contact>) {
        contacts.forEach { addItemDB(it) }
    }

    fun removeItemDB(contact: Contact) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                transaction.where(ContactsDB::class.java)
                    .equalTo("id", contact.id)
                    .findFirst()
                    ?.deleteFromRealm()
            }
        }
    }

    fun removeAllItemDB() {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                transaction.where(ContactsDB::class.java)
                    .findAll().deleteAllFromRealm()
            }
        }
    }

    fun editItemDB(contact: Contact) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                val contactDB = transaction.where(ContactsDB::class.java)
                    .equalTo("id", contact.id).findFirst()

                contactDB?.name = contact.name
                contactDB?.surname = contact.surname
                contactDB?.email = contact.email
            }
        }
    }

    fun getItemsDB() : List<Contact> {
        val storeCopy: MutableList<Contact> = mutableListOf()

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                val store = transaction.where(ContactsDB::class.java).findAll()

                store.forEach {
                    storeCopy.add(Contact(it.id, it.photo, it.name, it.surname, it.email))
                }
            }
        }

        return storeCopy
    }

    fun isEmptyDB(): Boolean {
        var isEmpty = true

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { transaction ->
                isEmpty = transaction.where(ContactsDB::class.java).findAll().isEmpty()
            }
        }

        return isEmpty
    }
}