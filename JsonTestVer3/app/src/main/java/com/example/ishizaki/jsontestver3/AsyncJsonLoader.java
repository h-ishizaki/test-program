package com.example.ishizaki.jsontestver3;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncJsonLoader extends AsyncTask<String, Integer, JSONObject> {
    public interface AsyncCallback {
        void preExecute();
        void postExecute(JSONObject result);
        void progressUpdate(int progress);
        void cancel();
    }

    private AsyncCallback mAsyncCallback = null;

    public AsyncJsonLoader(AsyncCallback _asyncCallback) {
        mAsyncCallback = _asyncCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mAsyncCallback.preExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... _progress) {
        super.onProgressUpdate(_progress);
        mAsyncCallback.progressUpdate(_progress[0]);
    }

    @Override
    protected void onPostExecute(JSONObject _result) {
        super.onPostExecute(_result);
        mAsyncCallback.postExecute(_result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mAsyncCallback.cancel();
    }

    @Override
    protected JSONObject doInBackground(String... _uri) {
        try {
            // コネクション生成
            HttpURLConnection connection = null;
            URL url = null;
            url = new URL(_uri[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // リクエスト送信
            connection.connect();

            // レスポンスコードチェック
            if (connection.getResponseCode() != 200) {
                return null;
            }

            // レスポンス文字列取得
            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream responseArray = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];

            int length;
            while ((length = inputStream.read(buff)) != -1) {
                if (length > 0) {
                    responseArray.write(buff, 0, length);
                }
            }

            JSONObject jsonObj = new JSONObject(new String(responseArray.toByteArray()));
            return jsonObj;
        } catch(Exception e) {
            e.printStackTrace();
        }


//        HttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(_uri[0]);
//        try {
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                httpResponse.getEntity().writeTo(outputStream);
//                outputStream.close();
//                return new JSONObject(outputStream.toString());
//            } else {
//                httpResponse.getEntity().getContent().close();
//                throw new IOException();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
