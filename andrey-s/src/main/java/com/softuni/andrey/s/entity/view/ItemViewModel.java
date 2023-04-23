package com.softuni.andrey.s.entity.view;

import com.softuni.andrey.s.entity.Category;
import com.softuni.andrey.s.entity.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemViewModel {

    private String id;

    private String name;

    private BigDecimal price;

    private Category category;
    private String description;

    private GenderEnum gender;

}
