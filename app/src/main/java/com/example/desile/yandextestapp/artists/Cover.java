package com.example.desile.yandextestapp.artists;

import java.io.Serializable;

/**
 * Класс содержащий ссылки на обложки.
 */
public class Cover implements Serializable {
    private String small;
    private String big;

    public String getSmall(){
        return small;
    }

    public String getBig(){
        return big;
    }


}
