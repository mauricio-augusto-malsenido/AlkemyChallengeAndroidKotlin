package edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades

data class Pelicula(val id: Int,
                    val titulo: String,
                    val poster: String,
                    val fechaEstreno: String,
                    val idiomaOriginal: String,
                    val duracion: Int,
                    val votacionPromedio: Float,
                    val estado: String,
                    val sinopsis: String,
                    val generos: String,
                    val compañiasProduccion: String,
                    val paisesProduccion: String)