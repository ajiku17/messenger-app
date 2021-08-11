package ge.ajikuridze.messengerapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ge.ajikuridze.messengerapp.conversations.ConversationsFragment
import ge.ajikuridze.messengerapp.profile.ProfileFragment
import ge.ajikuridze.messengerapp.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var navBar: BottomNavigationView
    private lateinit var newButton: FloatingActionButton

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

        navBar.selectedItemId = R.id.nav_item_home

        newButton = findViewById(R.id.new_button)

        newButton.setOnClickListener {
            SearchActivity.start(this)
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}