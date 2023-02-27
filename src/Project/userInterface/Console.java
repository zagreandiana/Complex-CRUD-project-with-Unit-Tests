package Project.userInterface;

import Project.domain.*;
import Project.service.CardClientService;
import Project.service.MedicamentService;
import Project.service.TranzactieService;

import java.text.ParseException;
import java.util.Scanner;

public class Console {
    private TranzactieService tranzactieService;
    private MedicamentService medicamentService;
    private CardClientService cardClientService;
    Scanner scanner = new Scanner(System.in);

    public Console(TranzactieService tranzactieService, MedicamentService medicamentService, CardClientService cardClientService) {
        this.tranzactieService = tranzactieService;
        this.medicamentService = medicamentService;
        this.cardClientService = cardClientService;
    }

    public void runUi() throws ParseException {
        while (true) {
            this.showMenu();
            String opt = this.scanner.nextLine();
            switch (opt) {
                case "1":
                    this.afisareMedicamente();
                    break;
                case "2":
                    this.adaugareMedicament();
                    break;
                case "3":
                    this.handleUpdateMedicament();
                    break;
                case "4":
                    this.handleDeleteMedicament();
                    break;
                case "5":
                    this.afisareCarduriClient();
                    break;
                case "6":
                    this.adaugareCardClient();
                    break;
                case "7":
                    this.handleUpdateCardClient();
                    break;
                case "8":
                    this.handleDeleteCardClient();
                    break;
                case "9":
                    this.afisareTranzactii();
                    break;
                case "10":
                    this.adaugareTranzactie();
                    break;
                case "11":
                    this.handleUpdateTranzatie();
                    break;
                case "12":
                    this.handleDeleteTranzactie();
                    break;
                case "13":
                    this.handleMedicamenteDescDupaNrTranzactii();
                    break;
                case "14":
                    this.handleSearchInTranzactie();
                    break;
                case "15":
                    this.handleSearchInMedicament();
                    break;
                case "16":
                    this.handleSearchInCardClient();
                    break;
                case "17":
                    this.handleSearchEverywhere();
                    break;
//                case "18":
//                    this.handleCarduriCuReduceriObtinute();
//                    break;
                case "19":
                    this.handleMedicamenteScumpite();
                    break;
                case "20":
                    this.handleAfisareTranzactiiPeInterval();
                    break;
                case "21":
                    this.handleStergereTranzactiiPeInterval();
                    return;
                default:
                    System.out.println("Optiunea selectata este invalida!");
            }
        }
    }

