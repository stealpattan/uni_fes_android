package ccm.uni_fes_app

import android.os.Bundle
import android.os.AsyncTask
//add widget library
import android.widget.TextView
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast
import android.widget.ListView
//Use http connection
import okhttp3.*
//use show log
import android.util.Log
//Fragment manager
import ccm.uni_fes_app.uniFesPagerAdapter
//Use Pager Adapter
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
//change activity
import android.content.Intent
//Array list
import java.util.ArrayList
//Array adapter, be used listView
import android.widget.ArrayAdapter
//Lunch other app
import android.net.Uri
//Use Bitmap -> grahics -> picture
import android.graphics.Bitmap
import android.graphics.BitmapFactory
//Json Parse
import org.json.JSONObject
import org.json.JSONArray
//web view
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //widget initialize
        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton
        val seeAll = findViewById(R.id.allnewsbutton) as ImageButton
        val newsimage1 = findViewById(R.id.newsimage1) as ImageButton
        val newsimage2 = findViewById(R.id.newsimage2) as ImageButton
        val newsimage3 = findViewById(R.id.newsimage3) as ImageButton
        val news1 = findViewById(R.id.news1) as TextView
        val news2 = findViewById(R.id.news2) as TextView
        val news3 = findViewById(R.id.news3) as TextView
        val twitterbutton = findViewById(R.id.sendcomment) as Button

        //TPUMarker
        try{
            var json_string = "" as String
            object: MyAsyncTack(){
                override fun doInBackground(vararg param: Void):String?{
                    return run()
                }
                override fun onPostExecute(text: String){
                    json_string = text
                    //news buttons
                    seeAll.setOnClickListener{
                        v->
                        val intent = Intent(getApplication(), allNews::class.java)
                        intent.putExtra("json",json_string)
                        startActivity(intent)
                    }
                    //json parse
                    val jsonarray = JSONArray(json_string) as JSONArray
                    val jsonlist = arrayOf(jsonarray.getJSONObject(0),jsonarray.getJSONObject(1),jsonarray.getJSONObject(2))
                    var picturelist: Array<Bitmap?> = arrayOfNulls(3)
                    var i = 0
                    object: getPicture(){
                        override fun doInBackground(vararg param: Void):Bitmap?{
                            return runPic(jsonlist[0].getString("picture"))
                        }
                        override fun onPostExecute(pic: Bitmap){
                            newsimage1.setImageBitmap(pic)
                            news1.setText(jsonlist[0].getString("title"))
                            newsimage1.setOnClickListener{
                                v->
                                val uri = Uri.parse(jsonlist[0].getString("link"))
                                val intent = Intent(Intent.ACTION_VIEW,uri)
                                startActivity(intent)
                            }
                        }
                    }.execute()
                    object: getPicture(){
                        override fun doInBackground(vararg param: Void):Bitmap?{
                            return runPic(jsonlist[1].getString("picture"))
                        }
                        override fun onPostExecute(pic: Bitmap){
                            newsimage2.setImageBitmap(pic)
                            news2.setText(jsonlist[1].getString("title"))
                            newsimage2.setOnClickListener{
                                v->
                                val uri = Uri.parse(jsonlist[1].getString("link"))
                                val intent = Intent(Intent.ACTION_VIEW,uri)
                                startActivity(intent)
                            }
                        }
                    }.execute()
                    object: getPicture(){
                        override fun doInBackground(vararg param: Void):Bitmap?{
                            return runPic(jsonlist[2].getString("picture"))
                        }
                        override fun onPostExecute(pic: Bitmap){
                            newsimage3.setImageBitmap(pic)
                            news3.setText(jsonlist[2].getString("title"))
                            newsimage3.setOnClickListener{
                                v->
                                val uri = Uri.parse(jsonlist[2].getString("link"))
                                val intent = Intent(Intent.ACTION_VIEW,uri)
                                startActivity(intent)
                            }
                        }
                    }.execute()
                }
            }.execute()
        }catch(e: Exception){
            Log.e("error","error",e)
        }

        //twitter View
        val wv = findViewById(R.id.twitterview) as WebView
        val client = WebViewClient() as WebViewClient
        wv.setWebViewClient(client)
        wv.loadUrl("https://twitter.com/search?q=%23soccer")
        wv.getSettings().setJavaScriptEnabled(true)

        //set twitter button
        twitterbutton.setOnClickListener{
            v->
            val uri = Uri.parse("https://twitter.com") as Uri
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

        //under menu
        homebutton.setImageResource(R.drawable.home)
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
        //under menu above
    }
    fun makeToast(text: String){
        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast.show()
    }
    //get TPUMarkar data
    fun run():String?{
        try{
            val client = OkHttpClient() as OkHttpClient
            val req = Request.Builder().url("http://ytrw3xix.0g0.jp/app2017/feed").get().build()
            val res = client.newCall(req).execute()
            return res.body()?.string()
        }catch(e: Exception){
            return "Failed to get datas"
        }
    }
    fun runPic(urlstr: String):Bitmap?{
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

open class MyAsyncTack: AsyncTask<Void,Void,String>(){
    override fun doInBackground(vararg param: Void):String?{
        return null
    }
    override fun onPostExecute(text: String){}
}

open class getPicture: AsyncTask<Void,Void,Bitmap>(){
    override fun doInBackground(vararg param: Void):Bitmap?{
        return null
    }
    override fun onPostExecute(pic: Bitmap){}
}