package com.example.eCommerceApp.WebApp.Repositories;

import com.example.eCommerceApp.WebApp.Entities.ProductsReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepo extends JpaRepository<ProductsReview,Long> {
}
