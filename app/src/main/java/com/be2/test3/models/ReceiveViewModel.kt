package com.be2.test3.models

import android.view.View
import androidx.lifecycle.ViewModel
import com.be2.test3.ui.listner

class ReceiveViewModel : ViewModel(){

    var url: String? = null
    var title: String? = null

    var listner : listner? = null

    fun onfull(view: View){
        listner?.onfull()
        if (url.isNullOrEmpty()){
            return
        }
        //success

    }

    fun onpip(view: View){
        listner?.onpip()
        if (url.isNullOrEmpty()){
            return
        }
        //success

    }

}
