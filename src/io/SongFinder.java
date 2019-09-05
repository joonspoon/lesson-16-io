package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SongFinder {

	final static String API_KEY = "0ba34c866e9d5e689504bdee99fbcac2";

	public static void main(String[] args) throws IOException {
		/* Find a song with lyrics containing a certain phrase. */
		String lyrics = "ajaja";
		String song = getSong(lyrics);
		System.out.println(song);
	}

	private static String getSong(String lyrics) throws MalformedURLException, IOException {

		String rootURL = "http://api.musixmatch.com/ws/1.1/track.search";
		rootURL += "?apikey=" + API_KEY;
		rootURL += "&q_lyrics=" + URLEncoder.encode(lyrics, "UTF-8");

		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);

		JSONObject root = new JSONObject(response);
		JSONObject message = (JSONObject) root.get("message");
		JSONObject body = (JSONObject) message.get("body");
		JSONArray track_list = (JSONArray) body.get("track_list");

		JSONObject firstTrack;
		try {
			firstTrack = (JSONObject) track_list.get(0);
		} catch (JSONException e) {
			System.out.println("No songs about " + lyrics + " :(");
			return "";
		}

		JSONObject trackDetails = (JSONObject) firstTrack.get("track");

		return (String) trackDetails.get("track_name") + "\n" +  trackDetails.get("artist_name");
	}

}
