package com.allendevbang.weathertotravel.util

fun String.getNavParameterKey():String = replace("/{","").replace("}","")