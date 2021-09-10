package com.rajdeep.assesmentwebskitters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_items.view.*

class PictureAdapter(var photosModel: MutableList<PhotosModel>): RecyclerView.Adapter<PictureAdapter.MyViewHolder>()
{

    //var userFilterList = ArrayList<PhotosModel>(photosModel)

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)
        val txtAlbumId = itemView.albumId
        val txtTitle = itemView.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.picture_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitle.text = photosModel[position].title
        holder.txtAlbumId.text = photosModel[position].albumId

        Picasso.get()
            .load(photosModel[position].url)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photosModel.size
    }

    fun filterList(achiveMentFilteredList: ArrayList<PhotosModel>) {
        photosModel = achiveMentFilteredList
        notifyDataSetChanged()
    }

    /* fun getFilter(): Filter = object : Filter() {
         override fun performFiltering(constraint: CharSequence?): FilterResults {
             val filteredList = ArrayList<PhotosModel>()

             if (constraint == null || constraint.isEmpty()) {
                 filteredList.addAll(userFilterList)
             } else {
                 val filteredStrings: String = constraint.toString().toLowerCase().trim()

                 for (results: PhotosModel in userFilterList) {
                     val fullName = results.title
                     if (fullName != null) {
                         if (fullName.toLowerCase().contains(filteredStrings)) {
                             filteredList.add(results)
                         }
                     }
                 }
             }
             return FilterResults().apply { values = filteredList }
         }*/

        /*override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            photosModel.clear()
            photosModel.addAll(results?.values as ArrayList<PhotosModel>)
            notifyDataSetChanged()
        }*/
}
