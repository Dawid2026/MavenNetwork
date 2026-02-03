
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

/**
 * Class that defiends mnist canvas
 * 
 * @author Dawid
 */
public class MNISTCanvas extends VBox {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private double lastX, lastY;
    public Button predict;

    private final int canvasSize = 280; 
    private final int mnistSize = 28;   
    private final int innerSize = 24;   
    private final int padding = (mnistSize - innerSize) / 2;

    public MNISTCanvas() {
        canvas = new Canvas(canvasSize, canvasSize);
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
            x = Math.max(padding * 10, Math.min(x, (canvasSize - padding * 10)));
            y = Math.max(padding * 10, Math.min(y, (canvasSize - padding * 10)));

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

    /**
     * 
     * Draw inner box inside of main canvas for better accuracy.
     * 
     */
    private void drawInnerBorder() {
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(2);
        double scale = canvasSize / (double) mnistSize;
        gc.strokeRect(padding * scale, padding * scale, innerSize * scale, innerSize * scale);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(12);
    }
    
    /**
     * 
     * Clear canvas after drawing
     * 
     */
    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawInnerBorder();
    }
    
    /**
     * 
     * Turns canvas drawing into flattende data array that 
     * is ready to use in the model
     * 
     * @return 
     */
    public double[] getMNISTImageFlattened() {
        WritableImage snapshot = canvas.snapshot(null, null);
        double[] pixels = new double[mnistSize * mnistSize];

        PixelReader reader = snapshot.getPixelReader();
        double scaleX = snapshot.getWidth() / innerSize; 
        double scaleY = snapshot.getHeight() / innerSize;

        Arrays.fill(pixels, 0.0);

        for (int y = 0; y < innerSize; y++) {
            for (int x = 0; x < innerSize; x++) {
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

                int targetX = x + padding;
                int targetY = y + padding;
                pixels[targetY * mnistSize + targetX] = sum / count;
            }
        }

        return pixels;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
