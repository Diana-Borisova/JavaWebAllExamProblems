package com.example.modelmapper.services.Impl;

import com.example.modelmapper.repositories.OrderRepository;
import com.example.modelmapper.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  final private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
