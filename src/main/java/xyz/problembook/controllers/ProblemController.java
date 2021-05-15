package xyz.problembook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.problembook.dtos.ProblemDTO;
import xyz.problembook.services.ProblemService;

@RestController
@RequestMapping(path = "problem")
public class ProblemController {
    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/")
    public Integer add(@RequestBody ProblemDTO problemDTO) {
        return problemService.add(problemDTO);
    }

    @GetMapping("/findById")
    public ProblemDTO findById(@RequestParam(name = "id") Integer id) {
        return problemService.findById(id);
    }

    @GetMapping("/findNext")
    public ProblemDTO findNext(@RequestParam(name = "id") Integer id) {
        return problemService.findNext(id);
    }

    @GetMapping("/findPrevious")
    public ProblemDTO findPrevious(@RequestParam(name = "id") Integer id) {
        return problemService.findPrevious(id);
    }
}
