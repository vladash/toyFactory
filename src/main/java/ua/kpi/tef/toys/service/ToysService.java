package ua.kpi.tef.toys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.ToySaveException;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.enums.ToySize;
import ua.kpi.tef.toys.pojo.enums.Kind;
import ua.kpi.tef.toys.repository.ToysRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToysService {
    private final ToysRepository toyRepository;

    @Autowired
    public ToysService(ToysRepository toyRepository) {
        this.toyRepository = toyRepository;
    }

    public void createNewToy(Toy toy) throws ToySaveException {
        try {
            toyRepository.save(toy);
        }catch (DataIntegrityViolationException e) {
            throw new ToySaveException("Can not toy with id=" + toy.getId());
        }
    }

    public Toy getToyById(Long id) throws ToyNotFoundException {
        return  toyRepository.findById(id)
                .orElseThrow(() -> new ToyNotFoundException("Toy id=" + id + " not found"));
    }

    public List<Toy> getAllToy() {
        return toyRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Toy editToy(Toy toy){
        toyRepository.save(toy);
        log.info("editing toy");
        return toy;
    }

    public void deleteToyById(Long toyId) throws ToyNotFoundException {
        Toy toy = toyRepository.findToyById(toyId).orElseThrow(()
                -> new ToyNotFoundException("toy" + toyId + " not found"));
        toyRepository.delete(toy);
        log.info("deleting toy");
    }

    public List<Toy> getSearchedToyByKind(Kind kind) {
        return toyRepository.findToyByKind(kind);
    }

    public List<Toy> getSearchedToysBySize(ToySize size) {
        return toyRepository.findToyByToySize(size);
    }
}
