package Project.service;

import Project.domain.*;
import Project.repository.IRepository;

import java.util.*;
import java.util.Scanner;


public class MedicamentService {
    private IRepository<Medicament> medicamentIRepository;

    private MedicamentValidator medicamentValidator;

    Scanner scanner = new Scanner(System.in);


    public MedicamentService(IRepository<Medicament> medicamentIRepository, MedicamentValidator medicamentValidator) {
        this.medicamentIRepository = medicamentIRepository;
        this.medicamentValidator = medicamentValidator;


    }

    public void addMedicament(String id, String nume, String producator, float pret, boolean necesitaReteta) {
        Medicament medicament = new Medicament(id, nume, producator, pret, necesitaReteta);
        this.medicamentValidator.validateM(medicament);
        this.medicamentIRepository.create(medicament);
    }

    public List<Medicament> getAllM() {
        return this.medicamentIRepository.read();
    }

    public void updateMedicament(String id, String nume, String producator, float pret, boolean necesitaReteta) {
        Medicament medicament = new Medicament(id, nume, producator, pret, necesitaReteta);
        this.medicamentIRepository.update(medicament);


    }

    public void deleteMedicament(String id) {
        this.medicamentIRepository.delete(id);
    }

    public String searchInMedicament(String input) {
        return this.medicamentIRepository.search(input);
    }

    public List<MedicamenteScumpite> getListaMedicamenteScumpite() {
        Map<Medicament, Float> mapMedicamenteScumpite = new HashMap<>();
        float noulPret = 1F;
        float pretNeschimbat = 1F;
        List<MedicamenteScumpite> listaFinala = new ArrayList<>();
        System.out.println("Introduceti pragul de valoare peste care vor fi scumpite preturile: ");
        float pragValoare = Float.parseFloat(this.scanner.nextLine());
        System.out.println("Introduceti procentajul cu care vor fi scumpite preturile: ");
        float procentaj = Float.parseFloat(this.scanner.nextLine());
        for (Medicament medicament : this.getAllM()) {
            if (medicament.getPret() > pragValoare) {
                noulPret = medicament.getPret() + (medicament.getPret() * procentaj) / 100;
                mapMedicamenteScumpite.put(medicament, noulPret);
            } else {
                pretNeschimbat = medicament.getPret();
                mapMedicamenteScumpite.put(medicament, pretNeschimbat);
            }
            List<MedicamenteScumpite> valueList = new ArrayList<>();
            for (Medicament medicament1 : mapMedicamenteScumpite.keySet()) {
                if (medicament1.getPret() > pragValoare) {
                    valueList.add(new MedicamenteScumpite(medicament, noulPret));
                } else {
                    valueList.add(new MedicamenteScumpite(medicament, pretNeschimbat));
                }

            }
            listaFinala.add(valueList.get(0));

        }

        return listaFinala;
    }


        public List<CarduriCuReduceriObtinute> getCarduriOrdonateDescDupaReducerileObtinute () {

        CardClient cardClient = new CardClient();
            Map<Medicament, Float> mapCarduri = new HashMap<>();
            float pretFinal1 = 1;
            float pretFinal2 = 1;
            float reducere1 = (float) 0.1;
            float reducere2 = (float) 0.15;
            List<CarduriCuReduceriObtinute> listaFinala = new ArrayList<>();
            for (Medicament medicament : this.getAllM()) {
                if (medicament.isNecesitaReteta() == false) {
                    float pretReducere1 = (float) (medicament.getPret() * reducere1);
                    pretFinal1 = medicament.getPret() - pretReducere1;
                    mapCarduri.put(medicament, pretFinal1);
                } else {
                    float pretReducere2 = (float) (medicament.getPret() * reducere2);
                    pretFinal2 = medicament.getPret() - pretReducere2;
                    mapCarduri.put(medicament, pretFinal2);
                }
                List<CarduriCuReduceriObtinute> valueList = new ArrayList<>();
                for (Medicament medicament1 : mapCarduri.keySet()){
                    if(medicament1.isNecesitaReteta() == false){
                    valueList.add(new CarduriCuReduceriObtinute(cardClient, pretFinal1));
                } else {
                        valueList.add(new CarduriCuReduceriObtinute(cardClient, pretFinal2));
                }
            }
                listaFinala.add(valueList.get(0));
        }

            listaFinala.sort(new Comparator<CarduriCuReduceriObtinute>() {
                @Override
                public int compare(CarduriCuReduceriObtinute o1, CarduriCuReduceriObtinute o2) {
                    return -Double.compare(o1.reduceriObtinute, o2.reduceriObtinute);
                    }
                });
                return listaFinala;
            }

}





