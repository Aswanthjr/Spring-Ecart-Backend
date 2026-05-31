package com.example.eCommerceApp.WebApp.Utilities;

import com.example.eCommerceApp.WebApp.Entities.Products;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Products> hasCategory(String category){
        return (root, query, cb) -> {

            if (category == null || category.isBlank()) {
                return null;
            }

            return cb.equal(root.get("category"), category);
        };
    }

    public static Specification<Products> priceBetween(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder) -> {
          if (minPrice == null && maxPrice == null) return null;
          if (minPrice == null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice);
          if (maxPrice == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
          return criteriaBuilder.between(root.get("price"),minPrice,maxPrice);
        };
    }

    public static Specification<Products> hasNameOrDescriptionLike(String keyword) {
        return (root, query, criteriaBuilder) -> {

            if (keyword == null || keyword.isBlank()) {
                return null;
            }

            String searchKeyword = "%" + keyword.toLowerCase() + "%";

            return criteriaBuilder.or(

                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            searchKeyword
                    ),

                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("description")),
                            searchKeyword
                    )
            );
        };
    }

    public static Specification<Products> ratingGreaterThan(Double ratings) {
        return (root, query, criteriaBuilder) -> {

            if (ratings == null) {
                return null;
            }

            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("ratings"),
                    ratings
            );
        };
    }
}
