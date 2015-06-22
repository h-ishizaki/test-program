package com.example.ishizaki.jsontestver3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private final String uri = "http://api.atnd.org/events/?keyword=android&format=json";
//        private final String uri = "http://newscloud.id774.net/newscloud/blogs/json";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(new AsyncJsonLoader.AsyncCallback() {
                // 実行前
                public void preExecute() {
                }
                // 実行後
                public void postExecute(JSONObject result) {
                    if (result == null) {
                        showLoadError(); // エラーメッセージを表示
                        return;
                    }
                    try {
                        // 各 ATND イベントのタイトルを配列へ格納
                        ArrayList<String> list = new ArrayList<>();
                        JSONArray eventArray = result.getJSONArray("events");
                        for (int i = 0; i < eventArray.length(); i++) {
                            JSONObject eventObj = eventArray.getJSONObject(i);
                            JSONObject event = eventObj.getJSONObject("event");
                            //Log.d("title", event.getString("title"));
                            list.add(event.getString("title"));
                        }
                        setListAdapter(list);

//                        // ListView 用のアダプタを作成
//                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
//                                getActivity(), android.R.layout.simple_list_item_1, list
//                        );
//                        // ListView にアダプタをセット
//                        ListView listView = (ListView)getActivity().findViewById(R.id.listView);
//                        listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showLoadError(); // エラーメッセージを表示
                    }
                }
                // 実行中
                public void progressUpdate(int progress) {
                }
                // キャンセル
                public void cancel() {
                }
            });
            // 処理を実行
            asyncJsonLoader.execute(uri);
        }

        // エラーメッセージ表示
        private void showLoadError() {
            Toast toast = Toast.makeText(getActivity(), "データを取得できませんでした。", Toast.LENGTH_SHORT);
            toast.show();
        }

        private void setListAdapter(ArrayList<String> list){
            // リソースに準備した画像ファイルからBitmapを作成しておく
            Bitmap image;
            image = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

            // データの作成
            List<CustomData> objects = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                CustomData data = new CustomData();
                data.setNumberData("10:50");
                data.setImagaData(image);
                data.setTitleData(list.get(i));
//                data.setTweetData(123);
//                data.setCommentCount(45);
//                data.setIsRepry(true);
                objects.add(data);
            }

            CustomAdapter customAdapater = new CustomAdapter(getActivity(), 0, objects);

            ListView listView = (ListView)getActivity().findViewById(R.id.listView);
            listView.setAdapter(customAdapater);
        }

    }



}