package com.example.myapplication


import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Lista_EvFragment : Fragment() {

    lateinit var ref:DatabaseReference
    lateinit var evidList: MutableList<Evidencia>
    lateinit var listView: ListView
    //lateinit var evidencia: Evidencia
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_lista__ev, container, false)
        ref = FirebaseDatabase.getInstance().getReference("evidencia")
        evidList = mutableListOf()
        listView = view!!.findViewById(R.id.listViewEvid)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                //Toast.makeText(getActivity(), "Error al mostrar 1", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0 != null) {
                        evidList.clear()
                        for(h in p0.children){
                            //Toast.makeText(getActivity(), "Error  al mostrar", Toast.LENGTH_SHORT).show()
                            val evidencia = h.getValue(Evidencia::class.java)
                            //Toast.makeText(getActivity(), "Error al mostrar 2", Toast.LENGTH_SHORT).show()
                            evidList.add(evidencia!!)
                        }
                        val adapter = EvAdapter(inflater.context,R.layout.listavidencia,evidList)
                        listView.adapter = adapter


                }
            }

        })
        // Inflate the layout for this fragment
        return view
    }


}
