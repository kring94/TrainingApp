package com.example.calculator.horoscopeapp.ui.lucky.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import javax.inject.Inject

data class LuckyModel @Inject constructor(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)
