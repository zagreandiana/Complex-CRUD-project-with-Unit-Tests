package Project.domain;

public class TranzactiiPeInterval {
    private final Tranzactie tranzactie;
    private final String inceputInterval;
    private final String sfarsitInterval;


    public TranzactiiPeInterval(Tranzactie tranzactie, String inceputInterval, String sfarsitInterval) {
        this.tranzactie = tranzactie;
        this.inceputInterval = inceputInterval;
        this.sfarsitInterval = sfarsitInterval;

    }

    @Override
    public String toString() {
        return "TranzactiiPeInterval{" +
                "tranzactie=" + tranzactie +
                ", inceputInterval='" + inceputInterval + '\'' +
                ", sfarsitInterval='" + sfarsitInterval + '\'' +
                '}';
    }
}

