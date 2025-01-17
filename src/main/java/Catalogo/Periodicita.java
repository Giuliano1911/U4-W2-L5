package Catalogo;

public enum Periodicita {
    SETTIMANALE,
    MENSILE,
    SEMESTRALE;

    @Override
    public String toString() {
        return switch (this) {
            case SETTIMANALE -> "Settimanale";
            case MENSILE -> "Mensile";
            case SEMESTRALE -> "Semestrale";
        };
    }
}
