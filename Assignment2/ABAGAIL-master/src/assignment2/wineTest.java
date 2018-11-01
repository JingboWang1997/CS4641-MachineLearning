package assignment2;

import func.nn.backprop.BackPropagationNetwork;
import func.nn.backprop.BackPropagationNetworkFactory;
import opt.OptimizationAlgorithm;
import opt.RandomizedHillClimbing;
import opt.SimulatedAnnealing;
import opt.example.NeuralNetworkOptimizationProblem;
import opt.ga.StandardGeneticAlgorithm;
import shared.DataSet;
import shared.ErrorMeasure;
import shared.Instance;
import shared.SumOfSquaresError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of randomized hill climbing, simulated annealing, and genetic algorithm to
 * find optimal weights to a neural network that is classifying abalone as having either fewer 
 * or more than 15 rings.
 *
 * @author Hannah Lau
 * @version 1.0
 */
public class wineTest {
    private static Instance[] trainingInstances = initializeTrainingInstances();
    private static Instance[] testInstances = initializeTestInstances();

    private static int inputLayer = 11, hiddenLayer = 5, outputLayer = 1, trainingIterations = 1000;
    private static BackPropagationNetworkFactory factory = new BackPropagationNetworkFactory();
    
    private static ErrorMeasure measure = new SumOfSquaresError();

    private static DataSet trainingSet = new DataSet(trainingInstances);
    private static DataSet testSet = new DataSet(testInstances);

    private static BackPropagationNetwork networks[] = new BackPropagationNetwork[3];
    private static NeuralNetworkOptimizationProblem[] nnop = new NeuralNetworkOptimizationProblem[3];

    private static OptimizationAlgorithm[] oa = new OptimizationAlgorithm[3];
    private static String[] oaNames = {"RHC", "SA", "GA"};
    private static String results = "";

    private static DecimalFormat df = new DecimalFormat("0.000");

    public static void main(String[] args) {
        for(int i = 0; i < oa.length; i++) {
            networks[i] = factory.createClassificationNetwork(
                new int[] {inputLayer, hiddenLayer, outputLayer});
            nnop[i] = new NeuralNetworkOptimizationProblem(trainingSet, networks[i], measure);
        }

        oa[0] = new RandomizedHillClimbing(nnop[0]);
        oa[1] = new SimulatedAnnealing(1E11, .95, nnop[1]);
        oa[2] = new StandardGeneticAlgorithm(200, 100, 10, nnop[2]);

        for(int i = 0; i < oa.length; i++) {
            Instance optimalInstance = null;
//            if (i == 0) {
            double start = System.nanoTime(), end, trainingTime, testingTime, testCorrect = 0, testIncorrect = 0, trainingCorrect = 0, trainingIncorrect = 0;
            int randomRestart = 10;
            optimalInstance = train(oa[i], networks[i], oaNames[i], randomRestart); //trainer.train();
            end = System.nanoTime();
            trainingTime = end - start;
            trainingTime /= Math.pow(10, 9);

            networks[i].setWeights(optimalInstance.getData());

            double trainingPredicted, trainingActual;
            for (int j = 0; j < trainingInstances.length; j++) {
                networks[i].setInputValues(trainingInstances[j].getData());
                networks[i].run();

                trainingActual = Double.parseDouble(trainingInstances[j].getLabel().toString());
                trainingPredicted = Double.parseDouble(networks[i].getOutputValues().toString());

                double trash = Math.abs(trainingPredicted - trainingActual) < 0.5 ? trainingCorrect++ : trainingIncorrect++;

            }

            double testPredicted, testActual;
            start = System.nanoTime();
            for (int j = 0; j < testInstances.length; j++) {
                networks[i].setInputValues(testInstances[j].getData());
                networks[i].run();

                testActual = Double.parseDouble(testInstances[j].getLabel().toString());
                testPredicted = Double.parseDouble(networks[i].getOutputValues().toString());

                double trash = Math.abs(testPredicted - testActual) < 0.5 ? testCorrect++ : testIncorrect++;

            }
            end = System.nanoTime();
            testingTime = end - start;
            testingTime /= Math.pow(10, 9);

            results += "\nResults for " + oaNames[i] + ": \nTraining:\nCorrectly classified " + trainingCorrect + " instances." +
                    "\nIncorrectly classified " + trainingIncorrect + " instances.\nPercent correctly classified: "
                    + df.format(trainingCorrect / (trainingCorrect + trainingIncorrect) * 100) + "\nTest:\nCorrectly classified " + testCorrect + " instances." +
                    "\nIncorrectly classified " + testIncorrect + " instances.\nPercent correctly classified: "
                    + df.format(testCorrect / (testCorrect + testIncorrect) * 100) + "%\nTraining time: " + df.format(trainingTime)
                    + " seconds\nTesting time: " + df.format(testingTime) + " seconds\n";
            if (oaNames[i].equals("RHC")) {
                results += randomRestart + " random restarts";
            }
//            }
        }

        System.out.println(results);
    }

