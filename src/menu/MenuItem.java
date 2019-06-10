package menu;

public class MenuItem {

	public MenuItem(int code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		if (code > 20)
			sort = "Food";
		else
			sort = "Drink";
	}

	int code;
	String name, sort;
	double price;

}
