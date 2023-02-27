package Project.domain;

import Project.Exceptii.ExceptiiCardClient;
import Project.repository.IRepository;

public class CardClientValidator {


    public void validateC(CardClient cardClient) {

        String errors = "";
        if (cardClient.getNume().isEmpty()) {
            errors += "Numele clientului nu poate fi gol";
        }
        if(cardClient.getPrenume().isEmpty()){
            errors += "Prenumele clientului nu poate fi gol";
        }
        if (cardClient.getCNP().isEmpty()) {
            errors += "CNP -ul clientului nu poate fi nul";
        }
       // if()
        if (cardClient.getId().isEmpty()) {
            errors += "Id-ul cardului nu poate fi gol";
        }
        if(cardClient.getDataNasterii().isEmpty()){
            errors += "Data nasterii clientului nu poate fi nula";
        }
        if(cardClient.getDataInregistrarii().isEmpty()){
            errors += "Data inregistrarii cardului client nu poate fi nula";
        }


        if (!errors.isEmpty()) {
            throw new ExceptiiCardClient(errors);
        }
    }

}
