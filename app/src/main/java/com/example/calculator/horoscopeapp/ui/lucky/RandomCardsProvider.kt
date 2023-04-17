package com.example.calculator.horoscopeapp.ui.lucky

import com.example.calculator.R
import com.example.calculator.horoscopeapp.ui.lucky.model.LuckyModel
import javax.inject.Inject

class RandomCardsProvider @Inject constructor() {

    fun getLucky(): LuckyModel =
        when((0..11).random()){
            0-> {
                LuckyModel(R.drawable.card_aquarius, R.string.app_name)
            }
            1-> {
                LuckyModel(R.drawable.card_aries, R.string.app_name)
            }
            2-> {
                LuckyModel(R.drawable.card_cancer, R.string.app_name)
            }
            3-> {
                LuckyModel(R.drawable.card_scorpio, R.string.app_name)
            }
            4-> {
                LuckyModel(R.drawable.card_sagittarius, R.string.app_name)
            }
            5-> {
                LuckyModel(R.drawable.card_leo, R.string.app_name)
            }
            6-> {
                LuckyModel(R.drawable.card_libra, R.string.app_name)
            }
            7-> {
                LuckyModel(R.drawable.card_capricorn, R.string.app_name)
            }
            8-> {
                LuckyModel(R.drawable.card_gemini, R.string.app_name)
            }
            9-> {
                LuckyModel(R.drawable.card_pisces, R.string.app_name)
            }
            10-> {
                LuckyModel(R.drawable.card_taurus, R.string.app_name)
            }
            11-> {
                LuckyModel(R.drawable.card_virgo, R.string.app_name)
            }
            else -> {
                LuckyModel(R.drawable.card_blank, R.string.app_name)
            }
        }

}