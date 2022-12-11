package br.com.formatura.formaturaatual.domain

import java.io.Serializable

data class Pessoa (
    val name: String,
    val description: String,
    val imageResId: Int
): Serializable