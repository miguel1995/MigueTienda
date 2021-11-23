package com.miatorresch.miguetienda.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miatorresch.miguetienda.R
import com.miatorresch.miguetienda.databinding.FragmentCommentsBinding
import com.miatorresch.miguetienda.model.Comentario
import com.miatorresch.miguetienda.view.adapter.ComentarioAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var comentarios = listOf(

        Comentario("Harol1",5, "La mejor Tienda del mundo"),
        Comentario("Harol2",5, "La mejor Tienda del mundo"),
        Comentario("Harol3",5, "La mejor Tienda del mundo"),
        Comentario("Harol4",5, "La mejor Tienda del mundo"),
        Comentario("Harol5",5, "La mejor Tienda del mundo"),
        Comentario("Harol6",5, "La mejor Tienda del mundo"),
        Comentario("Harol7",5, "La mejor Tienda del mundo"),
        Comentario("Harol7",5, "La mejor Tienda del mundo"),
        Comentario("Harol8",5, "La mejor Tienda del mundo"),
        Comentario("Harol10",5, "La mejor Tienda del mundo")

    )


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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //var rvComments = view.findViewById<RecyclerView>(R.id.rvComments)

        val binding = FragmentCommentsBinding.bind(view)

        binding.rvComments.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = ComentarioAdapter(comentarios)
        binding.rvComments.adapter = adapter

            super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}