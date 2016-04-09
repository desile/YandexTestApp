package com.example.desile.yandextestapp.artists;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import junit.framework.TestCase;

import java.util.List;


public class ArtistMapperTest extends TestCase {

    String json = "[{\"id\":1080505," +
                    "\"name\":\"Tove Lo\"," +
                    "\"genres\":[\"pop\",\"dance\",\"electronics\"]," +
                    "\"tracks\":81,\"albums\":22," +
                    "\"link\":\"http://www.tove-lo.com/\"," +
                    "\"description\":\"шведская певица и автор песен...\"," +
                    "\"cover\":{\"small\":\"300x300\",\"big\":\"1000x1000\"}}," +
                    "{\"id\":2003004," +
                    "\"name\":\"Chichi\"," +
                    "\"genres\":[\"rock\",\"dance\",\"ambient\"]," +
                    "\"tracks\":333,\"albums\":221," +
                    "\"link\":\"http://www.chichi.com/\"," +
                    "\"description\":\"who knows...\"," +
                    "\"cover\":{\"small\":\"301x301\",\"big\":\"1001x1001\"}}]";


    public void testJsonMappingToArtistClass(){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray array = element.getAsJsonArray();

        ArtistMapper mapper = new ArtistMapper();
        List<Artist> artists = mapper.mapJsonOnArtistList(array);

        assertEquals(artists.get(0).getId(),"1080505");
        assertEquals(artists.get(0).getName(),"Tove Lo");
        assertEquals(artists.get(0).getTracks(),81);
        assertEquals(artists.get(0).getAlbums(),22);
        assertEquals(artists.get(0).getGenres().contains("pop"),true);
        assertEquals(artists.get(0).getGenres().contains("dance"),true);
        assertEquals(artists.get(0).getGenres().contains("electronics"),true);
        assertEquals(artists.get(0).getGenres().contains("folk"),false);
        assertEquals(artists.get(0).getLink(),"http://www.tove-lo.com/");
        assertEquals(artists.get(0).getDescription(),"шведская певица и автор песен...");
        assertEquals(artists.get(0).getCover().getBig(),"1000x1000");
        assertEquals(artists.get(0).getCover().getSmall(),"300x300");

        assertEquals(artists.get(1).getId(),"2003004");
        assertEquals(artists.get(1).getName(),"Chichi");
        assertEquals(artists.get(1).getTracks(),333);
        assertEquals(artists.get(1).getAlbums(),221);
        assertEquals(artists.get(1).getLink(),"http://www.chichi.com/");
        assertEquals(artists.get(1).getDescription(),"who knows...");
        assertEquals(artists.get(1).getCover().getBig(),"1001x1001");
        assertEquals(artists.get(1).getCover().getSmall(),"301x301");

    }

}