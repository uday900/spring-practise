package com.darla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.darla.modal.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	@Query("select p from Product p where " +
		       "lower(p.name) like lower(concat('%', :keyword, '%')) or " +
		       "lower(p.description) like lower(concat('%', :keyword, '%')) or " +
		       "lower(p.category) like lower(concat('%', :keyword, '%')) or " +
		       "lower(p.Brand) like lower(concat('%', :keyword, '%'))")
	List<Product> searchProducts(String keyword);

}
