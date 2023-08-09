import org.joda.time.DateTime;
import javax.money.MonetaryAmount;

public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDuration;
    private DateTime checkOutDate;
    private DateTime returnDate;
    private MonetaryAmount dailyRentalCharge;
    private int chargeableDays;
    private MonetaryAmount preDiscountPrice;
    private int discountPercent;
    private MonetaryAmount discountAmount;
    private MonetaryAmount finalPrice;

    public RentalAgreement(String toolCode, int rentalDayCount, int discountPercent, DateTime checkoutDate) {
        Tool tool = PointOfSale.lookUpTool(toolCode);
        ToolInfo info = PointOfSale.getToolInfo(tool.getToolType());
        this.rentalDuration = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkOutDate = checkoutDate;

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

    public DateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(DateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public DateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

    public MonetaryAmount getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(MonetaryAmount dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeableDays() {
        return chargeableDays;
    }

    public void setChargeableDays(int chargeableDays) {
        this.chargeableDays = chargeableDays;
    }

    public MonetaryAmount getPreDiscountPrice() {
        return preDiscountPrice;
    }

    public void setPreDiscountPrice(MonetaryAmount preDiscountPrice) {
        this.preDiscountPrice = preDiscountPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public MonetaryAmount getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(MonetaryAmount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public MonetaryAmount getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(MonetaryAmount finalPrice) {
        this.finalPrice = finalPrice;
    }
}
