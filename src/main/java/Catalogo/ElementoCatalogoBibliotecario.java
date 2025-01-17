package Catalogo;

public abstract class ElementoCatalogoBibliotecario {
    String ISBN;
    String titolo;
    int annoPubblicazione;
    int numeroPagine;

    public ElementoCatalogoBibliotecario(String ISBN, String t, int a, int n) {
        this.ISBN = ISBN;
        this.titolo = t;
        this.annoPubblicazione = a;
        this.numeroPagine = n;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}