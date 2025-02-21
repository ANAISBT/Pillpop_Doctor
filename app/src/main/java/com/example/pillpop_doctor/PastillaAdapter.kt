package com.example.pillpop_doctor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PastillaAdapter(private val pastillasList: MutableList<Pastilla>) : RecyclerView.Adapter<PastillaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pastillas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pastilla = pastillasList[position]
        holder.bind(pastilla)
        holder.itemView.findViewById<ImageView>(R.id.eliminarPastillaButton).setOnClickListener {
            // Eliminar la pastilla de la lista
            eliminarPastilla(position)
        }
        holder.itemView.findViewById<ImageView>(R.id.editarPastillaButton).setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditarPastillaView::class.java)

            // Pasar los datos de la pastilla al Intent
            intent.putExtra("pastilla_id", pastilla.pastillla_id)
            intent.putExtra("pastilla_nombre", pastilla.pastilla_nombre)
            intent.putExtra("cantidad", pastilla.cantidad)
            intent.putExtra("dosis", pastilla.dosis)
            intent.putExtra("frecuencia", pastilla.Frecuencia)
            intent.putExtra("fechaInicio", pastilla.fechaInicio)
            intent.putExtra("hora", pastilla.hora)
            intent.putExtra("observaciones", pastilla.observaciones)

            // Iniciar la actividad para editar la pastilla
            context.startActivity(intent)
        }
    }

    private fun eliminarPastilla(position: Int) {
        pastillasList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = pastillasList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nombrePastilla: TextView = itemView.findViewById(R.id.nombrePastilla)
        private var cantidadText: TextView = itemView.findViewById(R.id.cantidadNumero)
        private var dosisText: TextView = itemView.findViewById(R.id.DosisNumero)
        private var frecuenciaText: TextView = itemView.findViewById(R.id.Fecuencia)
        private var fechaInicioText: TextView = itemView.findViewById(R.id.fechaNumero)
        private var horaText: TextView = itemView.findViewById(R.id.horaNumero)
        private var observacionesText: TextView = itemView.findViewById(R.id.ObservacionesText)

        fun bind(pastilla: Pastilla) {
            nombrePastilla.text = pastilla.pastilla_nombre
            cantidadText.text = pastilla.cantidad.toString()
            dosisText.text = pastilla.dosis.toString()
            frecuenciaText.text = pastilla.Frecuencia
            fechaInicioText.text = pastilla.fechaInicio
            horaText.text = pastilla.hora
            observacionesText.text = pastilla.observaciones
        }
    }
}
