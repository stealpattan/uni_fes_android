package ccm.uni_fes_app

import android.app.Activity
import android.os.Bundle
import android.os.AsyncTask

//widget
import android.widget.ImageButton
import android.widget.Button
import android.widget.TextView
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.AdapterView
//ArrayList (be used by ListView)
import java.util.ArrayList
//Change Activity
import android.content.Intent
//Change image(imageView)
import android.graphics.Bitmap
import android.graphics.BitmapFactory
//OkHttpClient
import okhttp3.*
//JSON parser
import org.json.JSONObject
import org.json.JSONArray
//use Log
import android.util.Log
import android.net.Uri

class allNews : Activity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_news)
        //widget initialize here
        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton
        val al = ArrayList<Int>() as ArrayList<Int>
        val titletext = findViewById(R.id.titletext) as TextView
        val lv = findViewById(R.id.newslist) as ListView

        //TPUMarker json parse
        val intent = getIntent()
        try{
            val jsonarray = JSONArray(intent.getStringExtra("json"))
            val amount = jsonarray.length()
            val list_content = ArrayList<listItem>() as ArrayList<listItem>
            val list_adapter = listAdapter(this, R.layout.list_with_image, list_content)
            var i = 0
            while(i < amount){
                val li = listItem() as listItem
                li.setText(jsonarray.getJSONObject(i).getString("title"))
                li.setUrl(jsonarray.getJSONObject(i).getString("picture"))
                li.setArticleUrl(jsonarray.getJSONObject(i).getString("link"))
                list_content.add(li)
                i++
            }
            list_adapter.notifyDataSetChanged()
            lv.setAdapter(list_adapter)
            lv.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, pos, id->
                var item_iterator = listItem() as listItem
                item_iterator = list_content.get(pos)
                var uri = Uri.parse(item_iterator.getArticleUrl()) as Uri
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }catch(e: Exception){
            Log.e("error","error",e)
        }

        //under menu bar
        homebutton.setOnClickListener{
            v->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        schedulebutton.setOnClickListener{
            v->
            val intent = Intent(this, schedule::class.java)
            startActivity(intent)
        }
        shopbutton.setOnClickListener{
            v->
            val intent = Intent(this, recommend_shops::class.java)
            startActivity(intent)
        }
        mapbutton.setOnClickListener{
            v->
            val intent = Intent(this, mapPage::class.java)
            startActivity(intent)
        }
//        var x = 0
//        //Array that is set listView
//        val array = ArrayList<listItem>() as ArrayList<listItem> // array
//        //test bitmap data -> be used by imageView
//        val thumbdata = BitmapFactory.decodeResource(getResources(), R.drawable.home_selected) as Bitmap
//        val thumbdata2 = BitmapFactory.decodeResource(getResources(), R.drawable.schedule_selected) as Bitmap
//        //set datas
//        while(x<10){
//            if(x != 2) {
//                val items = listItem() as listItem
//                items.setThumb(thumbdata)
//                items.setText("sample")
//                items.setText2("投稿時間:" + "sample")
//                array.add(items)
//            }
//            else{
//                val items = listItem() as listItem
//                items.setThumb(thumbdata2)
//                items.setText("hoge")
//                items.setText2("投稿時間:" + "hoge")
//                array.add(items)
//            }
//            x++
//        }
//        //set adapter
//        val la = listAdapter(this, R.layout.list_with_image, array) as listAdapter
//        lv.setAdapter(la)
    }
}
