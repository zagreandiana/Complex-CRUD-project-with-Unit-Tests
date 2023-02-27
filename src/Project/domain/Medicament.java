package Project.domain;

public class Medicament extends Entity {
    private String id;
    private String nume;
    private String producator;
    private float pret;
    private boolean necesitaReteta;

    public Medicament(String id, String nume, String producator, float pret, boolean necesitaReteta) {
        super(id);
        this.id = id;
        this.nume = nume;
        this.producator = producator;
        this.pret = pret;
        this.necesitaReteta = necesitaReteta;
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getProducator() {
        return producator;
    }

    public float getPret() {
        return pret;
    }

    public boolean isNecesitaReteta() {
        return necesitaReteta;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id='" + id + '\'' +
                ", nume='" + nume + '\'' +
                ", producator='" + producator + '\'' +
                ", pret=" + pret +
                ", necesitaReteta=" + necesitaReteta +
                '}';
    }
}

