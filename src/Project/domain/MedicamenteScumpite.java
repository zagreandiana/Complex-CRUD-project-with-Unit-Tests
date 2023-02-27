package Project.domain;

public class MedicamenteScumpite {
    private Medicament medicament;
    public float noulPret;

    public MedicamenteScumpite(Medicament medicament, float noulPret) {
        this.medicament = medicament;
        this.noulPret = noulPret;
    }

    @Override
    public String toString() {
        return "MedicamenteScumpite{" +
                "medicament=" + medicament +
                ", pretNou=" + noulPret +
                '}';
    }
}
