package Project.domain;

public class Tranzactie extends Entity {
    private String id;
    private String id_medicament;
    private String id_card_client;
    private int numarBucati;
    private String dataSiOra;

    public Tranzactie(String id, String id_medicament, String id_card_client, int numarBucati, String dataSiOra) {
        super(id);
        this.id = id;
        this.id_medicament = id_medicament;
        this.id_card_client = id_card_client;
        this.numarBucati = numarBucati;
        this.dataSiOra = dataSiOra;
    }

    public String getId() {
        return id;
    }

    public String getId_medicament() {
        return id_medicament;
    }

    public String getId_card_client() {
        return id_card_client;
    }

    public int getNumarBucati(){
        return numarBucati;
    }

    public String getDataSiOra() {
        return dataSiOra;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "id='" + id + '\'' +
                ", id_medicament='" + id_medicament + '\'' +
                ", id_card_client='" + id_card_client + '\'' +
                ", numarBucati=" + numarBucati +
                ", dataSiOra='" + dataSiOra + '\'' +
                '}';
    }
}
