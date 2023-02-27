package Project.domain;


import Project.Exceptii.ExceptiiTranzactie;
import Project.repository.IRepository;

public class TranzactieValidator {

    public void validate(Tranzactie tranzactie) {

        String errors = "";
        if (tranzactie.getId().isEmpty()) {
            errors += "Id-ul tranzactiei nu poate fi nul\n";
        }
        if (tranzactie.getId_medicament().isEmpty()) {
            errors += "Id-ul medicamentului tranzactionat nu poate fi nul\n";
        }
        if (tranzactie.getId_card_client().isEmpty()) {
            errors += "Id-ul cardului client nu poate fi nul\n";
        }
        if (tranzactie.getNumarBucati() < 0) {
            errors += "Numarul bucatilor vandute trebuie sa fie mai mare decat 0\n";
        }
        if (tranzactie.getDataSiOra().isEmpty()) {
            errors += "Data si ora tranzactiei nu pot fi nule\n";
        }
        if (!errors.isEmpty()) {
            throw new ExceptiiTranzactie(errors);
        }

    }
}
