package com.example.testbrowser.mvp.presenter;

import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.testbrowser.mvp.view.MainActivityView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    private String url;

    public void takeURL(String text) {
        url = checkURL(text);
        getViewState().changeTextinEditText(url);
        getViewState().loadURLtoWebView(url);
    }

    public String checkURL (String url){
        if (url.contains("http://")) return url;
        return "http://" + url;
    }
}
