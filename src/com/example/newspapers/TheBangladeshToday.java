/*package com.example.newspapers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.news_b.Headline;

public class TheBangladeshToday extends NewsPaper{

	public TheBangladeshToday(String url){
		super(url);
	}

	@Override
	public List<Headline> parseDocumentToHeadlines(Document document) {
		List<Headline> headlines = new ArrayList<Headline>();

		Elements elements = document.select("article a");

		for (org.jsoup.nodes.Element element : elements) {
			if(element.attr("href").startsWith("http://thebangladeshtoday.com/author/"))
				continue;
			if(element.attr("href").startsWith("http://thebangladeshtoday.com/category/"))
				continue;
			if(element.text().equals(""))
				continue;
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
*/