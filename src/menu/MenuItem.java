package menu;

public class MenuItem {

	private int code, num;
	private String name, sort;
	private double price;
	private byte[] imageByteData;

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

	public MenuItem(int code, String name, double price, String sort, byte[] imageByteData) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.sort = sort;
		this.num = 0;
		this.imageByteData = imageByteData;
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

	public byte[] getImageByteData() {
		return imageByteData;
	}

	public void setImageByteData(byte[] imageByteData) {
		this.imageByteData = imageByteData;
	}

}
