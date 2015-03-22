package com.example.newspapers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.news_b.Headline;

public class ProthomAlo extends NewsPaper{

	public ProthomAlo(String url){
		super(url);
	}
	
	@Override
	public List<Headline> parseDocumentToHeadlines(Document document) {
		List<Headline> headlines = new ArrayList<Headline>();

		Elements elements = document.select("body a");
		for (org.jsoup.nodes.Element element : elements) {
			
			String absoluteUrlOfElement = "http://en.prothom-alo.com/"+element.attr("href");
			
			if (absoluteUrlOfElement.startsWith(url) && !absoluteUrlOfElement.equals(url)) {
				Headline headline = new Headline(element.text(),
						absoluteUrlOfElement);
				headlines.add(headline);
			}
		}
		return headlines;
	}

	@Override
	public String getBriefNews(Headline headline) {
		// TODO Auto-generated method stub
		return null;
	}

}
