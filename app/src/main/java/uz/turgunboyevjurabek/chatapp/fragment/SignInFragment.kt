package uz.turgunboyevjurabek.chatapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.chatapp.R
import uz.turgunboyevjurabek.chatapp.madels.binding.AppBinding

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onResume() {
        super.onResume()
        AppBinding.appBinding.btnGroup.visibility=View.GONE
        AppBinding.appBinding.btnChat.visibility=View.GONE
    }
}