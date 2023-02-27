package Tests;

import org.junit.jupiter.api.Test;
import Project.domain.CardClient;
import Project.domain.CardClientValidator;
import Project.domain.Medicament;
import Project.domain.MedicamentValidator;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class CardClientValidatorTest {

    @Test
    void validateC() {
        IRepository<CardClient> cardClientRepository = new InMemoryRepository<>();

        cardClientRepository.create( new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021"));
        cardClientRepository.create( new CardClient("222", "Popescu", "Tudor", "1987955288370", "1989-10-11", "01.01.2022"));
        cardClientRepository.create(new CardClient("333", "Coman", "Ana", "2957915288333", "1974-03-19", "17.02.2022"));
        CardClientValidator cardClientValidator = new CardClientValidator();

        CardClient c1 = new CardClient("111", "", "Maria", "2987915288377", "1990-12-04", "12.03.2021");
        try{
            cardClientValidator.validateC(c1, cardClientRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Numele clientului nu poate fi gol", e.getMessage());
        }

        CardClient c2 = new CardClient("111", "Ionescu", "", "2987915288377", "1990-12-04", "12.03.2021");
        try{
            cardClientValidator.validateC(c2, cardClientRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Prenumele clientului nu poate fi gol", e.getMessage());
        }

        CardClient c3= new CardClient("111", "Ionescu", "Maria", "", "1990-12-04", "12.03.2021");
        try{
            cardClientValidator.validateC(c3, cardClientRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("CNP -ul clientului nu poate fi nul", e.getMessage());
        }

        CardClient c4= new CardClient("111", "Ionescu", "Maria", "2987915288377", "", "12.03.2021");
        try{
            cardClientValidator.validateC(c4, cardClientRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Data nasterii clientului nu poate fi nula", e.getMessage());
        }

        CardClient c5= new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "");
        try{
            cardClientValidator.validateC(c5, cardClientRepository);
            fail();
        } catch (RuntimeException e){
            assertEquals("Data inregistrarii cardului client nu poate fi nula", e.getMessage());
        }
    }

}