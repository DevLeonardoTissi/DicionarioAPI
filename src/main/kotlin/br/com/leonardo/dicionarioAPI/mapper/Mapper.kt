package br.com.leonardo.dicionarioAPI.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}