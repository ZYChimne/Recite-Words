package com.example.android.guesstheword.screens.translate

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.R
import java.net.URL

class TranslateViewModel: ViewModel() {

    private val _srcPos = MutableLiveData<Long>()
    val srcPos: LiveData<Long>
        get() = _srcPos

    private val _tarPos = MutableLiveData<Long>()
    val tarPos: LiveData<Long>
        get() = _tarPos

    private val _srcText = MutableLiveData<Long>()
    val srcText: LiveData<Long>
        get() = _srcText

    private val _tarText = MutableLiveData<Long>()
    val tarText: LiveData<Long>
        get() = _tarText

    init{


    }

    fun translate(){
        val apiUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate"

        var finalUrl = ""
        val result = URL(finalUrl).readText()
    }
}