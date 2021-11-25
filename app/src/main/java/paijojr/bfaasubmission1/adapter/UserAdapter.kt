package paijojr.bfaasubmission1.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import paijojr.bfaasubmission1.R
import paijojr.bfaasubmission1.model.UserModel

class UserAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val contexts: Context = holder.itemView.context
        val imageResource: Int = contexts.resources.getIdentifier(listUser[position].avatar, null, contexts.packageName)
        val res: Drawable? = ResourcesCompat.getDrawable(contexts.resources, imageResource, null)

        Glide.with(contexts)
            .load(res)
            .circleCrop()
            .into(holder.imageAvatar)
        holder.textLocation.text = listUser[position].location
        holder.textName.text = listUser[position].name

        holder.imageAvatar.setOnClickListener{ onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageAvatar: ImageView = itemView.findViewById(R.id.item_avatar)
        var textName: TextView = itemView.findViewById(R.id.item_text_name)
        var textLocation: TextView = itemView.findViewById(R.id.item_text_location)

    }

}