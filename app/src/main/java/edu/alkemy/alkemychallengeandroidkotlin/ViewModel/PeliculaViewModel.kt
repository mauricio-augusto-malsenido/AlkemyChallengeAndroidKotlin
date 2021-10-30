package edu.alkemy.alkemychallengeandroidkotlin.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.alkemy.alkemychallengeandroidkotlin.DAO.DBOpenHelper
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.CompañiaDeProduccion
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.Genero
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.PaisDeProduccion
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.Pelicula

class PeliculaViewModel : ViewModel() {

    fun agregarPelicula(id: Int, titulo: String, poster: String, fechaEstreno: String, idiomaOriginal: String, duracion: Int, votacion: Float, estado: String, sinopsis: String, generos: String, compañiasProduccion: String, paisesProduccion: String, context: Context): Boolean
    {
        val db: DBOpenHelper = DBOpenHelper(context,null)
        val pelicula: Pelicula = Pelicula(id,titulo,poster,fechaEstreno,idiomaOriginal,duracion,votacion,estado,sinopsis,generos,compañiasProduccion,paisesProduccion)
        return db.agregarPelicula(pelicula)
    }

    fun obtenerPelicula(id: Int, context: Context): Pelicula?
    {
        val db:DBOpenHelper = DBOpenHelper(context,null)
        return db.obtenerPelicula(id)
    }

    fun cargarGeneros(generos: ArrayList<Genero>) : String
    {
        var genero: String = ""
        if (generos.size == 1)
        {
            genero += generos[0].name
        }
        else
        {
            for (i in 0 until generos.size)
            {
                if (i > 0 && i < generos.size-1)
                {
                    genero += generos[i].name + " - "
                }
                else
                {
                    if (i == 0)
                    {
                        genero += generos[i].name + " - "
                    }
                    else
                    {
                        genero += generos[i].name
                    }
                }
            }
        }
        return genero
    }

    fun cargarCompañiasProduccion(compañias: ArrayList<CompañiaDeProduccion>) : String
    {
        var compañia: String = ""
        if (compañias.size == 1)
        {
            compañia += compañias[0].name
        }
        else
        {
            for (i in 0 until compañias.size)
            {
                if (i > 0 && i < compañias.size-1)
                {
                    compañia += compañias[i].name + " - "
                }
                else
                {
                    if (i == 0)
                    {
                        compañia += compañias[i].name + " - "
                    }
                    else
                    {
                        compañia += compañias[i].name
                    }
                }
            }
        }
        return compañia
    }

    fun cargarPaisesProduccion(paises: ArrayList<PaisDeProduccion>) : String
    {
        var pais: String = ""
        if (paises.size == 1)
        {
            pais += paises[0].name
        }
        else
        {
            for (i in 0 until paises.size)
            {
                if (i > 0 && i < paises.size-1)
                {
                    pais += paises[i].name + " - "
                }
                else
                {
                    if (i == 0)
                    {
                        pais += paises[i].name + " - "
                    }
                    else
                    {
                        pais += paises[i].name
                    }
                }
            }
        }
        return pais
    }
}