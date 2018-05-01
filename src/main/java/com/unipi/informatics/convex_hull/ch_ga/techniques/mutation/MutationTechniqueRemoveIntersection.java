package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Dna;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.*;

public class MutationTechniqueRemoveIntersection implements MutationTechnique<Map<Integer, List<Point>>> {

    private static MutationTechniqueRemoveIntersection mutationTechniqueRemoveIntersection;

    private MutationTechniqueRemoveIntersection() {
    }

    public static synchronized MutationTechniqueRemoveIntersection getInstance() {
        if (mutationTechniqueRemoveIntersection == null) {
            mutationTechniqueRemoveIntersection = new MutationTechniqueRemoveIntersection();
        }
        return mutationTechniqueRemoveIntersection;
    }

    @Override
    public Dna<Map<Integer, List<Point>>> execute(Dna<Map<Integer, List<Point>>> dnaToMutate) {
        Map<Integer, List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

        if (mutatedHull.size() > 3) {
            int possibleIntersectionPoint = new Random().nextInt(mutatedHull.size() - 1);
            mutatedHull.remove(possibleIntersectionPoint);
            Map<Integer, List<Point>> newGeneMap = new LinkedHashMap<>();
            newGeneMap.put(0,points);
            newGeneMap.put(1,mutatedHull);
            return new CH_Dna(newGeneMap);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveIntersection";
    }
}
