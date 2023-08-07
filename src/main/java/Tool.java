import javax.money.MonetaryAmount;

public class Tool {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private MonetaryAmount dailyCharge;
    private Boolean chargeOnWeekday;
    private Boolean chargeOnWeekend;
    private Boolean chargeOnHoliday;

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

    public MonetaryAmount getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(MonetaryAmount dailyCharge) {
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
