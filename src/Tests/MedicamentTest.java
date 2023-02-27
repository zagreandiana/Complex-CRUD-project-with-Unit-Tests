package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.Medicament;
import Project.service.MedicamentService;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentTest {

    @Test
    void getId() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        assertEquals("6", medicament.getId(), "getId a returnat " + medicament.getId() + " in loc de 6. ");
    }

    @Test
    void getNume() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        assertEquals("Diclofenac", medicament.getNume(), "getNume a returnat " + medicament.getNume() + " in loc de Diclofenac. ");
    }

    @Test
    void getProducator() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        assertEquals("Nycomed", medicament.getProducator(), "getProducator a returnat " + medicament.getProducator() + " in loc de Nycomed. ");
    }

    @Test
    void getPret() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        assertEquals(42, medicament.getPret(), "getPret a returnat " + medicament.getPret() + " in loc de 42. ");
    }

    @Test
    void isNecesitaReteta() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        assertEquals(false, medicament.isNecesitaReteta(), "isNecesitaReteta a returnat " + medicament.isNecesitaReteta() + " in loc de false. ");
    }

    @Test
    void testToString() {
        Medicament medicament = new Medicament("6", "Diclofenac", "Nycomed", 42, false);
        String expected = "Medicament{id='6', nume='Diclofenac', producator='Nycomed', pret=42.0, necesitaReteta=false}";
        assertEquals(expected, medicament.toString());

    }
}