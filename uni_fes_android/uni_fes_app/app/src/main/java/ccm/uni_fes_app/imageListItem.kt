package ccm.uni_fes_app

import android.graphics.Bitmap

class imageListItem(){
    private var text = null as String?
    private var imagedata = null as Bitmap?
    fun setText(catch: String){
        text = catch
    }
    fun setImage(catch: Bitmap){
        imagedata = catch
    }
    fun getText(): String?{
        return text
    }
    fun getImageData(): Bitmap?{
        return imagedata
    }
}