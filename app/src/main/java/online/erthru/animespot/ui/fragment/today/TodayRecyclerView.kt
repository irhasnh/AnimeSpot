package online.erthru.animespot.ui.fragment.today

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_today.view.*
import online.erthru.animespot.R
import online.erthru.animespot.network.model.Day
import online.erthru.animespot.ui.activity.anime.AnimeActivity
import java.lang.StringBuilder

class TodayRecyclerView(val context: TodayFragment, val arrayList: ArrayList<Day>?) : RecyclerView.Adapter<TodayRecyclerView.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_today,parent,false))
    }

    override fun getItemCount(): Int = arrayList?.size ?:0

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val today = arrayList?.get(position)
        holder.v.tvTitleLT.text = today?.title
        val timeReverse = StringBuilder(today?.airing_start).reverse().substring(0,10)
        val timeFix = StringBuilder(timeReverse).reverse()
        if(!timeFix.contains("JST")){
            holder.v.tvTimeLT.text = "Time: Unknown"
        }else{
            holder.v.tvTimeLT.text = "Time: "+timeFix
        }
        Glide.with(context).load(today?.image_url).into(holder.v.imgLT)

        holder.v.setOnClickListener {
            val i = Intent(context.activity,AnimeActivity::class.java)
            i.putExtra("id",today?.mal_id.toString())
            context.startActivity(i)
        }

    }


    class Holder(val v:View) : RecyclerView.ViewHolder(v)

}