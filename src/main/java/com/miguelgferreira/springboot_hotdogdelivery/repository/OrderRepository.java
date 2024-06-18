package com.miguelgferreira.springboot_hotdogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguelgferreira.springboot_hotdogdelivery.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
