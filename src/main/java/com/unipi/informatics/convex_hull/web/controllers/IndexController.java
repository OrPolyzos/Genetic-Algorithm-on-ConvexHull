package com.unipi.informatics.convex_hull.web.controllers;

import com.unipi.informatics.convex_hull.Convex_Hull_Problem;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.convex_hull.web.model.DetailsForm;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    GeneticAlgorithmRepository geneticAlgorithmRepository;

    @GetMapping(value = "/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneticAlgorithmDAO run(@Valid @ModelAttribute("detailsForm") DetailsForm detailsForm) {
        Convex_Hull_Problem convex_hull_problem = new Convex_Hull_Problem(500, 500, detailsForm.getMutationRate(), detailsForm.getPopulationCount(), detailsForm.getPointsCount());
        GeneticAlgorithm<Map<Integer,List<Point>>> geneticAlgorithm = convex_hull_problem.solve();
        return GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm);
    }
}
