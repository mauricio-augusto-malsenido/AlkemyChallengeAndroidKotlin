package edu.alkemy.alkemychallengeandroidkotlin.Model.API_Response

import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.CompañiaDeProduccion
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.Genero
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.PaisDeProduccion

data class ResponsePelicula(val genres: ArrayList<Genero>,
                            val hompage: String,
                            val id: Int,
                            val original_language: String,
                            val original_title: String,
                            val overview: String,
                            val popularity: Float,
                            val poster_path: String,
                            val production_companies: ArrayList<CompañiaDeProduccion>,
                            val production_countries: ArrayList<PaisDeProduccion>,
                            val release_date: String,
                            val runtime: Int,
                            val status: String,
                            val title: String,
                            val vote_average: Float)