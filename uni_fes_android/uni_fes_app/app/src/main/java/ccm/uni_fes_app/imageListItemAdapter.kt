package ccm.uni_fes_app

import android.content.Context
import android.widget.ArrayAdapter
import java.util.ArrayList
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.ImageView

class imageListItemAdapter(context: Context, layout: Int, listitems: ArrayList<imageListItem>): ArrayAdapter<imageListItem>(context,layout,listitems){
    //set value on private variable
    private val layout = layout
    private val listitems = listitems
    //initialize
    private val inflater: LayoutInflater
    init{
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    //constructor above
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View{
        //set view initialization
        val view: View
        if(convertView != null){
            view = convertView
        }
        else{
            view = inflater.inflate(this.layout, null)
        }
        //widget initialize here
        val image = view.findViewById(R.id.list_image) as ImageView
        val text = view.findViewById(R.id.list_text) as TextView
        //initialize and get data
        val listiteretar = listitems.get(position)
        image.setImageBitmap(listiteretar.getImageData())
        text.setText(listiteretar.getText())
        return view
    }
}