package menu;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuItem {

	private int id;

	private int num;

	private String name;

	private String sort;

	private double price;

	@JSONField(serialize = false)
	private String imageURL;

	public MenuItem() {
	}

	public MenuItem(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		if (id > 20)
			sort = "Drink";
		else
			sort = "Food";
		this.num = 0;
		imageURL = "resources/img/" + name + ".png";
	}

	public MenuItem(int id, String name, double price, String sort, String imageURL) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.sort = sort;
		this.num = 0;
		this.imageURL = imageURL;
	}

	@JSONField(name = "ID", ordinal = 1)
	public int getID() {
		return id;
	}

	@JSONField(name = "NAME", ordinal = 2)
	public String getName() {
		return name;
	}

	@JSONField(name = "PRICE", ordinal = 3)
	public double getPrice() {
		return price;
	}

	@JSONField(name = "SORT", ordinal = 4)
	public String getSort() {
		return sort;
	}

	@JSONField(name = "NUM", ordinal = 5)
	public int getNum() {
		return num;
	}

	@JSONField(name = "ID", ordinal = 1)
	public void setID(int id) {
		this.id = id;
	}

	@JSONField(name = "NAME", ordinal = 2)
	public void setName(String name) {
		this.name = name;
	}

	@JSONField(name = "PRICE", ordinal = 3)
	public void setPrice(double price) {
		this.price = price;
	}

	@JSONField(name = "SORT", ordinal = 4)
	public void setSort(String sort) {
		this.sort = sort;
	}

	@JSONField(name = "NUM", ordinal = 5)
	public void setNum(int num) {
		this.num = num;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
