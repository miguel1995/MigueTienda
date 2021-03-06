package com.miatorresch.miguetienda.view.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miatorresch.miguetienda.R
import com.miatorresch.miguetienda.view.adapter.ProductoAdapter
import com.miatorresch.miguetienda.viewmodel.CarritoListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarritoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel:CarritoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        var sharedPref:SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        var carrito_ids:String? = sharedPref.getString("carrito_ids", "Default")
        var carritoIdsArr:List<String> = listOf()

        if(carrito_ids != "Default"){
            carritoIdsArr = carrito_ids?.split(',')!!
        }

        println(">>> carritoIdsArr ${carritoIdsArr}")

        viewModel.getProductosByIds(carritoIdsArr)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rvProductosCarrito =  view.findViewById<RecyclerView>(R.id.rvProductosCarrito)

        var tvTotal =  view.findViewById<TextView>(R.id.tvTotal)
        var btnPagar =  view.findViewById<Button>(R.id.btnPagar)


        rvProductosCarrito.layoutManager = GridLayoutManager(requireActivity(), 2)

        viewModel.productosModel.observe(viewLifecycleOwner){

            productos ->
            val adapter = ProductoAdapter(productos, childFragmentManager)
            rvProductosCarrito.adapter = adapter


            var totalCount = 0
            productos.forEach { p ->  totalCount += p.precio }
            tvTotal.text =  "$"+totalCount.toString()

        }

        btnPagar.setOnClickListener {
            view:View->
                    //TODO: redireccionar


            //Limpia productos del carrito
            var sharedPref:SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.remove("carrito_ids")
            editor.apply()

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarritoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}