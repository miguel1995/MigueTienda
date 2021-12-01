package com.miatorresch.miguetienda.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductoRepository {


        val db = Firebase.firestore
        val docRef = db.collection("productos")
        private var productos = listOf<Producto>()
/*
        private var productos = listOf(

                Producto(
                        "1",
                        "Tesla X1",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "2",
                        "Tesla X2",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "3",
                        "Tesla X3",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "4",
                        "Tesla X4",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),

                Producto(
                        "5",
                        "Tesla X5",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "6",
                        "Tesla X6",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "7",
                        "Tesla X7",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "8",
                        "Tesla X8",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "9",
                        "Tesla X9",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "10",
                        "Tesla X10",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),

                Producto(
                        "11",
                        "Tesla X11",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "12",
                        "Tesla X12",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "13",
                        "Tesla X13",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        "14",
                        "Tesla X14",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),



                )
*/

        fun getProductos( mutableLiveData: MutableLiveData<List<Producto>> ){

/* OPCION 1
                db.collection("productos")
                        .get()
                        .addOnSuccessListener { result ->
                                println(result)


                                productos = listOf()

                                for (document in result) {

                                        var producto = document.toObject(Producto::class.java)
                                        producto.id = document.id
                                        productos += producto



                                }

                                mutableLiveData.postValue(productos)


                        }
                        .addOnFailureListener { exception ->
                                Log.w(TAG, "Error getting documents.", exception)
                        }
*/

                /*
                *
                * OPCION 2
                * */

                docRef.addSnapshotListener { snapshot, e ->
                        if (e != null) {
                                Log.w(TAG, "Listen failed.", e)
                                return@addSnapshotListener
                        }

                        if (snapshot != null && !snapshot.isEmpty) {

                                productos = listOf()
                                for (document in snapshot.documents) {

                                        var producto = document.toObject(Producto::class.java)

                                        if(producto != null){
                                                producto.id = document.id
                                                productos += producto
                                        }

                                }
                                mutableLiveData.postValue(productos)

                        } else {
                                Log.d(TAG, "Current data: null")
                        }
                }


        }

        fun findByIds(productosIds:List<String>, mutableLiveData: MutableLiveData<List<Producto>>):List<Producto>{

                println(">>> IDs de los Productos")
                println(productosIds)

                var productosFilter:List<Producto> = mutableListOf<Producto>()

                if(!productosIds.isEmpty()){

                        docRef.whereIn(FieldPath.documentId(), productosIds).addSnapshotListener { snapshot, e ->
                                if (e != null) {
                                        Log.w(TAG, "Listen failed.", e)
                                        return@addSnapshotListener
                                }

                                if (snapshot != null && !snapshot.isEmpty) {

                                        productosFilter = listOf()
                                        for (document in snapshot.documents) {

                                                var producto = document.toObject(Producto::class.java)

                                                if(producto != null){
                                                        producto.id = document.id
                                                        productosFilter += producto
                                                }

                                        }

                                        mutableLiveData.postValue(productosFilter)

                                } else {
                                        Log.d(TAG, "Current data: null")
                                }
                        }


                }

                return productosFilter
        }

        fun decrementarInventario(){
                //TODO Decrementar Inventario en Firebase

        }

}