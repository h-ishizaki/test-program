package com.example.ishizaki.jsontestver3;

import android.graphics.Bitmap;

public class CustomData {
    private String numberData_;
    private Bitmap imageData_;
    private String titleData_;

//    private int tweetCount_;
//    private int commentCount_;
//    private boolean isRepry_;

    public void setNumberData(String number) {
        numberData_ = number;
    }

    public String getNumberData() {
        return numberData_;
    }

    public void setImagaData(Bitmap image) {
        imageData_ = image;
    }

    public Bitmap getImageData() {
        return imageData_;
    }

    public void setTitleData(String title) {
        titleData_ = title;
    }

    public String getTitleData() {
        return titleData_;
    }

    // ツイート、コメント、リプライ
//    public void setTweetData(int tweet) {
//        tweetCount_ = tweet;
//    }
//
//    public int getTweetData() {
//        return tweetCount_;
//    }
//
//    public void setCommentCount(int comment) {
//        commentCount_ = comment;
//    }
//
//    public int getCommentData() {
//        return commentCount_;
//    }
//
//    public void setIsRepry(boolean isrepry) {
//        isRepry_ = isrepry;
//    }
//
//    public boolean getIsRepry() {
//        return isRepry_;
//    }

}