package order;

import java.util.ArrayList;
import java.util.Date;

import menu.MenuItem;

/**
 * OrderRecord
 */
public class OrderRecord {

    private int id;
    private int userId;
    private ArrayList<MenuItem> itemList = new ArrayList<>();
    private int numOfCustomer;
    private String remarks;
    private float total;
    private Date date;

    public OrderRecord(int id, int userId, ArrayList<MenuItem> itemList, int numOfCustomer, String remarks, float total, Date date) {
        this.id = id;
        this.userId = userId;
        this.itemList = itemList;
        this.numOfCustomer = numOfCustomer;
        this.remarks = remarks;
        this.total = total;
        this.date = date;
    }

    public OrderRecord() {
	}

	public static void main(String[] args) {
        System.out.println();
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
    public ArrayList<MenuItem> getItemList() {
        return itemList;
    }

    public int getNumOfCustomer() {
        return numOfCustomer;
    }

    public String getRemarks() {
        return remarks;
    }

    public float getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}