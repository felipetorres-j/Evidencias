package com.example.myapplication

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_crear.*
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isEmpty



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CrearFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CrearFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CrearFragment : Fragment(),View.OnClickListener {

    // TODO: Rename and change types of parameters


    var txttitulo: EditText? = null
    var txtdes: EditText? = null
    var carr: Spinner?= null
    var alcances:Spinner?= null
    var ambitos:Spinner?= null
    var tipos:Spinner?= null
    var carrera:String = ""
    var s_tipo:String=""
    var est_int:EditText? = null
    var est_ext:EditText? = null
    var prof_int:EditText? = null
    var prof_ext:EditText? = null
    var aut_int:EditText? = null
    var aut_ext:EditText? = null
    var profesional_int:EditText? = null
    var profesional_ext:EditText? = null
    var s_ambito:String=""
    var s_alcance:String=""
    var user:String=""
    lateinit var homeFragment: HomeFragment


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment

        val view = inflater!!.inflate(R.layout.fragment_crear, container, false)
        carr = view?.findViewById(R.id.spinner)




        alcances =view?.findViewById(R.id.sp_alcance)
        ambitos = view?.findViewById(R.id.sp_ambito)
        tipos = view?.findViewById(R.id.sp_tipo)
        txttitulo = view?.findViewById(R.id.txtTitulo)
        txtdes = view?.findViewById(R.id.txtDes)
        est_ext = view?.findViewById(R.id.idEst_ext)
        est_int = view?.findViewById(R.id.idEstudiantes)
        prof_ext =view?.findViewById(R.id.idProfe_ext)
        prof_int = view?.findViewById(R.id.idProfe)
        aut_ext = view?.findViewById(R.id.idAutor_ext)
        aut_int = view?.findViewById(R.id.idAutoridades)
        profesional_ext = view?.findViewById(R.id.idProfesionales_ext)
        profesional_int = view?.findViewById(R.id.idProfesionales)

        var carreras = arrayOf("Ingenieria Civil Informatica","Ingenieria en construccion", "Ingenieria Civil")
        var alcance = arrayOf("Comunal","Provincial","Regional","Nacional","Internacional")
        var ambito = arrayOf("Académico","Extensión","Extención académica","Gestión","Investigación","Productivo","Social")
        var tipo= arrayOf("Acuerdos","Apoyo","Asesoría","Asistencia","Beca","Campaña","Capacitación","Celebración","Charla","Conferencia","Congreso","Contrato","Convenio","Cursos","Difusión","Encuentro","Exposición","Induccíon")
        carr?.adapter = ArrayAdapter<String>(getActivity()!!, android.R.layout.simple_list_item_1, carreras)
        //listener para saber la opcion seleccionada
        carr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                carrera= carreras.get(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        alcances?.adapter = ArrayAdapter<String>(getActivity()!!, android.R.layout.simple_list_item_1,alcance)
        alcances?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                //obtenemos el valor elegido en la posicion p2
                s_alcance=alcance.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        ambitos?.adapter = ArrayAdapter<String>(getActivity()!!, android.R.layout.simple_list_item_1,ambito)
        ambitos?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //obtenemos el valor elegido que lo contiene  la posicion p2
                s_ambito= ambito.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        tipos?.adapter = ArrayAdapter<String>(getActivity()!!, android.R.layout.simple_list_item_1,tipo)
        tipos?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //obtenemos el valor elegido que lo contiene  la posicion p2
                s_tipo= tipo.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        val button: Button? = view?.findViewById(R.id.button)
        //button?.setOnClickListener(this)
        button?.setOnClickListener {
            saveEvidencia()
            //Toast.makeText(getActivity(), "Hola13", Toast.LENGTH_SHORT).show();
        }
        //button?.setOnClickListener{ v: View -> saveEvidencia(v)}
        //Toast.makeText(getActivity(), "Hola1", Toast.LENGTH_SHORT).show();
        return view
    }
    private fun saveEvidencia() {
        //Toast.makeText(getActivity(), "Hola2", Toast.LENGTH_SHORT).show();

        val titulo:String = txttitulo?.text.toString()

        val des:String=txtdes?.text.toString()

        if (arguments != null){
            user = arguments!!.getString("user","sin user")

        }

        val est_i: Int = Integer.parseInt(est_int?.text.toString())
        val est_ex:Int= Integer.parseInt(est_ext?.text.toString())

        val profe_i:Int= Integer.parseInt(prof_int?.text.toString())
        val profe_ex:Int= Integer.parseInt(prof_int?.text.toString())

        val aut_i:Int= Integer.parseInt(aut_int?.text.toString())
        val aut_ex:Int= Integer.parseInt(aut_ext?.text.toString())

        val pro_i:Int= Integer.parseInt(profesional_int?.text.toString())
        val pro_ex:Int= Integer.parseInt(profesional_ext?.text.toString())
        //if (carrera.isEmpty()){
            //Toast.makeText(this,"Error, llenar todas las casillas", Toast.LENGTH_LONG).show()
        //}

        val ref = FirebaseDatabase.getInstance().getReference("evidencia")

        val evid = ref.push().key //le asignamos un id
        val evidencia = Evidencia(evid,user,carrera,s_ambito,s_alcance,s_tipo,titulo ,des,est_i,est_ex,profe_i,profe_ex,aut_i,aut_ex,pro_i,pro_ex)

        ref.child(evid).setValue(evidencia).addOnCompleteListener{
            //startActivity(Intent(this,MenuSlideActivity::class.java))
            //Toast.makeText(this,"Evidencia guardada correctamente", Toast.LENGTH_LONG).show()
            Toast.makeText(getActivity(), "se a enviado la evidencia", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(getActivity(),MenuSlideActivity::class.java))
            val fragment = HomeFragment()
            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.menuid, fragment, "fragment_meters")
            ft.addToBackStack(null)  //opcional, si quieres agregarlo a la pila
            ft.commit()
        }

    }

    override fun onClick(v: View?) {

        Toast.makeText(getActivity(), "Hola1223", Toast.LENGTH_SHORT).show();

    }




}
