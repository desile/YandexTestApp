package com.example.desile.yandextestapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desile.yandextestapp.artists.Artist;
import com.example.desile.yandextestapp.artists.ArtistMapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView list;
    private List<Artist> artists;
    private MusicList musicList;
    private EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            artists = new ArtistMapper().getArtists(MainActivity.this, "http://cache-default04g.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json");
            Collections.sort(artists, new Comparator<Artist>() {
                @Override
                public int compare(Artist lhs, Artist rhs) {
                    return lhs.getName().compareToIgnoreCase(rhs.getName());
                }
            });

            musicList = new MusicList(MainActivity.this, artists);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(musicList);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ArtistInfoActivity.start(MainActivity.this, artists.get(position));
                }
            });

            inputSearch = (EditText) findViewById(R.id.inputSearch);
            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    MainActivity.this.musicList.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Не удалось получить список исполнителей", Toast.LENGTH_SHORT).show();
        }
    }
}
