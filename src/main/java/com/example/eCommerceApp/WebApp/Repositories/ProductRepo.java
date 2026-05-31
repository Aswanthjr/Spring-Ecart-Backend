package com.example.eCommerceApp.WebApp.Repositories;

import com.example.eCommerceApp.WebApp.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Products,Long>, JpaSpecificationExecutor<Products> {

}
