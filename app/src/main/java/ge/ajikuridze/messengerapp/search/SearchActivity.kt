package ge.ajikuridze.messengerapp.search

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.chat.ChatActivity
import ge.ajikuridze.messengerapp.models.Account
import java.io.File
import java.io.InputStream
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), ISearchView, SearchListItemListener {

    private lateinit var searchList: RecyclerView
    private lateinit var listAdapter: SearchListAdapter
    private var data: ArrayList<Account> = arrayListOf()
    private lateinit var searchField: EditText
    private lateinit var backButton: ImageButton
    private lateinit var progressBar: ProgressBar

    private lateinit var loader: Any

    private var presenter: ISearchPresenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchList = findViewById(R.id.search_list)
        searchField = findViewById(R.id.search_field)
        backButton = findViewById(R.id.back_button)
        progressBar = findViewById(R.id.search_progress_bar)

        listAdapter = SearchListAdapter(this, this, arrayListOf())
        searchList.adapter = listAdapter

        setListeners()

        disableLoader()
        // init initial view
    }

    override fun filteredAccounts(data: ArrayList<Account>) {
        this.data = data
        listAdapter.updateData(data)
        disableLoader()
    }


    fun setListeners() {
        backButton.setOnClickListener {
            finish()
        }

        searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,  before: Int, count: Int) {
                data = arrayListOf()
                listAdapter.updateData(arrayListOf())
                if (s.length >= 3) {
                    presenter.filterAccounts(s.toString())
                    showLoader()
                } else {
                    disableLoader()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun showLoader() {
        progressBar.visibility = View.VISIBLE

    }

    fun disableLoader() {
        progressBar.visibility = View.INVISIBLE
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    override fun accountClicked(acc: Account) {
        ChatActivity.start(this, acc.id!!)
    }

    override fun viewBinded(position: Int, acc: Account) {
        if (acc.avatarUri == null) {
            presenter.fetchAvatarOf(acc.id!!)
        }
    }

    override fun avatarFetched(file: File?, id: String) {
        val localUri: Uri?
        if (file != null) {
            localUri = Uri.fromFile(file)
        } else {
            localUri = Utils.getUriToDrawable(this, R.drawable.avatar_image_placeholder)
        }

        for (i in data.indices) {
            if (data[i].id!! == id) {
                data[i].avatarUri = localUri
                listAdapter.updateItem(data[i], i)
            }
        }
    }

}