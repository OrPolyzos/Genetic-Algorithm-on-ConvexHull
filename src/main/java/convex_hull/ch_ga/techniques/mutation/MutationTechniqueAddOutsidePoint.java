package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MutationTechniqueAddOutsidePoint implements MutationTechnique {

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
    public DNA execute(DNA dnaToMutate) {
        Map<Integer,List<Point>> geneMap = dnaToMutate.getGene();

        List<Point> outsidePoints = geneMap.get(2);
        if (!outsidePoints.isEmpty()) {
            List<Point> points = geneMap.get(0);
            List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));
            FitnessTechnique fitnessTechnique = dnaToMutate.getFitnessTechnique();

            int chosenOutsidePoint = new Random().nextInt(outsidePoints.size());
            int randomIndex = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(randomIndex, new Point(outsidePoints.get(chosenOutsidePoint)));

            return new CH_DNA(points, mutatedHull, fitnessTechnique);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueAddOutsidePoint";
    }
}
