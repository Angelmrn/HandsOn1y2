//Moran Flores Angel Daniel
public class Main {
    public static void main(String[] args) {
        // Configuración del perceptrón sin sesgo (para AND)
        System.out.println();
        System.out.println("-------------------Perceptrón sin sesgo compuerta AND-------------------");
        Perceptron perceptronAND = new Perceptron(2, 0.1, false);
        double[][] inputsAND = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] outputsAND = {0, 0, 0, 1}; // Salidas para AND
        perceptronAND.train(inputsAND, outputsAND, 10);
        perceptronAND.printParameters();

        // Configuración del perceptrón con sesgo (para OR)
        System.out.println();
        System.out.println("-------------------Perceptrón con sesgo compuerta OR-------------------");
        Perceptron perceptronOR = new Perceptron(2, 0.1, true);
        double[][] inputsOR = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] outputsOR = {0, 1, 1, 1}; // Salidas para OR
        perceptronOR.train(inputsOR, outputsOR, 10);
        perceptronOR.printParameters();
    }
}
