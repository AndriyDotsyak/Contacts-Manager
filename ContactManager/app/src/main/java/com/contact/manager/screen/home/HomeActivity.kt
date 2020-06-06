package com.contact.manager.screen.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.contact.manager.R
import com.contact.manager.data.model.Contact
import com.contact.manager.screen.base.BaseActivity
import com.contact.manager.screen.base.adapter.OnItemClickListener
import com.contact.manager.screen.detailedContact.DetailedContactActivity
import com.contact.manager.screen.home.adapter.ContactsRecyclerAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    companion object {
        const val REQUEST_CODE_DETAILED_CONTACT = 0
    }

    @Inject lateinit var homeViewModel: HomeViewModel

    lateinit var realm: Realm
    lateinit var contactsAdapter: ContactsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        realm = Realm.getDefaultInstance()

        setupRecyclerView()
        addItemsRecyclerView()
        swipeRefresh()
    }

    private fun setupRecyclerView() {
        recycler_AH.layoutManager = LinearLayoutManager(this.applicationContext)
        contactsAdapter = ContactsRecyclerAdapter(onItemClickListener, onItemClickRemove)
        recycler_AH.adapter = contactsAdapter

        val decoration = DividerItemDecoration(recycler_AH.context, LinearLayoutManager.VERTICAL)
        recycler_AH.addItemDecoration(decoration)
    }

    private fun addItemsRecyclerView() {
        if (homeViewModel.isEmptyDB())
            homeViewModel.addItemsDB(Contact().getContacts())

        contactsAdapter.addItems(homeViewModel.getItemsDB())
    }

    private fun swipeRefresh() {
        swipe_refresh_AH.setOnRefreshListener {
            homeViewModel.removeAllItemDB()
            homeViewModel.addItemsDB(Contact().getContacts())
            contactsAdapter.refreshContacts(homeViewModel.getItemsDB())
            swipe_refresh_AH.isRefreshing = false
        }
    }

    private val onItemClickListener = object : OnItemClickListener<Contact> {
        override fun onItemClick(item: Contact) {
            startActivityForResult(DetailedContactActivity.getIntent(this@HomeActivity, item), REQUEST_CODE_DETAILED_CONTACT)
        }
    }

    private val onItemClickRemove = object : ContactsRecyclerAdapter.OnItemClickRemove {
        override fun onItemClick(item: Contact, position: Int) {
            homeViewModel.removeItemDB(item)
            contactsAdapter.removeContact(item, position)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_DETAILED_CONTACT -> {
                    val contact = data.getParcelableExtra<Contact>(DetailedContactActivity.CONTACT_KEY)

                    contact?.let {
                        contactsAdapter.editContact(it)
                        homeViewModel.editItemDB(it)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }
}