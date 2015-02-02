package com.example.json;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JsonAdapter extends ArrayAdapter<Json>{

	
	private Context context;
	private int layoutResourceID;
	private Json data[];
	
	public JsonAdapter(Context context,int layoutResourceID,Json data[]){
		super(context,layoutResourceID,data);
		this.context=context;
		this.layoutResourceID=layoutResourceID;
		this.data=data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		RowHolder holder=null;
		
		if(row==null){//zaraz bedzie widoczny
			LayoutInflater inflater=((Activity)context).getLayoutInflater();
			row=inflater.inflate(layoutResourceID,parent,false);
			holder=new RowHolder();
			holder.textView=(TextView) row.findViewById(R.id.textView1);
			holder.textView1=(TextView) row.findViewById(R.id.textView2);
			holder.textView2=(TextView) row.findViewById(R.id.textView3);
			holder.imageView=(ImageView) row.findViewById(R.id.imageView1);
			holder.textView4=(TextView) row.findViewById(R.id.textView5);
			row.setTag(holder);
		}else{//widoczny
			holder=(RowHolder) row.getTag();
		}	
		
		Json item=data[position];
		holder.textView.setText(item.date);
		holder.textView1.setText(item.author);
		holder.textView2.setText(item.topic);
		holder.imageView.setImageURI(Uri.parse(item.image_url));
		holder.textView4.setText(item.text);
		
		return row;
	}		
}
	
	class RowHolder{
		ImageView imageView;
		TextView textView,textView1,textView2,textView3,textView4;
	}