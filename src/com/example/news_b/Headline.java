package com.example.news_b;

public class Headline {

	public Headline(String heading, String url) {
		Heading = heading;
		Url = url;
	}

	public String getHeading() {
		return Heading;
	}

	public String getUrl() {
		return Url;
	}

	private String Heading;
	private String Url;
}
