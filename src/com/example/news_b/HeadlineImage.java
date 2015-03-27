package com.example.news_b;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class HeadlineImage {
	
	private Context context;
	public HeadlineImage(Context context){
		this.context = context;
	}
	
	public Bitmap getHeadlineImage(String headlineImageUrl){
		InputStream in = null;
		try {
			in = new java.net.URL(headlineImageUrl).openStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Bitmap mIcon11 = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.download)).getBitmap();
		if(in != null)
			mIcon11 = BitmapFactory.decodeStream(in);
        return getResizedImage(mIcon11, 75, 75);
	}
	
	public Bitmap getResizedImage(Bitmap bm, int newHeight, int newWidth) {
		
		Bitmap resizedBitmap = null;
		try {
			int width = bm.getWidth();
			 
			int height = bm.getHeight();
			 
			float scaleWidth = ((float) newWidth) / width;
			 
			float scaleHeight = ((float) newHeight) / height;
			 
			// CREATE A MATRIX FOR THE MANIPULATION
			 
			Matrix matrix = new Matrix();
			 
			// RESIZE THE BIT MAP
			 
			matrix.postScale(scaleWidth, scaleHeight);
			 
			// RECREATE THE NEW BITMAP
			 
			resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		} catch (Exception e) {
			resizedBitmap = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.download)).getBitmap();
			resizedBitmap = getResizedImage(resizedBitmap, 75, 75);
			return resizedBitmap;
		}
		
		 
		return resizedBitmap;
		 
		}


}
