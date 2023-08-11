import org.javamoney.moneta.Money;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

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
        ToolInfo info = PointOfSale.getToolInfo(tool.getToolType());
        this.toolCode = toolCode;
        this.rentalDuration = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkOutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDayCount);
        this.chargeableDays = 0;
        this.dailyRentalCharge = info.getDailyCharge();
        this.toolBrand = tool.getToolBrand();
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

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Money getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(Money dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeableDays() {
        return chargeableDays;
    }

    public void setChargeableDays(int chargeableDays) {
        this.chargeableDays = chargeableDays;
    }

    public Money getPreDiscountPrice() {
        return preDiscountPrice;
    }

    public void setPreDiscountPrice(Money preDiscountPrice) {
        this.preDiscountPrice = preDiscountPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Money finalPrice) {
        this.finalPrice = finalPrice;
    }
}
