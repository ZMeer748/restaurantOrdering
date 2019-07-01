package menu;

// import java.io.BufferedReader;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.URL;
import java.util.ArrayList;
// import java.util.StringTokenizer;

import db.DBInteraction_Menu;

public class Menu {

	private static ArrayList<MenuItem> itemList = new ArrayList<>();

	static {
		getMenuContent();
		// getMenuContent("src/resources/menu.txt");
		// getMenuContent(Menu.class.getResource("resources/menu.txt"));
	}

	private static void getMenuContent() {
		itemList = DBInteraction_Menu.getMenuItemList();
	}

	// static private void getMenuContent(String filename) {
	/*
	 * static private void getMenuContent(URL url) { System.out.println(url); try {
	 * // FileReader fr = new FileReader(url.getPath()); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(url.openStream())); // File file = new
	 * File(url); String str = null; int tempCode; while ((str = br.readLine()) !=
	 * null) { MenuItem tempItem; StringTokenizer tokens = new StringTokenizer(str,
	 * "/"); tempCode = Integer.parseInt(tokens.nextToken()); tempItem = new
	 * MenuItem(tempCode, tokens.nextToken(),
	 * Double.parseDouble(tokens.nextToken())); itemList.add(tempItem); }
	 * br.close(); } catch (FileNotFoundException e) {
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
