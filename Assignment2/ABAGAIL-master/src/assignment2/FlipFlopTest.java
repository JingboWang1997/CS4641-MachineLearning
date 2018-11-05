package assignment2;

import dist.DiscreteDependencyTree;
import dist.DiscreteUniformDistribution;
import dist.Distribution;
import opt.*;
import opt.example.FlipFlopEvaluationFunction;
import opt.ga.*;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

import java.util.Arrays;

/**
 * A test using the flip flop evaluation function
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class FlipFlopTest {
    /** The n value */
    private static final int N = 80;
    
    public static void main(String[] args) {
        int[] ranges = new int[N];
        Arrays.fill(ranges, 2);
        EvaluationFunction ef = new FlipFlopEvaluationFunction();
        Distribution odd = new DiscreteUniformDistribution(ranges);
        NeighborFunction nf = new DiscreteChangeOneNeighbor(ranges);
        MutationFunction mf = new DiscreteChangeOneMutation(ranges);
        CrossoverFunction cf = new SingleCrossOver();
        Distribution df = new DiscreteDependencyTree(.1, ranges); 
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);
        ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(ef, odd, df);

        FixedIterationTrainer fit = null;
//        System.out.println("RHC");
//        RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);
//        fit = new FixedIterationTrainer(rhc, 4000);
//        fit.train();
//        System.out.println(ef.value(rhc.getOptimal()));

        System.out.println("SA");
        SimulatedAnnealing sa = new SimulatedAnnealing(100, .95, hcp);
        fit = new FixedIterationTrainer(sa, 8000);
        fit.train();
        System.out.println(ef.value(sa.getOptimal()));

//        System.out.println("GA");
//        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 50, 10, gap);
//        fit = new FixedIterationTrainer(ga, 4000);
//        fit.train();
//        System.out.println(ef.value(ga.getOptimal()));
    }
}
