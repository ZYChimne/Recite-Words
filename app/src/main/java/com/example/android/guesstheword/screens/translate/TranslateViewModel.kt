package com.example.android.guesstheword.screens.translate

import android.util.Log
import android.util.Log.WARN
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.guesstheword.R
import java.math.BigInteger
import java.net.MalformedURLException
import java.net.URL
import java.security.MessageDigest
import kotlin.random.Random

class TranslateViewModel: ViewModel() {

    private val _srcPos = MutableLiveData<Long>()
    val srcPos: LiveData<Long>
        get() = _srcPos

    private val _tarPos = MutableLiveData<Long>()
    val tarPos: LiveData<Long>
        get() = _tarPos

    private val _srcText = MutableLiveData<String>()
    val srcText: LiveData<String>
        get() = _srcText

    private val _tarText = MutableLiveData<String>()
    val tarText: LiveData<String>
        get() = _tarText

    init{


    }

    fun clickTranslateButton(){
        translate()
    }

    private fun translate(){
        val apiUrl = "https://fanyi-api.baidu.com/api/trans/vip/translate"
        val appid = "20210304000714619"
        val password = "MZxdX5cNlIRFeVxyMQCO"
        val salt = Random.nextInt()
        val sign = md5(appid+tarText.value+salt+password)
        val finalUrl = "$apiUrl?q=${srcText.value}&from=${srcPos.value}&to=${tarPos.value}&appid=$appid&salt=$salt&sign=$sign"
        try {
            val result = URL(finalUrl).readText()
            _srcText.value = result
        } catch (e : MalformedURLException){
            Log.v("translate: ", e.message)
        }
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}