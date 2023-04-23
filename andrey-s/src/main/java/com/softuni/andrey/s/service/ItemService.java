package com.softuni.andrey.s.service;

import com.softuni.andrey.s.entity.Item;
import com.softuni.andrey.s.entity.services.ItemServiceModel;
import com.softuni.andrey.s.entity.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    void addOrder(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAll();

    Item findById(String id);

    void deleteItemById(String id);
}
