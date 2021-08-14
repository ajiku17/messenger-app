package ge.ajikuridze.messengerapp.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Account

class SearchListAdapter(private val listener: SearchListItemListener,
                        var data: ArrayList<Account>): RecyclerView.Adapter<SearchListItemViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListItemViewHolder, position: Int) {
        val acc = data[position]

        // init holder
        holder.name.text = acc.name
        holder.profession.text = acc.profession
        holder.avatar.visibility = View.INVISIBLE
        holder.loader.visibility = View.VISIBLE

        if (acc.avatarBitmap != null) {
            holder.avatar.setImageBitmap(acc.avatarBitmap)
            holder.avatar.visibility = View.VISIBLE
            holder.loader.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            listener.accountClicked(acc)
        }

        listener.viewBinded(position, acc)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: ArrayList<Account>) {
        data = newData
        notifyDataSetChanged()
    }

    fun updateItem(acc: Account, position: Int) {
        data[position] = acc
        notifyItemChanged(position)
    }

}

class SearchListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var avatar: ImageView = view.findViewById(R.id.search_item_avatar)
    var name: TextView = view.findViewById(R.id.search_item_name)
    var profession: TextView = view.findViewById(R.id.search_item_profession)
    var loader: ProgressBar = view.findViewById(R.id.search_item_avatar_progress_bar)
}