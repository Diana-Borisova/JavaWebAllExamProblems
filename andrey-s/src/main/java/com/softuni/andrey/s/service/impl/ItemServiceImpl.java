package com.softuni.andrey.s.service.impl;

import com.softuni.andrey.s.entity.Category;
import com.softuni.andrey.s.entity.Item;
import com.softuni.andrey.s.entity.services.ItemServiceModel;
import com.softuni.andrey.s.entity.view.ItemViewModel;
import com.softuni.andrey.s.repository.ItemRepository;
import com.softuni.andrey.s.service.CategoryService;
import com.softuni.andrey.s.service.ItemService;
import com.softuni.andrey.s.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    private final CategoryService categoryService;


    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, LoggedUser loggedUser, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
    }


    @Override
    public void addOrder(ItemServiceModel itemServiceModel) {
        Item item = modelMapper.map(itemServiceModel, Item.class);
        Category category = this.categoryService.findByName(itemServiceModel.getCategory());
        item.setCategory(category);
        item.setDescription(item.getDescription());
        item.setGender(itemServiceModel.getGender());
        item.setName(itemServiceModel.getName());
        item.setPrice(itemServiceModel.getPrice());
        this.itemRepository.save(item);
    }

    @Override
    public List<ItemViewModel> findAll() {
        return  itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item,ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(String id) {
        return this.itemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItemById(String id) {
        this.itemRepository.deleteById(id);
    }
}
