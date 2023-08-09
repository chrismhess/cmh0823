import org.javamoney.moneta.Money;

public class ToolInfo {
    private String toolType;
    private Money dailyCharge;
    private Boolean chargeOnWeekday;
    private Boolean chargeOnWeekend;
    private Boolean chargeOnHoliday;

    public ToolInfo(String toolType, double dailyCharge, Boolean chargeOnWeekday, Boolean chargeOnWeekend,
                    Boolean chargeOnHoliday) {
        this.toolType = toolType;
        this.dailyCharge = Money.of(dailyCharge, PointOfSale.currency);
        this.chargeOnWeekday = chargeOnWeekday;
        this.chargeOnWeekend = chargeOnWeekend;
        this.chargeOnHoliday = chargeOnHoliday;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
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
