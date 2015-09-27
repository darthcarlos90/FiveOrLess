package com.classes;

public class Dish {

	private int id;
	private String name;
	private double price;
	private String description;
	private boolean isMain;

	/**
	 * Constructor of Dish class.
	 * 
	 * @param id
	 *            The id of the dish.
	 * @param name
	 *            The name of the dish.
	 * @param price
	 *            The price of the dish.
	 * @param description
	 *            The description of the dish.
	 * @param isMain
	 *            Is it the main dish?
	 */
	public Dish(int id, String name, double price, String description,
			boolean isMain) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.isMain = isMain;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