    private void adaugareMedicament() {
        try {
            System.out.println("Introduceti id-ul medicamentului: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti numele medicamentului: ");
            String nume = this.scanner.nextLine();
            System.out.println("Introduceti producatorul medicamentului: ");
            String producator = this.scanner.nextLine();
            System.out.println("Introduceti pretul medicamentului: ");
            float pret = Float.parseFloat(this.scanner.nextLine());
            System.out.println("Medicamentul necesita reteta: ");
            boolean necesitaReteta = Boolean.parseBoolean(this.scanner.nextLine());

            this.medicamentService.addMedicament(id, nume, producator, pret, necesitaReteta);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugareCardClient() {
        try {
            System.out.println("Introduceti id-ul cardului client: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti numele clientului: ");
            String nume = this.scanner.nextLine();
            System.out.println("Introduceti prenumele clientului: ");
            String prenume = this.scanner.nextLine();
            System.out.println("Introduceti CNP-ul clientului: ");
            String CNP = this.scanner.nextLine();
            System.out.println("Introduceti data nasterii clientului ");
            String dataNasterii = this.scanner.nextLine();
            System.out.println("Introduceti data inregistrarii cardului ");
            String dataIntregistrarii = this.scanner.nextLine();

            this.cardClientService.addCardClient(id, nume, prenume, CNP, dataNasterii, dataIntregistrarii);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugareTranzactie() {
        try {
            System.out.println("Introduceti id-ul tranzactie: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti id-ul medicamentului: ");
            String id_medicament = this.scanner.nextLine();
            System.out.println("Introduceti id cardClient: ");
            String id_card_client = this.scanner.nextLine();
            System.out.println("Introduceti numarul de bucati vandute: ");
            int numarBucati = Integer.parseInt(this.scanner.nextLine());
            System.out.println("Introduceti data si ora tranzactiei ");
            String dataSiOra = this.scanner.nextLine();

            this.tranzactieService.addTranzactie(id, id_medicament, id_card_client, numarBucati, dataSiOra);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void afisareMedicamente() {
        for (Medicament medicament : this.medicamentService.getAllM()) {
            System.out.println(medicament);
        }
    }

    private void afisareCarduriClient() {
        for (CardClient cardClient : this.cardClientService.getAllC()) {
            System.out.println(cardClient);
        }
    }

    private void afisareTranzactii() {
        for (Tranzactie tranzactie : this.tranzactieService.getAllT()) {
            System.out.println(tranzactie);
        }
    }

    private void handleUpdateMedicament() {
        try {
            System.out.println("Introduceti id-ul medicamentului: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti numele medicamentului: ");
            String nume = this.scanner.nextLine();
            System.out.println("Introduceti producatorul medicamentului: ");
            String producator = this.scanner.nextLine();
            System.out.println("Introduceti pretul medicamentului: ");
            float pret = Float.parseFloat(this.scanner.nextLine());
            System.out.println("Medicamentul necesita reteta (true/false) : ");
            boolean necesitaReteta = Boolean.parseBoolean(this.scanner.nextLine());

            this.medicamentService.updateMedicament(id, nume, producator, pret, necesitaReteta);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdateCardClient() {
        try {
            System.out.println("Introduceti id-ul cardului client: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti numele clientului: ");
            String nume = this.scanner.nextLine();
            System.out.println("Introduceti prenumele clientului: ");
            String prenume = this.scanner.nextLine();
            System.out.println("Introduceti CNP-ul clientului: ");
            String CNP = this.scanner.nextLine();
            System.out.println("Introduceti data nasterii clientului in format dd.mm.yyyy");
            String dataNasterii = this.scanner.nextLine();
            System.out.println("Introduceti data inregistrarii cardului in format dd.mm.yyyy HH:mm");
            String dataIntregistrarii = this.scanner.nextLine();

            this.cardClientService.updateCardClient(id, nume, prenume, CNP, dataNasterii, dataIntregistrarii);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdateTranzatie() {
        try {
            System.out.println("Introduceti id-ul tranzactie: ");
            String id = this.scanner.nextLine();
            System.out.println("Introduceti id-ul medicamentului: ");
            String id_medicament = this.scanner.nextLine();
            System.out.println("Introduceti id cardClient: ");
            String id_card_client = this.scanner.nextLine();
            System.out.println("Introduceti numarul de bucati vandute: ");
            int numarBucati = Integer.parseInt(this.scanner.nextLine());
            System.out.println("Introduceti data si ora tranzactiei in format dd.mm.yyyy HH:mm");
            String dataSiOra = this.scanner.nextLine();

            this.tranzactieService.updateTranzactie(id, id_medicament, id_card_client, numarBucati, dataSiOra);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSearchInTranzactie() {
        try {
            System.out.println("Introduceti textul:");
            String input = this.scanner.nextLine();
            this.tranzactieService.searchInTranzactie(input);
            System.out.println(tranzactieService.searchInTranzactie(input));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSearchInMedicament() {
        try {
            System.out.println("Introduceti textul:");
            String input = this.scanner.nextLine();
            this.medicamentService.searchInMedicament(input);
            System.out.println(medicamentService.searchInMedicament(input));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSearchInCardClient() {
        try {
            System.out.println("Introduceti textul:");
            String input = this.scanner.nextLine();
            this.cardClientService.searchInCardClient(input);
            System.out.println(cardClientService.searchInCardClient(input));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleSearchEverywhere() {
        try {
            System.out.println("Introduceti textul:");
            String input = this.scanner.nextLine();
            this.tranzactieService.searchEverywhere(input);
            System.out.println(tranzactieService.searchEverywhere(input));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteMedicament() {
        try {
            System.out.println("Dati id-ul medicamentului:");
            String id = this.scanner.nextLine();
            this.medicamentService.deleteMedicament(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteCardClient() {
        try {
            System.out.println("Dati id-ul cardului:");
            String id = this.scanner.nextLine();
            this.cardClientService.deleteCardClient(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteTranzactie() {
        try {
            System.out.println("Dati id-ul medicamentului:");
            String id = this.scanner.nextLine();
            this.tranzactieService.deleteTranzactie(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleMedicamenteScumpite() {
        for (MedicamenteScumpite medicamenteScumpite : this.medicamentService.getListaMedicamenteScumpite()) {
            System.out.println(medicamenteScumpite);
        }
    }
    private void handleAfisareTranzactiiPeInterval() {
        for (Tranzactie tranzactiiPeInterval : this.tranzactieService.getListaTranzactiiPeInterval()){
            System.out.println(tranzactiiPeInterval);
        }
    }

    private void handleStergereTranzactiiPeInterval() {
        for (Tranzactie tranzactiiPeInterval : this.tranzactieService.getListaStergeTranzactiiPeInterval()){
            System.out.println(tranzactiiPeInterval);
        }
    }
        private void handleMedicamenteDescDupaNrTranzactii () {
            for (MedicamentCuNrTranzactii medicamentCuNrTranzactii : this.tranzactieService.getMedicamenteOrdonateDescDupaNrTranzactii()) {
                System.out.println(medicamentCuNrTranzactii);
            }
        }
        private void handleCarduriCuReduceriObtinute () {
            for (CarduriCuReduceriObtinute carduriCuReduceriObtinute : this.medicamentService.getCarduriOrdonateDescDupaReducerileObtinute()) {
                System.out.println(carduriCuReduceriObtinute);
            }
        }


        private void showMenu () {
            System.out.println("1. Afiseaza medicamente");
            System.out.println("2. Adauga un medicament");
            System.out.println("3. Updateaza un medicament");
            System.out.println("4. Sterge un medicament");
            System.out.println("5. Afiseaza cardurile client");
            System.out.println("6. Adauga un card client");
            System.out.println("7. Updateaza un card client");
            System.out.println("8. Sterge un card client");
            System.out.println("9. Afiseaza tranzactiile");
            System.out.println("10. Adauga o tranzactie");
            System.out.println("11. Updateaza o tranzactie");
            System.out.println("12. Sterge o tranzactie");
            System.out.println("13. Raport medicamente ordonate descrescator dupa nr de tranzactii");
            System.out.println("14. Search in tranzactie");
            System.out.println("15. Search in medicament");
            System.out.println("16. Search in card client");
            System.out.println("17. Search in toate entitatile");
        //    System.out.println("18. Raport carduri client ordonate descrescator dupa nr de reduceri obtinute");
            System.out.println("19. Raport cu noile preturi dupa efectuarea scumpirilor");
            System.out.println("20. Raport cu tranzactii pentru un interval dat (yyyy-MM-dd HH:mm)");
            System.out.println("21. Raport cu tranzactii din care sunt excluse cele pe un interval dat (yyyy-MM-dd HH:mm)");

        }
    }


