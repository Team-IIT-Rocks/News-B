package com.example.news_b;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.newspapers.NewsPaper;
import com.example.newspapers.ProthomAlo;
import com.example.newspapers.TheBangladeshToday;
import com.example.newspapers.TheNewAge;
import com.example.urls.ProthomAloUrls;
import com.example.urls.TheBangladeshTodayUrls;
import com.example.urls.TheNewAgeUrls;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.NewsText);
	}

	public void getSportsHeadlines(View view){
		
		NewsPaper theBangladeshToday = new TheBangladeshToday(new TheBangladeshTodayUrls().theBangladeshTodaySports);
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloSports);
		NewsPaper theNewAge = new TheNewAge(new TheNewAgeUrls().theNewAgeSports);
		
		new DownloadHeadlines().execute(theBangladeshToday, prothomAlo, theNewAge);
	}
	
	public void getBusinessHeadlines(View view){
		
		NewsPaper theBangladeshToday = new TheBangladeshToday(new TheBangladeshTodayUrls().theBangladeshTodayBusiness);
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloBusiness);
		NewsPaper theNewAge = new TheNewAge(new TheNewAgeUrls().theNewAgeBusiness);
		
		new DownloadHeadlines().execute(theBangladeshToday, prothomAlo, theNewAge);
	}
	
	public void getEntertainmentHeadlines(View view){
		
		NewsPaper theBangladeshToday = new TheBangladeshToday(new TheBangladeshTodayUrls().theBangladeshTodayEntertainment);
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloEntertainment);
		NewsPaper theNewAge = new TheNewAge(new TheNewAgeUrls().theNewAgeEntertainment);
		
		new DownloadHeadlines().execute(theBangladeshToday, prothomAlo, theNewAge);
	}	
	
	public void getPoliticsHeadlines(View view){
		
		NewsPaper theBangladeshToday = new TheBangladeshToday(new TheBangladeshTodayUrls().theBangladeshTodayPolitics);
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloPolitics);
		NewsPaper theNewAge = new TheNewAge(new TheNewAgeUrls().theNewAgePolitics);
		
		new DownloadHeadlines().execute(theBangladeshToday, prothomAlo, theNewAge);
	}
	
	private class DownloadHeadlines extends AsyncTask<NewsPaper, Void, List<List<Headline>>>{

		@Override
		protected List<List<Headline>> doInBackground(NewsPaper... newsPapers) {
			
			List<Headline> onePaperheadlines;
			List<List<Headline>> allHeadlines = new ArrayList<List<Headline>>();
			for (NewsPaper newsPaper : newsPapers) {
				try {
					Document document = Jsoup.connect(newsPaper.url).get();
					onePaperheadlines = new ArrayList<Headline>();
					onePaperheadlines = newsPaper.parseDocumentToHeadlines(document); 
					allHeadlines.add(onePaperheadlines);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			/*for (List<Headline> headlines : allHeadlines) {
				for (Headline headline : headlines) {
					buffer.append(headline.getUrl() + "\n");
				}
			}*/
			return allHeadlines;
		}
		
		@Override
		protected void onPostExecute(List<List<Headline>> result) {
			super.onPostExecute(result);
			textView.setText(result.toString());
		}
	}
}
