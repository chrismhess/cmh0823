import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementTest {
    static PointOfSale pointOfSale;

    @BeforeAll
    public static void init(){
        pointOfSale = new PointOfSale();
    }

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
    }

    @Test
    void setToolType() {
    }

    @Test
    void getToolCode() {
    }

    @Test
    void setToolCode() {
    }

    @Test
    void getToolBrand() {
    }

    @Test
    void setToolBrand() {
    }

    @Test
    void getRentalDuration() {
    }

    @Test
    void setRentalDuration() {
    }

    @Test
    void getCheckOutDate() {
    }

    @Test
    void setCheckOutDate() {
    }

    @Test
    void getDueDate() {
    }

    @Test
    void setDueDate() {
    }

    @Test
    void getDailyRentalCharge() {
    }

    @Test
    void setDailyRentalCharge() {
    }

    @Test
    void getChargeableDays() {
    }

    @Test
    void setChargeableDays() {
    }

    @Test
    void getPreDiscountPrice() {
    }

    @Test
    void setPreDiscountPrice() {
    }

    @Test
    void getDiscountPercent() {
    }

    @Test
    void setDiscountPercent() {
    }

    @Test
    void getDiscountAmount() {
    }

    @Test
    void setDiscountAmount() {
    }

    @Test
    void getFinalPrice() {
    }

    @Test
    void setFinalPrice() {
    }
}