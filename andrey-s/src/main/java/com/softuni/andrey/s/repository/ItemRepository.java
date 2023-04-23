package com.softuni.andrey.s.repository;

import com.softuni.andrey.s.entity.CategoryNameEnum;
import com.softuni.andrey.s.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    List<Item> findAll();
}
