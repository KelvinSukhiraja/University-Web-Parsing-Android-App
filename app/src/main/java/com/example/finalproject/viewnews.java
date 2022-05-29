    package com.example.finalproject;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.text.method.ScrollingMovementMethod;
    import android.util.Log;
    import android.widget.ImageView;
    import android.widget.TextView;

    import org.jsoup.Jsoup;
    import org.jsoup.nodes.Document;
    import org.jsoup.nodes.Element;
    import org.jsoup.select.Elements;

    public class viewnews extends AppCompatActivity {
        String title;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_news);

            new Content().execute();
        }

        private class Content extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    String url = "https://en.sdust.edu.cn/News___Events.htm";
                    Document doc = Jsoup.connect(url).get();
                    Log.d("abc", doc.text());
                    Elements news = doc.select("ul.timing");

                    for(Element newstitle:news){
                        String originalTitle = newstitle.select("li").select("a").text();
                        title = originalTitle;

                        Log.d("abc", title);
                    }
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(title.toString());
                    text.setMovementMethod(new ScrollingMovementMethod());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }