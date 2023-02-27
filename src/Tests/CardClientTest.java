package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.CardClient;
import Project.domain.Tranzactie;

import static org.junit.jupiter.api.Assertions.*;

class CardClientTest {

    @Test
    void getId() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("111", cardClient.getId(), "getId a returnat " + cardClient.getId() + "in loc de 111.");
    }

    @Test
    void getNume() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("Ionescu", cardClient.getNume(), "getNume a returnat " + cardClient.getNume() + "in loc de Ionescu.");
    }

    @Test
    void getPrenume() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("Maria", cardClient.getPrenume(), "getPrenume a returnat " + cardClient.getPrenume() + "in loc de Maria.");
    }

    @Test
    void getCNP() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("2987915288377", cardClient.getCNP(), "getCNP a returnat " + cardClient.getCNP() + "in loc de 2987915288377.");
    }

    @Test
    void getDataNasterii() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("1990-12-04", cardClient.getDataNasterii(), "getDataNasterii a returnat " + cardClient.getDataNasterii() + "in loc de 1990-12-04.");
    }

    @Test
    void getDataInregistrarii() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        assertEquals("12.03.2021", cardClient.getDataInregistrarii(), "getDataInregistrarii a returnat " + cardClient.getDataInregistrarii() + "in loc de 12.03.2021.");
    }

    @Test
    void testToString() {
        CardClient cardClient = new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        String expected = "CardClient{id='111', numeClient='Ionescu', prenumeClient='Maria', CNP='2987915288377', dataNasterii='1990-12-04', dataInregistrarii='12.03.2021'}";
        assertEquals(expected, cardClient.toString());
    }
}