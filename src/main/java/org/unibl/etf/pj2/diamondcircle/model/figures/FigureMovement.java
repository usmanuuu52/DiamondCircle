package org.unibl.etf.pj2.diamondcircle.model.figures;

import org.unibl.etf.pj2.diamondcircle.GameService;
import org.unibl.etf.pj2.diamondcircle.model.elements.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FigureMovement {
    public static final String MOVEMENTS_PATH = "src/main/resources/movements/";
    private final String name;
    private final String label;
    private final String movementTime;
    private final Color color;
    private final ArrayList<Integer> crossedFields;

    public FigureMovement(String name, String label, String movementTime, Color color, ArrayList<Integer> crossedFields) {
        this.name = name;
        this.label = label;
        this.movementTime = movementTime;
        this.color = color;
        this.crossedFields = crossedFields;
        serialize();
    }

    private void serialize() {
        try (FileOutputStream fileOut = new FileOutputStream(MOVEMENTS_PATH + name + ".ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(label);
            out.writeObject(movementTime);
            out.writeObject(color);
            out.writeObject(crossedFields);
        } catch (IOException e) {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    public static FigureMovement deserialize(String fileName) {
        FigureMovement figureMovement = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(MOVEMENTS_PATH + fileName + ".ser"))) {
            String label = (String) in.readObject();
            String movementTime = (String) in.readObject();
            Color color = (Color) in.readObject();
            @SuppressWarnings("unchecked") ArrayList<Integer> crossedFields = (ArrayList<Integer>) in.readObject();
            figureMovement = new FigureMovement(fileName, label, movementTime, color, crossedFields);
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return figureMovement;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Integer> getCrossedFields() {
        return crossedFields;
    }

    public String getMovementTime() {
        return movementTime;
    }
}
