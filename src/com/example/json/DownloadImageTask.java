package com.example.json;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

	ImageView bmImage;
	Bitmap bitmap;
	
	public DownloadImageTask(ImageView bmImage){
		this.bmImage=bmImage;		
	}
	
	public DownloadImageTask(Bitmap bitmap){
		this.bitmap=bitmap;
	}
	
	public DownloadImageTask(){
		
	}
	
	@Override
	protected Bitmap doInBackground(String... urls) {
		String urldisplay=urls[0];
		Bitmap bitmap=null;
		try{
			InputStream in=new java.net.URL(urldisplay).openStream();
			bitmap=BitmapFactory.decodeStream(in);
		} catch (Exception e){
			Log.e("Error",e.getMessage());
			e.printStackTrace();
		}
		return bitmap;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		//bmImage.setImageBitmap(result);
		bitmap=result;
	}
}
