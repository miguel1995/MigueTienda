package com.miatorresch.miguetienda.model

class ProductoRepository {

        private var productos = listOf(

                Producto(
                        1,
                        "Tesla X1",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        2,
                        "Tesla X2",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        3,
                        "Tesla X3",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        4,
                        "Tesla X4",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),

                Producto(
                        5,
                        "Tesla X5",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        6,
                        "Tesla X6",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        7,
                        "Tesla X7",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        8,
                        "Tesla X8",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        9,
                        "Tesla X9",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        10,
                        "Tesla X10",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),

                Producto(
                        11,
                        "Tesla X11",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        12,
                        "Tesla X12",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        13,
                        "Tesla X13",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),
                Producto(
                        14,
                        "Tesla X14",
                        2699999,
                        "El Tesla Model X es un SUV completamente eléctrico, del segmento E, fabricado por Tesla desde 2016, con enfoque Premium y con posibilidad de acomodar hasta siete personas.",
                        "https://www.diariomotor.com/imagenes/picscache/1920x1600c/tesla-model-x-9_1920x1600c.jpg"
                ),



                )


        fun getProductos():List<Producto>{
            //TODO: Consultar todos los productos de Firebase

            return productos
        }

        fun findByIds(productosIds:List<Int>):List<Producto>{
                //TODO: Consultar todos los productos del carrito de Firebase

                println(">>> IDs de los Productos")
                println(productosIds)

                var productosFilter:List<Producto> = mutableListOf<Producto>()

                productosIds.forEach {

                        id:Int->
                        productos.forEach {
                            p:Producto ->
                                if(id == p.id){

                                        productosFilter += p

                                }
                        }
                }

                println(">>> Productos Filtrados")
                println(productosFilter)

                //return productos.filter { p -> productosIds.contains(p.id) }

                return productosFilter
        }


}