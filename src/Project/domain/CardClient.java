package Project.domain;

public class CardClient extends Entity {
    private String id;
    private String nume;
    private String prenume;
    private String CNP;   // trebuie sa fie unic
    private String dataNasterii;  //format de tip dd.mm.yyyy
    private String dataInregistrarii; //format de tip dd.mm.yyyy

    public CardClient(String id) {
        super(id);
    }

    public CardClient(String id, String nume, String prenume, String CNP, String dataNasterii, String dataInregistrarii){
        super(id);
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.dataNasterii = dataNasterii;
        this.dataInregistrarii = dataInregistrarii;
    }

    public CardClient() {

    }

    public String getId() {
        return id;
    }
    public String getNume(){
        return nume;
    }
    public String getPrenume(){
        return prenume;
    }
    public String getCNP(){
        return CNP;
    }
    public String getDataNasterii() {
        return dataNasterii;
    }
    public String getDataInregistrarii(){
        return dataInregistrarii;
    }

    @Override
    public String toString() {
        return "CardClient{" +
                "id='" + id + '\'' +
                ", numeClient='" + nume + '\'' +
                ", prenumeClient='" + prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                ", dataNasterii='" + dataNasterii + '\'' +
                ", dataInregistrarii='" + dataInregistrarii + '\'' +
                '}';
    }
}
