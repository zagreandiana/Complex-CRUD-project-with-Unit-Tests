package Project.service;

import Project.Exceptii.ExceptiiTranzactie;
import Project.domain.*;
import Project.repository.IRepository;
import Project.repository.InMemoryRepository;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class TranzactieService {
    private InMemoryRepository inMemoryRepository;
    private final IRepository<CardClient> cardClientRepository;
    private final IRepository<Medicament> medicamentIRepository;
    private final IRepository<Tranzactie> tranzactieRepository;
    private final TranzactieValidator tranzactieValidator;
    Scanner scanner = new Scanner(System.in);


    public TranzactieService(IRepository<Tranzactie> tranzactieRepository, IRepository<Medicament> medicamentIRepository, IRepository<CardClient> cardClientRepository, TranzactieValidator tranzactieValidator) {
        this.medicamentIRepository = medicamentIRepository;
        this.tranzactieRepository = tranzactieRepository;
        this.tranzactieValidator = tranzactieValidator;
        this.inMemoryRepository = inMemoryRepository;
        this.cardClientRepository = cardClientRepository;

    }

    public void addTranzactie(String id, String id_medicament, String id_card_client, int numarBucati, String dataSiOra) {
        Tranzactie tranzactie = new Tranzactie(id, id_medicament, id_card_client, numarBucati, dataSiOra);
        this.tranzactieValidator.validate(tranzactie);
        this.tranzactieRepository.create(tranzactie);
    }

    public List<Tranzactie> getAllT() {
        return this.tranzactieRepository.read();
    }


    public void updateTranzactie(String id, String id_medicament, String id_card_client, int numarBucati, String dataSiOra) {
        Tranzactie tranzactie = new Tranzactie(id, id_medicament, id_card_client, numarBucati, dataSiOra);
        this.tranzactieRepository.update(tranzactie);
    }

    public void deleteTranzactie(String id) {
        this.tranzactieRepository.delete(id);
    }

    public List<MedicamentCuNrTranzactii> getMedicamenteOrdonateDescDupaNrTranzactii() {
        Map<String, Integer> groupings = new HashMap<>();
        for (Tranzactie tranzactie : this.getAllT()) {
            String idMedicament = tranzactie.getId_medicament();
            if (groupings.containsKey(idMedicament)) {
                groupings.put(idMedicament, groupings.get(idMedicament) + 1); // + tranzactie.getNrBucati()
            } else {
                groupings.put(idMedicament, 1); // tranzactie.getNrBucati()
            }
        }
        List<MedicamentCuNrTranzactii> result = new ArrayList<>();
        for (String idMedicament : groupings.keySet()) {
            result.add(new MedicamentCuNrTranzactii(
                    this.medicamentIRepository.read(idMedicament),
                    groupings.get(idMedicament)
            ));
        }

        result.sort(new Comparator<MedicamentCuNrTranzactii>() {
            @Override
            public int compare(MedicamentCuNrTranzactii o1, MedicamentCuNrTranzactii o2) {
                return -Integer.compare(o1.nrTranzactii, o2.nrTranzactii);
            }
        });

        return result;
    }

    public List<CarduriCuReduceriObtinute> getCarduriOrdonateDescDupaReducerileObtinute() {
        Map<String, Float> mapCarduri = new HashMap<>();
        for (Tranzactie tranzactie : this.getAllT()) {
            String idCardClient = tranzactie.getId_card_client();
            if (mapCarduri.containsKey(idCardClient)) {
                mapCarduri.put(idCardClient, mapCarduri.get(idCardClient) + 1);
            } else {
                mapCarduri.put(idCardClient, 1F);
            }
        }
        List<CarduriCuReduceriObtinute> rezultat = new ArrayList<>();
        for (String idCardClient : mapCarduri.keySet()) {
            rezultat.add(new CarduriCuReduceriObtinute(this.cardClientRepository.read(idCardClient),
                    mapCarduri.get(idCardClient)));
        }
        rezultat.sort(new Comparator<CarduriCuReduceriObtinute>() {
            @Override
            public int compare(CarduriCuReduceriObtinute o1, CarduriCuReduceriObtinute o2) {
                return -Float.compare(o1.reduceriObtinute, o2.reduceriObtinute);
            }
        });
        return rezultat;
    }

    public String searchInTranzactie(String input) {
        return this.tranzactieRepository.search(input);
    }

    public String searchEverywhere(String input) {
         return this.tranzactieRepository.search(input);

    }

    public List<Tranzactie> getListaTranzactiiPeInterval()  {
        List<Tranzactie> getListaTranzactiiPeInterval = new ArrayList<>();
        System.out.println("Introduceti data la care incepe intervalul: ");
        String inceputInterval = this.scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
        System.out.println("Introduceti data la care se incheie intervalul: ");
        String sfarsitInterval = this.scanner.nextLine();
        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
        for (Tranzactie tranzactie : this.getAllT()) {
            LocalDateTime tranzactieDateTime = LocalDateTime.parse(tranzactie.getDataSiOra(), formatter);
            if (tranzactieDateTime.equals(DeLa) || tranzactieDateTime.isAfter(DeLa) && tranzactieDateTime.isBefore(PanaLa)) {
                getListaTranzactiiPeInterval.add(tranzactie);
            }

        }
        return getListaTranzactiiPeInterval;

    }

    public boolean isDateInRange(Date dateToVerify, Date startOfDateRange, Date endOfDateRange) {
        return (dateToVerify.before(startOfDateRange) || dateToVerify.after(endOfDateRange));
    }

    public List<Tranzactie> getListaStergeTranzactiiPeInterval(){
        List<Tranzactie> getListaStergeTranzactiiPeInterval = new ArrayList<>();
        System.out.println("Introduceti data la care incepe intervalul: (yyyy-MM-dd HH:mm) ");
        String inceputInterval = this.scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);
        System.out.println("Introduceti data la care se incheie intervalul: (yyyy-MM-dd HH:mm) ");
        String sfarsitInterval = this.scanner.nextLine();
        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
        for (Tranzactie tranzactie : this.getAllT()) {
            LocalDateTime tranzactieDateTime = LocalDateTime.parse(tranzactie.getDataSiOra(), formatter);
            if (tranzactieDateTime.isAfter(PanaLa) || tranzactieDateTime.isBefore(DeLa)) {
                getListaStergeTranzactiiPeInterval.add(tranzactie);
            }

        }
        return getListaStergeTranzactiiPeInterval;

    }


    public void medicamenteScumpite(Tranzactie tranzactie, IRepository<Tranzactie> tranzactieRepository, IRepository<Medicament> medicamentIRepository) {
        String errors = "";
        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>();

        Medicament medicamentTranzactionat = medicamentRepository.read(tranzactie.getId_medicament());
        if (medicamentTranzactionat.getId().isEmpty()) {
            errors += "Id-ul medicamentului tranzactionat nu poate fi nul";
        }
        if (medicamentTranzactionat == null) {
            errors += "Nu exista niciun medicament cu id-ul " + tranzactie.getId_medicament() + "\n";
        } else {
            if (medicamentRepository != null) {
                if (medicamentTranzactionat.isNecesitaReteta() == false) {
                    double reducere1 = 0.1;
                    double pretReducere1 = (float) (medicamentTranzactionat.getPret() * reducere1);
                    double pretFinal1 = medicamentTranzactionat.getPret() - pretReducere1;
                    System.out.println("Pretul cu reducere de " + reducere1 * 100 + "% este: " + pretFinal1);

                } else {
                    double reducere2 = 0.15;
                    double pretReducere2 = (float) (medicamentTranzactionat.getPret() * reducere2);
                    double pretFinal2 = medicamentTranzactionat.getPret() - pretReducere2;
                    System.out.println("Pretul cu reducere de " + reducere2 * 100 + "% este: " + pretFinal2);

                }
            }

            if (!errors.isEmpty()) {
                throw new ExceptiiTranzactie(errors);
            }
        }
    }
}






