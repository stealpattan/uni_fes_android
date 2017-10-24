package ccm.uni_fes_app

import android.os.Bundle
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager

//widget
import android.widget.Toast
import android.widget.ImageButton
//change display
import android.content.Intent
//Pager Adapter
import ccm.uni_fes_app.uniFesPagerAdapter
//log
import android.util.Log
//http connection
import okhttp3.*

class schedule: FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        val pager = findViewById(R.id.pager) as ViewPager
        var json_str = "" as String

        //set swipe action
        try {
            object: getJson(){
                override fun doInBackground(vararg param: Void):String?{
                    return run()
                }
                override fun onPostExecute(json: String){
                    json_str = json
                    pager.setAdapter(uniFesPagerAdapter(getSupportFragmentManager(), json_str))
                }
            }.execute()
        }catch(e: Exception){
            Log.e("error","error",e)
        }
        //under menu below
        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton

        //under menu bar
        schedulebutton.setImageResource(R.drawable.schedule)
        homebutton.setOnClickListener{
            v->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        shopbutton.setOnClickListener{
            v->
            val intent = Intent(this,recommend_shops::class.java)
            startActivity(intent)
        }
        mapbutton.setOnClickListener{
            v->
            val intent = Intent(this,mapPage::class.java)
            startActivity(intent)
        }

    }
    fun makeToast(str: String?){
        val toastString = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        toastString.show()
    }
    fun run():String?{
        try {
            val client = OkHttpClient() as OkHttpClient
            val req = Request.Builder().url("http://ytrw3xix.0g0.jp/app2017/schedule").get().build()
            val res = client.newCall(req).execute()
            return res.body()?.string()
        }catch(e: Exception){
            return null
        }
    }
}

open class getJson: AsyncTask<Void,Void,String>(){
    override fun doInBackground(vararg param:Void):String?{
        return null
    }
    override fun onPostExecute(json: String){}
}