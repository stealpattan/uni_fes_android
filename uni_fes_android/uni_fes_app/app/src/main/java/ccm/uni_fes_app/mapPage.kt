package ccm.uni_fes_app

import android.os.Bundle
import android.support.v4.app.FragmentActivity
//widget
import android.widget.ImageButton
//change activity
import android.content.Intent

class mapPage : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_page)

        //under menu below
        val homebutton = findViewById(R.id.homebutton) as ImageButton
        val schedulebutton = findViewById(R.id.schedulebutton) as ImageButton
        val shopbutton = findViewById(R.id.shopbutton) as ImageButton
        val mapbutton = findViewById(R.id.mapbutton) as ImageButton

        //under menu bar
        mapbutton.setImageResource(R.drawable.map)
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
        shopbutton.setOnClickListener{
            v->
            val intent = Intent(this,recommend_shops::class.java)
            startActivity(intent)
        }
    }
}