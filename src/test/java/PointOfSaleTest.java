
import java.time.LocalDate;

import org.javamoney.moneta.Money;
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
        RentalAgreement agreement = pointOfSale.checkout("LADW", 5, 10, LocalDate.of(2023,9,1));
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(Money.of(1.49, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals(5, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2023,9,1), agreement.getCheckOutDate());
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(.447, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(4.023, PointOfSale.currency)));
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(4.47, PointOfSale.currency)));
        assertEquals(LocalDate.of(2023,9,6), agreement.getDueDate());
        assertEquals("Stihl", agreement.getToolBrand());
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