package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.*;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;
import Project.service.TranzactieService;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieServiceTest {
    IRepository<Medicament> getMedicamentRepository() {
        InMemoryRepository<Medicament> testRepository = new InMemoryRepository<>();
        testRepository.create(new Medicament("1", "Algocalmin", "Ewopharma", 20, true));
        return testRepository;
    }
    IRepository<CardClient> getCardClientRepository(){
        InMemoryRepository<CardClient> testRepository=new InMemoryRepository<>();
        testRepository.create(new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021"));
        return testRepository;
    }
    IRepository<Tranzactie> getTranzactieRepository(){
        InMemoryRepository<Tranzactie> testRepository = new InMemoryRepository<>();
        testRepository.create(new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20"));
        return testRepository;
    }
    TranzactieValidator getTranzactieValidator() {
        TranzactieValidator tranzactieValidator = new TranzactieValidator();
        return tranzactieValidator;
    }
    TranzactieService getTranzactieService(){
        IRepository<Medicament> medicamentIRepository=getMedicamentRepository();
        IRepository<CardClient> cardClientIRepository=getCardClientRepository();
        IRepository<Tranzactie> tranzactieIRepository=getTranzactieRepository();
        TranzactieValidator tranzactieValidator = getTranzactieValidator();
        TranzactieService tranzactieService=new TranzactieService(tranzactieIRepository, medicamentIRepository, cardClientIRepository, tranzactieValidator);
        return tranzactieService;

    }

    @Test
    void addTranzactie() {
        TranzactieService tranzactieService=getTranzactieService();
        tranzactieService.addTranzactie("2", "2", "341", 1, "2022-10-02 13:20");
        List<Tranzactie> list=tranzactieService.getAllT();
        assertEquals(2,list.size());
    }

    @Test
    void getAllT() {
        TranzactieService tranzactieService = getTranzactieService();
        List <Tranzactie> list = tranzactieService.getAllT();
        assertFalse(list.isEmpty());
    }

    @Test
    void updateTranzactie() {
        TranzactieService tranzactieService = getTranzactieService();
        tranzactieService.updateTranzactie("1", "3", "342", 2, "2022-12-02 12:20");
        List<Tranzactie> list=tranzactieService.getAllT();
        Tranzactie tranzactie=list.get(0);
        assertEquals("3",tranzactie.getId_medicament());
    }

    @Test
    void deleteTranzactie() {
        TranzactieService tranzactieService = getTranzactieService();
        tranzactieService.deleteTranzactie("1");
        List<Tranzactie> list=tranzactieService.getAllT();
        assertEquals(0,list.size());
    }

    @Test
    void getMedicamenteOrdonateDescDupaNrTranzactii() {
        TranzactieService tranzactieService = getTranzactieService();
        List<MedicamentCuNrTranzactii> list = tranzactieService.getMedicamenteOrdonateDescDupaNrTranzactii();
        assertFalse(list.isEmpty());

    }

    @Test
    void searchInTranzactie() {
        TranzactieService tranzactieService = getTranzactieService();
        String s = "4";
        Tranzactie tranzactie1 = new Tranzactie("4", "4", "212", 1, "2022-10-01 09:01");
        Pattern stringPattern = Pattern.compile(s);
        Matcher similaritate = stringPattern.matcher(tranzactie1.getId());
        tranzactieService.searchInTranzactie(s);
        if (similaritate.find()) {
            assertEquals("4", tranzactie1.getId());
        }
    }

    @Test
    void searchEverywhere() {
        TranzactieService tranzactieService = getTranzactieService();
        String s = "712";
        Tranzactie tranzactie2 = new Tranzactie("5", "4", "712", 1, "2021-08-02 09:01");
        Pattern stringPattern = Pattern.compile(s);
        Matcher similaritate = stringPattern.matcher(tranzactie2.getId_card_client());
        tranzactieService.searchEverywhere(s);
        if (similaritate.find()) {
            assertEquals("712", tranzactie2.getId_card_client());
        }
    }

//    @Test
//    void getListaTranzactiiPeInterval() throws ParseException {
//        TranzactieService tranzactieService = getTranzactieService();
//        String inceputInterval = "2021-03-03 00:00";
//        String sfarsitInterval = "2022-03-09 00:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
//        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
//        List <Tranzactie> list = tranzactieService.getListaTranzactiiPeInterval();
//        assertTrue(list.isEmpty());
//
//    }
//
//
//    @Test
//    void getListaStergeTranzactiiPeInterval() throws ParseException {
//        TranzactieService tranzactieService = getTranzactieService();
//        String inceputInterval = "2021-03-03 00:00";
//        String sfarsitInterval = "2022-03-09 00:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
//        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
//        List <Tranzactie> list = tranzactieService.getListaStergeTranzactiiPeInterval();
//        assertFalse(list.isEmpty());


}