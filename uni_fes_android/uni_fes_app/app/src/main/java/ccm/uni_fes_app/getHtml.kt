package ccm.uni_fes_app

//library here
import android.os.AsyncTask

open class getHtml: AsyncTask<Void,Void,String>(){
    override fun doInBackground(vararg params: Void): String?{
        return null
    }

    override fun onPostExecute(text: String){}
}