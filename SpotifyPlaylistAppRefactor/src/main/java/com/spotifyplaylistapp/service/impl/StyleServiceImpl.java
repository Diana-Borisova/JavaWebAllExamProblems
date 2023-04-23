package com.spotifyplaylistapp.service.impl;

import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.repository.StyleRepository;
import com.spotifyplaylistapp.service.StyleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Getter
@Setter
@Service
public class StyleServiceImpl implements StyleService {
    private  final StyleRepository styleRepository;
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;

    }

    @Override
    public void initStyles() {
        if (this.styleRepository.count() == 0){
            Arrays.stream(StyleEnum.values())
                    .forEach(_style -> {
                        Style style = new Style();
                        style.setName(_style);
                        style.setDescription("...");

                        this.styleRepository.save(style);
                    });
    }

    }

    @Override
    public Style findByName(StyleEnum styleEnum) {
        return this.styleRepository.findByName(styleEnum).orElse(null);
    }


}
