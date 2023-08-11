import org.javamoney.moneta.Money;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * All the setter methods were intentionally left in to serve as a means of an adhoc update to an incorrect or existing
 * rental agreement that needed to be updated. If they are to be used they do not update the related fields and multiple
 * parts will need to be updated to create a rental agreement. But there was a possible need for manual input
 * for changing these given the potential for inconsistencies working with customers.
 */
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDuration;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private Money dailyRentalCharge;
    private int chargeableDays;
    private Money preDiscountPrice;
    private int discountPercent;
    private Money discountAmount;
    private Money finalPrice;

    /**
     *  Constructor for rental agreement takes the following parameters and generates the remaining information based
     *  upon these parameters. It also has a check in place that if for some reason the tool information is missing
     *  from that table it will exit elegantly with an error message informing the user of a problem with the system, not
     *  their input.
     * @param toolCode the code for tool being rented
     * @param rentalDayCount the number of days as an integer representing the duration of the rental period
     * @param discountPercent the discount percent represented as an int value from 0-100
     * @param checkoutDate the date for when the rental period begins
     */
    public RentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        // rental day count must be greater than 1
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException(String.format("Rental Day count %s is invalid. Rental day count must be" +
                    " 1 day or more", rentalDayCount));
        }
        // discount values are only valid as a percent value between 0 and 100 inclusive.
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(String.format("Discount Percentage value %s is invalid. Please provide a" +
                    " discount value as a whole number between 0 and 100.", discountPercent));
        }
        Tool tool = PointOfSale.getToolFromInventory(toolCode);
        ToolInfo info = PointOfSale.getToolInfo(tool.toolType());
        this.toolCode = toolCode;
        this.rentalDuration = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkOutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDayCount);
        this.chargeableDays = 0;
        this.dailyRentalCharge = info.getDailyCharge();
        this.toolBrand = tool.toolBrand();
        List<LocalDate> holidays = calculateHolidays(checkoutDate, rentalDuration);
        LocalDate currentDate = this.checkOutDate;
        int chargeableDays = 0;
        for (int i = 0; i < rentalDayCount; i++) {
            currentDate = currentDate.plusDays(1);
            if (currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // if it's a weekend
                if (info.getChargeOnWeekend()) {
                    this.chargeableDays++;
                }
            } else {// if it's not a weekend
                // if holiday and weekday true no need to check if its holiday
                if (info.getChargeOnHoliday() && info.getChargeOnWeekday()) {
                    this.chargeableDays++;
                } else if (holidays.contains(currentDate) ) { // if it's a holiday and chargeable
                    if (info.getChargeOnHoliday()) {
                        this.chargeableDays++;
                    }
                } else if (info.getChargeOnWeekday()) { // it's not a holiday, check if chargeable and add to days
                    this.chargeableDays++;
                }

            }
        }
        this.preDiscountPrice = info.getDailyCharge().multiply(this.chargeableDays);
        this.discountAmount = preDiscountPrice.multiply((discountPercent*1.0/100.0));
        this.finalPrice = preDiscountPrice.subtract(discountAmount);
    }

    /**
     * This function takes the provided checkout date and the rental duration to determine how many relevant
     * holidays will need to be known for determine chargeable days. It also handles cases where a customer
     * wants to rent a tool for multiple years and there could be multiple years of holidays to cover.
     * @param checkoutDate the date specified for checkout
     * @param rentalDuration the number of days represented as an int that the tool will be checked out for
     * @return a List of LocalDate Object representing all the relevant holidays for this rental agreement.
     */
    private List<LocalDate> calculateHolidays(LocalDate checkoutDate, int rentalDuration) {
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(getJulyFourthHolidayForYear(checkoutDate.getYear()));
        holidays.add(getLaborDayHolidayForYear(checkoutDate.getYear()));
        LocalDate localDate2 = LocalDate.of(checkoutDate.getYear(), 12, 31);
        int daysRemainingInYear = (int) ChronoUnit.DAYS.between(checkoutDate, localDate2);
        if (rentalDuration > daysRemainingInYear) {
            int currentYear = checkoutDate.getYear();
            rentalDuration = rentalDuration-daysRemainingInYear;
            while (rentalDuration > 0) {
                currentYear++;
                holidays.add(getJulyFourthHolidayForYear(currentYear));
                holidays.add(getLaborDayHolidayForYear(currentYear));
                // this declares a new LocalDate to get length of a year to account for potential leap years.
                rentalDuration = rentalDuration-LocalDate.of(currentYear,1,1).lengthOfYear();
            }
        }
        return holidays;
    }

    /**
     * This determines when the july fourth holiday is observed for a given year. It checks if the holiday lands on
     * a saturday and returns the friday prior as the date considered a holiday, and if it lands on a sunday it returns
     * the following monday as the day considered the holiday.
     * @param year the year that we want to determine when july fourth is observed
     * @return The LocalDate object that represents which date the holiday applies for our system calculation
     */
    private LocalDate getJulyFourthHolidayForYear(int year) {
        LocalDate julyFourthHoliday = LocalDate.of(year,7,4);
        if (julyFourthHoliday.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            julyFourthHoliday = julyFourthHoliday.minusDays(1);
        } else if (julyFourthHoliday.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            julyFourthHoliday = julyFourthHoliday.plusDays(1);
        }
        return julyFourthHoliday;
    }

    /**
     * This function takes a year represented as an int for which year we want to determine when the labor day holiday
     * actually applies. It then uses the first day in month method from java time to get the first monday of the month
     * of september in the given year and returns that date.
     * @param year the year that we want to determine labor day's date
     * @return a LocalDate object that represents which day labor day will be applied for our system's use
     */
    private LocalDate getLaborDayHolidayForYear(int year) {
        return LocalDate.of(year, 9,1).with(firstInMonth(DayOfWeek.MONDAY));
    }

    /**
     * Gets the tool type for the given tool object
     * @return the string representing the tool type
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * This takes string to set the type of tool in the rental agreement.
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due to a dispute or other issue during the rental process.
     * @param toolType the tool type for this rental agreement
     */
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }


    /**
     * Gets the tool code for the given tool object
     * @return the string representing the tool code
     */
    public String getToolCode() {
        return toolCode;
    }

    /**
     * This takes string to set the type of code in the rental agreement.
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due to a dispute or other issue during the rental process.
     * @param toolCode the new tool code for this rental agreement
     */public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    /**
     * Gets the tool brand for the given tool object
     * @return the string representing the tool brand
     */
    public String getToolBrand() {
        return toolBrand;
    }

    /**
     * This takes string to set the type of brand in the rental agreement.
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due to a new or different tool brand being available at the time
     * @param toolBrand the new tool brand for this rental agreement
     */
    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    /**
     * Gets the duration of this rental agreement
     * @return the integer representing the duration of this rental agreemnt
     */
    public int getRentalDuration() {
        return rentalDuration;
    }

    /**
     * This takes integer to set the rental duration of the agreement
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due an early or late return of a tool or other reason.
     * @param rentalDuration the new rental duration for this rental agreement
     */
    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    /**
     * Gets the date the tool is initially checked out
     * @return the LocalDate object that stores the checkout date for this rental agreement
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * This takes a date object to set the checkout date
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due an incorrect or moved checkout date
     * @param checkOutDate the new checkout date for this rental agreement
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Gets the date that this rental agreement ends and the tool is due to be returned
     * @return the LocalDate object that stores the date the tool needs to be returned
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * This takes a date object to set the return date
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement due change in when the tool may be due back
     * @param dueDate the new due date for this rental agreement
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the Money object representing how much this tool costs to rent per day
     * @return the Money object
     */
    public Money getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    /**
     * This takes a Money object for setting the daily rental charge
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a new or different daily rate to be applied.
     * @param dailyRentalCharge the new daily charge for this rental agreement
     */
    public void setDailyRentalCharge(Money dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    /**
     * Gets the number of days that are actually billed during this rental agreement period
     * @return the integer representing how many days the customer was charged for
     */
    public int getChargeableDays() {
        return chargeableDays;
    }

    /**
     * This takes an integer for setting the number of chargeable days in the rental agreement
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a need to change how many days were chargeable
     * @param chargeableDays the new number of chargeable days on the rental agreement
     */
    public void setChargeableDays(int chargeableDays) {
        this.chargeableDays = chargeableDays;
    }

    /**
     * Gets the Monetary value that indicates how much of a discount the customer will receive over the rental period
     * @return Money object
     */
    public Money getPreDiscountPrice() {
        return preDiscountPrice;
    }

    /**
     * This takes a Money object for setting how much total is before discount
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a need to change the pre discount price
     * @param preDiscountPrice the new pre discount total for the rental agreement
     */
    public void setPreDiscountPrice(Money preDiscountPrice) {
        this.preDiscountPrice = preDiscountPrice;
    }

    /**
     * Integer representing the percentage discount a customer is receiving between 0-100
     * @return Integer value
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * This takes an integer indicating the new amount of discount to be applied
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a need to change amount discounted
     * @param discountPercent the new discount percentage
     */
    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * Gets the Monetary value that indicates how much the discount amount will be round up from half cents.
     * @return Money object
     */
    public Money getDiscountAmount() {
        return discountAmount;
    }

    /**
     * This takes a Money object for setting how much of a discount value will be applied
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a need to change amount discounted
     * @param discountAmount the new discount amount
     */
    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Gets the Monetary value that indicates how much the final price the customer will pay
     * @return Money object
     */
    public Money getFinalPrice() {
        return finalPrice;
    }

    /**
     * This takes a Money object for setting how much the final total will be
     * The setter methods were intentionally included in case there may be a need in the future for a manger to override
     * an existing rental agreement if there is a need to change final total
     * @param finalPrice the new discount amount
     */
    public void setFinalPrice(Money finalPrice) {
        this.finalPrice = finalPrice;
    }
}
