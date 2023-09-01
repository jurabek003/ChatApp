package uz.turgunboyevjurabek.chatapp.fragment

import android.content.ContentProviderClient
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import uz.turgunboyevjurabek.chatapp.R
import uz.turgunboyevjurabek.chatapp.databinding.FragmentSignInBinding
import uz.turgunboyevjurabek.chatapp.madels.binding.AppBinding

class SignInFragment : Fragment() {
    lateinit var auth:FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient
    private val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
        googleSignInClient= GoogleSignIn.getClient(requireContext(),gso)
        auth= FirebaseAuth.getInstance()

        binding.signButton.setOnClickListener {
            signIn()
        }

        return binding.root
    }
    private fun signIn(){
        val signIntent=googleSignInClient.signInIntent
        startActivityForResult(signIntent,1)
    }

    override fun onResume() {
        super.onResume()
        AppBinding.appBinding.btnGroup.visibility=View.GONE
        AppBinding.appBinding.btnChat.visibility=View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            val tack=GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account=tack.getResult(ApiException::class.java)
                Log.d(TAG,"firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken)
            }catch (e:ApiException){
                Log.d(TAG,"Google sign in failed")
            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken:String?){
        val credential=GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { tack->
                if (tack.isSuccessful){
                    Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"isSuccessful:")
                }else{
                    Toast.makeText(requireContext(), "No successful", Toast.LENGTH_SHORT).show()
                }
            }
    }

}