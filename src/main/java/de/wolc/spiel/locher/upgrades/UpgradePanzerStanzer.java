package de.wolc.spiel.locher.upgrades;

import de.wolc.spiel.Farbe;
import de.wolc.spiel.Preis;
import de.wolc.spiel.locher.SimLocher;

/**
 * Der Panzer Stanzer erhöht die Stärke der Stanzer um mehr Blätter auf einal zu lochen.
 */
public class UpgradePanzerStanzer extends LocherUpgrade {
    /** MUSS um 1 erhöht werden, wenn sich die Eigenschaften der Klasse ändern. */ 
    private static final long serialVersionUID = 1L;
    private static final Preis PREIS = new Preis(Farbe.GRUEN, 20, Farbe.ROT, 10);
    
    private int staerke;

    public UpgradePanzerStanzer(int staerke) {
        super("Panzer Stanzer", PREIS);
        this.staerke = staerke;
    }

    /**
     * Gibt an wie umwieviel Stärke die Stanzer Stärke erhöht wird.
     * @return Wie Stark sind die Stanzer?
     */
    public int getStaerke() {
        return staerke;
    }

    @Override
    public int upgradeStaerke(SimLocher locher, int staerke) {
        return staerke + this.staerke;
    }

    @Override
    public String toString() {
        return this.getGuiName() + ": +" + staerke + " maximale Papierstapelgröße";
    }
}
