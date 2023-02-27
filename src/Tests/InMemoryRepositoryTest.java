package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.CardClient;
import Project.domain.Medicament;
import Project.domain.Tranzactie;
import Project.repository.InMemoryRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    InMemoryRepository getTestRepository(){
        InMemoryRepository repository = new InMemoryRepository();

        repository.create(new Medicament("1", "Algocalmin", "Ewopharma", 20, true));
        repository.create(new Medicament("2", "Nurofen", "Farmec", 13, false));


        return repository;

    }

    @Test
    void create() {
        InMemoryRepository repository = getTestRepository();
        assertEquals(2, repository.read().size());


        Medicament toAddSameId = new Medicament("1", "Algocalmin", "Ewopharma", 20, true);
        try{
            repository.create(toAddSameId);
            fail();
        } catch (RuntimeException rex){
            assertNotEquals(" ", rex.getMessage());
        }

        Medicament toAddNewId = new Medicament("100", "Algocalmin", "Ewopharma", 20, true);
        repository.create(toAddNewId);
        assertEquals(3, repository.read().size());
        assertNotEquals(null, repository.read("100"));

        Medicament medicament2 = new Medicament("1", "Algocalmin", "Ewopharma", 20, true);
        try {
            repository.create(medicament2);
            fail();
        } catch (RuntimeException e) {
            assertEquals("Exista deja o entitate cu id-ul 1", e.getMessage());
        }
    }

    @Test
    void read() {
        InMemoryRepository repository = getTestRepository();
        Medicament medicament1 = new Medicament("100", "Algocalmin", "Ewopharma", 20, true);
        assertEquals(2, repository.read().size());


    }

    @Test
    void testRead() {
        InMemoryRepository repository = getTestRepository();
    }

    @Test
    void update() {
        InMemoryRepository repository = getTestRepository();
        Medicament medicament2 = new Medicament("", "Algocalmin", "Ewopharma", 20, true);
        try {
            repository.update(medicament2);
            fail();
        } catch (RuntimeException e) {
            assertEquals("Nu exista nicio entitate cu id-ul: ", e.getMessage());
        }
    }

    @Test
    void delete() {
        InMemoryRepository repository = getTestRepository();
        Medicament medicament1 = new Medicament("1", "Algocalmin", "Ewopharma", 20, true);
        repository.delete(String.valueOf(medicament1.getId()));
        assertEquals(1, repository.read().size());

        Medicament medicament2 = new Medicament("", "Algocalmin", "Ewopharma", 20, true);
        try {
            repository.delete(medicament2.getId());
            fail();
        } catch (RuntimeException e) {
            assertEquals("Nu exista nicio entitate cu id-ul: ", e.getMessage());
        }
    }
    @Test
    void search() {
        InMemoryRepository repository = getTestRepository();
        String s = "Algocalmin";
        Medicament medicament1 = new Medicament("1", "Algocalmin", "Ewopharma", 20, true);
        Pattern stringPattern = Pattern.compile(s);
        Matcher similaritate = stringPattern.matcher(medicament1.getNume());
        repository.search(s);
        if(similaritate.find()) {
            assertEquals("Algocalmin", medicament1.getNume());
        }
    }
}

