package br.com.nrashy.currencyconverter.model

import androidx.databinding.ObservableDouble

class Price {
    private var value = ObservableDouble()

    fun getValue() =  this.value
    fun setValue(value:Double){
        this.value.set(value)
    }
}
