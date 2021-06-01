package ua.kpi.tef.toys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.ToySaveException;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.enums.ToySize;
import ua.kpi.tef.toys.pojo.enums.Kind;
import ua.kpi.tef.toys.service.ToysService;

import java.util.List;


@Controller
public class ToyController {
    private final ToysService toyService;

    @Autowired
    public ToyController(ToysService toyService) {
        this.toyService = toyService;
    }

    public List<Toy> getAllListOfToys(){
        return toyService.getAllToy();
    }

    public Toy saveNewToy(Toy toy) {
        try {
            toyService.createNewToy(toy);
        } catch (ToySaveException e){
            e.getMessage();
        }
        return toy;
    }

    public Toy getToyById(Long id) throws ToyNotFoundException {
        try {
            toyService.getToyById(id);
        } catch (ToyNotFoundException e){
            e.getMessage();
        }
        return toyService.getToyById(id);
    }

    public Toy editNewToy(Toy toy) {
        return toyService.editToy(toy);
    }

    public void deleteToy(Long id) {
        try {
            toyService.deleteToyById(id);
        } catch (ToyNotFoundException e){
            e.getMessage();
        }
    }

    public List<Toy> getSearchedToysByKind(Kind kind) {
        return toyService.getSearchedToyByKind(kind);
    }

    public List<Toy> getSearchedToysBySize(ToySize size) {
        return toyService.getSearchedToysBySize(size);
    }

}

