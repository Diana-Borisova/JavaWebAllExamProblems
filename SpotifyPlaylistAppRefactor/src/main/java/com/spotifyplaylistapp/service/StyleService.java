package com.spotifyplaylistapp.service;

import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;


public interface StyleService {
    void initStyles();

    Style findByName(StyleEnum styleEnum);


}
