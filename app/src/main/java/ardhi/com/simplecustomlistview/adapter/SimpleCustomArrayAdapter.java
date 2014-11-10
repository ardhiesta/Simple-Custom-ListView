package ardhi.com.simplecustomlistview.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import ardhi.com.simplecustomlistview.MainActivity;
import ardhi.com.simplecustomlistview.Movie;
import ardhi.com.simplecustomlistview.R;

/**
 * Created by linuxluv on 09/11/14.
 */
public class SimpleCustomArrayAdapter extends ArrayAdapter<Movie> {
    private final Context context;
    private final Movie[] values;
    Activity activity;

    public SimpleCustomArrayAdapter(Context context, Movie[] values, Activity activity) {
        super(context, R.layout.rowlayout, values); //rowlayout : layout setiap element listView
        this.context = context;
        this.values = values;
        this.activity = activity;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewTitle = (TextView) rowView.findViewById(R.id.label_one);
        TextView textViewYear = (TextView) rowView.findViewById(R.id.label_two);
        SmartImageView imageView = (SmartImageView) rowView.findViewById(R.id.icon);
        Movie movies = values[position];
        textViewTitle.setText(movies.getTitle());
        textViewYear.setText(String.valueOf(movies.getReleaseYear()));
        // Change the icon for Windows and iPhone
        String imageMovie = movies.getImage();
        imageView.setImageUrl(imageMovie);
//        Bitmap mIcon11 = null;
//        System.out.println(">>imageMovie "+imageMovie);
//        try {
//            InputStream in = new java.net.URL(imageMovie).openStream();
//            mIcon11 = BitmapFactory.decodeStream(in);
//            imageView.setImageBitmap(mIcon11);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        new DownloadImageTask((ImageView) activity.findViewById(R.id.icon)).execute(imageMovie);

//        BitmapFactory.Options bmOptions;
//        bmOptions = new BitmapFactory.Options();
//        bmOptions.inSampleSize = 1;
//        Bitmap bm = LoadImage(imageMovie, bmOptions);
//        imageView.setImageBitmap(bm);

        return rowView;
    }

//    private Bitmap LoadImage(String URL, BitmapFactory.Options options)
//    {
//        Bitmap bitmap = null;
//        InputStream in = null;
//        try {
//            in = OpenHttpConnection(URL);
//            bitmap = BitmapFactory.decodeStream(in, null, options);
//            in.close();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return bitmap;
//    }
//
//    private InputStream OpenHttpConnection(String strURL) throws IOException{
//        InputStream inputStream = null;
//        URL url = new URL(strURL);
//        URLConnection conn = url.openConnection();
//
//        try{
//            HttpURLConnection httpConn = (HttpURLConnection)conn;
//            httpConn.setRequestMethod("GET");
//            httpConn.connect();
//
//            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                inputStream = httpConn.getInputStream();
//            }
//        }
//        catch (Exception ex)
//        {
//        }
//        return inputStream;
//    }
//
//    public class DownloadImageTask extends AsyncTask {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
////    protected Bitmap doInBackground(String... urls) {
////        String urldisplay = urls[0];
////        Bitmap mIcon11 = null;
////        try {
////            InputStream in = new java.net.URL(urldisplay).openStream();
////            mIcon11 = BitmapFactory.decodeStream(in);
////        } catch (Exception e) {
//////            Log.e("Error", e.getMessage());
////            e.printStackTrace();
////        }
////        return mIcon11;
////    }
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            String urldisplay = objects[0].toString();
//            System.out.println("------------------------------------->>> urldisplay: "+urldisplay);
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
////            Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}
