package Project.domain;

public class MedicamentCuNrTranzactii {
    public Medicament medicament;
    public int nrTranzactii;

    public MedicamentCuNrTranzactii(Medicament medicament, int nrTranzactii) {
        this.medicament = medicament;
        this.nrTranzactii = nrTranzactii;
    }


    @Override
    public String toString() {
        return "MedicamentCuNrTranzactii{" +
                "medicament=" + medicament +
                ", nrTranzactii=" + nrTranzactii +
                '}';
    }
}
