package ge.ajikuridze.messengerapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.ajikuridze.messengerapp.conversations.ConversationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavBar()
    }

    fun initNavBar() {
        navBar = findViewById(R.id.bottom_navigation_view)
        navBar.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_item_profile) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, ProfileFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, ConversationsFragment())
                    .commit()
            }

            true
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}