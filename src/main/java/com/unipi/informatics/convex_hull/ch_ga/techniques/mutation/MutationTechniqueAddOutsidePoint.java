package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Dna;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.*;

public class MutationTechniqueAddOutsidePoint implements MutationTechnique<Map<Integer, List<Point>>> {

    private static MutationTechniqueAddOutsidePoint mutationTechniqueAddOutsidePoint;

    private MutationTechniqueAddOutsidePoint() {
    }

    public static synchronized MutationTechniqueAddOutsidePoint getInstance() {
        if (mutationTechniqueAddOutsidePoint == null) {
            mutationTechniqueAddOutsidePoint = new MutationTechniqueAddOutsidePoint();
        }
        return mutationTechniqueAddOutsidePoint;
    }

    @Override
    public Dna<Map<Integer, List<Point>>> execute(Dna<Map<Integer, List<Point>>> dnaToMutate) {
        Map<Integer, List<Point>> geneMap = dnaToMutate.getGene();

        List<Point> outsidePoints = geneMap.get(2);
        if (!outsidePoints.isEmpty()) {
            List<Point> points = geneMap.get(0);
            List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

            int chosenOutsidePoint = new Random().nextInt(outsidePoints.size());
            int randomIndex = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(randomIndex, new Point(outsidePoints.get(chosenOutsidePoint)));

            Map<Integer, List<Point>> newGeneMap = new LinkedHashMap<>();
            newGeneMap.put(0,points);
            newGeneMap.put(1,mutatedHull);
            return new CH_Dna(newGeneMap);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueAddOutsidePoint";
    }
}
