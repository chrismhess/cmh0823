import org.javamoney.moneta.Money;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public RentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        Tool tool = PointOfSale.lookUpTool(toolCode);
        ToolInfo info = PointOfSale.getToolInfo(tool.getToolType());
        this.toolCode = toolCode;
        this.rentalDuration = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkOutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDayCount);
        this.chargeableDays = 0;
        this.dailyRentalCharge = info.getDailyCharge();
        this.toolBrand = tool.getToolBrand();
        List<LocalDate> holidays = calculateHolidays(checkoutDate);
        LocalDate currentDate = this.checkOutDate.plusDays(1);
        int chargeableDays = 0;
        for(int i = 0; i < rentalDayCount; i++) {
            if(currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // if it's a weekend
                if(info.getChargeOnWeekend()) {
                    this.chargeableDays++;
                }
            } else {// if it's not a weekend
                if(holidays.contains(currentDate) && info.getChargeOnHoliday()) { // if it's a holiday and chargeable
                    this.chargeableDays++;
                } else if (info.getChargeOnWeekday()) { // it's not a holiday, check if chargeable and add to days
                    this.chargeableDays++;
                }

            }
            currentDate = currentDate.plusDays(1);
        }
        this.preDiscountPrice = info.getDailyCharge().multiply(this.chargeableDays);
        this.discountAmount = preDiscountPrice.multiply((discountPercent*1.0/100.0));
        this.finalPrice = preDiscountPrice.subtract(discountAmount);


    }

    /**
     * to handle multiple years pass checkout length, compare against whether it goes beyond current year, and if it does detereine holidays for all
     * years relevant
     * @param checkoutDate
     * @return
     */
    private List<LocalDate> calculateHolidays(LocalDate checkoutDate) {
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(getJulyFourthHolidayForYear(checkoutDate.getYear()));
        holidays.add(getLaborDayForYear(checkoutDate.getYear()));
        return holidays;
    }

    private LocalDate getJulyFourthHolidayForYear(int year) {
        LocalDate julyFourthHoliday = LocalDate.of(year,7,4);
        if(julyFourthHoliday.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            julyFourthHoliday = julyFourthHoliday.minusDays(1);
        }
        if(julyFourthHoliday.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            julyFourthHoliday = julyFourthHoliday.plusDays(1);
        }
        return julyFourthHoliday;
    }

    private LocalDate getLaborDayForYear(int year) {
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
