package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class Vertex {
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
        number = vertexCount++;
    }

    // конструктор для ввода вершин из файла
    public Vertex(double x, double y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(currentColor);
        gc.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(String.valueOf(number), x, y + 5);
        if (isHasLoop) drawLoop(gc);
    }

    private void drawLoop(GraphicsContext gc) {
        gc.setStroke(currentColor);
        gc.setLineWidth(2);
        gc.strokeOval(x, y-2*(RADIUS-1), (RADIUS-1)*2, (RADIUS-1)*2);
        gc.strokeLine(x + RADIUS, y, x + 2 * RADIUS, y);

        double angle = Math.toRadians(70.0);
        double X1 = x + RADIUS;
        double Y1 = y;
        double X2 = X1 + Math.cos(angle)* RADIUS;
        double Y2 = Y1 - Math.sin(angle)* RADIUS;

        gc.strokeLine(X1, Y1, X2, Y2);
    }

    public boolean isDotInRadius(double dotX, double dotY, double diff) {
        return (dotX - x)*(dotX - x) + (dotY - y)*(dotY - y) <= (RADIUS*3/2+diff)*(RADIUS*3/2+diff);
    }

    public void addAdj(Vertex vert) {
        adj.add(vert);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Vertex> getAdj() {
        return adj;
    }

    public double getX() {return x;}
    public double getY() {return y;}

    public void setRinged(boolean bool) {isRinged = bool;}
    public Boolean getRinged() {return isRinged;}

    public void setColor(Color color) {currentColor = color;}
    public Color getColor() {return currentColor;}

    public void setHasLoop(boolean bool) {isHasLoop = bool;}

    private final ArrayList<Vertex> adj = new ArrayList<>();

    private final double x;
    private final double y;
    private final int number;
    private boolean isHasLoop = false;
    private boolean isRinged = false;
    private Color currentColor = Color.BLACK;

    public static int vertexCount = 1;
    public static int RADIUS = 16;

    public static Color computeColor(int numb) {
        return colors[(numb-1) % colors.length];
    }

    public static Color[] colors = {Color.GREEN, Color.PURPLE, Color.ORANGE, Color.PINK, Color.YELLOW,
            Color.AQUA, Color.BROWN, Color.GRAY, Color.CORAL, Color.BLUE, Color.DARKGREEN, Color.CYAN};
}
