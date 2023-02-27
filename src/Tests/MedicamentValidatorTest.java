package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import Project.domain.Medicament;
import Project.domain.MedicamentValidator;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;

import javax.xml.validation.Validator;

import java.lang.module.Configuration;

import static org.junit.jupiter.api.Assertions.*;



class MedicamentValidatorTest {

    @Test
    void validateM() {
        IRepository<Medicament> medicamentIRepository = new InMemoryRepository<>();

        medicamentIRepository.create( new Medicament("1", "Algocalmin", "Ewopharma", 20, true));
        medicamentIRepository.create( new Medicament("2", "Nurofen", "Farmec", 13, false));
        medicamentIRepository.create(new Medicament("3", "Eupantol", "Nycomed", 54, true ));
        MedicamentValidator medicamentValidator = new MedicamentValidator();

        Medicament m1 = new Medicament("9", "", "swde", 234, false);
        try{
            medicamentValidator.validateM(m1, medicamentIRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Numele medicamentului nu poate fi gol\n", e.getMessage());
        }

        Medicament m2 = new Medicament("10", "Nurofen", "Farmec", -1, true);
        try{
            medicamentValidator.validateM(m2, medicamentIRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Pretul trebuie sa fie unul pozitiv si mai mare decat 0\n", e.getMessage());
        }

        Medicament m3 = new Medicament("", "Nurofen", "swde", 234, false);
        try{
            medicamentValidator.validateM(m3, medicamentIRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Id-ul medicamentului nu poate fi gol\n", e.getMessage());
        }

        Medicament m4 = new Medicament("9", "Nurofen", "", 234, false);
        try{
            medicamentValidator.validateM(m4, medicamentIRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Producatorul medicamentului nu poate fi gol\n", e.getMessage());
        }

        Medicament m5 = new Medicament("9", "Nurofen", "", 234, false);
        String errors = "";
        if (m5.getNume().isEmpty()) {
            errors += "Numele medicamentului nu poate fi gol\n";
        }
        if(!errors.isEmpty()){
            Exception exception = assertThrows(RuntimeException.class, () -> {
                Integer.parseInt("1");
            });

            String expectedMessage = "Errors";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }
}