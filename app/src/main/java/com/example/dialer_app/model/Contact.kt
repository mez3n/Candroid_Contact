package com.example.dialer_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Contact
    (
    @StringRes val name: Int,
    @StringRes val number: Int,
    @DrawableRes val picture: Int
)

