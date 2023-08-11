import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementTest {
    static RentalAgreement rentalAgreement;

    @BeforeEach
    public void init(){
        rentalAgreement = new RentalAgreement("JAKR", 5, 10,
                LocalDate.of(2015, 9, 3));
    }

    /**
     * This method tests that printing to console works as expected by changing the output to console
     * location to a new byte array output stream, calling the print method, and comparing what got written
     * out to that byte array.
     */
    @Test
    void testPrint() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        RentalAgreement agreement = new RentalAgreement("JAKR", 5, 10,
                LocalDate.of(2015, 9, 3));
        agreement.printToConsole();
        assertEquals("Tool code: JAKR\r\n" +
                "Tool type: Jackhammer\r\n" +
                "Tool brand: Ridgid\r\n" +
                "Check Out Date: 09/03/15\r\n" +
                "Rental Duration: 5\r\n" +
                "Due Date: 09/08/15\r\n" +
                "Daily Rental Charge: $2.99\r\n" +
                "Chargeable Days: 2\r\n" +
                "Pre-Discount Price: $5.98\r\n" +
                "Discount Percentage: 10%\r\n" +
                "Discount Amount: $0.60\r\n" +
                "Final Price: $5.38\r\n", outContent.toString());
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    void getToolType() {
        assertEquals("Jackhammer", rentalAgreement.getToolType());
    }

    @Test
    void setToolType() {
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        rentalAgreement.setToolType("Tablesaw");
        assertEquals("Tablesaw", rentalAgreement.getToolType());
    }

    @Test
    void getToolCode() {
        assertEquals("JAKR", rentalAgreement.getToolCode());
    }

    @Test
    void setToolCode() {
        assertEquals("JAKR", rentalAgreement.getToolCode());
        rentalAgreement.setToolCode("TBLS");
        assertEquals("TBLS", rentalAgreement.getToolCode());
    }

    @Test
    void getToolBrand() {
        assertEquals("Ridgid", rentalAgreement.getToolBrand());
    }

    @Test
    void setToolBrand() {
        assertEquals("Ridgid", rentalAgreement.getToolBrand());
        rentalAgreement.setToolBrand("Milwaukee");
        assertEquals("Milwaukee", rentalAgreement.getToolBrand());
    }

    @Test
    void getRentalDuration() {
        assertEquals(5, rentalAgreement.getRentalDuration());
    }

    @Test
    void setRentalDuration() {
        assertEquals(5, rentalAgreement.getRentalDuration());
        rentalAgreement.setRentalDuration(10);
        assertEquals(10, rentalAgreement.getRentalDuration());
    }

    @Test
    void getCheckOutDate() {
        assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckOutDate());
    }

    @Test
    void setCheckOutDate() {
        assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckOutDate());
        rentalAgreement.setCheckOutDate(LocalDate.of(2015, 9, 10));
        assertEquals(LocalDate.of(2015, 9, 10), rentalAgreement.getCheckOutDate());
    }

    @Test
    void getDueDate() {
        assertEquals(LocalDate.of(2015, 9, 8), rentalAgreement.getDueDate());
    }

    @Test
    void setDueDate() {
        assertEquals(LocalDate.of(2015, 9, 8), rentalAgreement.getDueDate());
        rentalAgreement.setDueDate(LocalDate.of(2015, 9, 12));
        assertEquals(LocalDate.of(2015, 9, 12), rentalAgreement.getDueDate());
    }

    @Test
    void getDailyRentalCharge() {
        assertEquals(Money.of(2.99, PointOfSale.currency), rentalAgreement.getDailyRentalCharge());
    }

    @Test
    void setDailyRentalCharge() {
        assertEquals(Money.of(2.99, PointOfSale.currency), rentalAgreement.getDailyRentalCharge());
        rentalAgreement.setDailyRentalCharge(Money.of(3.99, PointOfSale.currency));
        assertEquals(Money.of(3.99, PointOfSale.currency), rentalAgreement.getDailyRentalCharge());

    }

    @Test
    void getChargeableDays() {
        assertEquals(2, rentalAgreement.getChargeableDays());
    }

    @Test
    void setChargeableDays() {
        assertEquals(2, rentalAgreement.getChargeableDays());
        rentalAgreement.setChargeableDays(5);
        assertEquals(5, rentalAgreement.getChargeableDays());
    }

    @Test
    void getPreDiscountPrice() {
        assertEquals(Money.of(5.98, PointOfSale.currency), rentalAgreement.getPreDiscountPrice());
    }

    @Test
    void setPreDiscountPrice() {
        assertEquals(Money.of(5.98, PointOfSale.currency), rentalAgreement.getPreDiscountPrice());
        rentalAgreement.setPreDiscountPrice(Money.of(10.98, PointOfSale.currency));
        assertEquals(Money.of(10.98, PointOfSale.currency), rentalAgreement.getPreDiscountPrice());
    }

    @Test
    void getDiscountPercent() {
        assertEquals(10, rentalAgreement.getDiscountPercent());
    }

    @Test
    void setDiscountPercent() {
        assertEquals(10, rentalAgreement.getDiscountPercent());
        rentalAgreement.setDiscountPercent(20);
        assertEquals(20, rentalAgreement.getDiscountPercent());
    }

    @Test
    void getDiscountAmount() {
        assertEquals(Money.of(.598, PointOfSale.currency), rentalAgreement.getDiscountAmount());
    }

    @Test
    void setDiscountAmount() {
        assertEquals(Money.of(.598, PointOfSale.currency), rentalAgreement.getDiscountAmount());
        rentalAgreement.setDiscountAmount(Money.of(2.50, PointOfSale.currency));
        assertEquals(Money.of(2.5, PointOfSale.currency), rentalAgreement.getDiscountAmount());
    }

    @Test
    void getFinalPrice() {
        assertEquals(Money.of(5.382, PointOfSale.currency), rentalAgreement.getFinalPrice());
    }

    @Test
    void setFinalPrice() {
        assertEquals(Money.of(5.382, PointOfSale.currency), rentalAgreement.getFinalPrice());
        rentalAgreement.setFinalPrice(Money.of(1, PointOfSale.currency));
        assertEquals(Money.of(1, PointOfSale.currency), rentalAgreement.getFinalPrice());

    }

}