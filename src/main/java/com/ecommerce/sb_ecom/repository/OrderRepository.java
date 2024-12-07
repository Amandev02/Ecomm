package com.ecommerce.sb_ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.sb_ecom.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}