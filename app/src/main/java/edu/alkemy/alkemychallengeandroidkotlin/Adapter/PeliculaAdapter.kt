package edu.alkemy.alkemychallengeandroidkotlin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import edu.alkemy.alkemychallengeandroidkotlin.Model.Entidades.PeliculaPopular
import edu.alkemy.alkemychallengeandroidkotlin.R

class PeliculaAdapter(val lista: ArrayList<PeliculaPopular>) : BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Any {
        return lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return lista.get(position).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        var view = convertView

        if (view==null)
        {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_gv_peliculas,parent,false)
            holder = ViewHolder(view)
            view.setTag(holder)
        }
        else
        {
            holder = view.getTag() as ViewHolder
        }

        val portada_path: String = "https://image.tmdb.org/t/p/original"+lista[position].poster_path
        Picasso.get()
            .load(portada_path)
            .into(holder.imgPelicula)

        return view!!
    }

    class ViewHolder(view: View)
    {
        var imgPelicula: ImageView

        init {
            imgPelicula = view.findViewById(R.id.imgPelicula)
        }
    }
}