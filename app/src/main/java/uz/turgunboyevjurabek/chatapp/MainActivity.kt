package uz.turgunboyevjurabek.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import uz.turgunboyevjurabek.chatapp.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.chatapp.madels.binding.AppBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppBinding.appBinding=binding

        val naviget=findNavController(R.id.my_navigation_host)
        binding.btnChat.setOnClickListener {
            naviget.navigate(R.id.chatFragment)
            binding.btnChat.alpha=1f
            binding.btnGroup.alpha=.35f
        }
        binding.btnGroup.setOnClickListener {
            naviget.navigate(R.id.groupFragment)
            binding.btnChat.alpha=.35f
            binding.btnGroup.alpha=1f
        }
    }
}