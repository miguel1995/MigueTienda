package com.miatorresch.miguetienda.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import com.miatorresch.miguetienda.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TelefonoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TelefonoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var teleTextPhone:TextView
    lateinit var btnEnvioMsm:Button
    lateinit var teleTextCodigo:TextView
    lateinit var btnValidar:Button

    private lateinit var auth: FirebaseAuth

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    lateinit var storedVerificationId:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        auth = Firebase.auth


        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.


                //Log.d(TAG, "onVerificationCompleted:$credential")
                //signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                var resendToken = token

                println(">> Envio un mensaje MSM")
                println(verificationId)
                println(token)



            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_telefono, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teleTextPhone = view.findViewById(R.id.teleTextPhone)
        btnEnvioMsm = view.findViewById(R.id.btnEnvioMsm)
        teleTextCodigo = view.findViewById(R.id.teleTextCodigo)
        btnValidar = view.findViewById(R.id.btnValidar)

        btnEnvioMsm.setOnClickListener {
            view:View ->
              println(">> Estoy en btnEnvioMsm")

            startPhoneNumberVerification(teleTextPhone.text.toString())



        }
        btnValidar.setOnClickListener {
            view:View ->
              println(">> Estoy en btnValidar")

            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, teleTextCodigo.text.toString())
            println(">>> Credentials")
            println(credential);
            signInWithPhoneAuthCredential(view, credential)

        }


    }

    private fun signInWithPhoneAuthCredential(view:View, credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information


                    val user = task.result?.user
                    println(">>> INGRESO CON TELEGONO EXITOSO")
                    view.findNavController().navigate(R.id.action_telefonoFragment_to_homeActivity)


                } else {
                    // Sign in failed, display a message and update the UI

                    println(">>> INGRESO CON TELEGONO FALLIDO")

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid

                    }
                    // Update UI
                }
            }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        auth.setLanguageCode("es")
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TelefonoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TelefonoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}