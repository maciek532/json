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
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listview;
	ProgressDialog progressdialog;
	List<Json> jsons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		jsons=new ArrayList<Json>();
		listview=(ListView) findViewById(R.id.listView1);
		progressdialog=ProgressDialog.show(this, "³adowanie", "loading data");
		JSONAsyncTask task=new JSONAsyncTask();
		task.execute("http://psporysz.boo.pl/inne/document.json");	
		/*try{
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
		}*/		
	}
	
	void setup(String result){
		try{
			JSONObject jsonObject=new JSONObject(result);
			JSONArray recentPosts=jsonObject.getJSONArray("recent_posts");
			ParseJson parse=new ParseJson();
			jsons=parse.Parse(recentPosts);
			Json data[]=jsons.toArray(new Json[jsons.size()]);
			JsonAdapter adapter=new JsonAdapter(this, R.layout.jsonlayout, data);
			listview.setAdapter(adapter);
		} catch (JSONException e){
			e.printStackTrace();
		} finally{
			progressdialog.dismiss();
		}
	}
	
	private class JSONAsyncTask extends AsyncTask<String, Void, String>{
		
		@Override
		protected String doInBackground(String... params) {
			InputStream is=null;
			String json="";
			String stringURL=params[0];
			
			try{
				DefaultHttpClient httpClient=new DefaultHttpClient();
				HttpPost httpPost=new HttpPost(stringURL);
				HttpResponse httpResponse=httpClient.execute(httpPost);
				HttpEntity httpEntity =httpResponse.getEntity();
				is=httpEntity.getContent();
			} catch(UnsupportedEncodingException e){
				e.printStackTrace();
				e.toString();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				e.toString();
			} catch (IOException e) {
				e.printStackTrace();
				e.toString();
			}
			
			try{
				BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
				StringBuilder sb=new StringBuilder();
				String line=null;
				while((line=reader.readLine())!=null){
					sb.append(line);
				}
				is.close();
				json=sb.toString();
				return json;
			} catch (Exception e){
				Log.e("Buffer Error", "Eror converting result" + e.toString());
				return e.toString();
			}
			
		}
		@Override
		protected void onPostExecute(String result) {
			setup(result);
			super.onPostExecute(result);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static String jsonToStringFromAssetFolder(String fileName, Context context) throws IOException{
		
		AssetManager manager=context.getAssets();
		InputStream file=manager.open(fileName);
		
		byte[] data=new byte[file.available()];
		file.read(data);
		file.close();
		return new String(data);
	}*/
}