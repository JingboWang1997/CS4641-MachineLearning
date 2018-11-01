package assignment2;

import dist.DiscreteDependencyTree;
import dist.DiscretePermutationDistribution;
import dist.Distribution;
import opt.*;
import opt.ga.*;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

import java.util.Random;

/**
 * @author kmanda1
 * @version 1.0
 */
public class NQueensTest {
    /** The n value */
    private static final int N = 100;
    /** The t value */
    
    public static void main(String[] args) {
        int[] ranges = new int[N];
        Random random = new Random(N);
        for (int i = 0; i < N; i++) {
        	ranges[i] = random.nextInt();
        }
        NQueensFitnessFunction ef = new NQueensFitnessFunction();
        Distribution odd = new DiscretePermutationDistribution(N);
        NeighborFunction nf = new SwapNeighbor();
        MutationFunction mf = new SwapMutation();
        CrossoverFunction cf = new SingleCrossOver();
        Distribution df = new DiscreteDependencyTree(.1); 
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);
        ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(ef, odd, df);
        
        RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);      
        FixedIterationTrainer fit = new FixedIterationTrainer(rhc, 1000);
        fit.train();
        long starttime = System.currentTimeMillis();
        System.out.println("RHC: " + ef.value(rhc.getOptimal()));
        System.out.println("RHC: Board Position: ");
//        System.out.println(ef.boardPositions());
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
        
        System.out.println("============================");
        
        SimulatedAnnealing sa = new SimulatedAnnealing(2E1, .1, hcp);
        fit = new FixedIterationTrainer(sa, 1000);
        fit.train();
        
        starttime = System.currentTimeMillis();
        System.out.println("SA: " + ef.value(sa.getOptimal()));
        System.out.println("SA: Board Position: ");
//        System.out.println(ef.boardPositions());
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
        
        System.out.println("============================");
        
        starttime = System.currentTimeMillis();
        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 20, 30, gap);
        fit = new FixedIterationTrainer(ga, 1000);
        fit.train();
        System.out.println("GA: " + ef.value(ga.getOptimal()));
        System.out.println("GA: Board Position: ");
//        System.out.println(ef.boardPositions());
        System.out.println("Time : "+ (System.currentTimeMillis() - starttime));
    }
}
