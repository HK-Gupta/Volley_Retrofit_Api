package com.example.api_volley

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DetailsAdapter(
    val context: Context,
    val userInfo: UserInfo
): RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userInfo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(userInfo[position].avatar_url).into(holder.userImage)
        holder.userName.text = userInfo[position].login

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val userImage: ImageView = itemView.findViewById(R.id.admin_pic)
        val userName: TextView = itemView.findViewById(R.id.admin_name)


    }
}