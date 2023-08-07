import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;

public class PointOfSale {
    // hash map is used to store tools to ensure unique tool codes and fast access
    private HashMap<String, Tool> toolInventory;
    private ArrayList<DateTime> holidays;
    public PointOfSale() {
        this.toolInventory = new HashMap<>();
        this.holidays = new ArrayList<>();
        this.initializeInventory();
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, DateTime checkoutDate) {
        return null;
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    public void addHoliday(DateTime newHoliday) {
        this.holidays.add(newHoliday);
    }

    public void removeHoliday(DateTime deletedHoliday) {
        this.holidays.remove(deletedHoliday);
    }

    private void initializeInventory() {
    }


}
