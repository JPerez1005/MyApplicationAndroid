package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var registro: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var dataModelArrayList: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registro= findViewById(R.id.textViewRegistro)

        registro.setOnClickListener {
            Toast.makeText(this,"Registro clikeado", Toast.LENGTH_LONG).show()
            val intent = Intent(this,RegistroActivity::class.java)
            startActivity(intent)
        }

        val contactos=listOf<Contact>(
            Contact("Ru mi novia hermosa","3166553404"),
            Contact("Juan Pérez", "123456789"),
            Contact("Ana López", "987654321"),
            Contact("Carlos Gómez", "555555555"),
            Contact("Sofía Ramírez", "444444444"),
            Contact("María Torres", "333333333"),
            Contact("Luis Hernández", "222222222"),
            Contact("Lucía Castillo", "111111111"),
            Contact("Pedro Domínguez", "666666666"),
            Contact("Andrea Morales", "777777777")
        )

        dataModelArrayList = ArrayList()
        recyclerView=findViewById(R.id.rvlistaContactos)

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=ContactAdapter(contactos)

        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/users"

        val jsonArrayRequest = JsonArrayRequest(url,
            Response.Listener {
                response ->
                Log.i("Volley","response: $response")
                for(i in 0 until response.length()){
                    val item = response.getJSONObject(i)
                    Log.i("Volley","item: $item")
                    dataModelArrayList.add(Contact(item.get("name").toString(), item.get("phone").toString()))
                }
                recyclerView.adapter = ContactAdapter(dataModelArrayList)
                recyclerView.layoutManager = LinearLayoutManager(this)
            },
            Response.ErrorListener {
                error -> error.printStackTrace()
            }
        )

        queue.add(jsonArrayRequest)
    }
}