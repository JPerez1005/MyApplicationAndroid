package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class RegistroActivity : AppCompatActivity() {

    lateinit var primerBoton: Button
    lateinit var segundoBoton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        primerBoton=findViewById(R.id.buttonPrimerFragmento)
        segundoBoton=findViewById(R.id.buttonSegundoFragmento)

        val pf= PrincipalFragment()
        val prf= PrimerFragment()
        val sf= SegundoFragment()

        // Mostrar el fragmento inicial
        transaccion(pf)

        // Configurar los listeners de los botones
        primerBoton.setOnClickListener {
            transaccion(prf)
        }

        segundoBoton.setOnClickListener {
            transaccion(sf)
        }

    }

    private fun transaccion(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        //con la siguiente linea podemos regresar al anterior momento
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}