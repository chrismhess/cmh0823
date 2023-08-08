import static org.junit.jupiter.api.Assertions.*;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;

class ToolTest {
    Tool testTool;

    @BeforeEach
    void init(){
        testTool = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
    }

    @org.junit.jupiter.api.Test
    void testGetToolCode() {
        assertEquals("CHNS",testTool.getToolCode());
    }

    @org.junit.jupiter.api.Test
    void testSetToolCode() {
        assertEquals("CHNS",testTool.getToolCode());
        testTool.setToolCode("test");
        assertEquals("test",testTool.getToolCode());
    }

    @org.junit.jupiter.api.Test
    void getToolType() {
        assertEquals("Chainsaw", testTool.getToolType());
    }

    @org.junit.jupiter.api.Test
    void testSetToolType() {
        assertEquals("Chainsaw", testTool.getToolType());
        testTool.setToolType("test");
        assertEquals("test",testTool.getToolType());
    }

    @org.junit.jupiter.api.Test
    void getToolBrand() {
        assertEquals("Stihl", testTool.getToolBrand());
    }

    @org.junit.jupiter.api.Test
    void testSetToolBrand() {
        assertEquals("Stihl", testTool.getToolBrand());
        testTool.setToolBrand("test");
        assertEquals("test",testTool.getToolBrand());
    }

    @org.junit.jupiter.api.Test
    void getDailyCharge() {
        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
    }

    @org.junit.jupiter.api.Test
    void testSetDailyChargeDouble() {
        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
        testTool.setDailyCharge(2.);
        assertEquals(Money.of(2, PointOfSale.currency), testTool.getDailyCharge());
    }

    @org.junit.jupiter.api.Test
    void testSetDailyChargeMoney() {
        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
        testTool.setDailyCharge(Money.of(2, PointOfSale.currency));
        assertEquals(Money.of(2, PointOfSale.currency), testTool.getDailyCharge());
    }

    @org.junit.jupiter.api.Test
    void getChargeOnWeekday() {
    }

    @org.junit.jupiter.api.Test
    void testSetChargeOnWeekday() {
    }

    @org.junit.jupiter.api.Test
    void getChargeOnWeekend() {
    }

    @org.junit.jupiter.api.Test
    void testSetChargeOnWeekend() {
    }

    @org.junit.jupiter.api.Test
    void getChargeOnHoliday() {
    }

    @org.junit.jupiter.api.Test
    void testSetChargeOnHoliday() {
    }
}