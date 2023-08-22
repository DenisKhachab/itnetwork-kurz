package cz.itnetwork;

public class Pojisteni {
    private final String jmeno;
    private final String prijmeni;
    private final int vek;
    private final String telefonniCislo;

    //Konstruktor pro zjištění údajů od pojištěné osoby.
    public Pojisteni(String jmeno, String prijmeni, int vek, String telefonniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefonniCislo = telefonniCislo;
    }

    @Override
    public String toString() {
        return jmeno + " " + prijmeni + ", věk: " + vek + ", telefon: " + telefonniCislo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }
}
