package com.example.android.guesstheword.screens.translate

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.JsonReader
import android.util.Log
import android.util.Log.WARN
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.guesstheword.MainActivity
import com.example.android.guesstheword.R
import com.example.android.guesstheword.constData.constData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.math.BigInteger
import java.net.MalformedURLException
import java.net.URL
import java.nio.channels.AsynchronousFileChannel.open
import java.security.MessageDigest
import kotlin.random.Random

class TranslateViewModel: ViewModel() {

    private val _srcLang = MutableLiveData<String>()
    val srcLang: LiveData<String>
        get() = _srcLang

    private val _tarLang = MutableLiveData<String>()
    val tarLang: LiveData<String>
        get() = _tarLang

    var srcText = MutableLiveData<String>()

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
        // _srcText.value = "I love you"
        val sign = md5(appid+srcText.value+salt+password)
        val finalUrl = "$apiUrl?q=${srcText.value}&from=${constData().langMap[srcLang.value]}&to=${constData().langMap[tarLang.value]}&appid=$appid&salt=$salt&sign=$sign"
        _tarText.value=srcText.value
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = URL(finalUrl).readText()
                var resDst = JSONObject(result)
                var resArr = JSONArray(resDst["trans_result"].toString())
                resDst = JSONObject(resArr[0].toString())
               withContext(Dispatchers.Main){
                   _tarText.value = resDst["dst"].toString()
//                      _tarText.value = result
               }
            } catch (e : MalformedURLException){
                Log.e("translate: ", e.message)
            }
        }
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun updateSrcSelection(selection: String){
        _srcLang.value = selection
    }

    fun updateTarSelection(selection: String){
        _tarLang.value = selection
    }
}