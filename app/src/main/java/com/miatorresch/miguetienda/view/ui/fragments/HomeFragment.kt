package com.miatorresch.miguetienda.view.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.miatorresch.miguetienda.R
import com.journeyapps.barcodescanner.ScanOptions

import com.journeyapps.barcodescanner.ScanContract

import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import com.journeyapps.barcodescanner.ScanIntentResult
import com.miatorresch.miguetienda.model.Producto
import com.miatorresch.miguetienda.view.adapter.ProductoAdapter
import com.miatorresch.miguetienda.viewmodel.ProductosListViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: ProductosListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.scan-> {

                Toast.makeText(requireContext(), "TODO: SCAN", Toast.LENGTH_LONG).show()
                barcodeLauncher.launch(ScanOptions())


            }
            R.id.search-> Toast.makeText(requireContext(),"TODO: search", Toast.LENGTH_LONG).show()
            R.id.filter-> Toast.makeText(requireContext(),"TODO: filter", Toast.LENGTH_LONG).show()
            R.id.adminFragment-> Toast.makeText(requireContext(),"TODO: adminFragment", Toast.LENGTH_LONG).show()
            R.id.logOut-> Toast.makeText(requireContext(),"TODO: logOut", Toast.LENGTH_LONG).show()

        }


        return true

    }


    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {

            Toast.makeText(requireActivity(), "Cancelled", Toast.LENGTH_LONG).show()

        } else {

            Toast.makeText(
                requireActivity(),
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()


            var producto:Producto

            viewModel.getProductoByBarCode(result.contents)
            viewModel.productoModel.observe(viewLifecycleOwner){
                    producto->
                        println(">>>> PRODUCTO")
                        println(producto)

                var barCodeFragment = BarCodeDetailDialogFragment().newInstance(

                    producto.id,
                    producto.nombre,
                    producto.precio,
                    producto.descripcion,
                    producto.imageUrl,
                    producto.inventario,
                    producto.cod_barras

                )

                barCodeFragment.show(childFragmentManager, "BarCodeDetailDialogFragment")



            }

        }
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMapReady(p0: GoogleMap) {
        var name = "Tienda Don Emilio"
        var address = "Rionegro, Antioquia"
        var latitude = 4.636107
        var longitude = -74.0848331
        var zoom = 15f

        var centerMap = LatLng(latitude, longitude)
        p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))


        val marker = p0.addMarker(
            MarkerOptions()
                .title(name)
                .position(centerMap)
        )


    }
}