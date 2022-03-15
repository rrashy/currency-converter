package br.com.nrashy.currencyconverter.model

interface IObserver {
    fun updateUI(data:MutableMap<String,Any>)
}