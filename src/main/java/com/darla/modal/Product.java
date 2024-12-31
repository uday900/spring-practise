package com.darla.modal;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_with_image")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	private int quantity;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	private boolean available;
	private String category;
	private String Brand;
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", releaseDate=" + releaseDate + ", available=" + available + ", category="
				+ category + ", Brand=" + Brand + ", imageName=" + imageName + ", imagetype=" + imagetype
				+ ", imageData=" + Arrays.toString(imageData) + "]";
	}
	private String imageName;
	private String imagetype;
	@Lob
	private byte[] imageData;
	

	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagetype() {
		return imagetype;
	}
	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public Product(int id, String name, String description, double price, int quantity, Date releaseDate,
			boolean available, String category, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.releaseDate = releaseDate;
		this.available = available;
		this.category = category;
		Brand = brand;
	}
	public Product(int id, String name, String description, double price, int quantity, Date releaseDate,
			boolean available, String category, String brand, String imageName, String imagetype, byte[] imageData) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.releaseDate = releaseDate;
		this.available = available;
		this.category = category;
		Brand = brand;
		this.imageName = imageName;
		this.imagetype = imagetype;
		this.imageData = imageData;
	}
	public Product() {
		super();
	}
	
	

}
