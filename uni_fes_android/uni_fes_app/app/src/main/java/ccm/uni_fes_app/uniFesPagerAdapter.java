package ccm.uni_fes_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ccm.uni_fes_app.Fragments.timeLine0;
import ccm.uni_fes_app.Fragments.timeLine1;
import ccm.uni_fes_app.Fragments.timeLine2;

//json parse


public class uniFesPagerAdapter extends FragmentStatePagerAdapter{
    String json = "";
    public uniFesPagerAdapter(FragmentManager fm, String json){
        super(fm);
        this.json = json;
    }
    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
                return new timeLine0(json);
            case 1:
                return new timeLine1(json);
            case 2:
                return new timeLine2(json);
            default:
                return new timeLine0(json);
        }
    }
    @Override
    public int getCount(){
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "10/27(前夜祭)";
            case 1:
                return "10/28";
            case 2:
                return "10/29";
            default:
                return "page" + position;
        }
    }
}
