package ccm.uni_fes_app;

import android.graphics.Bitmap;

public class listItem {
    private String listText = null;
    private Bitmap thumb = null;
    private String listText2 = null;
    private String url = "";
    private String aurl = "";
    private String id = "";
    private boolean b = false;

    public listItem(){};

    public listItem(Bitmap thumb, String listText, String time){
        this.listText = listText;
        this.thumb = thumb;
        this.listText2 = time;
    }

    public listItem(String string, String string2){
        this.listText = string;
        this.listText2 = string2;
    }
    public listItem(String url, String listText, String listText2){
        this.listText = listText;
        this.listText2 = listText2;
        this.url = url;
    }
    public void setThumb(Bitmap thumb){
        this.thumb = thumb;
    }
    public void setText(String listText){
        this.listText = listText;
    }
    public void setText2(String listText2){this.listText2 = listText2;}
    public void setUrl(String url){this.url = url;}
    public void setArticleUrl(String aurl){this.aurl = aurl;}
    public void setID(String id){this.id = id;}
    public void setPIC(boolean b){this.b = b;}
    public Bitmap getThumb(){
        return this.thumb;
    }
    public String getText(){
        return this.listText;
    }
    public String getText2(){return this.listText2;}
    public String getUrl(){return this.url;}
    public String getArticleUrl(){return this.aurl;}
    public String getID(){return this.id;}
    public boolean getPIC(){return this.b;}
}
