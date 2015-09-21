package com.classes;

public class Dish {
	
	private String name;
	private float price;
	private String description;
	private boolean isMain;
	
	public Dish(String name, float price, String description, boolean isMain) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.isMain = isMain;
	}
	
	public Dish(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

}
