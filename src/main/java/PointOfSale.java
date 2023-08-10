

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
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
        toolInventory.put("JARK", new Tool("JARK", "Jackhammer", "Rigid"));
    }

    private void initializeToolTable() {
        toolInfoTable.put("Ladder", new ToolInfo("Ladder", 1.99, true, true, false));
        toolInfoTable.put("Chainsaw", new ToolInfo("Chainsaw", 1.49, true, false, true));
        toolInfoTable.put("Jackhammer", new ToolInfo("Jackhammer", 2.99, true, false, false));
    }


}
