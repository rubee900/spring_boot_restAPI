package com.techtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtech.dto.ProductDTO;
import com.techtech.enitity.ProductEntity;
import com.techtech.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void deleteById(int id) {
		productRepository.deleteById(id);
	}
	
	public List<ProductDTO> findAll(){
		List<ProductEntity> list= productRepository.findAll();
		return convertEntityDTO(list);
	}
	
	public void save(ProductDTO productDTO) {
		ProductEntity entity= new ProductEntity();
		BeanUtils.copyProperties(productDTO, entity);
		productRepository.save(entity);
	}
	
	public List<ProductDTO> searchProduct(String stext){
		List<ProductEntity> list = productRepository.findByNameContainingOrCategoryContaining(stext, stext);
		return convertEntityDTO(list);
	}
	
	/*
	public List<ProductEntity> searchProduct1(String searchPro){
		return productRepository.findByNameContainingOrCategoryContaining(searchPro, searchPro);
	}
	*/
	
	private List<ProductDTO> convertEntityDTO(List<ProductEntity> list){
		List<ProductDTO> dtos= new ArrayList<ProductDTO>();
		for(ProductEntity entity:list){
			ProductDTO dto= new ProductDTO();
			BeanUtils.copyProperties(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}
	
}
