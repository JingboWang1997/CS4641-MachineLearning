package assignment2;

import dist.DiscreteDependencyTree;
import dist.DiscretePermutationDistribution;
import dist.DiscreteUniformDistribution;
import dist.Distribution;
import opt.*;
import opt.example.TravelingSalesmanCrossOver;
import opt.example.TravelingSalesmanEvaluationFunction;
import opt.example.TravelingSalesmanRouteEvaluationFunction;
import opt.example.TravelingSalesmanSortEvaluationFunction;
import opt.ga.*;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class TravelingSalesmanTest {
    /** The n value */
    private static final int N = 50;
    /**
     * The test main
     * @param args ignored
     */
    public static void main(String[] args) {
        Random random = new Random();
        // create the random points
        double[][] points = new double[N][2];
        for (int i = 0; i < points.length; i++) {
            points[i][0] = random.nextDouble();
            points[i][1] = random.nextDouble();   
        }
        // for rhc, sa, and ga we use a permutation based encoding
        TravelingSalesmanEvaluationFunction ef = new TravelingSalesmanRouteEvaluationFunction(points);
        Distribution odd = new DiscretePermutationDistribution(N);
        NeighborFunction nf = new SwapNeighbor();
        MutationFunction mf = new SwapMutation();
        CrossoverFunction cf = new TravelingSalesmanCrossOver(ef);
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);

        FixedIterationTrainer fit = null;
        System.out.println("RHC");
        RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);
        fit = new FixedIterationTrainer(rhc, 4000);
        fit.train();
        System.out.println(ef.value(rhc.getOptimal()));

        System.out.println("SA");
        SimulatedAnnealing sa = new SimulatedAnnealing(1E12, .95, hcp);
        fit = new FixedIterationTrainer(sa, 4000);
        fit.train();
        System.out.println(ef.value(sa.getOptimal()));

        System.out.println("GA");
        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 50, 30, gap); // 150 20
        fit = new FixedIterationTrainer(ga, 800);
        fit.train();
        System.out.println(ef.value(ga.getOptimal()));
//
    }
}
