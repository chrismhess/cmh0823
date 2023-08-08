import org.javamoney.moneta.Money;

public class Tool {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Money dailyCharge;
    private Boolean chargeOnWeekday;
    private Boolean chargeOnWeekend;
    private Boolean chargeOnHoliday;

    public Tool(String toolCode, String toolType, String toolBrand, double dailyCharge, Boolean chargeOnWeekday, Boolean chargeOnWeekend, Boolean chargeOnHoliday) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.dailyCharge = Money.of(dailyCharge, PointOfSale.currency);
        this.chargeOnWeekday = chargeOnWeekday;
        this.chargeOnWeekend = chargeOnWeekend;
        this.chargeOnHoliday = chargeOnHoliday;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public Money getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = Money.of(dailyCharge, PointOfSale.currency);;
    }

    public void setDailyCharge(Money dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Boolean getChargeOnWeekday() {
        return chargeOnWeekday;
    }

    public void setChargeOnWeekday(Boolean chargeOnWeekday) {
        this.chargeOnWeekday = chargeOnWeekday;
    }

    public Boolean getChargeOnWeekend() {
        return chargeOnWeekend;
    }

    public void setChargeOnWeekend(Boolean chargeOnWeekend) {
        this.chargeOnWeekend = chargeOnWeekend;
    }

    public Boolean getChargeOnHoliday() {
        return chargeOnHoliday;
    }

    public void setChargeOnHoliday(Boolean chargeOnHoliday) {
        this.chargeOnHoliday = chargeOnHoliday;
    }
}
