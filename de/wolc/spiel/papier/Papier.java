package de.wolc.spiel.papier;

public abstract class Papier
{
    private final int maximaleAnzahlLochen;
    private Farbe farbe;
    private int lochAnzahl;
    
    protected Papier(int maximaleAnzahlLochen) {
        this.farbe = Farbe.WEISS;
        this.maximaleAnzahlLochen = maximaleAnzahlLochen;
    }

    public Farbe getFarbe() {
        return this.farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }
    
    public int getMaximaleLochAnzahl() {
        return this.maximaleAnzahlLochen;
    }

    /**
     * Überprüft ob dieses Papier noch existiert
     * Papiere die mehr oder gleich die Anzahl der maximalen Löcher haben existieren nicht mehr.
     * @return true wenn das Papier existiert, sonst false.
     */
    public boolean existiert() {
        return this.lochAnzahl >= this.getMaximaleLochAnzahl();
    }

    /**
     * Gibt die gesamt Zahl der Löcher dieses Papieres zurück.
     * @return Die Löcher auf diesem Papier.
     */
    public int getLochAnzahl() {
        return this.lochAnzahl;
    }

    /**
     * Wird aufgerufen wenn das Papier gelocht wurde.
     * @return Alle Konfettis, die bei diesem Lochprozess entstanden sind.
     */
    public void gelocht(Lochprozess prozess) {
        // Füge neue soviele Löcher wie möglich hinzu, und der Locher Stanzer hat.
        int loecher = Math.max(this.moeglicheLoecher(), prozess.getLocher().getStanzer());
        this.lochAnzahl += loecher;
        // Für jedes erstellte Loch soll ein Konfetti erstellt werden.
        for(int i = 0; i < loecher; i++) {
            Konfetti konfetti = new Konfetti(this.getFarbe());
            prozess.getKonfetti().add(konfetti);
        }
    }

    /**
     * Überprüft wieviele Löcher noch in das Papier gemacht werden können, bevor es nicht mehr existiert. 
     * @return Die Anzahl der möglichen Löcher, oder 0.
     */
    private int moeglicheLoecher() {
        int lochZahl = this.maximaleAnzahlLochen - this.lochAnzahl;
        return Math.max(lochZahl, 0);
    }
}