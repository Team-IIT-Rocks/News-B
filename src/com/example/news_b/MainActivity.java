package com.example.news_b;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.newspapers.NewsPaper;
import com.example.newspapers.ProthomAlo;
import com.example.urls.ProthomAloUrls;
import com.example.urls.TheBangladeshTodayUrls;
import com.example.urls.TheNewAgeUrls;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private ListView listView;
	private CustomList adapter;
	private List<Headline> headlines;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloSports, MainActivity.this);
		new DownloadHeadlines().execute(prothomAlo);
		
	}

	public void getSportsHeadlines(View view){
		NewsPaper prothomAlo = new ProthomAlo(new ProthomAloUrls().prothomAloSports, MainActivity.this);
		new DownloadHeadlines().execute(prothomAlo);
		
	}
	
	private class DownloadHeadlines extends AsyncTask<NewsPaper, Void, List<Headline>>{

		@Override
		protected List<Headline> doInBackground(NewsPaper... newsPapers) {
			
			List<Headline> onePaperheadlines = null;
			for (NewsPaper newsPaper : newsPapers) {
				try {
					Document document = Jsoup.connect(newsPaper.url).get();
					onePaperheadlines = new ArrayList<Headline>();
					onePaperheadlines = newsPaper.parseDocumentToHeadlines(document); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return onePaperheadlines;
		}
		
		@Override
		protected void onPostExecute(List<Headline> result) {
			super.onPostExecute(result);
			headlines = result;
			adapter = new CustomList(MainActivity.this, headlines);
			listView = (ListView) findViewById(R.id.newsList);
			listView.setAdapter(adapter);
		}
	}
}
