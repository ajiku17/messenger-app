package ge.ajikuridze.messengerapp.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Account

class SearchListAdapter(var data: ArrayList<Account>): RecyclerView.Adapter<SearchListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListItemViewHolder, position: Int) {
        val acc = data[position]

        // init holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: ArrayList<Account>) {
        data = newData
        notifyDataSetChanged()
    }

}

class SearchListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var avatar: EditText = view.findViewById(R.id.search_item_avatar)
    var name: TextView = view.findViewById(R.id.search_item_name)
    var profession: TextView = view.findViewById(R.id.search_item_profession)
}