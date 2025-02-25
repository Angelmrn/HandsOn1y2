import java.util.Random;

public class Perceptron {

    private double[] weights; // Pesos del perceptrón
    private double bias;      // Sesgo (solo para perceptrón con sesgo)
    private double learningRate; // Tasa de aprendizaje
    private boolean useBias;  // Indica si el perceptrón utiliza sesgo

    public Perceptron(int inputSize, double learningRate, boolean useBias) {
        this.weights = new double[inputSize];
        this.learningRate = learningRate;
        this.useBias = useBias;
        this.bias = useBias ? new Random().nextDouble() : 0.0; // Inicializa el sesgo si se usa

        // Inicialización aleatoria de los pesos
        Random rand = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble(); 
        }
    }

    // Función Sigmoid como función de activación
    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    // Predicción: Calcula la salida para un conjunto de entradas
    public double predict(double[] inputs) {
        double weightedSum = 0.0;
        for (int i = 0; i < inputs.length; i++) {
            weightedSum += inputs[i] * weights[i]; // Suma ponderada
        }
        if (useBias) {
            weightedSum += bias; // Agregar el sesgo si corresponde
        }
        return sigmoid(weightedSum); // Devuelve la salida usando la función Sigmoid
    }

    // Entrenamiento del perceptrón
    public void train(double[][] inputs, double[] outputs, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            System.out.println("Época: " + (epoch + 1));
            for (int i = 0; i < inputs.length; i++) {
                double prediction = predict(inputs[i]); // Predicción para el patrón actual
                double error = outputs[i] - prediction; // Error
                System.out.println();
                System.out.println("Entrada: " + java.util.Arrays.toString(inputs[i]));
                System.out.println("Salida esperada: " + outputs[i]);
                System.out.println("Predicción: " + prediction);
                System.out.println("Error: " + error);

                // Actualización de los pesos
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * inputs[i][j];
                }

                // Actualización del sesgo, si corresponde
                if (useBias) {
                    bias += learningRate * error;
                }
            }
            System.out.println();
        }
    }

    // Imprimir los valores finales de los pesos y el sesgo
    public void printParameters() {
        System.out.println("Pesos finales:");
        for (int i = 0; i < weights.length; i++) {
            System.out.println("w[" + i + "] = " + weights[i]);
        }
        if (useBias) {
            System.out.println("Sesgo (bias) = " + bias);
        }
    }

    
}
