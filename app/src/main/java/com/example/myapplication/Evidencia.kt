package com.example.myapplication

class Evidencia(val id:String ,val user:String,val carrera: String, val ambito:String,val alcance:String,val tipo:String, val titulo:String, val des:String, val est_i:Int, val est_ex:Int,val profe_i:Int,val profe_ex:Int,val aut_i:Int, val aut_ex:Int, val profesional_i:Int,val profesional_ex:Int){
    constructor():this("","","","","","","","",0,0,0,0,0,0,0,0){

    }
}