package com.example.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class ParseJson{
	
	public List<Json> Parse(JSONArray array){
	List<Json> jsons=new ArrayList<Json>();
	
	
	for (int i = 0; i < array.length(); i++) {
		try{
			Json json=new Json();
		for (int j = 0; j < array.getJSONObject(i).getJSONArray("keywords").length(); j++) {
			//json.keywords[j]=array.getJSONObject(i).getJSONArray("keywords").getString(j);
		}
		json.author=array.getJSONObject(i).optString("author");
		json.date=array.getJSONObject(i).optString("date");
		json.image_url=array.getJSONObject(i).optString("image_url");
		json.text=array.getJSONObject(i).optString("text");
		json.topic=array.getJSONObject(i).optString("topic");
		jsons.add(json);
		}
		catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	return jsons;
	}
}