package com.miatorresch.miguetienda.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.miatorresch.miguetienda.R
import com.miatorresch.miguetienda.model.Producto
import com.miatorresch.miguetienda.view.ui.fragments.OrderDetailDialogFragment

class ProductoAdapter (val productoList:List<Producto>, val fragmentManager:FragmentManager):RecyclerView.Adapter<ProductoAdapter.ProductoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        var layoutInflater = LayoutInflater.from(parent.context)

        return ProductoHolder(layoutInflater.inflate(R.layout.item_product, parent, false), fragmentManager)

    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        holder.render(productoList[position])
    }

    override fun getItemCount(): Int {
        return productoList.size
    }

    class ProductoHolder(val view: View, val fragmentManager:FragmentManager):RecyclerView.ViewHolder(view){
        fun render(producto:Producto){

            var cardView = view.findViewById<CardView>(R.id.cardView)
            var ivImage = view.findViewById<ImageView>(R.id.ivImage)
            var tvName = view.findViewById<TextView>(R.id.tvName)
            var tvCost = view.findViewById<TextView>(R.id.tvCost)


            ivImage.setImageResource(R.mipmap.ic_launcher)
            tvName.text = producto.nombre
            tvCost.text = producto.precio.toString()

            cardView.setOnClickListener {
                view:View ->
                    var dialogFragment =  OrderDetailDialogFragment().newInstance(
                        producto.id,
                        producto.nombre,
                        producto.precio,
                        producto.descripcion,
                        producto.imageUrl
                    )
                    dialogFragment.show(fragmentManager, "prueba")
            }



        }

    }


}