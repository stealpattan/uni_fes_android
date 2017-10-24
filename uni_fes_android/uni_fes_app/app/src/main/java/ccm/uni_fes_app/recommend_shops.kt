package ccm.uni_fes_app

import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.os.AsyncTask
//ArrayList
import java.util.ArrayList
//Array Adapter be used when you use listView
import android.widget.ArrayAdapter
//widget
import android.widget.ListView
import android.widget.ImageButton
import android.view.View
import android.widget.AdapterView
//change Activity
import android.content.Intent
//json parse
import org.json.JSONObject
import org.json.JSONArray
//Log
import android.util.Log
//http connection
import okhttp3.*

class recommend_shops : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend_shops)

        //widget initialize
        val listView = findViewById(R.id.listView) as ListView

        val arrayList = ArrayList<listItem>() as ArrayList<listItem>
        var x = 0 as Int
        var json_str = "" as String?
        object: getShopData(){
            override fun doInBackground(vararg param: Void):String?{
                return getter()
            }
            override fun onPostExecute(json: String){
                json_str = json
                val jsonarray = JSONArray(json_str) as JSONArray
                var i = 0;
                val arraylong = jsonarray.length()
                try {
                    while (i < arraylong) {
                        val listitem = listItem() as listItem
                        listitem.setText("team:  " + jsonarray.getJSONObject(i).getString("stname"))
//                        listitem.setText2("place:  " + jsonarray.getJSONObject(i).getString("location"))
                        listitem.setID(jsonarray.getJSONObject(i).getString("id"))
                        arrayList.add(listitem)
                        i++
                    }
                    val arrayAdapter = listAdapter2(getApplication(),R.layout.list_content,arrayList) as listAdapter2
                    listView.setAdapter(arrayAdapter)
                }catch(e:Exception){
                    Log.e("error","error",e)
                }
            }
        }.execute()
        //set click action
        listView.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, pos, id->
            var postcontent = listItem() as listItem
            postcontent = arrayList.get(pos)
            val intent = Intent(this, detail_shops::class.java)
            intent.putExtra("id", postcontent.getID())
            intent.putExtra("json", json_str)
            startActivity(intent)
        }

        //under menu below
        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton

        //under menu bar
        shopbutton.setImageResource(R.drawable.shop)
        homebutton.setOnClickListener{
            v->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        schedulebutton.setOnClickListener{
            v->
            val intent = Intent(this,schedule::class.java)
            startActivity(intent)
        }
        mapbutton.setOnClickListener{
            v->
            val intent = Intent(this,mapPage::class.java)
            startActivity(intent)
        }
    }
    fun getter():String?{
        try{
            val client = OkHttpClient() as OkHttpClient
            val req = Request.Builder().url("http://ytrw3xix.0g0.jp/app2017/stall").get().build()
            val res = client.newCall(req).execute()
            return res.body()?.string()
        }catch(e: Exception){
            Log.e("error","error",e)
            return "Faild get datas"
        }
    }
}
open class getShopData: AsyncTask<Void,Void,String>(){
    override fun doInBackground(vararg param: Void):String?{
        return null
    }
    override fun onPostExecute(json: String){}
}