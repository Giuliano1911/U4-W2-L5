package Catalogo;

import java.time.LocalDate;
import java.util.Objects;

public abstract class ElementoCatalogoBibliotecario {
    private final long ISBN;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public ElementoCatalogoBibliotecario(long isbn, String t, int a, int n) {
        this.ISBN = isbn;
        this.setTitolo(t);
        this.setAnnoPubblicazione(a);
        this.setNumeroPagine(n);
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public long getISBN() {
        return ISBN;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        //Anno di invenzione della scrittura! Sicuro non può essere precedente l'elemento!
        if (annoPubblicazione >= -3200 && annoPubblicazione <= LocalDate.now().getYear())
            this.annoPubblicazione = annoPubblicazione;
        else throw new IllegalArgumentException("L'anno non è corretto");
    }

    //Il numero di pagine non può essere negativo
    public void setNumeroPagine(int numeroPagine) {
        if (numeroPagine > 0)
            this.numeroPagine = numeroPagine;
        else throw new IllegalArgumentException("Il numero non può essere negativo");
    }

    //Controlla che non sia una stringa vuota
    public void setTitolo(String titolo) {
        if (!titolo.isEmpty())
            this.titolo = titolo;
        else throw new IllegalArgumentException("Il titolo non può essere vuoto");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ElementoCatalogoBibliotecario that)) return false;
        return ISBN == that.ISBN;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ISBN);
    }

    @Override
    public String toString() {
        return "ElementoCatalogoBibliotecario{" +
                "ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}