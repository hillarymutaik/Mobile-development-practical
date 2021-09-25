package com.shaznishiraz.bmicalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class mapswebview extends Fragment {
    private WebView webView;
    EditText fname;
    EditText lname;
    EditText email;
    Button btnUpdateProfile;
    TextView mname;
    TextView memail;


    public static final String SHARED_PREFS_CATEGORY = "sharedPrefs";
    public static final String SHARED_PREFS_NAME = "name";
    public  static  final String SHARED_PREFS_VALUE = "value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map,container,false);

        webView = (WebView) v.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com/maps/search/gym/");

return  v;


    }




}
