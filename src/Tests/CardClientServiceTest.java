package Tests;

import Project.domain.CardClient;
import Project.domain.CardClientValidator;
import Project.domain.Medicament;
import Project.domain.MedicamentValidator;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;
import Project.service.CardClientService;
import Project.service.MedicamentService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CardClientServiceTest {

    IRepository<CardClient> getCardClientRepository(){
        InMemoryRepository<CardClient> testRepository=new InMemoryRepository<>();
        testRepository.create(new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021"));
        return testRepository;
    }
    CardClientValidator getCardClientValidator(){
        CardClientValidator cardClientValidator=new CardClientValidator();
        return cardClientValidator;
    }
    CardClientService getCardClientService(){
        IRepository<CardClient> cardClientIRepository=getCardClientRepository();
        CardClientValidator cardClientValidator=getCardClientValidator();
        CardClientService cardClientService=new CardClientService(cardClientIRepository,cardClientValidator);
        return cardClientService;
    }
    @org.junit.jupiter.api.Test
    void addCardClient() {
        CardClientService cardClientService=getCardClientService();
        cardClientService.addCardClient("222", "Popescu", "Tudor", "1987955288370", "1989-10-11", "01.01.2022");
        List<CardClient> list=cardClientService.getAllC();
        assertEquals(2,list.size());
    }

    @org.junit.jupiter.api.Test
    void getAllC() {
        CardClientService cardClientService = getCardClientService();
        List <CardClient> list = cardClientService.getAllC();
        assertFalse(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void updateCardClient() {
        CardClientService cardClientService=getCardClientService();
        cardClientService.updateCardClient("111", "Ionescu", "Ioana", "2987915288377", "1990-12-04", "12.03.2021");
        List<CardClient> list=cardClientService.getAllC();
        CardClient cardClient=list.get(0);
        assertEquals("Ioana",cardClient.getPrenume());

    }

    @org.junit.jupiter.api.Test
    void deleteCardClient() {
        CardClientService cardClientService=getCardClientService();
        cardClientService.deleteCardClient("111");
        List<CardClient> list=cardClientService.getAllC();
        assertEquals(0,list.size());
    }


    @org.junit.jupiter.api.Test
    void searchInCardClient() {
        CardClientService cardClientService = getCardClientService();
        String s = "2957915288333";
        CardClient cardClient1 = new CardClient("333", "Coman", "Ana", "2957915288333", "1974-03-19", "17.02.2022");
        Pattern stringPattern = Pattern.compile(s);
        Matcher similaritate = stringPattern.matcher(cardClient1.getCNP());
        cardClientService.searchInCardClient(s);
        if (similaritate.find()) {
            assertEquals("2957915288333", cardClient1.getCNP());
        }
    }
}