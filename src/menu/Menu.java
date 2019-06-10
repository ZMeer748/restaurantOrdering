package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.Test;

public class Menu {

	static ArrayList<MenuItem> itemList = new ArrayList<>();

	public static void main(String[] args) {
		getList_test();
	}

	@Test
	static void getList_test() {
		getMenuContent("menu.txt");
		for (MenuItem item : itemList) {
			System.out.println(item.code);
		}
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
			// e.printStackTrace();
			System.out.println("We lose the data for the menu.");
		} catch (NumberFormatException | IOException e) {
			// e.printStackTrace();
			System.out.println("There is something wrong.");
		}
	}

}
