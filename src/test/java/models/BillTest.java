package models;

import exceptions.InsufficientMoney;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void testNameBill() {
        Bill bill = new Bill("Jose GF", new BigDecimal("1000.12345"));
        //bill.setPeople("Luis");
        String expected = "Jose";
        String current = bill.getPeople();

        assertNotNull(current, "La cuenta no puede ser nula");

        assertEquals(expected, current, "EL nombre de la cuenta no es el esperado");
        assertTrue(current.equals("Jose"), "Nombre esperado no es igual al existente");
    }

    @Test
    void balanceBill() {
        Bill bill = new Bill("Luis", new BigDecimal("1000.22222"));

        assertEquals(1000.22222, bill.getBalance().doubleValue());
        assertFalse(bill.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(bill.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void referenceBill() {
        Bill bill = new Bill("Jhon Doe", new BigDecimal("8900.999"));
        Bill billTwo = new Bill("Jhon Doe", new BigDecimal("8900.999"));

        //Valida que el monto no sea nulo
        assertNotNull(bill.getBalance());

        //Comparacion por referencia
        //assertNotEquals(bill, billTwo);

        //Comparacion por valor
        assertEquals(bill, billTwo);


    }

    @Test
    void debitBill() {

        Bill bill = new Bill("Andres", new BigDecimal("1000.12345"));
        bill.debit(new BigDecimal(100));

        //Valida que el monto no sea nulo
        assertNotNull(bill.getBalance());

        //Valida que el debito a la cuenta este dando la resta esperada
        assertEquals(900, bill.getBalance().intValue());

        assertEquals("900.12345", bill.getBalance().toPlainString());
    }

    @Test
    void creditBill() {

        Bill bill = new Bill("Andres", new BigDecimal("1000.12345"));
        bill.credit(new BigDecimal(100));

        //Valida que el monto no sea nulo
        assertNotNull(bill.getBalance());

        //Valida que el debito a la cuenta este dando la resta esperada
        assertEquals(1100, bill.getBalance().intValue());

        assertEquals("1100.12345", bill.getBalance().toPlainString());
    }

    @Test
    void insufficientMoneyBillException() {

        Bill bill = new Bill("Jose", new BigDecimal("1000.12345"));

        //El metodo assertThrows retorna el objeto de exception
        Exception ex = assertThrows(InsufficientMoney.class, () -> {
            bill.debit(new BigDecimal(1500));
        });

        String current = ex.getMessage();
        String real = "Insufficient Money";

        assertEquals(current, real);
    }

    @Test
    void toTransferAccount() {

        Bill bill = new Bill("Jhon", new BigDecimal("2500.12345"));
        Bill billTwo = new Bill("Maria", new BigDecimal("500.12345"));

        Bank bank = new Bank();
        bank.setName("Venezuela");
        bank.toTransfer(billTwo, bill, new BigDecimal(500));

        assertEquals("0.12345", billTwo.getBalance().toPlainString());
        assertEquals("3000.12345", bill.getBalance().toPlainString());
    }

    @Test
    void bankToAccountRelationship() {

        Bill bill = new Bill("Jhon", new BigDecimal("2500.12345"));
        Bill billTwo = new Bill("Andres", new BigDecimal("500.12345"));

        Bank bank = new Bank();
        bank.addBill(bill);
        bank.addBill(billTwo);

        bank.setName("Venezuela");
        bank.toTransfer(billTwo, bill, new BigDecimal(500));

        assertAll(() -> {
                    assertEquals("0.12345", billTwo.getBalance().toPlainString());
                },
                () -> {
                    assertEquals("3000.12345", bill.getBalance().toPlainString());
                },
                () -> {
                    assertEquals(2, bank.getListBill().size());
                },
                () -> {
                    assertEquals("Venezuela", bill.getBank().getName());
                },
                () -> {
                    assertEquals("Andres", bank.getListBill()
                            .stream()
                            .filter(b -> b.getPeople().equals("Andres"))
                            .findFirst()
                            .get()
                            .getPeople());
                },
                () -> {
                    assertTrue(bank.getListBill()
                            .stream()
                            .anyMatch(b -> b.getPeople().equals("Andres")));
                });

        /* Misma sentencia
        .filter(b -> b.getPeople().equals("Andres"))
                .findFirst()
                .isPresent());
        */
    }
}