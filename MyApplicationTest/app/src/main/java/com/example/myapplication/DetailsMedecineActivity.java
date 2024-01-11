package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsMedecineActivity extends AppCompatActivity{
    TextView tv_title;
    TextView tv_subTitle;
    TextView tv_supportingText;
    ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medecine);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String QRCode = bundle.getString("QRCode");
        QRCode="doliprane";

        tv_title = findViewById(R.id.details_title);
        tv_subTitle = findViewById(R.id.details_subTitle);
        tv_supportingText = findViewById(R.id.details_supportingText);
        iv_img = findViewById(R.id.details_img);

        new FetchDataTask().execute("http://10.0.2.2:8000/medecine/api/?title="+QRCode);
    }

        private class FetchDataTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String urlString = params[0];
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(result);


                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            tv_title.setText(jsonObject.getString("title"));
                            tv_subTitle.setText(jsonObject.getString("title"));
                            tv_supportingText.setText(jsonObject.getString("desc"));
                            String endPoint_img = jsonObject.getString("img");
                            String imageUrl = "http://10.0.2.2:8000/"+endPoint_img;

                            new DownloadImageTask().execute(imageUrl);

                            // Affichez ou utilisez d'autres valeurs si nécessaire


                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de l'analyse du JSON");
                    }
                } else {
                    System.out.println("Erreur lors de la récupération des données");
                }
            }
        }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                iv_img.setImageBitmap(result);
            } else {
                // Gérez l'erreur ou affichez un message d'erreur
                Toast.makeText(DetailsMedecineActivity.this, "Erreur lors du téléchargement de l'image", Toast.LENGTH_SHORT).show();
            }
        }
    }

}