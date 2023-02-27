package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.Medicament;
import Project.domain.Tranzactie;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieTest {

    @Test
    void getId() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        assertEquals("1", tranzactie.getId(), "getId a returnat " + tranzactie.getId() + "in loc de 1.");
    }

    @Test
    void getId_medicament() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        assertEquals("2", tranzactie.getId_medicament(), "getId_medicament a returnat " + tranzactie.getId_medicament() + "in loc de 2.");
    }

    @Test
    void getId_card_client() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        assertEquals("342", tranzactie.getId_card_client(), "getId_card_client a returnat " + tranzactie.getId_card_client() + "in loc de 342.");
    }

    @Test
    void getNumarBucati() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        assertEquals(2 , tranzactie.getNumarBucati(), "getNumarBucati a returnat " + tranzactie.getNumarBucati() + "in loc de 2.");
    }

    @Test
    void getDataSiOra() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        assertEquals("2022-12-02 12:20", tranzactie.getDataSiOra(), "getDataSiOra a returnat " + tranzactie.getDataSiOra() + "in loc de 2022-12-02 12:20.");
    }

    @Test
    void testToString() {
        Tranzactie tranzactie = new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20");
        String expected = "Tranzactie{id='1', id_medicament='2', id_card_client='342', numarBucati=2, dataSiOra='2022-12-02 12:20'}";
        assertEquals(expected, tranzactie.toString());
    }
}