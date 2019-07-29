package com.cmaozs.punto1

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.*
import android.widget.ArrayAdapter as ArrayAdapter1

class MainActivity : AppCompatActivity() {

    private var sexo = "Masculino"
    private var hobbies = ""
    private var hobbies1 = ""
    private var hobbies2 = ""
    private var hobbies3 = ""
    private var hobbies4 = ""
    private val c = Calendar.getInstance()
    private var date =""
    lateinit var respuesta : String
    private var ciudad = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month:Int, day: Int) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, day)
                updateDateInView()
            }

        }

        btnFecha.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,dateSetListener, c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        val adapter = ArrayAdapter1.createFromResource(this, R.array.ciudades, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCiudad.adapter = adapter

        spCiudad.selectedItem.toString()

        spCiudad.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                ciudad = "Lugar de nacimiento: ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }


        btnGuardar.setOnClickListener {
            var nombre = etNombre.text.toString()
            var apellidos = etLastname.text.toString()
            var correo = etCorreo.text.toString()
            var password = etPassword.text.toString()
            var rpassword = etRPassword.text.toString()

            hobbies = hobbies1 +  hobbies2 +  hobbies3 + hobbies4

            if (password.equals(rpassword)){
                if (nombre.equals("") || apellidos.equals("") || correo.equals("") || password.equals("") || rpassword.equals("")
                    || sexo.equals("") || hobbies.equals("") || hobbies.equals("") || date.equals("") || ciudad.equals("")){
                    Toast.makeText(this, "Algún espacio vacío", Toast.LENGTH_SHORT).show()
                }
                else{
                    respuesta = "Nombre completo: "+ nombre + " " + apellidos +"\n" +"e-mail: "+ correo + "\n" + "Sexo: " + sexo + "\n" +
                            "Hobbies: "+ hobbies1 +  hobbies2 +  hobbies3 + hobbies4 + "\n" +"Fecha de naciemiento: "+ date + "\n" + ciudad
                    tvResultado.text = respuesta
                }
            }
            else{
                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateDateInView(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date = sdf.format(c.getTime())
    }


    fun onCheckboxClicked(view: View){
        if(view is CheckBox){
            val checked: Boolean = view.isChecked
            when (view.id){
                R.id.cbSoccer->
                    if(checked){
                        hobbies1 = "Fútbol "
                    }
                    else{
                        hobbies1 = ""
                    }
                R.id.cbCine->
                    if(checked){
                        hobbies2 = " Cine "
                    }
                    else{
                        hobbies2 = ""
                    }
                R.id.cbComer->
                    if(checked){
                        hobbies3 = hobbies3 + " Comer "
                    }
                    else{
                        hobbies3 = ""
                    }
                R.id.cbDormir->
                    if(checked){
                        hobbies4 = " Dormir"
                    }
                    else{
                        hobbies4 = ""
                    }
            }
        }
    }


    fun onRadioButtonCliked(view: View){
        if(view is RadioButton){
            when (view.id){
                R.id.rbMasculino->
                    if(view.isChecked){
                        sexo = "Masculino"
                    }
                R.id.rbFemenino->
                    if(view.isChecked){
                        sexo = "Femenino"
                    }
            }
        }
    }

}
