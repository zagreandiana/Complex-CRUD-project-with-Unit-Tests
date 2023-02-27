package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.*;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;
import Project.service.MedicamentService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentServiceTest {
    IRepository<Medicament> getMedicamentRepository(){
        InMemoryRepository<Medicament> testRepository=new InMemoryRepository<>();
        testRepository.create(new Medicament("1", "Algocalmin", "Ewopharma", 20, true));
        return testRepository;
    }
    IRepository<CardClient> getCardClientRepository(){
        InMemoryRepository<CardClient> testRepository=new InMemoryRepository<>();
        testRepository.create(new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021"));
        return testRepository;
    }
    MedicamentValidator getMedicamentValidator(){
        MedicamentValidator medicamentValidator=new MedicamentValidator();
        return medicamentValidator;
    }
    MedicamentService getMedicamentService(){
        IRepository<Medicament> medicamentIRepository=getMedicamentRepository();
        IRepository<CardClient> cardClientIRepository=getCardClientRepository();
        MedicamentValidator medicamentValidator=getMedicamentValidator();
  //      CardClient cardClient = (CardClient) getCardClientRepository();
        MedicamentService medicamentService=new MedicamentService(cardClientIRepository,medicamentIRepository ,medicamentValidator);
        return medicamentService;
    }

    @Test
    void addMedicament() {
        MedicamentService medicamentService = getMedicamentService();
        medicamentService.addMedicament("5", "Ewofex", "Ewopharma", 64, true);
        List<Medicament> list=medicamentService.getAllM();
        assertEquals(2,list.size());
    }

    @Test
    void getAllM() {
        MedicamentService medicamentService = getMedicamentService();
        List <Medicament> list = medicamentService.getAllM();
        assertFalse(list.isEmpty());
    }

    @Test
    void updateMedicament() {
        MedicamentService medicamentService = getMedicamentService();
        medicamentService.updateMedicament("1", "Ewofex", "Ewopharma", 51, true);
        List<Medicament> list=medicamentService.getAllM();
        Medicament medicament=list.get(0);
        assertEquals(51,medicament.getPret());

    }

    @Test
    void deleteMedicament() {
        MedicamentService medicamentService = getMedicamentService();
        medicamentService.deleteMedicament("1");
        List<Medicament> list=medicamentService.getAllM();
        assertEquals(0,list.size());
    }

    @Test
    void searchInMedicament() {
        MedicamentService medicamentService = getMedicamentService();
        String s = "4";
        Medicament medicament1 = new Medicament("3", "Eupantol", "Nycomed", 54, true );
        Pattern stringPattern = Pattern.compile(s);
        Matcher similaritate = stringPattern.matcher(medicament1.getId());
        medicamentService.searchInMedicament(s);
        if (similaritate.find()) {
            assertEquals("4", medicament1.getId());
        }
    }

//    @Test
//    void getCarduriOrdonateDescDupaReducerileObtinute() {
//        MedicamentService medicamentService = getMedicamentService();
//        List<CarduriCuReduceriObtinute> list = medicamentService.getCarduriOrdonateDescDupaReducerileObtinute();
//        assertFalse(list.isEmpty());
//    }
}