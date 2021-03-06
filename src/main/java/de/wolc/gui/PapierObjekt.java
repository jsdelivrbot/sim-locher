package de.wolc.gui;

import java.util.Random;

import de.wolc.spiel.papier.Papier;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.DepthTest;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class PapierObjekt {
    private static final Image IMAGE = new Image("de/wolc/gui/images/paper_master.png");
    private static final Random RANDOM = new Random();

    public double zufallsBreite() {
        double versetzung = IMAGE.getWidth() / 2d;
        return zufall(-versetzung, this.game.getArea().getWidth() - versetzung);
    }

    public double zufallsHoehe() {
        double halfHeight = this.game.getArea().getHeight() / 2d;
        return zufall(halfHeight + IMAGE.getHeight(), halfHeight + halfHeight - IMAGE.getHeight());
    }

    public double zufall(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    private final Papier papier;
    private final Game game;
    private final Rectangle drawnPapier;

    public PapierObjekt(Game game, Papier papier) {
        this.papier = papier;
        this.game = game;
        Color farbe = papier.getFarbe().getGuiFarbe();

        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, farbe));

        this.drawnPapier = new Rectangle();
        this.drawnPapier.setScaleX(papier.getGroesse());
        this.drawnPapier.setScaleY(papier.getGroesse());
        this.drawnPapier.setScaleZ(papier.getGroesse());
        this.drawnPapier.setFill(new ImagePattern(IMAGE));
        this.drawnPapier.setWidth(IMAGE.getWidth());
        this.drawnPapier.setHeight(IMAGE.getHeight());
        this.drawnPapier.setTranslateX(this.zufallsBreite());
        this.drawnPapier.setTranslateY(this.zufallsHoehe());
        this.drawnPapier.setDepthTest(DepthTest.ENABLE);
        this.drawnPapier.setEffect(lighting);
        this.drawnPapier.setOnMouseDragged((MouseEvent e) -> {
            //Change the location if the cursor has moved
            this.drawnPapier.toFront();
            this.drawnPapier.setTranslateX(this.drawnPapier.getTranslateX() + (e.getX() - (this.drawnPapier.getWidth() * 0.35)));
            this.drawnPapier.setTranslateY(this.drawnPapier.getTranslateY() + (e.getY() - (this.drawnPapier.getHeight() * 0.35)));
            if (this.game.checkForLocherCollision(this.drawnPapier)){
                this.game.papierAufLocherGezogen(this);
                e.consume();
            }
        });
        this.game.getArea().getChildren().add(this.drawnPapier);
    }

    public Papier getPapier() {
        return this.papier;
    }

    public void zerstoeren() {
        this.game.getArea().getChildren().remove(this.drawnPapier);
    }
}