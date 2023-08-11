import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToolInfoTest {
    ToolInfo testToolInfo;
    @BeforeEach
    void init(){
        testToolInfo = new ToolInfo("Jackhammer",
                                    2.99,
                                    true,
                                    false,
                                    false);
    }
    @Test
    void getToolType() {
        assertEquals("Jackhammer", testToolInfo.getToolType());
    }
    @Test
    void getDailyCharge() {
        assertEquals(Money.of(2.99, PointOfSale.currency), testToolInfo.getDailyCharge());
    }
    @Test
    void getChargeOnWeekday() {
        assertTrue(testToolInfo.getChargeOnWeekday());
    }
    @Test
    void getChargeOnWeekend() {
        assertFalse(testToolInfo.getChargeOnWeekend());
    }
    @Test
    void getChargeOnHoliday() {
        assertFalse(testToolInfo.getChargeOnHoliday());
    }
}