package com.miatorresch.miguetienda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miatorresch.miguetienda.model.Producto
import com.miatorresch.miguetienda.model.ProductoRepository

class ProductosListViewModel:ViewModel() {


    var productoRepository: ProductoRepository
    var productosModel = MutableLiveData<List<Producto>>()

    init {
        productoRepository = ProductoRepository()
    }

    fun getProductos(){
        var currentProductoList =  productoRepository.getProductos()
        productosModel.postValue(currentProductoList)
    }




}