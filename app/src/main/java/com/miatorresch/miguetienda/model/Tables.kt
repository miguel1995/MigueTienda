package com.miatorresch.miguetienda.model

class Tables {

    companion object{

        var information:Map<String,String> =  mapOf(

            "TABLE_NAME" to "usuarios",
            "_id" to "id",
            "_nombre" to "nombre",
            "_direccion" to "direccion",
            "_correo" to "correo",
            "_telefono" to "telefono"

        )

    }


}