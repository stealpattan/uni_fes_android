package ccm.uni_fes_app

import android.app.Activity
import android.os.Bundle
//widget
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ImageView
//change activity
import android.content.Intent
import android.content.Context
import android.os.AsyncTask
//json library
import org.json.JSONObject
import org.json.JSONArray
//use image
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request

class detail_shops: Activity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_detail)

        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton
        val titletext = findViewById(R.id.detail_title) as TextView
        val contenttext = findViewById(R.id.detail_text) as TextView
        val teamimage = findViewById(R.id.teamimage) as ImageView

        val intent = getIntent()
        val json_str = intent.getStringExtra("json")

        teamimage.setImageResource(R.drawable.damy)

        try{
            val jsonarray = JSONArray(json_str) as JSONArray
            var i = 0
            val amount = jsonarray.length() as Int
            while(i < amount){
                val id_str = jsonarray.getJSONObject(i).getString("id")
                if(id_str == intent.getStringExtra("id")){
                    titletext.setText(jsonarray.getJSONObject(i).getString("stname"))

                    object: getPic(){
                        override fun doInBackground(vararg param: Void):Bitmap?{
                            return run(jsonarray.getJSONObject(i).getString("picture"))
                        }
                        override fun onPostExecute(pic: Bitmap){
                            teamimage.setImageBitmap(pic)
                        }
                    }.execute()
                    break
                }
                i++
            }
        }catch(e: Exception){
            Log.e("error","error",e)
        }
        //under menu bar
        shopbutton.setImageResource(R.drawable.shop)
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
    }
    fun run(urlstr: String):Bitmap?{
        try{
            val client = OkHttpClient() as OkHttpClient
            val req = Request.Builder().url(urlstr).get().build()
            val res = client.newCall(req).execute()
            var bitmapimage = null as Bitmap?
            bitmapimage = BitmapFactory.decodeStream(res.body()?.byteStream())
            return bitmapimage
        }catch(e: Exception){
            Log.e("error","error",e)
            return null
        }
    }
}


open class getPic: AsyncTask<Void, Void, Bitmap>(){
    override fun doInBackground(vararg param: Void): Bitmap?{
        return null
    }
    override fun onPostExecute(pic: Bitmap){}
}