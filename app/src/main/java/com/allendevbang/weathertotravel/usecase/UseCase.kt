package com.allendevbang.weathertotravel.usecase

interface UseCase<in T, out O> {
    operator fun invoke(input: T? = null): O
}