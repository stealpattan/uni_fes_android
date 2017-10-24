package ccm.uni_fes_app;

import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.content.Context;
import okhttp3.*;

class getImage extends AsyncTask<String,Void,Bitmap>{
    private ImageView imgv;
    private Context ctx;
    public getImage(ImageView imgv, Context ctx){
        this.imgv = imgv;
        this.ctx = ctx;
    }
    @Override
    protected Bitmap doInBackground(String... params){
        synchronized(this.ctx){
            try{
                OkHttpClient client = new OkHttpClient();
                Request req = new Request.Builder().url(params[0]).get().build();
                Response res = client.newCall(req).execute();
                Bitmap image = null;
                image = BitmapFactory.decodeStream(res.body().byteStream());
                return image;
            }catch(Exception e){
                Log.e("error","error",e);
                return null;
            }
        }
    }
    @Override
    protected void onPostExecute(Bitmap pic){
        if(pic != null) {
            imgv.setImageBitmap(pic);
        }
    }
}
