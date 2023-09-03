package co.tiagoaguiar.course.instagram.post.view

import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R


class PictureAdapter(private val onClick: (Uri) -> Unit) :
    RecyclerView.Adapter<PictureAdapter.PostViewHolder>() {

    var items: List<Uri> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Uri) {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                itemView.context.contentResolver.loadThumbnail(image, Size(200, 200), null)
            } else {
                val wholeID =
                    DocumentsContract.getDocumentId(image)
                val id = wholeID!!.split(":").toTypedArray()[1]
                val imageId = id.toLong()
                MediaStore.Images.Thumbnails.getThumbnail(
                    itemView.context.contentResolver,
                    imageId,
                    MediaStore.Images.Thumbnails.MINI_KIND,
                    null
                )
            }
            itemView.findViewById<ImageView>(R.id.item_profile_img_grid).setImageBitmap(bitmap)
            itemView.setOnClickListener {
                onClick.invoke(image)
            }
        }
    }
}