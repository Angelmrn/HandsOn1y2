//Moran Flores Angel Daniel
import java.util.Random;

public class Perceptron {
    private double[] weights; 
    private double TazaAprendizaje;
    
    public Perceptron(int inputSize, double TazaAprendizaje, boolean usaBias) {
        int weightsSize = usaBias ? inputSize + 1 : inputSize;
        this.weights = new double[weightsSize];
        this.TazaAprendizaje = TazaAprendizaje;
   
        Random rand = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble();
        }
    }
    
    // Función Sigmoid como función de activación
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));//f(x) = 1 / (1 + e^(-x))
    }
    

    public double prediccion(double[] inputs) {
        boolean tieneBias = weights.length > inputs.length;
        double sumPonderada = tieneBias ? weights[0] : 0.0; //weights[0] es el sesgo (bias)
        
        for (int i = 0; i < inputs.length; i++) {
            sumPonderada += inputs[i] * weights[tieneBias ? i + 1 : i];
        }
        double salida = sigmoid(sumPonderada);
        return salida >= 0.5 ? 1 : 0;//Redondeo salida 0.5
    }
    
    // Entrenamiento del perceptrón
    public void train(double[][] inputs, double[] outputs, int maxEpocas) {
        boolean tieneBias = weights.length > inputs[0].length;
    
        for (int epoca = 0; epoca < maxEpocas; epoca++) {
            System.out.println("Época: " + (epoca + 1));
            boolean allCorrect = true; 
    
            for (int i = 0; i < inputs.length; i++) {
                double prediction = prediccion(inputs[i]);
                double error = outputs[i] - prediction;
    
                System.out.println();
                System.out.println("Entrada: " + java.util.Arrays.toString(inputs[i]));
                System.out.println("Salida esperada: " + outputs[i]);
                System.out.println("Predicción: " + prediction);
                System.out.println("Error: " + error);
    
                if (error != 0) {
                    allCorrect = false;
                }
    
                // Actualizar sesgo 
                if (tieneBias) {
                    weights[0] += TazaAprendizaje * error;
                }
    
                // Actualizar los pesos de las entradas
                for (int j = 0; j < inputs[i].length; j++) {
                    weights[tieneBias ? j + 1 : j] += TazaAprendizaje * error * inputs[i][j];
                }
            }
    
            System.out.println();
    
            if (allCorrect) {
                System.out.println("Entrenamiento completado en " + (epoca + 1) + " épocas.");
                break;
            }
        }
    }
    
    // Imprimir los valores finales de los pesos y el sesgo
    public void printParameters() {
        boolean hasBias = weights.length > 2;
        
        if (hasBias) {
            System.out.println("Sesgo (bias) = " + weights[0]);
        }
        
        System.out.println("Pesos finales:");
        for (int i = 0; i < weights.length - (hasBias ? 1 : 0); i++) {
            System.out.println("w[" + i + "] = " + weights[hasBias ? i + 1 : i]);
        }
    }
}