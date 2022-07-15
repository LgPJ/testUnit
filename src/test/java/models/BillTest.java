package models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void testNameBill() {
        Bill bill = new Bill("Jose", new BigDecimal("1000.12345"));
        //bill.setPeople("Luis");
        String expected = "Jose";
        String current = bill.getPeople();

        assertEquals(expected, current);
        assertTrue(current.equals("Jose"));
    }

    @Test
    void balanceBill(){
        Bill bill = new Bill("Luis", new BigDecimal("1000.22222"));

        assertEquals(1000.22222, bill.getBalance().doubleValue());
        assertFalse(bill.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(bill.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }
}