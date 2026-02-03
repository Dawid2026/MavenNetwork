
package Logic;
import java.util.Arrays;
/**
 * Class for usefull math function used in the rest of codebase
 * 
 * @author Dawid
 */
public class helperMathFunctions {
    /**
     * 
     * @param arr
     * @return Biggest number in array 
     */
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
    /**
     * One hot encoding
     * @param labels
     * @param numClasses Number of classe
     * @return 
     */
    public static double[][] oneHotEncode(int[] labels, int numClasses) {
        double[][] Y = new double[labels.length][numClasses];

        for (int i = 0; i < labels.length; i++) {
            int label = labels[i];
            Y[i][label] = 1.0; 
        }

        return Y;
    }
    
    /**
     * One hot decoding
     * @param Y
     * @return 
     */
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
    /**
     * Calculate softmax and truns outputs into probabilities
     * @param input 
     * @return array of probabiliteis [0-1]
     */
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
