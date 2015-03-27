package com.example.newspapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.news_b.CustomList;
import com.example.news_b.Headline;
import com.example.news_b.HeadlineImage;
import com.example.news_b.MainActivity;
import com.example.news_b.R;
import com.example.urls.ProthomAloUrls;

public class ProthomAlo extends NewsPaper{

	private HeadlineImage headlineImage;
	private Context context;
	public ProthomAlo(String url, Context context){
		super(url); 
		this.context = context;
		headlineImage = new HeadlineImage(context);
	}
	
	@Override
	public List<Headline> parseDocumentToHeadlines(Document document) {
		List<Headline> headlines = new ArrayList<Headline>();
		List<String> headlineUrls = new ArrayList<String>();
		
		Elements elements = document.select("body a");
		for (org.jsoup.nodes.Element element : elements) {
			String absoluteUrlOfElement = "http://en.prothom-alo.com/"+element.attr("href");
			if (absoluteUrlOfElement.startsWith(url) && !absoluteUrlOfElement.equals(url) && !headlineUrls.contains(absoluteUrlOfElement)) {
				headlineUrls.add(absoluteUrlOfElement);
			}
		}
		for (String headlineUrl : headlineUrls) {
			headlines.add(getBriefNews(headlineUrl));
		}
		return headlines;
	}

	@Override
	public Headline getBriefNews(String headlineUrl) {

		Document document = connectTo(headlineUrl);
		
		String fullNews = document.getElementsByAttributeValue("itemprop", "articleBody").text();
		String headlineText = document.getElementsByTag("h1").text();

		String sourceImage = document.getElementsByAttributeValue("itemprop", "articleBody").select("img").attr("src").toString();
		Log.i("ashol", sourceImage);
		Bitmap image = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.download)).getBitmap();;
		image = headlineImage.getResizedImage(image, 75, 75);
		
		if(sourceImage != null && !sourceImage.isEmpty()){
			if(!sourceImage.startsWith("http://www.en.prothom-alo.com/")){
				Log.i("aaaa", sourceImage);
				image = headlineImage.getHeadlineImage("http://en.prothom-alo.com/" + sourceImage);
			}	
			else{
				Log.i("bbbb", sourceImage);
				image = headlineImage.getHeadlineImage(sourceImage);
			}
			
			Log.i("anik", sourceImage);
		}
		return new Headline(headlineText, headlineUrl, image, fullNews);
	}

}
