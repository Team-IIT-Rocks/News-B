package com.example.newspapers;
import java.util.List;

import org.jsoup.nodes.Document;

import com.example.news_b.Headline;


public abstract class NewsPaper {
	public String url;
	
	public NewsPaper(String url){
		this.url = url;
	}
	
	public abstract List<Headline> parseDocumentToHeadlines(Document document);
	public abstract String getBriefNews(Headline headline);

}
