package com.example.desile.yandextestapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desile.yandextestapp.artists.Artist;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MusicList extends ArrayAdapter<Artist> {

    private final Activity context;
    private List<Artist> artists; //полный список артистов
    private List<Artist> filteredArtists; //отфильтрованный список артистов
    private ArtistFilter artistFilter;

    private String infoTemplate = "%s , %s";

    public MusicList(Activity context, List<Artist> artists){
        super(context,R.layout.music_list, artists);
        this.context = context;
        this.artists = new ArrayList<>();
        this.artists.addAll(artists);
        this.filteredArtists = new ArrayList<>();
        this.filteredArtists.addAll(artists);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.music_list, null, true);

        Artist curArtist = filteredArtists.get(position);

        TextView artistName = (TextView) rowView.findViewById(R.id.name);
        TextView genres = (TextView) rowView.findViewById(R.id.genres);
        TextView info = (TextView) rowView.findViewById(R.id.info);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        artistName.setText(curArtist.getName());
        genres.setText(curArtist.genresToString());
        info.setText(String.format(infoTemplate,DeclensionBuilder.build(DeclensionBuilder.albumCase, curArtist.getAlbums()),
                                                DeclensionBuilder.build(DeclensionBuilder.trackCase, curArtist.getTracks())));

        //Используем библиотеку ION для асинхронной загрузки обложки для текущего исполнителя (строки).
        Ion.with(context)
                .load(curArtist.getCover().getSmall())
                .withBitmap()
                .error(R.drawable.music)
                .intoImageView(imageView).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView imageView) {
                    imageView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.scaling));
            }
        });

        return rowView;
    }

    @Override
    public Filter getFilter(){
        if(artistFilter == null){
            artistFilter = new ArtistFilter();
        }
        return artistFilter;
    }

    private class ArtistFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();

            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<Artist> filtered = new ArrayList<Artist>();

                for(int i = 0, l = artists.size(); i < l; i++)
                {
                    Artist a = artists.get(i);
                    if(a.getName().toLowerCase().contains(constraint))
                        filtered.add(a);
                }
                result.count = filtered.size();
                result.values = filtered;
            }
            else
            {
                synchronized(this)
                {
                    result.values = artists;
                    result.count = artists.size();
                }
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredArtists = (ArrayList<Artist>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = filteredArtists.size(); i < l; i++)
                add(filteredArtists.get(i));
            notifyDataSetInvalidated();
        }

    }

}
