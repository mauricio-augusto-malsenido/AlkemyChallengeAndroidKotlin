package edu.alkemy.alkemychallengeandroidkotlin.DAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.Pelicula

class DBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "Pelicula.db"
        private val DATABASE_VERSION = 1

        val TABLE_PELICULA = "Pelicula"
        val COLUMN_ID = "id"
        val COLUMN_TITULO = "titulo"
        val COLUMN_POSTER_PATH = "poster_path"
        val COLUMN_FECHA_ESTRENO = "fecha_estreno"
        val COLUMN_IDIOMA_ORIGINAL = "idioma_original"
        val COLUMN_DURACION = "duracion"
        val COLUMN_VOTACION_PROMEDIO = "votacion_promedio"
        val COLUMN_ESTADO = "estado"
        val COLUMN_SINOPSIS = "sinopsis"
        val COLUMN_GENEROS = "generos"
        val COLUMN_COMPAÑIAS_PRODUCCION = "compañias_produccion"
        val COLUMN_PAISES_PRODUCCION = "paises_produccion"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePelicula = ("CREATE TABLE $TABLE_PELICULA (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_TITULO TEXT NOT NULL, " +
                "$COLUMN_POSTER_PATH TEXT NOT NULL, " +
                "$COLUMN_FECHA_ESTRENO TEXT NOT NULL, " +
                "$COLUMN_IDIOMA_ORIGINAL TEXT NOT NULL, " +
                "$COLUMN_DURACION INTEGER NOT NULL, " +
                "$COLUMN_VOTACION_PROMEDIO FLOAT, " +
                "$COLUMN_ESTADO TEXT NOT NULL, " +
                "$COLUMN_SINOPSIS TEXT NOT NULL, " +
                "$COLUMN_GENEROS TEXT NOT NULL, " +
                "$COLUMN_COMPAÑIAS_PRODUCCION TEXT NOT NULL, " +
                "$COLUMN_PAISES_PRODUCCION TEXT NOT NULL)")

        db?.execSQL(createTablePelicula)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TALBE IF EXISTS " + TABLE_PELICULA)
        onCreate(db)
    }

    fun agregarPelicula(pelicula: Pelicula): Boolean
    {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_ID,pelicula.id)
            values.put(COLUMN_TITULO,pelicula.titulo)
            values.put(COLUMN_POSTER_PATH,pelicula.poster)
            values.put(COLUMN_FECHA_ESTRENO,pelicula.fechaEstreno)
            values.put(COLUMN_IDIOMA_ORIGINAL,pelicula.idiomaOriginal)
            values.put(COLUMN_DURACION,pelicula.duracion)
            values.put(COLUMN_VOTACION_PROMEDIO,pelicula.votacionPromedio)
            values.put(COLUMN_ESTADO,pelicula.estado)
            values.put(COLUMN_SINOPSIS,pelicula.sinopsis)
            values.put(COLUMN_GENEROS,pelicula.generos)
            values.put(COLUMN_COMPAÑIAS_PRODUCCION,pelicula.compañiasProduccion)
            values.put(COLUMN_PAISES_PRODUCCION,pelicula.paisesProduccion)

            db.insert(TABLE_PELICULA,null,values)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun obtenerPelicula(id: Int): Pelicula?
    {
        val db = this.readableDatabase
        var pelicula: Pelicula? = null

        val query = "SELECT * FROM $TABLE_PELICULA WHERE $COLUMN_ID = $id"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst())
        {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
            val poster = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH))
            val fechaEstreno = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_ESTRENO))
            val idiomaOriginal = cursor.getString(cursor.getColumnIndex(COLUMN_IDIOMA_ORIGINAL))
            val duracion = cursor.getInt(cursor.getColumnIndex(COLUMN_DURACION))
            val votacionPromedio = cursor.getFloat(cursor.getColumnIndex(COLUMN_VOTACION_PROMEDIO))
            val estado = cursor.getString(cursor.getColumnIndex(COLUMN_ESTADO))
            val sinopsis = cursor.getString(cursor.getColumnIndex(COLUMN_SINOPSIS))
            val generos = cursor.getString(cursor.getColumnIndex(COLUMN_GENEROS))
            val compañiasProduccion = cursor.getString(cursor.getColumnIndex(COLUMN_COMPAÑIAS_PRODUCCION))
            val paisesProduccion = cursor.getString(cursor.getColumnIndex(COLUMN_PAISES_PRODUCCION))

            pelicula = Pelicula(id,titulo,poster,fechaEstreno,idiomaOriginal,duracion,votacionPromedio,estado,sinopsis,generos,compañiasProduccion,paisesProduccion)
        }

        return pelicula
    }
}