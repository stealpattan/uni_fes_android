package ccm.uni_fes_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class listAdapter2 extends ArrayAdapter<listItem>{
    private int layout;
    private List<listItem> Items;
    private LayoutInflater inflater;

    public listAdapter2(Context context, int layout, List<listItem> items){
        super(context, layout, items);
        this.layout = layout;
        // items have "lisetext" and "thumbneil"
        Items = items;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        //set view
        if(convertView != null){
            view = convertView;
        }
        else{
            view = inflater.inflate(layout, null);
        }
        //get list items
        listItem li = Items.get(position);
        //widget initialize
        TextView tv = (TextView)view.findViewById(R.id.textview1);
        TextView tv2 = (TextView)view.findViewById(R.id.textview2);
        //set item
        tv.setText(li.getText());
        tv2.setText(li.getText2());

        return view;
    }
}
