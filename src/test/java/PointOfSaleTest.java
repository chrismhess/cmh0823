import org.javamoney.moneta.Money;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointOfSaleTest {
    static PointOfSale pointOfSale;

    @BeforeAll
    public static void init(){
        pointOfSale = new PointOfSale();
    }


    @Test
    void testCheckout() {
        RentalAgreement agreement = pointOfSale.checkout("CHNS", 5, 0, "09/01/2023");
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(Money.of(1.49, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals(5, agreement.getRentalDuration());
        assertEquals(DateTime.parse("09/01/2023"), agreement.getCheckOutDate());
        assertEquals(Money.of(.45, PointOfSale.currency), agreement.getDiscountAmount());
        assertEquals(Money.of(4.02, PointOfSale.currency), agreement.getFinalPrice());
        assertEquals(Money.of(4.47, PointOfSale.currency), agreement.getPreDiscountPrice());
        assertEquals(DateTime.parse("09/06/2023"), agreement.getReturnDate());
        assertEquals("Werner", agreement.getToolBrand());
    }

    @Test
    void addTool() {
    }

    @Test
    void addFixedHoliday() {
    }

    @Test
    void removeFixedHoliday() {
    }

    @Test
    void testIsLaborDay(){

    }
}