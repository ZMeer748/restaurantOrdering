package menu;

public class MenuItem {

	private int code;
	private String name, sort;
	private double price;

	public MenuItem(int code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		if (code > 20)
			sort = "Food";
		else
			sort = "Drink";
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

}
