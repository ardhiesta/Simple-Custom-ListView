package ardhi.com.simplecustomlistview;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by linuxluv on 09/11/14.
 */
public class Movie implements Serializable{
    private String title;
    private String image;
    private double rating;
    private int releaseYear;
    private ArrayList genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ArrayList getGenre() {
        return genre;
    }

    public void setGenre(ArrayList genre) {
        this.genre = genre;
    }
}
