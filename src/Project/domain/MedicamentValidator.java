package Project.domain;

import Project.Exceptii.ExceptiiMedicament;
import Project.repository.IRepository;

public class MedicamentValidator {
    public void validateM(Medicament medicament) {
        String errors = "";
        if (medicament.getNume().isEmpty()) {
            errors += "Numele medicamentului nu poate fi gol\n";
        }
        if (medicament.getPret() < 0) {
            errors += "Pretul trebuie sa fie unul pozitiv si mai mare decat 0\n";
        }
        if (medicament.getId().isEmpty()) {
            errors += "Id-ul medicamentului nu poate fi gol\n";
        }
        if (medicament.getProducator().isEmpty()) {
            errors += "Producatorul medicamentului nu poate fi gol\n";
        }
        if (!errors.isEmpty()) {
            throw new ExceptiiMedicament(errors);
        }
    }
}
