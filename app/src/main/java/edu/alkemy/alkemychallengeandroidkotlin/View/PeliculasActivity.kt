package edu.alkemy.alkemychallengeandroidkotlin.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import edu.alkemy.alkemychallengeandroidkotlin.Adapter.PeliculaAdapter
import edu.alkemy.alkemychallengeandroidkotlin.Api.Implementation.ApiMoviesImp
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.PeliculaPopular
import edu.alkemy.alkemychallengeandroidkotlin.Model.API_Response.ResponsePeliculasPopulares
import edu.alkemy.alkemychallengeandroidkotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeliculasActivity : AppCompatActivity() {

    lateinit var etFiltroPelicula: EditText
    lateinit var btnFiltrarPelicula: Button
    lateinit var gv_peliculas: GridView
    lateinit var peliculaPopulars: ArrayList<PeliculaPopular>
    lateinit var peliculasFiltradas: ArrayList<PeliculaPopular>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        inicializarComponentes()

        peliculaPopulars = ArrayList()
        peliculasFiltradas = ArrayList()

        val api: ApiMoviesImp = ApiMoviesImp()
        api.getPeliculasPopulares().enqueue(object : Callback<ResponsePeliculasPopulares> {
            override fun onResponse(call: Call<ResponsePeliculasPopulares>, response: Response<ResponsePeliculasPopulares>) {
                if (response.body() != null)
                {
                    peliculaPopulars = response.body()!!.results
                    if (peliculaPopulars.size > 0)
                    {
                        cargarPeliculas(peliculaPopulars)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Ninguna pelicula cargada o devuelta",Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponsePeliculasPopulares>, t: Throwable) {
                Toast.makeText(applicationContext,"Problemas de conexión o error en la consulta a la API",Toast.LENGTH_LONG).show()
                Log.e("apirest",t.message.toString())
            }
        })
    }

    private fun inicializarComponentes()
    {
        etFiltroPelicula = findViewById(R.id.etFiltroPelicula)
        btnFiltrarPelicula = findViewById(R.id.btnFiltrarPelicula)
        gv_peliculas = findViewById(R.id.gv_peliculas)
    }

    private fun cargarPeliculas(lista: ArrayList<PeliculaPopular>)
    {
        gv_peliculas.adapter = PeliculaAdapter(lista)
        gv_peliculas.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val verDetallePelicula: Intent = Intent(view!!.context,DetallePeliculaActivity::class.java)
                verDetallePelicula.putExtra("pelicula",lista[position])
                startActivity(verDetallePelicula)
            }
        })
    }

    fun filtrarPeliculas(view: View)
    {
        val tituloAlbum: String = etFiltroPelicula.text.toString()
        if (peliculaPopulars.size > 0)
        {
            for (i in 0 until peliculaPopulars.size)
            {
                if (peliculaPopulars[i].title.contains(tituloAlbum))
                {
                    peliculasFiltradas.add(peliculaPopulars[i])
                }
            }
        }

        if (peliculasFiltradas.size > 0)
        {
            gv_peliculas.adapter = PeliculaAdapter(peliculasFiltradas)
        }
        else
        {
            Toast.makeText(applicationContext,"No se encontró el título ingresado",Toast.LENGTH_LONG).show()
        }
    }

    fun limpiar(view: View)
    {
        peliculasFiltradas = ArrayList()
        etFiltroPelicula.setText("")
        gv_peliculas.adapter = PeliculaAdapter(peliculaPopulars)
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_peliculas,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.opSalir)
            finishAndRemoveTask()
        return true
    }
}