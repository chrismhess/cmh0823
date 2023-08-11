
import java.time.LocalDate;
import java.util.MissingResourceException;
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

    /**
     * Test 1 per the Assignment specification, this attempts to check a tool out with a discount value higher than
     * acceptable, it throws an Illegal argument exception and returns a user-friendly error message informing them of
     * the issue and how to correct it.
     */
    @Test
    void checkoutWithDiscountOverLimitTest1(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("JAKR", 5, 101,
                    LocalDate.of(2015, 9, 3));
        });
        assertEquals("Discount Percentage value 101 is invalid. Please provide a discount value as a whole " +
                "number between 0 and 100.", thrown.getMessage());
    }

    /**
     * Test 2 per the assignment specification, this is a test of a valid Ladder tool being checked out for 3 days  over
     * the july fourth holiday of 2020 with a 10% discount applied.
     */
    @Test
    void checkoutWithValidInputTest2(){
        RentalAgreement agreement = pointOfSale.checkout("LADW", 3, 10,
                LocalDate.of(2020,7,2));
        assertEquals(2, agreement.getChargeableDays());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(Money.of(1.99, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertEquals("LADW", agreement.getToolCode());
        assertEquals(3, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2020,7,2), agreement.getCheckOutDate());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(3.98, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(.398, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(3.582, PointOfSale.currency)));
        assertEquals(LocalDate.of(2020,7,5), agreement.getDueDate());
        assertEquals("Werner", agreement.getToolBrand());
        agreement.printToConsole();
    }

    /**
     * Test 3 per the assignment specification, this is a test of a valid Chainsaw tool being checked out for 4 days
     * over the july fourth holiday of 2015 with a 25% discount applied.
     */
    @Test
    void checkoutWithValidInputTest3(){
        RentalAgreement agreement = pointOfSale.checkout("CHNS", 5, 25,
                LocalDate.of(2015,7,2));
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals("Stihl", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2015,7,2), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015,7,7), agreement.getDueDate());
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(Money.of(1.49, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(4.47, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(1.1175, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(3.3525, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * Test 3 per the assignment specification, this is a test of a valid Chainsaw tool being checked out for 4 days
     * over the july fourth holiday of 2015 with a 25% discount applied. This takes a checkout date in a string format
     * to ensure we support flexibility in input for future use cases.
     */
    @Test
    void checkoutWithValidInputTest3WithStringDate(){
        RentalAgreement agreement = pointOfSale.checkout("CHNS", 5, 25, "07/02/2015");
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals("Stihl", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2015,7,2), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015,7,7), agreement.getDueDate());
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(Money.of(1.49, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(4.47, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(1.1175, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(3.3525, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * Test 4 per the assignment specification, this is a test of a valid Jackhammer tool being checked out for six days
     * over the labor day holiday of 2015 with a no discount applied.
     */
    @Test
    void checkoutWithValidInputTest4(){
        RentalAgreement agreement = pointOfSale.checkout("JAKD", 6, 0,
                LocalDate.of(2015,9,3));
        assertEquals("JAKD", agreement.getToolCode());
        assertEquals("DeWalt", agreement.getToolBrand());
        assertEquals(6, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2015,9,3), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015,9,9), agreement.getDueDate());
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(Money.of(2.99, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(8.97, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(0, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(8.97, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * Test 5 per the assignment specification, this is a test of a valid Jackhammer tool being checked out for nine
     * days over the July Fourth holiday of 2015 with no discount applied.
     */
    @Test
    void checkoutWithValidInputTest5(){
        RentalAgreement agreement = pointOfSale.checkout("JAKD", 9, 0,
                LocalDate.of(2015,7,2));
        assertEquals("JAKD", agreement.getToolCode());
        assertEquals("DeWalt", agreement.getToolBrand());
        assertEquals(9, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2015,7,2), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015,7,11), agreement.getDueDate());
        assertEquals(5, agreement.getChargeableDays());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(Money.of(2.99, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(14.95, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(0, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(14.95, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * Test 5 per the assignment specification, this is a test of a valid Jackhammer tool being checked out for four
     * days over the July Fourth holiday of 2015 with a 50% discount applied.
     */
    @Test
    void checkoutWithValidInputTest6(){
        RentalAgreement agreement = pointOfSale.checkout("JAKR", 4, 50,
                LocalDate.of(2020,7,2));
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(4, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2020,7,2), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2020,7,6), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(Money.of(2.99, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(2.99, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(1.495, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(1.495, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * During development, an important edge needed to be covered where a customer could potentially check
     * a tool out over multiple years and encompass multiple sets of holidays beyond the year provided on the checkout
     * date. This test covers this case where a customer is checking out a tool for 3 years which requires the system
     * to determine holidays across multiple years. Thankfully we gave this customer a 75% discount given the length
     * of rental duration.
     */
    @Test
    void checkoutWithValidInputMultiYearDuration(){
        RentalAgreement agreement = pointOfSale.checkout("JAKR", 1095, 75,
                LocalDate.of(2020,7,2));
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(1095, agreement.getRentalDuration());
        assertEquals(LocalDate.of(2020,7,2), agreement.getCheckOutDate());
        assertEquals(LocalDate.of(2023,7,2), agreement.getDueDate());
        assertEquals(775, agreement.getChargeableDays());
        assertEquals(75, agreement.getDiscountPercent());
        assertEquals(Money.of(2.99, PointOfSale.currency), agreement.getDailyRentalCharge());
        assertTrue(agreement.getPreDiscountPrice().isEqualTo(Money.of(2317.25, PointOfSale.currency)));
        assertTrue(agreement.getDiscountAmount().isEqualTo(Money.of(1737.9375, PointOfSale.currency)));
        assertTrue(agreement.getFinalPrice().isEqualTo(Money.of(579.3125, PointOfSale.currency)));
        agreement.printToConsole();
    }

    /**
     * This is a test to verify that if a user provides a negative value for the discount being applied the system
     * throws an IllegalArgument Exception with a user-friendly error message informing them of how to correct the
     * issue.
     */
    @Test
    void checkoutWithDiscountUnderLimit(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("JAKR", 5, -1,
                    LocalDate.of(2015, 9, 3));
        });
        assertEquals("Discount Percentage value -1 is invalid. Please provide a discount value as a whole " +
                "number between 0 and 100.", thrown.getMessage());
    }

    @Test
    void checkoutWithMissingToolCode(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pointOfSale.checkout("INVALIDTOOLCODE", 5, 10,
                    LocalDate.of(2015, 9, 3));
        });
        assertEquals("Tool Code INVALIDTOOLCODE was not found in tool inventory. Please check tool code spelling and" +
                " try again.", thrown.getMessage());
    }

    @Test
    void checkoutWithMissingToolInfo(){
        MissingResourceException thrown = assertThrows(MissingResourceException.class, () -> {
            pointOfSale.checkout("MissingInfo", 5, 10,
                    LocalDate.of(2015, 9, 3));
        });
        assertEquals("Provided tool code was found in inventory but tool type info was missing in system, " +
                "please contact support for assistance or try a similar tool code.", thrown.getMessage());
    }
    @Test
    void addTool() {
    }
}