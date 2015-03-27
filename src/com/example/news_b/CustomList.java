package com.example.news_b;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<Headline>{

	private final Activity context;
	private final List<Headline> headlines;
	
	public CustomList(Activity context, List<Headline> headlines){
		super(context, R.layout.headline_row, headlines);
		this.context = context;
		this.headlines = headlines;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.headline_row, null, true);
		
		ImageView headlineImage = (ImageView) rowView.findViewById(R.id.headlineImage);
		TextView headlineText = (TextView) rowView.findViewById(R.id.headlineText);
		
		//headlineImage.setImageResource(headlines.indexOf(headlines.get(position).getHeadlineImage()));
		/*new DownloadHeadlineImage(headlineImage).execute(headlines.get(position).getHeadlineImageUrl());
		paperLogo.setImageResource(headlines.get(position).getPaperLogo());*/
		
		headlineImage.setImageBitmap(headlines.get(position).getHeadlineImage());
		headlineText.setText(headlines.get(position).getHeading());
		
		return rowView;
	}
}
