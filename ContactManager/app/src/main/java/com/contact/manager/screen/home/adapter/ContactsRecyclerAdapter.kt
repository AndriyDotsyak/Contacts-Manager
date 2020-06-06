package com.contact.manager.screen.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.contact.manager.R
import com.contact.manager.data.model.Contact
import com.contact.manager.screen.base.adapter.BaseRecyclerAdapter
import com.contact.manager.screen.base.adapter.BaseViewHolder
import com.contact.manager.screen.base.adapter.OnItemClickListener
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsRecyclerAdapter(
    private val onItemClickListener: OnItemClickListener<Contact>,
    private val onItemClickRemove: OnItemClickRemove)
    : BaseRecyclerAdapter<Contact, CounterpartsViewHolder>() {

    interface OnItemClickRemove {
        fun onItemClick(item: Contact, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterpartsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return CounterpartsViewHolder(view, onItemClickListener, onItemClickRemove)
    }

    fun editContact(contact: Contact) {
        val position = items.indexOfFirst { it.id == contact.id }
        items[position] = contact
        notifyItemChanged(position)
    }

    fun removeContact(contact: Contact, position: Int) {
        items.remove(contact)
        notifyItemRemoved(position)
    }

    fun refreshContacts(contacts: List<Contact>) {
        items.clear()
        items.addAll(contacts)
        notifyDataSetChanged()
    }
}

class CounterpartsViewHolder(
    itemView: View,
    private val onItemClickListener: OnItemClickListener<Contact>,
    private val onItemClickRemove: ContactsRecyclerAdapter.OnItemClickRemove)
    : BaseViewHolder<Contact>(itemView) {

    private lateinit var item: Contact

    init {
        itemView.setOnClickListener { onItemClickListener.onItemClick(item) }
    }

    override fun bindItem(item: Contact) {
        this.item = item

        itemView.image_photo_IC.setImageResource(item.photo)
        itemView.text_full_name_IC.text = itemView.resources.getString(R.string.ic_full_name, item.name, item.surname)
        itemView.text_email_IC.text = item.email
        itemView.image_button_remove_IC.setOnClickListener {
            onItemClickRemove.onItemClick(item, layoutPosition)
        }
    }
}