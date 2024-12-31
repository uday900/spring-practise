package com.darla.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.darla.modal.Product;
import com.darla.services.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> getMethodName() {
		return service.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getMethodName(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	@GetMapping("/products/{id}/image")
	public ResponseEntity<byte[]> m(@PathVariable int id){
		
		Product product = service.getProductById(id);
		byte[] imageFile = product.getImageData();
		
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(product.getImagetype()))
			.body(imageFile);
		
		
	}
	
	@PostMapping("/product")
	public void postMethodName(@RequestPart Product product,
			@RequestPart MultipartFile imageFile) {
		
		try{
			service.addProduct(product, imageFile);
		} catch(Exception e) {
			System.out.println("failed to post");
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> putMethodName(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile) throws IOException {
		
//		Product pro = service.getProductById(id);
//				
//		if ( pro != null ) {
//			service.updateProduct(id, product, imageFile);
//			return new ResponseEntity<>("updated", HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		
		Product product1 = null;
        try {
            product1 = service.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (product1 != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product pro = service.getProductById(id);
		if ( pro != null ) {
			service.deleteProductbyId(id);
			return new ResponseEntity<String>("deleted.", HttpStatus.GONE);
		}
		return new ResponseEntity<String>("failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		
		System.out.println(keyword);
		List<Product> products = service.getSearchProducts(keyword);
		return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
		
		
	}
	
	
	
	

}
