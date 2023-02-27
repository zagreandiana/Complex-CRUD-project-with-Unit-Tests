package Project.userInterface;

import Project.domain.*;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;
import Project.service.CardClientService;
import Project.service.MedicamentService;
import Project.service.TranzactieService;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        IRepository<Medicament> medicamentIRepository= new InMemoryRepository<>();
        IRepository<Tranzactie> tranzactieRepository= new InMemoryRepository<>();
        IRepository<CardClient> cardClientRepository= new InMemoryRepository<>();

        TranzactieValidator tranzactieValidator = new TranzactieValidator();
        MedicamentValidator medicamentValidator = new MedicamentValidator();
        CardClientValidator cardClientValidator = new CardClientValidator();


        TranzactieService tranzactieService = new TranzactieService(tranzactieRepository, medicamentIRepository, cardClientRepository, tranzactieValidator);
        MedicamentService medicamentService = new MedicamentService( medicamentIRepository, medicamentValidator);
        CardClientService cardClientService = new CardClientService( cardClientRepository, cardClientValidator);
        medicamentIRepository.create(new Medicament("1", "Algocalmin", "Ewopharma", 20, true));
        medicamentIRepository.create(new Medicament("2", "Nurofen", "Farmec", 13, false));
        medicamentIRepository.create(new Medicament("3", "Eupantol", "Nycomed", 54, true ));
        medicamentIRepository.create(new Medicament("4", "Venlafaxin", "Basics", 67, false ));
        medicamentIRepository.create(new Medicament("5", "Ewofex", "Ewopharma", 64, true ));

        tranzactieRepository.create(new Tranzactie("1", "2", "342", 2, "2022-12-02 12:20"));
        tranzactieRepository.create(new Tranzactie("2", "2", "341", 1, "2022-10-02 13:20"));
        tranzactieRepository.create(new Tranzactie("3", "2", "3231", 1, "2022-04-17 09:11"));
        tranzactieRepository.create(new Tranzactie("4", "4", "212", 1, "2022-10-01 09:01"));
        tranzactieRepository.create(new Tranzactie("5", "4", "712", 1, "2021-08-02 09:01"));
        tranzactieRepository.create(new Tranzactie("6", "3", "552", 1, "2022-02-14 16:00"));

        cardClientRepository.create(new CardClient("111", "Ionescu", "Maria", "2987915288377", "1990-12-04", "12.03.2021"));
        cardClientRepository.create(new CardClient("222", "Popescu", "Tudor", "1987955288370", "1989-10-11", "01.01.2022"));
        cardClientRepository.create(new CardClient("333", "Coman", "Ana", "2957915288333", "1974-03-19", "17.02.2022"));


        Console console = new Console(tranzactieService, medicamentService, cardClientService);

        console.runUi();
    }
}
