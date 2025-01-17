package Catalogo;

public class Libro extends ElementoCatalogoBibliotecario {
    private String autore;
    private String genere;

    public Libro(long isbn, String t, int a, int n, String aut, String g) {
        super(isbn, t, a, n);
        this.setAutore(aut);
        this.setGenere(g);
    }

    public String getAutore() {
        return autore;
    }

    //Per evitare il warning
    @SuppressWarnings("unused")
    public String getGenere() {
        return genere;
    }

    //Controlla che non sia una stringa vuota
    public void setAutore(String autore) {
        if (!autore.isEmpty())
            this.autore = autore;
        else throw new IllegalArgumentException("Non può essere vuoto");
    }

    //Controlla che non sia una stringa vuota
    public void setGenere(String genere) {
        if (!genere.isEmpty())
            this.genere = genere;
        else throw new IllegalArgumentException("Non può essere vuoto");
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN=" + super.getISBN() +
                ", titolo='" + super.getTitolo() + '\'' +
                ", annoPubblicazione=" + super.getAnnoPubblicazione() +
                ", numeroPagine=" + super.getNumeroPagine() + '\'' +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
