/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.UI;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import java.util.Arrays;

public class MNISTCanvas extends VBox {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private double lastX, lastY;
    public Button predict;

    private final int CANVAS_SIZE = 280; 
    private final int MNIST_SIZE = 28;   
    private final int INNER_SIZE = 24;   
    private final int PADDING = (MNIST_SIZE - INNER_SIZE) / 2;

    public MNISTCanvas() {
        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();
        predict = new Button("Predict");

        clearCanvas();

        drawInnerBorder();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(12);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            lastX = e.getX();
            lastY = e.getY();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            // Constrain drawing to inner area
            x = Math.max(PADDING * 10, Math.min(x, (CANVAS_SIZE - PADDING * 10)));
            y = Math.max(PADDING * 10, Math.min(y, (CANVAS_SIZE - PADDING * 10)));

            gc.strokeLine(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        });

        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> {
            clearCanvas();
        });

        this.getChildren().addAll(canvas, clearBtn, predict);
        this.setSpacing(10);
    }

    private void drawInnerBorder() {
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(2);
        double scale = CANVAS_SIZE / (double) MNIST_SIZE;
        gc.strokeRect(PADDING * scale, PADDING * scale, INNER_SIZE * scale, INNER_SIZE * scale);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(12);
    }

    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawInnerBorder();
    }

    public double[] getMNISTImageFlattened() {
        WritableImage snapshot = canvas.snapshot(null, null);
        double[] pixels = new double[MNIST_SIZE * MNIST_SIZE];

        PixelReader reader = snapshot.getPixelReader();
        double scaleX = snapshot.getWidth() / INNER_SIZE; 
        double scaleY = snapshot.getHeight() / INNER_SIZE;

        Arrays.fill(pixels, 0.0);

        for (int y = 0; y < INNER_SIZE; y++) {
            for (int x = 0; x < INNER_SIZE; x++) {
                double sum = 0;
                int count = 0;

                for (int i = 0; i < (int) scaleX; i++) {
                    for (int j = 0; j < (int) scaleY; j++) {
                        int px = Math.min((int) (x * scaleX + i), (int) snapshot.getWidth() - 1);
                        int py = Math.min((int) (y * scaleY + j), (int) snapshot.getHeight() - 1);
                        Color color = reader.getColor(px, py);
                        sum += 1 - color.getRed(); 
                        count++;
                    }
                }

                int targetX = x + PADDING;
                int targetY = y + PADDING;
                pixels[targetY * MNIST_SIZE + targetX] = sum / count;
            }
        }

        return pixels;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
