package com.example.android.guesstheword.screens.translate

import androidx.lifecycle.ViewModel
import java.net.URL

class TranslateViewModel: ViewModel() {
    init{

    }

    fun translate(){
        val apiUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate"

        var finalUrl = ""
        val result = URL(finalUrl).readText()
    }
}