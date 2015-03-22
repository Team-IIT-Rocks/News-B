package com.example.newspapers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.news_b.Headline;

public class TheNewAge extends NewsPaper{


	public TheNewAge(String url){
		super(url);
	}
	
	@Override
	public List<Headline> parseDocumentToHeadlines(Document document) {
		
		List<Headline> headlines = new ArrayList<Headline>();

		Elements elements = document.select("h2 a");
		for (Element element : elements) {
			
				Headline headline = new Headline(element.text(),
						element.attr("href"));
				headlines.add(headline);
			
		}
		return headlines;
	}

	@Override
	public String getBriefNews(Headline headline) {
		// TODO Auto-generated method stub
		return null;
	}

}
