package com.miatorresch.miguetienda.model

data class Producto (
    var id:String,
    var nombre:String,
    var precio:Int,
    var descripcion:String,
    var imageUrl:String
){

    constructor() : this(
    "","",0,"",""
    ) {}

}
