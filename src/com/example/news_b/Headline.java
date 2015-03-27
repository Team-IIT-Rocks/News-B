package com.example.news_b;

import android.graphics.Bitmap;


public class Headline {

	public Headline(String heading, String headlineUrl, Bitmap headlineImage, String fullNews) {
		Heading = heading;
		HeadlineUrl = headlineUrl;
		HeadlineImage = headlineImage;
		FullNews = fullNews;
	}

	public String getHeading() {
		return Heading;
	}

	public String getUrl() {
		return HeadlineUrl;
	}
	
	public Bitmap getHeadlineImage(){
		return HeadlineImage;
	}
	
	public String getFullNews(){
		return FullNews;
	}

	private String Heading;
	private String HeadlineUrl;
	private Bitmap HeadlineImage;
	private String FullNews;

}
