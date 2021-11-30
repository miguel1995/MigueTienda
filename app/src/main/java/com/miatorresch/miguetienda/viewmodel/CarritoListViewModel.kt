package com.miatorresch.miguetienda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miatorresch.miguetienda.model.Producto
import com.miatorresch.miguetienda.model.ProductoRepository

class CarritoListViewModel:ViewModel() {


    var productoRepository: ProductoRepository
    var productosModel = MutableLiveData<List<Producto>>()

    init {
        productoRepository = ProductoRepository()
    }

    fun getProductosByIds(productosIds:List<String>){
        var currentProductoList =  productoRepository.findByIds(productosIds)
        productosModel.postValue(currentProductoList)
    }




}