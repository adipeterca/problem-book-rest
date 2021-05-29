package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.problembook.dtos.ProblemDTO;
import xyz.problembook.entities.ProblemEntity;
import xyz.problembook.repositories.ProblemRepository;

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
        problemRepository.save(new ProblemEntity(problemDTO.getTeacherId(), problemDTO.getTeacherEmail(), problemDTO.getHint1(), problemDTO.getHint2(), problemDTO.getContent()));
        return problemRepository.getIdOf(problemDTO.getTeacherId(), problemDTO.getHint1(), problemDTO.getHint2(), problemDTO.getContent()).getId();
        // return 0;
    }

    public ProblemDTO findById(Integer id) {
        Optional<ProblemEntity> problemEntity = problemRepository.findById(id);
        if (problemEntity.isEmpty()) return null;
        return ProblemDTO.convert(problemEntity.get());
    }

    public ProblemDTO findNext(Integer currentId) {
        ProblemDTO problem = findById(currentId + 1);
        if (problem == null)
            return findById(0);
        return problem;
    }

    public ProblemDTO findPrevious(Integer currentId) {
        ProblemDTO problem = findById(currentId - 1);
        if (problem == null)
            return findById((int) problemRepository.count());
        return problem;
    }
}
