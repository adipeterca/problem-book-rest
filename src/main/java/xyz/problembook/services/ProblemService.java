package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.problembook.dtos.ProblemDTO;
import xyz.problembook.entities.ProblemEntity;
import xyz.problembook.repositories.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    /**
     * Public method that add a new problem to the database.
     * @param problemDTO the problem to be added
     * @return the ID of the new added problem
     */
    public int add(ProblemDTO problemDTO) {
        int id = (int)problemRepository.count();
        problemRepository.save(new ProblemEntity(id + 1, problemDTO.getTeacherId(), problemDTO.getTeacherEmail(), problemDTO.getHint1(), problemDTO.getHint2(), problemDTO.getContent()));
        return problemRepository.getIdOf(problemDTO.getTeacherId(), problemDTO.getHint1(), problemDTO.getHint2(), problemDTO.getContent()).getId();
        // return 0;
    }

    public ProblemDTO findById(Integer id) {
        List<ProblemEntity> problemEntityList = problemRepository.findAll();
        if (problemEntityList.size() <= id)
            return null;
        return ProblemDTO.convert(problemEntityList.get(id));
    }

    public ProblemDTO findNext(Integer currentId) {
        List<ProblemEntity> problemEntityList = problemRepository.findAll();
        if (currentId + 1 >= problemEntityList.size())
            return ProblemDTO.convert(problemEntityList.get(0));
        return ProblemDTO.convert(problemEntityList.get(currentId + 1));
    }

    public ProblemDTO findPrevious(Integer currentId) {
        List<ProblemEntity> problemEntityList = problemRepository.findAll();
        if (currentId == 0)
            return ProblemDTO.convert(problemEntityList.get(problemEntityList.size() - 1));
        return ProblemDTO.convert(problemEntityList.get(currentId - 1));
    }
}
