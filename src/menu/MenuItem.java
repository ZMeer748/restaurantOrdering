package menu;

public class MenuItem {

	private int code, num;
	private String name, sort;
	private double price;
	private String imageURL;

	public MenuItem(int code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		if (code > 20)
			sort = "Drink";
		else
			sort = "Food";
		this.num = 0;
	}

	public MenuItem(int code, String name, double price, String sort, String imageURL) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.sort = sort;
		this.num = 0;
		this.imageURL = imageURL;
	}

	public int getCode() {
		return code;
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
