package com.miatorresch.miguetienda.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.miatorresch.miguetienda.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var btnInicio: Button
    private lateinit var btnRegistro: Button
    private lateinit var btnTelofono: ImageButton
    private lateinit var googleButton: SignInButton

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

            }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        email = view.findViewById(R.id.loginTextEmail)
        pass = view.findViewById(R.id.loginTextPassword)
        btnInicio = view.findViewById(R.id.loginButtonInicio)
        btnRegistro = view.findViewById(R.id.loginButtonRegistro)
        btnTelofono = view.findViewById(R.id.loginButtonTelefono)
        googleButton = view.findViewById(R.id.google_button)

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser !=null ){
            println(">> YA HAY UN USUARIO LOGUEADO")
            view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }else{
            println(">> NO HAY UN USUARIO LOGUEADO")
        }

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("47046962745-72nc49cds47a0juksherjja7s7q3hhe3.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        // [END config_signin]

        btnInicio.setOnClickListener {

            view:View ->
            println("BTN INICIO")

            signIn(view, email.text.toString(), pass.text.toString())

        }

        btnRegistro.setOnClickListener {
                view:View ->
            println("BTN REGISTRO")
            view.findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        btnTelofono.setOnClickListener {
            view:View->
                view.findNavController().navigate(R.id.action_loginFragment_to_telefonoFragment)

        }

        googleButton.setOnClickListener{

            view:View->
                println(">>> BTN de Google")
                signInGoogle()

        }



    }

    override fun onStart() {
        super.onStart()


    }


    fun signIn (view: View, email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    println(">>> LOGIN CON EXITO")
                    view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)



                } else {
                    // If sign in fails, display a message to the user.
                    println(">>> LOGIN CON FALLA")
                    Toast.makeText(requireContext().applicationContext, "Usario no Valido", Toast.LENGTH_LONG).show()


                }
            }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!

                println(">>> LOGIN CON GOOGLE EXITOSO")

                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                println(">>> LOGIN CON GOOGLE FALLIDO")            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    println(">>> LOGIN CON GOOGLE EXITOSO EN FIREBASE")
                    findNavController(this).navigate(R.id.action_loginFragment_to_homeActivity)

                } else {
                    // If sign in fails, display a message to the user.
                    println(">>> LOGIN CON GOOGLE FALLIDO EN FIREBASE")
                }
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}