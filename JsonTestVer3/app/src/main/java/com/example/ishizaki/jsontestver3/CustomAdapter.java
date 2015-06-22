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
        // ����̍s(position)�̃f�[�^�𓾂�
        CustomData item = getItem(position);

        // convertView�͎g���񂵂���Ă���\��������̂�null�̎������V�������
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.listitem, null);
        }

        // CustomData�̃f�[�^��View�̊eWidget�ɃZ�b�g����
        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.number);
        textView.setText(item.getNumberData());

        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImageData());

//        TextView textView;  // �g���܂킵
        textView = (TextView)convertView.findViewById(R.id.title);
        textView.setText(item.getTitleData());

//        textView = (TextView)convertView.findViewById(R.id.under);

        return convertView;
    }
}
