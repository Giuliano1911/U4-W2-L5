package org.example;

import Catalogo.ElementoCatalogoBibliotecario;
import Catalogo.Libro;
import Catalogo.Periodicita;
import Catalogo.Rivista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ElementoCatalogoBibliotecario libro1 = new Libro(1111, "Orgoglio e Pregiudizio", 1970, 1000, "Jane Austen", "Romanzo");
        ElementoCatalogoBibliotecario libro2 = new Libro(1112, "Il Signore degli Anelli", 2002, 1975, "J.R.R. Tolkien", "Fantasy");
        ElementoCatalogoBibliotecario libro3 = new Libro(1113, "1984", 1949, 550, "George Orwell", "Romanzo");
        ElementoCatalogoBibliotecario libro4 = new Libro(1114, "Se questo è un uomo", 1947, 430, "Primo Levi", "Memorialistico");

        ElementoCatalogoBibliotecario rivista1 = new Rivista(1001, "Men's Health", 1987, 130, Periodicita.MENSILE);
        ElementoCatalogoBibliotecario rivista2 = new Rivista(1002, "Vogue", 1892, 150, Periodicita.MENSILE);
        ElementoCatalogoBibliotecario rivista3 = new Rivista(1003, "Vanity Fair", 1949, 90, Periodicita.SEMESTRALE);

        Archivio archivio = new Archivio();
        //Ho deciso di far interrompere il processo se ci sono più ISBN uguali e non mettere un try/catch perchè in questa fase siamo ancora
        //fuori dalla fase dello scanner, per cui l'errore è sicuramente di chi ha inserito i dati nell'archivio e non dell'utente finale
        archivio.add(libro1);
        archivio.add(rivista1);
        archivio.add(libro2);
        archivio.add(rivista2);
        archivio.add(libro3);
        archivio.add(libro4);
        archivio.add(rivista3);

        System.out.println("Benvenuto nell'archivio bibliotecario ");

        int input = 1;
        while (input != 0) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("0 Esci");
            System.out.println("1 Aggiungi un elemento all'archivio");
            System.out.println("2 Cerca un elemento nell'archivio");
            System.out.println("3 Rimuovi elemento dall'archivio");
            System.out.println("4 Ricerca tutti gli elementi pubblicati in un dato anno");
            System.out.println("5 Ricerca tutti gli elementi prodotti dal medesimo autore");
            System.out.println("6 Modifica un elemento già presente nell'archivio");
            System.out.println("7 Statistiche dell'archivio");
            input = Integer.parseInt(scanner.nextLine());
            //I vari tipi delle variabili che verranno inseriti tramite scanner, sceltaI = sceltaInteger - sceltaL = sceltaLong ecc.
            int sceltaI = 0;
            long sceltaL;
            String sceltaS = "";
            switch (input) {
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                case 1:
                    //inserimento di tutte le variabili necessarie pre la creazione di un libro o di una rivista
                    System.out.println("Inserisci ISBN");
                    long isbn = Long.parseLong(scanner.nextLine());
                    System.out.println("Inserisci Titolo");
                    String titolo = scanner.nextLine();
                    System.out.println("Inserisci anno pubblicazione");
                    int anno = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci numero pagine");
                    int numeroPagine = Integer.parseInt(scanner.nextLine());
                    while (sceltaI != 1 && sceltaI != 2) {
                        System.out.println("Che elemento vuoi inserire?");
                        System.out.println("1 Libro");
                        System.out.println("2 Rivista");
                        sceltaI = Integer.parseInt(scanner.nextLine());
                        switch (sceltaI) {
                            case 1:
                                System.out.println("Inserisci l'autore del libro");
                                String autore = scanner.nextLine();
                                System.out.println("Inserisci il genere del libro");
                                String genere = scanner.nextLine();
                                try {
                                    ElementoCatalogoBibliotecario newLibro = new Libro(isbn, titolo, anno, numeroPagine, autore, genere);
                                    archivio.add(newLibro);
                                    System.out.println("Libro inserito con successo");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Inserisci la periodicità della rivista (mensile o settimanale o semestrale)");
                                String periodicita = scanner.nextLine();
                                try {
                                    //Controlla se il valore della stringa periodicita sia valido per l'ENUM
                                    Periodicita period = Periodicita.valueOf(periodicita.toUpperCase());
                                    ElementoCatalogoBibliotecario newRivista = new Rivista(isbn, titolo, anno, numeroPagine, period);
                                    archivio.add(newRivista);
                                    System.out.println("Rivista inserita con successo");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            default:
                                System.out.println("Numero non corretto, prova ad inserire 1 o 2");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Inserisci l'ISBN dell'elemento da cercare");
                    sceltaL = Long.parseLong(scanner.nextLine());
                    try {
                        System.out.println(archivio.ricercaISBN(sceltaL));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Inserisci l'ISBN dell'elemento da eliminare");
                    sceltaL = Long.parseLong(scanner.nextLine());
                    try {
                        archivio.eliminaISBN(sceltaL);
                        System.out.println("Elemento rimosso con successo!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Inserisci anno di pubblicazione degli elementi da ricercare");
                    sceltaI = Integer.parseInt(scanner.nextLine());
                    try {
                        System.out.println(archivio.ricercaAnno(sceltaI));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Inserisci il nome dell'autore");
                    sceltaS = scanner.nextLine();
                    try {
                        System.out.println(archivio.ricercaAutore(sceltaS));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Inserisci l'ISBN dell'elemento da modificare");
                    sceltaL = Long.parseLong(scanner.nextLine());
                    try {
                        boolean isLibro = archivio.ricercaISBN(sceltaL) instanceof Libro;
                        while (sceltaI != 1 && sceltaI != 2 && sceltaI != 3 && sceltaI != 4 && sceltaI != 5) {
                            //mi serve un'altra variabile int perché la prima è in uso per il controllo dello switch
                            int sceltaI2;
                            Periodicita sceltaP;
                            System.out.println("Cosa vuoi modificare?");
                            System.out.println("1 Titolo");
                            System.out.println("2 Anno pubblicazione");
                            System.out.println("3 Numero pagine");
                            if (isLibro) {
                                System.out.println("4 Autore");
                                System.out.println("5 Genere");
                            } else System.out.println("4 Periodicità");
                            sceltaI = Integer.parseInt(scanner.nextLine());
                            switch (sceltaI) {
                                case 1:
                                    System.out.println("Inserisci il titolo da inserire al posto del precedente");
                                    sceltaS = scanner.nextLine();
                                    try {
                                        archivio.modificaTitolo(sceltaL, sceltaS);
                                    } catch (Exception ignored) {
                                    }
                                    break;
                                case 2:
                                    System.out.println("Inserisci l'anno di pubblicazione da inserire al posto del precedente");
                                    sceltaI2 = Integer.parseInt(scanner.nextLine());
                                    try {
                                        archivio.modificaAnnoPubblicazione(sceltaL, sceltaI2);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Inserisci il numero di pagine da inserire al posto del precedente");
                                    sceltaI2 = Integer.parseInt(scanner.nextLine());
                                    try {
                                        archivio.modificaNumeroPagine(sceltaL, sceltaI2);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 4:
                                    //Se è un libro il 4 modifica l'autore, se è una rivista modifica la periodicità
                                    if (isLibro) {
                                        System.out.println("Inserisci l'autore da inserire al posto del precedente");
                                        sceltaS = scanner.nextLine();
                                        try {
                                            archivio.modificaAutore(sceltaL, sceltaS);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else {
                                        System.out.println("Inserisci la nuova periodicità dell'elemento (mensile o settimanale o semestrale)");
                                        while (!sceltaS.matches("mensile|settimanale|semestrale")) {
                                            sceltaS = scanner.nextLine();
                                            try {
                                                //Controlla se il valore della stringa sceltaS sia valido per l'ENUM
                                                sceltaP = Periodicita.valueOf(sceltaS.toUpperCase());
                                                archivio.modificaPeriodicita(sceltaL, sceltaP);
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                    break;
                                case 5:
                                    //Dato che la rivista ha solo un attributo "unico" se si preme il 5 il switch va lo stesso nel default
                                    //perché il break si trova dentro l'if
                                    if (isLibro) {
                                        System.out.println("Inserisci il genere da inserire al posto del precedente");
                                        sceltaS = scanner.nextLine();
                                        try {
                                            archivio.modificaGenere(sceltaL, sceltaS);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                default:
                                    if (isLibro)
                                        System.out.println("Numero non corretto, prova 1 o 2 o 3 o 4 o 5");
                                    else System.out.println("Numero non corretto, prova 1 o 2 o 3 o 4");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    archivio.statistiche();
                    break;
                default:
                    System.out.println("Numero non corretto, Prova a inserire 0 o 1 o 2 o 3 o 4 o 5 o 6 o 7");
            }
        }
    }
}