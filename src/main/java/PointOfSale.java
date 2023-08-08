import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PointOfSale {
    // hash map is used to store tools to ensure unique tool codes and fast access
    private HashMap<String, Tool> toolInventory;
    private ArrayList<DateTime> holidays;

    public static String currency = "USD";
    public PointOfSale() {
        this.toolInventory = new HashMap<>();
        this.holidays = new ArrayList<>();
        this.initializeInventory();
        this.initializeFixedHolidays();
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, DateTime checkoutDate) {
        return null;
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    public void addFixedHoliday(DateTime newHoliday) {
        this.holidays.add(newHoliday);
    }

    public void removeFixedHoliday(DateTime deletedHoliday) {
        this.holidays.remove(deletedHoliday);
    }

    public RentalAgreement checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, String checkoutDate) {

        return null;
    }

    private void initializeInventory() {
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        toolInventory.put("JARK", new Tool("JARK", "Jackhammer", "Rigid", 2.99, true, false, false));

    }

    private void initializeFixedHolidays() {
        holidays.add(new DateTime("07041900")); // july 4th
    }

    public Boolean isLaborDay(DateTime date) {
        return date.monthOfYear().getAsText().equals("September") && // is september
                date.dayOfWeek().getAsText().equals("Monday") && // is monday
                Integer.parseInt(date.dayOfMonth().getAsText()) < 8; // is first monday
    }


}
