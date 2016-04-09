package com.example.desile.yandextestapp.artists;

import android.content.Context;
import android.text.TextUtils;

import com.example.desile.yandextestapp.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Класс содержит информация об исполнителе.
 */
public class Artist implements Serializable{

    private String id;
    private String name;
    private Set<String> genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    /**
     * @return Список жанров в текстовом виде с запятой в качестве разделителя.
     */
    public String genresToString(){
        return TextUtils.join(", ",genres);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Cover getCover() {
        return cover;
    }

}
