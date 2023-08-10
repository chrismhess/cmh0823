

import java.time.LocalDate;
import java.util.HashMap;
import java.util.MissingResourceException;

public class PointOfSale {
    // hash map is used to store tools to ensure unique tool codes, tool types, and fast access
    private static HashMap<String, Tool> toolInventory;
    private static HashMap<String, ToolInfo> toolInfoTable;
    public static String currency = "USD";
    public PointOfSale() {
        toolInventory = new HashMap<>();
        toolInfoTable = new HashMap<>();
        this.initializeInventory();
        this.initializeToolInfoTable();
    }

    public static Tool getToolFromInventory(String toolCode) {
        Tool foundTool =  toolInventory.get(toolCode);
        if(foundTool == null) {
            throw new IllegalArgumentException(String.format("Tool Code %s was not found in tool inventory. Please check" +
                    " tool code spelling and try again.", toolCode));
        }
        return foundTool;
    }

    /**
     * A method for getting tool info by providing the tool type
     * @param toolType the type of tool we are trying to look up
     * @return the ToolInfo object if found, otherwise throw exception and end execution to avoid null pointer
     * exceptions where the hash map would return null if nothing is found.
     */
    public static ToolInfo getToolInfo(String toolType) {
        ToolInfo foundToolInfo = toolInfoTable.get(toolType);
        if(foundToolInfo == null) {
            throw new MissingResourceException("Provided tool code was found in inventory but tool type info was " +
                    "missing in system, please contact support for assistance or try a similar tool code.", "ToolInfo",
                    toolType);
        }
        return foundToolInfo;
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
        return new RentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    public RentalAgreement checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, String checkoutDate) {

        return null;
    }

    /**
     * This method initializes the inventory to hold all the tools defined in the specification with some additional
     * inventory for testing purposes
     */
    private void initializeInventory() {
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
        toolInventory.put("JAKR", new Tool("JARK", "Jackhammer", "Ridgid"));
        toolInventory.put("MissingInfo", new Tool("MissingInfo", "missing", "missing"));
    }

    /**
     * this method initializes the tool information table to meet the provided specification. Tool and Tool info
     * could have been combined but given the need to duplicate tool info stored in tools of the same type it made more
     * sense to instead store the information in two different tables with toolType being the key shared between objects
     */
    private void initializeToolInfoTable() {
        toolInfoTable.put("Ladder", new ToolInfo("Ladder", 1.99, true, true, false));
        toolInfoTable.put("Chainsaw", new ToolInfo("Chainsaw", 1.49, true, false, true));
        toolInfoTable.put("Jackhammer", new ToolInfo("Jackhammer", 2.99, true, false, false));
    }


}
