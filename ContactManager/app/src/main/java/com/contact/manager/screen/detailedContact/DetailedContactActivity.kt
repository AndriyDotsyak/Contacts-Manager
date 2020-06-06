package com.contact.manager.screen.detailedContact

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.contact.manager.R
import com.contact.manager.data.model.Contact
import com.contact.manager.screen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed_contact.*

class DetailedContactActivity : BaseActivity() {

    companion object {
        const val CONTACT_KEY = "CONTACT_KEY"

        fun getIntent(context: Context, contact: Contact) : Intent {
            val intent = Intent(context, DetailedContactActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putExtra(CONTACT_KEY, contact)

            return intent
        }
    }

    private val contact: Contact by lazy { intent.extras?.getParcelable(CONTACT_KEY) ?: Contact() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_contact)
        title = getString(R.string.dca_title)

        initUI()
        onClickSave()
        onClickCancel()
    }

    private fun initUI() {
        image_photo_ADC.setImageResource(contact.photo)
        edit_text_name_ADC.setText(contact.name)
        edit_text_surname_ADC.setText(contact.surname)
        edit_text_email_ADC.setText(contact.email)
    }

    private fun onClickSave() {
        button_save_ADC.setOnClickListener {
            contact.name = edit_text_name_ADC.text.toString()
            contact.surname = edit_text_surname_ADC.text.toString()
            contact.email = edit_text_email_ADC.text.toString()

            intent = Intent().putExtra(CONTACT_KEY, contact)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun onClickCancel() {
        button_cancel_ADC.setOnClickListener {
            finish()
        }
    }
}