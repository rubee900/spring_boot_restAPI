package com.techtech.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtech.enitity.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

	public List<ProductEntity> findByNameContainingOrCategoryContaining(String name, String category);

}
