package com.platzi.platziconf2.network

import java.lang.Exception

interface Callback<T> {

    //si la operacion es exitosa nos devolvera un resultado
    fun onSuccess(result: T?)

    //si la interaccion con la base de datos falla nos regresara una excepcion
    fun onFailed(exception: Exception)
}