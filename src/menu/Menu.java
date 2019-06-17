package menu;

import java.util.ArrayList;

import db.DB_IO;

public class Menu {

	private static ArrayList<MenuItem> itemList = new ArrayList<>();

	static {
		getMenuContent();
	}

	private static void getMenuContent() {
		itemList = DB_IO.getItemList();
	}

	/*
	 * static private void getMenuContent(String filename) { try { FileReader fr =
	 * new FileReader(filename); BufferedReader br = new BufferedReader(fr); String
	 * str = null; int tempCode; while ((str = br.readLine()) != null) { MenuItem
	 * tempItem; StringTokenizer tokens = new StringTokenizer(str, "/"); tempCode =
	 * Integer.parseInt(tokens.nextToken()); tempItem = new MenuItem(tempCode,
	 * tokens.nextToken(), Double.parseDouble(tokens.nextToken()));
	 * itemList.add(tempItem); } br.close(); } catch (FileNotFoundException e) {
	 * System.out.println("We lose the data for the menu."); e.printStackTrace(); }
	 * catch (NumberFormatException | IOException e) {
	 * System.out.println("There is something wrong."); e.printStackTrace(); } }
	 */

	public static int getNum() {
		return itemList.size();
	}

	public static ArrayList<MenuItem> getList() {
		return itemList;
	}

	public static MenuItem getItem(int inCode) {
		return itemList.get(inCode);
	}

}
