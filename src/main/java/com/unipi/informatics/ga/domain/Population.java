package com.unipi.informatics.ga.domain;

import java.util.List;

public class Population<T> {

    private List<Chromosome<T>> chromosomes;
    private Chromosome<T> fittestChromosome;

    public Population(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();
    }

    public void calculateProbabilities() {
        double fitnessesSum = 0;
        for (Chromosome<T> chromosome : chromosomes) {
            fitnessesSum += chromosome.getFitness();
        }
        //Calculate probabilities
        for (Chromosome<T> chromosome : chromosomes) {
            double probability = chromosome.getFitness() / fitnessesSum;
            chromosome.setProbability(probability);
        }
    }

    public Chromosome<T> findFittestChromosome() {
        double bestFitness = Double.MIN_VALUE;
        for (Chromosome<T> chromosome : chromosomes) {
            if (chromosome.getFitness() > bestFitness) {
                fittestChromosome = chromosome;
                bestFitness = chromosome.getFitness();
            }
        }
        return fittestChromosome;
    }

    public List<Chromosome<T>> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();
    }

    public Chromosome<T> getFittestChromosome() {
        return fittestChromosome;
    }

    public void setFittestChromosome(Chromosome<T> fittestChromosome) {
        this.fittestChromosome = fittestChromosome;
    }
}
