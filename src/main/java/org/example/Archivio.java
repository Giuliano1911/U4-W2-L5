package org.example;

import Catalogo.*;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio extends HashSet<ElementoCatalogoBibliotecario> {

    //genero l'archivio vuoto e poi nel metodo successivo controllo che gli isbn siano tutti diversi
    public Archivio() {
        super();
    }

    //Estendo HashSet per fare Override al metodo add
    @Override
    public boolean add(ElementoCatalogoBibliotecario elementoCatalogoBibliotecario) {
        if (super.add(elementoCatalogoBibliotecario))
            return true;
        else
            throw new sameISBNException("ISBN già presente");
    }

    //se l'isbn cercato non è presente lancia l'eccezione
    public ElementoCatalogoBibliotecario ricercaISBN(long input) {
        return this.stream()
                .filter(elemento -> elemento.getISBN() == input)
                .findAny()
                .orElseThrow(() -> new ISBNNotFoundException("ISBN non trovato"));
    }

    //rimuove l'elemento trovato con il metodo di ricerca creato prima
    public void eliminaISBN(long input) {
        this.remove(this.ricercaISBN(input));
    }

    //Se la lista è vuota lancia l'eccezione
    public List<ElementoCatalogoBibliotecario> ricercaAnno(int input) {
        List<ElementoCatalogoBibliotecario> result = this.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == input)
                .toList();
        if (result.isEmpty())
            throw new annoNotFoundException("Non sono presenti elementi pubblicati nel " + input);
        return result;
    }

    //Se la lista è vuota lancia l'eccezione, nel primo filtro prendo solo i libri, nel secondo Casto i libri per poter usare .getAutore()
    public List<ElementoCatalogoBibliotecario> ricercaAutore(String input) {
        List<ElementoCatalogoBibliotecario> result = this.stream()
                .filter(elemento -> elemento instanceof Libro)
                .filter(libro -> ((Libro) libro).getAutore().equals(input))
                .toList();
        if (result.isEmpty()) {
            throw new autoreNotFoundException("La ricerca non ha prodotto alcun risultato");
        }
        return result;
    }

    //Dopo aver filtrato l'elemento per isbn procede alla modifica, così come per tutti i successivi metodi
    public void modificaTitolo(long isbn, String titolo) {
        this.ricercaISBN(isbn).setTitolo(titolo);
    }

    public void modificaAnnoPubblicazione(long isbn, int anno) {
        this.ricercaISBN(isbn).setAnnoPubblicazione(anno);
    }

    public void modificaNumeroPagine(long isbn, int n) {
        this.ricercaISBN(isbn).setNumeroPagine(n);
    }

    public void modificaAutore(long isbn, String aut) {
        if (Objects.requireNonNull(ricercaISBN(isbn)) instanceof Libro libro)
            libro.setAutore(aut);
    }

    public void modificaGenere(long isbn, String gen) {
        if (Objects.requireNonNull(ricercaISBN(isbn)) instanceof Libro libro)
            libro.setGenere(gen);
    }

    public void modificaPeriodicita(long isbn, Periodicita per) {
        if (Objects.requireNonNull(ricercaISBN(isbn)) instanceof Rivista rivista)
            rivista.setPeriodicita(per);
    }

    public int getNLibri() {
        return this.stream()
                .filter(elemento -> elemento instanceof Libro)
                .toList()
                .size();
    }

    public int getNRiviste() {
        //si poteva risolvere anche così però sarebbe stato meno efficace perché in futuro potrebbe essere necessario aggiungere una classe diversa da Libro o Rivista
        //return this.size() - this.getNLibri();
        return this.stream()
                .filter(elemento -> elemento instanceof Rivista)
                .toList()
                .size();
    }

    //ritorna l'elemento con il numero di pagine più alto
    public ElementoCatalogoBibliotecario getMaxPagesElement() {
        return this.stream()
                .max((Comparator.comparing(ElementoCatalogoBibliotecario::getNumeroPagine)))
                .orElse(null);
    }

    //ritorna la media delle pagine di tutti gli elementi dell'archivio
    public double getMediaNPagine() {
        return this.stream()
                .collect(Collectors.averagingDouble(ElementoCatalogoBibliotecario::getNumeroPagine));
    }

    //un semplice metodo che stampa gli scorsi 4 metodi
    public void statistiche() {
        System.out.println("N. libri: " + this.getNLibri());
        System.out.println("N. Riviste: " + this.getNRiviste());
        System.out.println("Elemento con più pagine: " + this.getMaxPagesElement());
        System.out.println("Media pagine di tutti gli elementi: " + this.getMediaNPagine());
    }
}
