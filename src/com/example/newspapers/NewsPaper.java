package com.example.newspapers;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.news_b.Headline;


public abstract class NewsPaper {
	public String url;
	
	public NewsPaper(String url){
		this.url = url;
	}
	
	public abstract List<Headline> parseDocumentToHeadlines(Document document);
	public abstract Headline getBriefNews(String headlineUrl);
	
	public Document connectTo(String paperUrl){
		Document document = null;
		try {
			document = Jsoup.connect(paperUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

}
