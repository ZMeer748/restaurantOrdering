package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Menu {

	static private ArrayList<MenuItem> itemList = new ArrayList<>();

	static {
		getMenuContent("menu.txt");
	}

	static private void getMenuContent(String filename) {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			int tempCode;
			while ((str = br.readLine()) != null) {
				MenuItem tempItem;
				StringTokenizer tokens = new StringTokenizer(str, "/");
				tempCode = Integer.parseInt(tokens.nextToken());
				tempItem = new MenuItem(tempCode, tokens.nextToken(), Double.parseDouble(tokens.nextToken()));
				itemList.add(tempItem);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("We lose the data for the menu.");
			e.printStackTrace();
		} catch (NumberFormatException | IOException e) {
			System.out.println("There is something wrong.");
			e.printStackTrace();
		}
	}

	static public int getNum() {
		return itemList.size();
	}

	static public ArrayList<MenuItem> getList() {
		return itemList;
	}

	static public MenuItem getItem(int inCode) {
		return itemList.get(inCode);
	}

}
