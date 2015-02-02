package com.example.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Json> jsons=new ArrayList<Json>();
		listview=(ListView) findViewById(R.id.listView1);
		try{
			String json=jsonToStringFromAssetFolder("document.json", this);
			JSONObject response=new JSONObject(json);
			JSONArray recentPosts=response.getJSONArray("recent_posts");
			ParseJson parse=new ParseJson();
			jsons=parse.Parse(recentPosts);
			Toast.makeText(this, "ilosc: " + recentPosts.length(), Toast.LENGTH_SHORT).show();
		} catch (IOException e){
			e.printStackTrace();
		} catch(JSONException e){
			e.printStackTrace();
		}
		Json data[]=jsons.toArray(new Json[jsons.size()]);
		JsonAdapter adapter=new JsonAdapter(this, R.layout.jsonlayout, data);
		listview.setAdapter(adapter);
	}
	
	public static String jsonToStringFromAssetFolder(String fileName, Context context) throws IOException{
		
		AssetManager manager=context.getAssets();
		InputStream file=manager.open(fileName);
		
		byte[] data=new byte[file.available()];
		file.read(data);
		file.close();
		return new String(data);
	}
}