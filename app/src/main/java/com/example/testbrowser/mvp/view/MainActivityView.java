package com.example.testbrowser.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainActivityView extends MvpView {
    void loadURLtoWebView (String url);
    void changeTextinEditText (String text);
    void progressUpdate();
}
