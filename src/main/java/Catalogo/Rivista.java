package Catalogo;

public class Rivista extends ElementoCatalogoBibliotecario {
    private Periodicita periodicita;

    public Rivista(long isbn, String t, int a, int n, Periodicita p) {
        super(isbn, t, a, n);
        this.periodicita = p;
    }

    //Per evitare il warning
    @SuppressWarnings("unused")
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "ISBN=" + super.getISBN() +
                ", titolo='" + super.getTitolo() + '\'' +
                ", annoPubblicazione=" + super.getAnnoPubblicazione() +
                ", numeroPagine=" + super.getNumeroPagine() + '\'' +
                ", periodicita='" + periodicita + '\'' +
                '}';
    }
}
