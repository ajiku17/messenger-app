package ge.ajikuridze.messengerapp.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.conversations.ConversationsListAdapter
import ge.ajikuridze.messengerapp.conversations.ConversationsPresenter
import ge.ajikuridze.messengerapp.conversations.IConversationsPresenter
import ge.ajikuridze.messengerapp.models.Account
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), ISearchView {

    private lateinit var searchList: RecyclerView
    private lateinit var listAdapter: SearchListAdapter
    private lateinit var searchField: EditText
    private lateinit var backButton: Button

    private lateinit var loader: Any

    private var presenter: ISearchPresenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchList = findViewById(R.id.search_list)
        searchField = findViewById(R.id.search_field)
        backButton = findViewById(R.id.back_button)

        listAdapter = SearchListAdapter(arrayListOf())
        searchList.adapter = listAdapter

        setListeners()

        // init initial view
    }

    override fun filteredAccounts(data: ArrayList<Account>) {
        disableLoader()
        listAdapter.updateData(data)
    }


    fun setListeners() {
        backButton.setOnClickListener {
            finish()
        }

        searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,  before: Int, count: Int) {
                if (s.length >= 3) {
                    presenter.filterAccounts(s.toString())
                }
                showLoader()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun showLoader() {

    }

    fun disableLoader() {

    }


}