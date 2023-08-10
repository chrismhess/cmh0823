

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingResourceException;

public class PointOfSale {
    // hash map is used to store tools to ensure unique tool codes and fast access
    private static HashMap<String, Tool> toolInventory;
    private static HashMap<String, ToolInfo> toolInfoTable;
    private ArrayList<LocalDate> holidays;

    public static String currency = "USD";
    public PointOfSale() {
        toolInventory = new HashMap<>();
        toolInfoTable = new HashMap<>();
        this.holidays = new ArrayList<>();
        this.initializeInventory();
        this.initializeToolTable();
    }

    public static Tool lookUpTool(String toolCode) {
        return toolInventory.get(toolCode);
    }

    public static ToolInfo getToolInfo(String toolType) {
        return toolInfoTable.get(toolType);
    }

    /**
     *
     * @param toolCode
     * @param rentalDayCount
     * @param discountPercent
     * @param checkoutDate
     * @return
     */
    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        // check to ensure tool code provided is in inventory
        if(!toolInventory.containsKey(toolCode)) {
            throw new IllegalArgumentException(String.format("Tool Code %s was not found in tool inventory. Please check tool code spelling and try again.", toolCode));

        }
        // rental day count must be greater than 1
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException(String.format("Rental Day count %s is invalid. Rental day count must be 1 day or more", rentalDayCount));

        }
        // discount values are only valid as a percent value between 0 and 100 inclusive.
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(String.format("Discount Percentage value %s is invalid. Please provide a discount value as a whole number between 0 and 100.", discountPercent));
        }
        return new RentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    public void addFixedHoliday(LocalDate newHoliday) {
        this.holidays.add(newHoliday);
    }

    public void removeFixedHoliday(LocalDate deletedHoliday) {
        this.holidays.remove(deletedHoliday);
    }

    public RentalAgreement checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, String checkoutDate) {

        return null;
    }

    private void initializeInventory() {
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
        toolInventory.put("JAKR", new Tool("JARK", "Jackhammer", "Ridgid"));
    }

    private void initializeToolTable() {
        toolInfoTable.put("Ladder", new ToolInfo("Ladder", 1.99, true, true, false));
        toolInfoTable.put("Chainsaw", new ToolInfo("Chainsaw", 1.49, true, false, true));
        toolInfoTable.put("Jackhammer", new ToolInfo("Jackhammer", 2.99, true, false, false));
    }


}
