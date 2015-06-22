package com.example.ishizaki.jsontestver3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomData> {
    private LayoutInflater layoutInflater_;

    public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        CustomData item = getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.listitem, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.number);
        textView.setText(item.getNumberData());

        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImageData());

//        TextView textView;  // 使いまわし
        textView = (TextView)convertView.findViewById(R.id.title);
        textView.setText(item.getTitleData());

//        textView = (TextView)convertView.findViewById(R.id.under);

        return convertView;
    }
}
