package com.darla.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.darla.modal.Product;
import com.darla.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(new Product());
	}

	
	public void addProduct(Product product, MultipartFile image) throws IOException {
		product.setImageData(image.getBytes());
		product.setImageName(image.getOriginalFilename());
		product.setImagetype(image.getContentType());
		
		repository.save(product);
		
	}

	public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		product.setId(id);
		product.setImageData(imageFile.getBytes());
		product.setImageName(imageFile.getOriginalFilename());
		product.setImagetype(imageFile.getContentType());
		
		repository.save(product);
		//		repository
		return null;
	}

	public void deleteProductbyId(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	public List<Product> getSearchProducts(String keyword) {
		// TODO Auto-generated method stub
		return repository.searchProducts(keyword);
	}
}
