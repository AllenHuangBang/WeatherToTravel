package com.allendevbang.weathertotravel.util

fun String.getNavParameterKey(): String =
    replace("/{", "").replace("}", "")
fun String.addAsNavParams(): String =
    StringBuilder().append("/{").append(this).append("}").toString()