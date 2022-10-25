package com.example.practicamovilp

import android.R
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.practicamovilp.Constantes.key_mat
import com.example.practicamovilp.Constantes.key_nombre
import com.example.practicamovilp.Constantes.key_numero
import com.example.practicamovilp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPerfence: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeSharedPreference()
        loadData()
        binding.btnGuardar.setOnClickListener {
            saveData()
            loadData()
        }
        val spinner = binding.spMaterias
        val lista = listOf("Programacio movil <3 :) ","Progrmacion Web","Game Developer","Base de Datos")
        val adaptador = ArrayAdapter(this, R.layout.simple_spinner_item,lista)
        spinner.adapter =adaptador

    }
    private fun initializeSharedPreference() {
        sharedPerfence = getSharedPreferences("datos", MODE_PRIVATE)
        editor = sharedPerfence.edit()
    }
    private fun saveData(){
        val nombreCompleto = binding.edNombreEncuesta.text.toString()
        val valoracion = binding.edValoracion.text.toString()
        val materia = binding.spMaterias.getSelectedItem().toString();
        editor.apply{
            putString(key_nombre, nombreCompleto)
            putString(key_numero, valoracion)
            putString(key_mat,materia)
        }.apply()
    }
    private fun loadData() {
        val nombreEncuesta = sharedPerfence.getString(key_nombre, "...")
        binding.txtMostrar.text = nombreEncuesta
        val valoracion = sharedPerfence.getString(key_numero, "...")
        binding.txtMostrar1.text = valoracion
        val materia = sharedPerfence.getString(key_mat,"...")
        binding.txtMostrar2.text = materia
    }
}