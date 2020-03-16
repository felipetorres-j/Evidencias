package com.example.myapplication

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase
//controlador para las evidencias
class EvAdapter(val mCtx: Context,val layoutResId:Int, val evList:List<Evidencia>)
    :ArrayAdapter<Evidencia>(mCtx,layoutResId,evList)
{
    lateinit var homeFragment: HomeFragment
    var s_ambito:String=""
    var s_alcance:String=""
    var carrera:String = ""
    var s_tipo:String=""

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx) // lo utilizamos para asignar la instancia (para utilizar el xml)
        val  view: View = layoutInflater.inflate(layoutResId, null)
        val textViewtit = view.findViewById<TextView>(R.id.textViewtit)
        val textViewuser = view.findViewById<TextView>(R.id.textViewuser)
        val textViewcarrera = view.findViewById<TextView>(R.id.textViewcarrera)
        val updatebutton: Button =  view.findViewById<Button>(R.id.updatebutton)
        val deletebutton = view.findViewById<Button>(R.id.deletebutton)
        val evid = evList[position] // entrega los resultados de evList segun la posicion
        //asignamos los valores al text view
        textViewtit.text = evid.titulo
        textViewuser.text = evid.user
        textViewcarrera.text = evid.carrera
        updatebutton.setOnClickListener{
            showUpdate(position,evid)
        }
        deletebutton.setOnClickListener{
            EvDelete(evid)
        }

        return view
    }
    //funcion para actualizar las evidencias
    private fun showUpdate(position: Int,evidencia: Evidencia){
        Toast.makeText(mCtx,"hasta aqui funciona",Toast.LENGTH_LONG).show()
        val builder= Builder(this.mCtx)
        builder.setTitle("Actualizar Evidencia")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.layout_update_ev,null)

        val carr = view?.findViewById<Spinner>(R.id.spinner)
        val alcances =view?.findViewById<Spinner>(R.id.sp_alcance)
        val ambitos = view?.findViewById<Spinner>(R.id.sp_ambito)
        val tipos = view?.findViewById<Spinner>(R.id.sp_tipo)
        val txttitulo = view?.findViewById<EditText>(R.id.txtTitulo)
        txttitulo?.setText(evidencia.titulo)
        val txtdes = view?.findViewById<EditText>(R.id.txtDes)
        Toast.makeText(mCtx,"estudiantes:"+evidencia.aut_ex,Toast.LENGTH_LONG).show()
        txtdes?.setText(evidencia.des)
        val est_ext = view?.findViewById<EditText>(R.id.idEst_ext)

        //est_ext?.text =
        est_ext?.setText(""+evidencia.est_ex)
        val est_int = view?.findViewById<EditText>(R.id.idEstudiantes)
        est_int?.setText(""+evidencia.est_i)
        val prof_ext =view?.findViewById<EditText>(R.id.idProfe_ext)
        prof_ext?.setText(""+evidencia.profe_ex)
        val prof_int = view?.findViewById<EditText>(R.id.idProfe)
        prof_int?.setText(""+evidencia.profe_i)
        val aut_int = view?.findViewById<EditText>(R.id.idAutoridades)
        aut_int?.setText(""+evidencia.aut_i)
        val aut_ext = view?.findViewById<EditText>(R.id.idAutor_ext)
        aut_ext?.setText(""+evidencia.aut_ex)
        val profesional_ext = view?.findViewById<EditText>(R.id.idProfesionales_ext)
        profesional_ext?.setText(""+evidencia.profesional_ex)
        val profesional_int = view?.findViewById<EditText>(R.id.idProfesionales)
        profesional_int?.setText(""+evidencia.profesional_i)

        var carreras = arrayOf("Ingenieria Civil Informatica","Ingenieria en construccion", "Ingenieria Civil")
        var alcance = arrayOf("Comunal","Provincial","Regional","Nacional","Internacional")
        var ambito = arrayOf("Académico","Extensión","Extención académica","Gestión","Investigación","Productivo","Social")
        var tipo= arrayOf("Acuerdos","Apoyo","Asesoría","Asistencia","Beca","Campaña","Capacitación","Celebración","Charla","Conferencia","Congreso","Contrato","Convenio","Cursos","Difusión","Encuentro","Exposición","Induccíon")
        carr?.adapter = ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1, carreras)
        //listener para saber la opcion seleccionada
        carr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                carrera= carreras.get(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        alcances?.adapter = ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1,alcance)
        alcances?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                //obtenemos el valor elegido en la posicion p2
                s_alcance=alcance.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        ambitos?.adapter = ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1,ambito)
        ambitos?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //obtenemos el valor elegido que lo contiene  la posicion p2
                s_ambito= ambito.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        tipos?.adapter = ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1,tipo)
        tipos?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //obtenemos el valor elegido que lo contiene  la posicion p2
                s_tipo= tipo.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }


        builder.setView(view)
        builder.setPositiveButton("Actualizar", object: DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val dbevid = FirebaseDatabase.getInstance().getReference("evidencia")

                val titulo:String = txttitulo?.text.toString().trim()

                val des:String=txtdes?.text.toString().trim()

                val est_i: Int = Integer.parseInt(est_int?.text.toString())
                val est_ex:Int= Integer.parseInt(est_ext?.text.toString())

                val profe_i:Int= Integer.parseInt(prof_int?.text.toString())
                val profe_ex:Int= Integer.parseInt(prof_int?.text.toString())

                val aut_i:Int= Integer.parseInt(aut_int?.text.toString())
                val aut_ex:Int= Integer.parseInt(aut_ext?.text.toString())

                val pro_i:Int= Integer.parseInt(profesional_int?.text.toString())
                val pro_ex:Int= Integer.parseInt(profesional_ext?.text.toString())

                val evid_up = Evidencia(evidencia.id,evidencia.user,carrera,s_ambito,s_alcance,s_tipo,titulo ,des,est_i,est_ex,profe_i,profe_ex,aut_i,aut_ex,pro_i,pro_ex)

                dbevid.child(evidencia.id).setValue(evid_up)

                Toast.makeText(mCtx,"Evidencia Actualizada",Toast.LENGTH_LONG).show()

            }
        })

        builder.setNegativeButton("Volver", object: DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {


            }
        })
        val alert = builder.create()
        alert.show()


    }
    fun EvDelete(evidencia: Evidencia){
        val EvDatabase = FirebaseDatabase.getInstance().getReference("evidencia")
        EvDatabase.child(evidencia.id).removeValue()
        Toast.makeText(mCtx,"Evidencia Eliminada correctamente",Toast.LENGTH_LONG).show()
    }
}