package Project.service;

import Project.domain.CardClient;
import Project.domain.CardClientValidator;
import Project.repository.IRepository;

import java.util.List;

public class CardClientService {
    private IRepository<CardClient> cardClientRepository;
    private CardClientValidator cardClientValidator;

    public CardClientService(IRepository<CardClient> cardClientRepository, CardClientValidator cardClientValidator){
        this.cardClientRepository = cardClientRepository;
        this.cardClientValidator = cardClientValidator;
    }
    public void addCardClient(String id, String nume, String prenume, String CNP, String dataNasterii, String dataInregistrarii){
        CardClient cardClient = new CardClient(id, nume, prenume, CNP, dataNasterii, dataInregistrarii);
        this.cardClientValidator.validateC(cardClient);
        this.cardClientRepository.create(cardClient);
    }
    public List<CardClient> getAllC() {
        return this.cardClientRepository.read();
    }

    public void updateCardClient(String id, String nume, String prenume, String CNP, String dataNasterii, String dataInregistrarii) {
        CardClient cardClient = new CardClient(id, nume, prenume, CNP, dataNasterii, dataInregistrarii);
        this.cardClientRepository.update(cardClient);
    }
    public void deleteCardClient(String id){
        this.cardClientRepository.delete(id);
    }

    public String searchInCardClient(String input) {
        return this.cardClientRepository.search(input) ;
    }


}