    private static Instance train(OptimizationAlgorithm oa, BackPropagationNetwork network, String oaName, int numRandomRestart) {
        System.out.println("\nError results for " + oaName + "\n---------------------------");
        Instance optimal = null;
        double optimalVal = Double.NEGATIVE_INFINITY;

        for (int r = 0; r < numRandomRestart; r++) {
            for(int i = 0; i < trainingIterations; i++) {
                oa.train();

                Instance optimalInstance = oa.getOptimal();
                network.setWeights(optimalInstance.getData());

                double error = 0;
                for(int j = 0; j < trainingInstances.length; j++) {
                    network.setInputValues(trainingInstances[j].getData());
                    network.run();

                    Instance actual = trainingInstances[j].getLabel(), output = new Instance(network.getOutputValues());
                    output.setLabel(new Instance(Double.parseDouble(network.getOutputValues().toString())));
                    error += measure.value(actual, output);
                }

                System.out.println(df.format(error));
            }
            Instance cur = oa.getOptimal();
            double curVal = nnop[0].value(cur);
            if (curVal > optimalVal) {
                optimal = cur;
                optimalVal = curVal;
            }
            System.out.println("random restart " + r + ": " + optimalVal);
            oa = new RandomizedHillClimbing(nnop[0]);
        }
        return optimal;
    }

//    private static Instance trainWithRHC(OptimizationAlgorithm oa, BackPropagationNetwork network, int numRandomRestart) {
//        System.out.println("\nError results for " + "RHC" + "\n---------------------------");
//        Instance optimal = null;
//        double optimalVal = Double.NEGATIVE_INFINITY;
//
//        for (int r = 0; r < numRandomRestart; r++) {
//            for (int i = 0; i < trainingIterations; i++) {
//                oa.train();
//
//                Instance optimalInstance = oa.getOptimal();
//                network.setWeights(optimalInstance.getData());
//
//                double error = 0;
//                for (int j = 0; j < trainingInstances.length; j++) {
//                    network.setInputValues(trainingInstances[j].getData());
//                    network.run();
//
//                    Instance actual = trainingInstances[j].getLabel(), output = new Instance(network.getOutputValues());
//                    output.setLabel(new Instance(Double.parseDouble(network.getOutputValues().toString())));
//                    error += measure.value(actual, output);
//                }
//
//                System.out.println(df.format(error));
//            }
//            Instance cur = oa.getOptimal();
//            double curVal = nnop[0].value(cur);
//            if (curVal > optimalVal) {
//                optimal = cur;
//                optimalVal = curVal;
//            }
//            System.out.println("random restart " + r + ": " + optimalVal);
//            oa = new RandomizedHillClimbing(nnop[0]);
//        }
//        return optimal;
//    }

    private static Instance[] initializeTrainingInstances() {

        double[][][] attributes = new double[3919][][];

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/assignment2/winequality-binary-training.txt")));

            for(int i = 0; i < attributes.length; i++) {
                Scanner scan = new Scanner(br.readLine());
                scan.useDelimiter(",");

                attributes[i] = new double[2][];
                attributes[i][0] = new double[11]; // 11 attributes
                attributes[i][1] = new double[1];

                for(int j = 0; j < 11; j++)
                    attributes[i][0][j] = Double.parseDouble(scan.next());

                // category
                String category = scan.next();
                double categoryEncode;
                if (category.equals("'not good'")) {
                    categoryEncode = 0.0;
                } else if (category.equals("good")) {
                    categoryEncode = 1.0;
                } else {
                    throw new Exception("category encoding error");
                }
//                System.out.println(categoryEncode);
                attributes[i][1][0] = categoryEncode;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // print attributes array
//        for (double[][] attribute: attributes) {
//            for (double[] a: attribute) {
//                System.out.println(Arrays.toString(a));
//            }
//        }

        Instance[] instances = new Instance[attributes.length];

        for(int i = 0; i < instances.length; i++) {
            instances[i] = new Instance(attributes[i][0]);
            // classification is either 0 or 1 (not good or good)
            instances[i].setLabel(new Instance(attributes[i][1][0] <= 0.5 ? 0 : 1));
        }

        return instances;
    }

    private static Instance[] initializeTestInstances() {

        double[][][] attributes = new double[979][][];

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/assignment2/winequality-binary-test.txt")));

            for(int i = 0; i < attributes.length; i++) {
                Scanner scan = new Scanner(br.readLine());
                scan.useDelimiter(",");

                attributes[i] = new double[2][];
                attributes[i][0] = new double[11]; // 11 attributes
                attributes[i][1] = new double[1];

                for(int j = 0; j < 11; j++)
                    attributes[i][0][j] = Double.parseDouble(scan.next());

                // category
                String category = scan.next();
                double categoryEncode;
                if (category.equals("'not good'")) {
                    categoryEncode = 0.0;
                } else if (category.equals("good")) {
                    categoryEncode = 1.0;
                } else {
                    throw new Exception("category encoding error");
                }
//                System.out.println(categoryEncode);
                attributes[i][1][0] = categoryEncode;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // print attributes array
//        for (double[][] attribute: attributes) {
//            for (double[] a: attribute) {
//                System.out.println(Arrays.toString(a));
//            }
//        }

        Instance[] instances = new Instance[attributes.length];

        for(int i = 0; i < instances.length; i++) {
            instances[i] = new Instance(attributes[i][0]);
            // classification is either 0 or 1 (not good or good)
            instances[i].setLabel(new Instance(attributes[i][1][0] <= 0.5 ? 0 : 1));
        }

        return instances;
    }
}
