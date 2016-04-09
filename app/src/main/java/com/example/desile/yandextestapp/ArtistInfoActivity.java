package com.example.desile.yandextestapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desile.yandextestapp.artists.Artist;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class ArtistInfoActivity extends ActionBarActivity {

    private static final String ARTIST_ID = "artistId";

    Artist artist;

    public static void start(Context context, Artist artist){
        Intent intent = new Intent(context, ArtistInfoActivity.class);
        intent.putExtra(ARTIST_ID, artist);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        artist = (Artist)this.getIntent().getSerializableExtra(ARTIST_ID);

        setTitle(artist.getName());

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    protected void onResume(){
        super.onResume();

        ImageView cover = (ImageView) findViewById(R.id.img);

        TextView genres = (TextView) findViewById(R.id.genres);
        TextView tracks = (TextView) findViewById(R.id.tracks);
        TextView albums = (TextView) findViewById(R.id.albums);
        TextView bio = (TextView) findViewById(R.id.bio);

        Ion.with(ArtistInfoActivity.this)
                .load(artist.getCover().getBig())
                .withBitmap()
                .error(R.drawable.music)
                .intoImageView(cover).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView imageView) {
                findViewById(R.id.progressBar1).setVisibility(View.GONE);
                imageView.startAnimation(AnimationUtils.loadAnimation(ArtistInfoActivity.this, R.anim.fadeinfromright));
            }
        });

        genres.setText(artist.genresToString());
        tracks.setText(DeclensionBuilder.build(DeclensionBuilder.trackCase, artist.getTracks()));
        albums.setText(DeclensionBuilder.build(DeclensionBuilder.albumCase, artist.getAlbums()));
        bio.setText(artist.getDescription().substring(0,1).toUpperCase() + artist.getDescription().substring(1));

        findViewById(R.id.fullinfo).startAnimation(AnimationUtils.loadAnimation(ArtistInfoActivity.this, R.anim.fadeinfromright));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
