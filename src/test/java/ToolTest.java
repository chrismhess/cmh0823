import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToolTest {
    Tool testTool;

    @BeforeEach
    void init(){
        testTool = new Tool("CHNS", "Chainsaw", "Stihl");
    }

    @Test
    void testGetToolCode() {
        assertEquals("CHNS",testTool.getToolCode());
    }

    @Test
    void testSetToolCode() {
        assertEquals("CHNS",testTool.getToolCode());
        testTool.setToolCode("test");
        assertEquals("test",testTool.getToolCode());
    }

    @Test
    void getToolType() {
        assertEquals("Chainsaw", testTool.getToolType());
    }

    @Test
    void testSetToolType() {
        assertEquals("Chainsaw", testTool.getToolType());
        testTool.setToolType("test");
        assertEquals("test",testTool.getToolType());
    }

    @Test
    void getToolBrand() {
        assertEquals("Stihl", testTool.getToolBrand());
    }

    @Test
    void testSetToolBrand() {
        assertEquals("Stihl", testTool.getToolBrand());
        testTool.setToolBrand("test");
        assertEquals("test",testTool.getToolBrand());
    }

//    @Test
//    void getDailyCharge() {
//        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
//    }
//
//    @Test
//    void testSetDailyChargeDouble() {
//        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
//        testTool.setDailyCharge(2.);
//        assertEquals(Money.of(2, PointOfSale.currency), testTool.getDailyCharge());
//    }
//
//    @Test
//    void testSetDailyChargeMoney() {
//        assertEquals(Money.of(1.49, PointOfSale.currency), testTool.getDailyCharge());
//        testTool.setDailyCharge(Money.of(2, PointOfSale.currency));
//        assertEquals(Money.of(2, PointOfSale.currency), testTool.getDailyCharge());
//    }
//
//    @Test
//    void getChargeOnWeekday() {
//        assertEquals(true, testTool.getChargeOnWeekday());
//    }
//
//    @Test
//    void testSetChargeOnWeekday() {
//        assertEquals(true, testTool.getChargeOnWeekday());
//        testTool.setChargeOnWeekday(false);
//        assertEquals(false, testTool.getChargeOnWeekday());
//    }
//
//    @Test
//    void getChargeOnWeekend() {
//        assertEquals(false, testTool.getChargeOnWeekend());
//    }
//
//    @Test
//    void testSetChargeOnWeekend() {
//        assertEquals(false, testTool.getChargeOnWeekend());
//        testTool.setChargeOnWeekend(true);
//        assertEquals(true, testTool.getChargeOnWeekend());
//    }
//
//    @Test
//    void getChargeOnHoliday() {
//        assertEquals(true, testTool.getChargeOnHoliday());
//    }
//    @Test
//    void testSetChargeOnHoliday() {
//        assertEquals(true, testTool.getChargeOnHoliday());
//        testTool.setChargeOnHoliday(false);
//        assertEquals(false, testTool.getChargeOnHoliday());
//    }
}