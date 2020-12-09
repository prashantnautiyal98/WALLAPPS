package com.example.wallappy

import android.app.DownloadManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.list_single_item.view.*

class WallpaperListAdapter(var wallpaperList:List<WallpaperModels>, private val clickListener: (WallpaperModels) -> Unit) : RecyclerView.Adapter<WallpaperListAdapter.WallpapersViewHolder>() {

    class WallpapersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

fun bind(wallpapers: WallpaperModels,clickListener: (WallpaperModels)->Unit){
    //load the image

       Glide.with(itemView.context).load(wallpapers.image).listener(
        object  :RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                itemView.list_single_progress.visibility=View.GONE
                return false
            }


        }

       ).into(itemView.list_single_image)
    //click listner
      itemView.setOnClickListener {
      clickListener(wallpapers)
       }
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersViewHolder
    {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.list_single_item,parent,false)
        return WallpapersViewHolder(view)

    }

    override fun onBindViewHolder(holder: WallpapersViewHolder, position: Int) {

        (holder as WallpapersViewHolder ).bind(wallpaperList[position],clickListener)
    }

    override fun getItemCount(): Int {

        return wallpaperList.size
    }


}