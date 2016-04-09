package com.example.desile.yandextestapp.artists;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Класс, отвечающий за процесс переноса информации об исполнителях из JSON
 * в список объектов.
 */
public class ArtistMapper {

    /**
     * С помощью библиотеки ION в асинхронном режиме загружаем JSON по указанному url.
     *
     * @param context Контекст активити.
     * @param url Ссылка на JSON
     * @return Массив JSON элементов с информацией об исполнителях.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private JsonArray getJson(Context context, String url) throws ExecutionException, InterruptedException {
        return Ion.with(context).load(url)
                .asJsonArray()
                .get();
    }

    /**
     * Создаем список из объектов исполнителей.
     * По сути, происходит маппинг Json-массива на массив(список) Java-объектов.
     *
     * @param rootElement
     * @return
     */
    public List<Artist> mapJsonOnArtistList(JsonArray rootElement){
        Gson gson = new Gson();

        List artists = new ArrayList<>();
        for(JsonElement artist : rootElement){
            artists.add(gson.fromJson(artist,Artist.class));
        }

        return artists;
    }

    /**
     * Композиция функций mapJsonOnArtistList и getJson
     */
    public List<Artist> getArtists(Context context, String url) throws ExecutionException, InterruptedException {
        return mapJsonOnArtistList(getJson(context, url));
    }

}
