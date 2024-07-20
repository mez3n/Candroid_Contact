package com.example.dialer_app.data

import androidx.compose.ui.res.stringResource
import com.example.dialer_app.R
import com.example.dialer_app.model.Contact

class DataSource {
    fun getContactsData(): List<Contact> {
        val contacts = mutableListOf<Contact>()

        contacts.add(
            Contact(
                R.string.son,
                R.string.sonNumber,
                R.drawable.son
            )
        )

        contacts.add(
            Contact(
                R.string.brother,
                R.string.brotherNumber,
                R.drawable.brother
            )
        )
        contacts.add(
            Contact(
                R.string.daughter,
                R.string.daughterNumber,
                R.drawable.daughter
            )
        )
        contacts.add(
            Contact(
                R.string.grandfather,
                R.string.grandfatherNumber,
                R.drawable.grandfather
            )
        )
        contacts.add(
            Contact(
                R.string.granny,
                R.string.grannyNumber,
                R.drawable.granny
            )
        )
        contacts.add(
            Contact(
                R.string.friend1,
                R.string.friend1Number,
                R.drawable.friend_1
            )
        )
        contacts.add(
            Contact(
                R.string.friend2,
                R.string.friend2Number,
                R.drawable.friend_2
            )
        )
        contacts.add(
            Contact(
                R.string.sister,
                R.string.sisterNumber,
                R.drawable.sister
            )
        )
        contacts.add(
            Contact(
                R.string.uncle,
                R.string.uncleNumber,
                R.drawable.uncle
            )
        )
        contacts.add(
            Contact(
                R.string.auntie,
                R.string.auntieNumber,
                R.drawable.auntie
            )
        )
        contacts.add(
            Contact(
                R.string.neighbour,
                R.string.neighbourNumber,
                R.drawable.neigbour
            )
        )

        return contacts
    }
}
