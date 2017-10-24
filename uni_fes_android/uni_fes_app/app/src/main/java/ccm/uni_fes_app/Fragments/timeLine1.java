package ccm.uni_fes_app.Fragments;

import ccm.uni_fes_app.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Log
import android.util.Log;

//List View
import java.util.ArrayList;
import android.widget.ListView;

import ccm.uni_fes_app.listItem;
import ccm.uni_fes_app.listAdapter2;

//json
import org.json.JSONArray;

public class timeLine1 extends Fragment{
    private String json = "";
    public timeLine1(String json){
        this.json = json;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.time_line1, null);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        ArrayList<listItem> al = new ArrayList<listItem>();
        try{
            JSONArray jsonarray = new JSONArray(this.json);
            int amount = jsonarray.length();
            for(int i=0;i<amount;i++){
                if(jsonarray.getJSONObject(i).getString("date").equals("28")) {
                    listItem li = new listItem();
                    li.setText(jsonarray.getJSONObject(i).getString("title"));
                    li.setText2(jsonarray.getJSONObject(i).getString("time"));
                    al.add(li);
                }
            }
        }catch(Exception e){
            Log.e("error","error",e);
        }
        listAdapter2 aa = new listAdapter2(this.getContext(),R.layout.list_content,al);
        ListView lv = (ListView)v.findViewById(R.id.listView);
        lv.setAdapter(aa);
    }
}
