package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.Test;

public class Menu {

	static private Map<Integer, MenuItem> itemMap = new TreeMap<>();

	public static void main(String[] args) {
		getList_test();
	}
	
	static {
		getMenuContent("menu.txt");
	}

	@Test
	public static void getList_test() {
		getMenuContent("menu.txt");
		for (Map.Entry<Integer, MenuItem> item : itemMap.entrySet()) {
			System.out.println(item.getValue().getName());
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
				itemMap.put(tempCode, tempItem);
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

	static public int getNum() {
		return itemMap.size();
	}

	static public Set<Entry<Integer, MenuItem>> getEntrySet() {
		return itemMap.entrySet();
	}

	static public MenuItem getItem(int inCode) {
		return itemMap.get(inCode);
	}

}
