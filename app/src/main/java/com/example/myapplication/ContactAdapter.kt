package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val listaContactos: List<Contact>):
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ContactViewHolder(layoutInflater.inflate(R.layout.item_contact, parent, false))
    }

    //obtenemos todos los datos
    override fun getItemCount(): Int {
        return listaContactos.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = listaContactos[position]
        holder.render(item)
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombre= view.findViewById<TextView>(R.id.textViewNombre)
        val celular= view.findViewById<TextView>(R.id.textViewCelular)

        fun render(contact: Contact){
            nombre.text=contact.nombre
            celular.text=contact.telefono
        }
    }
}