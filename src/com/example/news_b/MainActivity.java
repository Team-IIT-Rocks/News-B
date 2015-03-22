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
	
	private class DownloadHeadlines extends AsyncTask<NewsPaper, Void, String>{

		@Override
		protected String doInBackground(NewsPaper... newsPapers) {
			
			List<Headline> onePaperheadlines;
			List<List<Headline>> allHeadlines = new ArrayList<List<Headline>>();
			StringBuffer buffer = new StringBuffer();
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
			//buffer.append(allHeadlines);
			
			for (List<Headline> headlines : allHeadlines) {
				for (Headline headline : headlines) {
					buffer.append(headline.getUrl() + "\n");
				}
			}
			return buffer.toString();
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			textView.setText(result);
		}
	}
}
