import javax.swing.*;
import java.awt.*;

public class Feld extends JButton {
    private final byte circle;
    private final byte positionOnCircle;
    private boolean isEmpty;
    private boolean colourOfStone;
    private int index;

    public Feld(byte circle, byte positionOnCircle) {
        setBackground(Color.WHITE);
        this.circle = circle;
        this.positionOnCircle = positionOnCircle;
        isEmpty = true;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte getCircle() {
        return circle;
    }

    public byte getPositionOnCircle() {
        return positionOnCircle;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isColourOfStone() {
        return colourOfStone;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setColourOfStone(boolean colourOfStone) {
        this.colourOfStone = colourOfStone;
    }
}
