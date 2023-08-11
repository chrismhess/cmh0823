import org.javamoney.moneta.Money;

public class ToolInfo {
    private final String toolType;
    private final Money dailyCharge;
    private final Boolean chargeOnWeekday;
    private final Boolean chargeOnWeekend;
    private final Boolean chargeOnHoliday;

    public ToolInfo(String toolType, double dailyCharge, Boolean chargeOnWeekday, Boolean chargeOnWeekend,
                    Boolean chargeOnHoliday) {
        this.toolType = toolType;
        this.dailyCharge = Money.of(dailyCharge, PointOfSale.currency);
        this.chargeOnWeekday = chargeOnWeekday;
        this.chargeOnWeekend = chargeOnWeekend;
        this.chargeOnHoliday = chargeOnHoliday;
    }

    /**
     * This class is purposefully missing the setter methods as the fields are set as part of the constructor's logic
     * and once these fields are created they should not be edited by any other object given they represent tool info in
     * the tool info collection.
     */

    public String getToolType() {
        return toolType;
    }

    /**
     * Gets the daily charge amount for that toolInfo object
     * @return Money object representing the cost to rent the tool for a day
     */
    public Money getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Gets the boolean value indicating whether this toolType will be charged on a weekday
     * @return boolean indicating whether this toolType will be charged on a weekday
     */
    public Boolean getChargeOnWeekday() {
        return chargeOnWeekday;
    }
    /**
     * Gets the boolean value indicating whether this toolType will be charged on a weekend
     * @return boolean indicating whether this toolType will be charged on a weekend
     */
    public Boolean getChargeOnWeekend() {
        return chargeOnWeekend;
    }
    /**
     * Gets the boolean value indicating whether this toolType will be charged on a holiday
     * @return boolean indicating whether this toolType will be charged on a holiday
     */
    public Boolean getChargeOnHoliday() {
        return chargeOnHoliday;
    }
}
