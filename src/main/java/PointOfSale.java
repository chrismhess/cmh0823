import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * A method for querying the tool inventory given a string
     * @param toolCode the tool code for the tool in the inventory collection we are trying to find
     * @return the tool if present, otherwise the method will throw an exception informing the user the tool is not
     * in the inventory list
     */
    public static Tool getToolFromInventory(String toolCode) {
        Tool foundTool =  toolInventory.get(toolCode);
        if (foundTool == null) {
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
        if (foundToolInfo == null) {
            throw new MissingResourceException("Provided tool code was found in inventory but tool type info was " +
                    "missing in system, please contact support for assistance or try a similar tool code.", "ToolInfo",
                    toolType);
        }
        return foundToolInfo;
    }

    /**
     * This function serves as the checkout method for creating a rental agreement
     * @param toolCode the code for tool being rented
     * @param rentalDayCount the number of days as an integer representing the duration of the rental period
     * @param discountPercent the discount percent represented as an int value from 0-100
     * @param checkoutDate the date for when the rental period begins as a LocalDate object
     * @return a complete rental agreement containing all the correct attributes per the specification
     */
    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        return new RentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);
    }

    public void addTool(Tool tool) {
        toolInventory.put(tool.getToolCode(), tool);
    }

    /**
     *
     * This function serves as the checkout method for creating a rental agreement, it is overloaded to allow for using
     * either a date object or a string to be turned into a date object for flexibility
     * @param toolCode the code for tool being rented
     * @param rentalDayCount the number of days as an integer representing the duration of the rental period
     * @param discountPercent the discount percent represented as an int value from 0-100
     * @param checkoutDate the date as a string matching the instructions specification format mm/dd/yyyy to be
     *                     converted to LocalDate object for use
     * @return a complete rental agreement containing all the correct attributes per the specification
     */
    public RentalAgreement checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, String checkoutDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return new RentalAgreement(toolCode, rentalDayCount, discountPercent, LocalDate.parse(checkoutDate, formatter));
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
