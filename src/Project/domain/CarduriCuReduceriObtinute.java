package Project.domain;

public class CarduriCuReduceriObtinute {

    public Medicament medicament;
    public float reduceriObtinute;

    @Override
    public String toString() {
        return "CarduriCuReduceriObtinute{" +
                ", medicament=" + medicament +
                ", reduceriObtinute=" + reduceriObtinute +
                '}';
    }

    public CarduriCuReduceriObtinute(CardClient cardClient, float reduceriObtinute) {
        this.medicament = medicament;
        this.reduceriObtinute = reduceriObtinute;
    }


    }

