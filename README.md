# WebView-android7
This is a simple Android application shows the issue of WebView with Android 7.1 and its current workaround solution. 

### Issue overview
With Android 7.1 (API-25) application resets Locale to device one after launching WebView.

### App Structure
Its very simple. It has three views:
+ MainView (MainActivity) to navigate to Detail and WebView
+ WebView (WebActivity) launch WebView with localized content with **LoadData**
+ Detail (DetailActivity) show localized content
Also app includes a custom application. Resources are base and cs-rCZ (Czech) 

### Requirements
Device/Emulator with Android 7.1 Nougat (API level 25)

### Procedures
- Launch App 
- Tap on Floating Action Button to open webView
- Return
- Tap on Detail Button

### Observed behavior
- WebView shows non-localized text: **Base text: English**
- Detail shows non-localized text: **TextEnglish**

### Expected behavior
- WebView should show localized text: **Base text: Czech**
- Detail should show localized text: **TextCzech**

### Quick Technical review
It looks that issues takes places after initializing WebView widget implicitly by inflating or explicitly by constructor. 

### Workaround solution
Currently the workaround solution is to enforce locale after initializing WebView and  before loading page by:
```java
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    MyApp.getApplication().switchToCzLocale();
}
```

So WebActivity will look like:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    mWebView = (WebView) findViewById(R.id.webview);
    //add this block before loading page
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        MyApp.getApplication().switchToCzLocale();
    }
    mWebView.loadData(getString(R.string.web_content), "text/html", "charset=UTF-8");
}
```

Developed by
=======

    Maher Abuthraa 2016

