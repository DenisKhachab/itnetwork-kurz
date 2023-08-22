package cz.itnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Projekt {

    // Seznam všech pojištěných osob.
    private final List<Pojisteni> evidencePojistenych = new ArrayList<>();

    // Metoda pro ověření platnosti tel. čísla.
    private boolean jePlatneTelefoniCislo(String telefonniCislo) {
        return telefonniCislo.matches("^[0-9]{9,10}$");
    }

    // Metoda pro přidání nové osoby.
    public void pridatNovehoPojisteneho(String jmeno, String prijmeni, int vek, String telefonniCislo) {
        Pojisteni pojisteni = new Pojisteni(jmeno, prijmeni, vek, telefonniCislo);
        evidencePojistenych.add(pojisteni);
    }

    // Metoda pro výpis všech pojištěných osob.
    public void vypsatVsechnyPojistene() {
        for (Pojisteni pojisteni : evidencePojistenych) {
            System.out.println(pojisteni);
        }
    }

    public static void main(String[] args) {
        Projekt projekt = new Projekt();
        Scanner scanner = new Scanner(System.in);
        boolean pokracovat = true;

        // Smyčka pro interakci s uživatelem.
        while (pokracovat) {
            System.out.println("Vyberte si akci:");
            System.out.println("\n1 - Přidat nového pojištěného");
            System.out.println("2 - Vypsat všechny pojištěné");
            System.out.println("3 - Vyhledat pojištěného");
            System.out.println("4 - Konec");

            int volba = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            // Konkrétní volba akce na základě výběru od uživatele.
            switch (volba) {
                case 1:
                    // Zde zadá uživatel své údaje.
                    String jmeno;
                    do {
                        System.out.print("Zadejte jméno pojištěného:\n ");
                        jmeno = scanner.nextLine().trim();
                        if (jmeno.isEmpty()) {
                            System.out.println("Jméno není vyplněné. Zkuste to prosím znovu.");
                        }
                    } while (jmeno.isEmpty());

                    String prijmeni;
                    do {
                        System.out.print("Zadejte příjmení:\n ");
                        prijmeni = scanner.nextLine().trim();
                        if (prijmeni.isEmpty()) {
                            System.out.println("Příjmení není vyplněné. Zkuste to prosím znovu. ");
                        }
                    } while (prijmeni.isEmpty());

                    // Ověření tel. čísla.
                    String telefon;
                    do {
                        System.out.print("Zadejte telefonní číslo:\n ");
                        telefon = scanner.nextLine();
                        if (!projekt.jePlatneTelefoniCislo(telefon)) {
                            System.out.println("Neplatné telefonní číslo. Zkuste to prosím znovu. ");
                        }
                    } while (!projekt.jePlatneTelefoniCislo(telefon));

                    // Ověření věku.
                    int vek = -1;
                    do {
                        System.out.print("Zadejte věk:\n ");
                        if (scanner.hasNextInt()) {
                            vek = scanner.nextInt();
                            if (vek < 0 || vek > 120) {
                                System.out.println("Věk musí být v rozmezí 0-120. Zkuste to prosím znovu. ");
                            }
                        }
                    } while (vek < 0 || vek > 120);
                    scanner.nextLine();


                    projekt.pridatNovehoPojisteneho(jmeno, prijmeni, vek, telefon);
                    break;

                case 2:
                    // Zobrazení všech pojištěných osob ze seznamu.
                    System.out.println("Toto je výpis všech pojištěných:\n ");
                    projekt.vypsatVsechnyPojistene();
                    System.out.println();
                    break;

                case 3:
                    // Vyhledání pojištěné osoby podle jména a příjmení.
                    System.out.print("Zadejte jméno pojištěného:\n ");
                    jmeno = scanner.nextLine();
                    System.out.print("Zadejte příjmení:\n ");
                    prijmeni = scanner.nextLine();

                    // Metoda, která vyhodí chybu když uživatel není v seznamu pojištěných.
                    boolean nalezeno = false;
                    for (Pojisteni pojisteni : projekt.evidencePojistenych) {
                        if (pojisteni.getJmeno().equalsIgnoreCase(jmeno) && pojisteni.getPrijmeni().equalsIgnoreCase(prijmeni)) {
                            System.out.println(pojisteni);
                            nalezeno = true;
                            break;
                        }
                    }

                    if (!nalezeno) {
                        System.out.println("Tato osoba není v databázi, zkuste to znovu.\n ");
                    }
                    break;

                case 4:
                    pokracovat = false;
                    break;
                default:
                    // Když uživatel vybere z menu jiné číslo, než 1-4.
                    System.out.println("Toto je neplatná volba. Zkuste to prosím znovu.\n ");
            }
        }
        scanner.close(); // Uzavření scanneru po ukončení aplikace.
    }
}

