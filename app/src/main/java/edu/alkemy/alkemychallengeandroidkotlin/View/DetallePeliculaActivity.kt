package edu.alkemy.alkemychallengeandroidkotlin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import edu.alkemy.alkemychallengeandroidkotlin.Api.Implementation.ApiMoviesImp
import edu.alkemy.alkemychallengeandroidkotlin.Model.API_Response.ResponsePelicula
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.*
import edu.alkemy.alkemychallengeandroidkotlin.R
import edu.alkemy.alkemychallengeandroidkotlin.ViewModel.PeliculaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePeliculaActivity : AppCompatActivity() {

    lateinit var imgPortada: ImageView
    lateinit var txtTitulo: TextView
    lateinit var txtFechaLanzamiento: TextView
    lateinit var txtIdiomaOriginal: TextView
    lateinit var txtDuracion: TextView
    lateinit var txtVotacion: TextView
    lateinit var txtEstado: TextView
    lateinit var txtSinopsis: TextView
    lateinit var txtGenero: TextView
    lateinit var txtCompañiasProduccion: TextView
    lateinit var txtPaisesProduccion: TextView
    lateinit var pelicula: PeliculaPopular
    lateinit var peliculaVM: PeliculaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)
        inicializarComponentes()

        peliculaVM = PeliculaViewModel()

        pelicula = intent.getSerializableExtra("pelicula") as PeliculaPopular

        if (peliculaVM.obtenerPelicula(pelicula.id,this) != null)
        {
            val peliculaDBLocal = peliculaVM.obtenerPelicula(pelicula.id,this)
            cargarDetallePeliculaDBLocal(peliculaDBLocal!!)
        }
        else
        {
            val api: ApiMoviesImp = ApiMoviesImp()
            api.getPelicula(pelicula.id).enqueue(object : Callback<ResponsePelicula>{
                override fun onResponse(call: Call<ResponsePelicula>, response: Response<ResponsePelicula>) {
                    if (response.body() != null)
                    {
                        cargarDetallePeliculaAPI(response.body()!!)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"No hay información de la pelicula seleccionada",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePelicula>, t: Throwable) {
                    Toast.makeText(applicationContext,"Problemas de conexión o error en la consulta a la API", Toast.LENGTH_LONG).show()
                    Log.e("apirest",t.message.toString())
                }

            })
        }
    }

    private fun inicializarComponentes()
    {
        imgPortada = findViewById(R.id.imgPortada)
        txtTitulo = findViewById(R.id.txtTitulo)
        txtFechaLanzamiento = findViewById(R.id.txtFechaLanzamiento)
        txtIdiomaOriginal = findViewById(R.id.txtIdiomaOriginal)
        txtDuracion = findViewById(R.id.txtDuracion)
        txtVotacion = findViewById(R.id.txtVotacion)
        txtEstado = findViewById(R.id.txtEstado)
        txtSinopsis = findViewById(R.id.txtSinopsis)
        txtGenero = findViewById(R.id.txtGenero)
        txtCompañiasProduccion = findViewById(R.id.txtCompañiasProduccion)
        txtPaisesProduccion = findViewById(R.id.txtPaisesProduccion)
    }

    private fun cargarDetallePeliculaAPI(pelicula: ResponsePelicula)
    {
        val poster_path: String = "https://image.tmdb.org/t/p/original"+pelicula.poster_path
        Picasso.get()
            .load(poster_path)
            .into(imgPortada)

        txtTitulo.setText(pelicula.original_title)
        txtFechaLanzamiento.setText(pelicula.release_date)
        txtIdiomaOriginal.setText(pelicula.original_language)
        txtDuracion.setText(pelicula.runtime.toString())
        txtVotacion.setText(pelicula.vote_average.toString())
        txtEstado.setText(pelicula.status)
        txtSinopsis.setText(pelicula.overview)
        txtGenero.setText(peliculaVM.cargarGeneros(pelicula.genres))
        txtCompañiasProduccion.setText(peliculaVM.cargarCompañiasProduccion(pelicula.production_companies))
        txtPaisesProduccion.setText(peliculaVM.cargarPaisesProduccion(pelicula.production_countries))

        peliculaVM.agregarPelicula(pelicula.id,
            pelicula.original_title,
            pelicula.poster_path,
            pelicula.release_date,
            pelicula.original_language,
            pelicula.runtime,
            pelicula.vote_average,
            pelicula.status,
            pelicula.overview,
            peliculaVM.cargarGeneros(pelicula.genres),
            peliculaVM.cargarCompañiasProduccion(pelicula.production_companies),
            peliculaVM.cargarPaisesProduccion(pelicula.production_countries),this)
    }

    private fun cargarDetallePeliculaDBLocal(pelicula: Pelicula)
    {
        val poster_path: String = "https://image.tmdb.org/t/p/original"+pelicula.poster
        Picasso.get()
            .load(poster_path)
            .into(imgPortada)

        txtTitulo.setText(pelicula.titulo)
        txtFechaLanzamiento.setText(pelicula.fechaEstreno)
        txtIdiomaOriginal.setText(pelicula.idiomaOriginal)
        txtDuracion.setText(pelicula.duracion.toString())
        txtVotacion.setText(pelicula.votacionPromedio.toString())
        txtEstado.setText(pelicula.estado)
        txtSinopsis.setText(pelicula.sinopsis)
        txtGenero.setText(pelicula.generos)
        txtCompañiasProduccion.setText(pelicula.compañiasProduccion)
        txtPaisesProduccion.setText(pelicula.paisesProduccion)
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        finish()
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detalle_pelicula,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.opAtras)
            finish()
        return true
    }
}