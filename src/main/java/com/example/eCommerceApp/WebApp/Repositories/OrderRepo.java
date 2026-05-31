package com.example.eCommerceApp.WebApp.Repositories;

import com.example.eCommerceApp.WebApp.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Long> {
    Optional<Order> findByReferenceId(String referenceId);
}
