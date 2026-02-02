/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import java.util.Arrays;
/**
 * Class for usefull math function used in the rest of codebase
 * 
 * @author Dawid
 */
public class helperMathFunctions {
    public static int argMax(double[] arr) {
        int idx = 0;
        double max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                idx = i;
            }
        }
        return idx;
    }

    public static double[][] oneHotEncode(int[] labels, int numClasses) {
        double[][] Y = new double[labels.length][numClasses];

        for (int i = 0; i < labels.length; i++) {
            int label = labels[i];
            Y[i][label] = 1.0;  // set 1.0 at the correct class index
        }

        return Y;
    }
    
    public static int[] oneHotDecode(double[][] Y) {
    int[] labels = new int[Y.length];

    for (int i = 0; i < Y.length; i++) {
        int maxIndex = 0;
        double maxValue = Y[i][0];

        for (int j = 1; j < Y[i].length; j++) {
            if (Y[i][j] > maxValue) {
                maxValue = Y[i][j];
                maxIndex = j;
            }
        }

        labels[i] = maxIndex;
    }

    return labels;
}
    
    public static double[] Softmax(double[] input) {
        double max = Arrays.stream(input).max().orElse(0.0);

        double[] exp = new double[input.length];
        double sum = 0.0;
        for (int i = 0; i < input.length; i++) {
            exp[i] = Math.exp(input[i] - max);
            sum += exp[i];
        }

        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = exp[i] / sum;
        }
        return output;
    }

}
