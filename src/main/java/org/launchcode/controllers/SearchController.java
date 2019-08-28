package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results (Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("type", searchType );
        model.addAttribute("term", searchTerm );

        //get a list of jobs based on user input.
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        if ((searchType.equals("all")) && searchTerm != "") {
            list = JobData.findByValue(searchTerm);

        }

        else{
            if (searchTerm != "")
                list = JobData.findByColumnAndValue(searchType, searchTerm);
        }


        // add this to model to display
        model.addAttribute("list", list);

        return "search";
    }

}
