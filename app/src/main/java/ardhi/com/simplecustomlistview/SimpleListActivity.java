package ardhi.com.simplecustomlistview;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ardhi.com.simplecustomlistview.adapter.SimpleCustomArrayAdapter;


public class SimpleListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        ServiceGetListViewItem task = new ServiceGetListViewItem();
        task.applicationContext = SimpleListActivity.this;
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ServiceGetListViewItem extends AsyncTask {
        private ProgressDialog dialog;
        protected Context applicationContext;
        JSONArray jHasil = null;

        protected void onPreExecute(){
            dialog = new ProgressDialog(SimpleListActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setTitle("Menghubungi Server");
            dialog.setMessage("Menampilkan data, mohon tunggu...");
            dialog.setMax(100);
            dialog.setProgress(0);
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(Object... arg0){
            while(dialog.getProgress() < 1){ dialog.incrementProgressBy(1); }
            String urlService = "http://api.androidhive.info/json/movies.json";
            try {
                HttpEntity myEntityX = null;
                HttpGet request = new HttpGet(urlService);

                HttpClient client = new DefaultHttpClient();
                HttpResponse httpResponse;
                while(dialog.getProgress() < 30){ dialog.incrementProgressBy(1); }

                httpResponse = client.execute(request);
                myEntityX = httpResponse.getEntity();

                if(myEntityX != null){
                    InputStream instreamX = myEntityX.getContent();
                    String response = convertStreamToString(instreamX);
                    jHasil = new JSONArray(response);
//                    JSONObject jHasil2 = new JSONObject(jHasil.getJSONObject("GetListMebelByNameResult").toString());
                    System.out.println("hasil >> "+jHasil);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            while(dialog.getProgress() < 100){ dialog.incrementProgressBy(1); }
            return null;
        }

        protected void onPostExecute(Object result) {
            this.dialog.cancel();
            Movie movieX[] = new Movie[jHasil.length()];

            if(jHasil.length() > 0){
                for(int i=0; i<jHasil.length(); i++){
                    Movie movie = new Movie();
                    try {
                        movie.setTitle(jHasil.getJSONObject(i).getString("title"));
                        movie.setImage(jHasil.getJSONObject(i).getString("image"));
                        movie.setRating(jHasil.getJSONObject(i).getDouble("rating"));
                        movie.setReleaseYear(jHasil.getJSONObject(i).getInt("releaseYear"));
                        movieX[i] = movie;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                SimpleCustomArrayAdapter adapter = new SimpleCustomArrayAdapter(SimpleListActivity.this, movieX, SimpleListActivity.this);
                setListAdapter(adapter);
            }
        }
    }

    public String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while((line = reader.readLine()) != null){
                sb.append(line+"\n");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
