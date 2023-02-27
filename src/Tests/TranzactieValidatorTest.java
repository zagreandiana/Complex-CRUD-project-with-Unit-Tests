package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.*;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieValidatorTest {

    @Test
    void validate() {

        IRepository<Tranzactie> tranzactieRepository = new InMemoryRepository<>();


        tranzactieRepository.create( new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20"));
        tranzactieRepository.create( new Tranzactie("2", "2", "341", 1, "2022-10-02 13:20"));
        tranzactieRepository.create(new Tranzactie("3", "2", "3231", 1, "2022-04-17 09:11"));
        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        Tranzactie t1 = new Tranzactie("", "2", "2345", 3, "2019-02-02 12:10");
        try{
            tranzactieValidator.validate(t1, tranzactieRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Id-ul tranzactiei nu poate fi nul\n", e.getMessage());
        }

        Tranzactie t2 = new Tranzactie("1", "", "234", 3, "2019-02-02 12:10");
        try{
            tranzactieValidator.validate(t2, tranzactieRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Id-ul medicamentului tranzactionat nu poate fi nul\n", e.getMessage());
        }

        Tranzactie t3 = new Tranzactie("1", "2", "2345", -3, "2019-02-02 12:10");
        try{
            tranzactieValidator.validate(t3, tranzactieRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Numarul bucatilor vandute trebuie sa fie mai mare decat 0\n", e.getMessage());
        }

        Tranzactie t4 = new Tranzactie("1", "2", "", 3, "2019-02-02 12:10");
        try{
            tranzactieValidator.validate(t4, tranzactieRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Id-ul cardului client nu poate fi nul\n", e.getMessage());
        }

        Tranzactie t5 = new Tranzactie("1", "2", "234", 3, "");
        try{
            tranzactieValidator.validate(t5, tranzactieRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Data si ora tranzactiei nu pot fi nule\n", e.getMessage());
        }

    }
}