package com.example.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class ParseJson {
	
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