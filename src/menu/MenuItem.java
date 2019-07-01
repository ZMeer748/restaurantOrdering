package menu;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuItem {

	@JSONField(name = "ID", ordinal = 1)
	private int id;

	@JSONField(name = "NUM", ordinal = 5)
	private int num;

	@JSONField(name = "NAME", ordinal = 2)
	private String name;

	@JSONField(name = "SORT", ordinal = 4)
	private String sort;

	@JSONField(name = "PRICE", ordinal = 3)
	private double price;

	@JSONField(serialize = false)
	private String imageURL;

	public MenuItem(int code, String name, double price) {
		this.id = code;
		this.name = name;
		this.price = price;
		if (code > 20)
			sort = "Drink";
		else
			sort = "Food";
		this.num = 0;
		imageURL = "resources\\img\\" + name + ".png";
	}

	public MenuItem(int code, String name, double price, String sort, String imageURL) {
		this.id = code;
		this.name = name;
		this.price = price;
		this.sort = sort;
		this.num = 0;
		this.imageURL = imageURL;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getSort() {
		return sort;
	}

	public int getNum() {
		return num;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

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
