package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Menu {

	static private Map<Integer, MenuItem> itemMap = new TreeMap<>();

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
				itemMap.put(tempCode, tempItem);
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
		return itemMap.size();
	}

	static public Set<Map.Entry<Integer, MenuItem>> getEntrySet() {
		return itemMap.entrySet();
	}

	static public MenuItem getItem(int inCode) {
		return itemMap.get(inCode);
	}

}
