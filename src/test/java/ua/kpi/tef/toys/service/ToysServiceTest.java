package ua.kpi.tef.toys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.ToySaveException;
import ua.kpi.tef.toys.pojo.enums.*;
import ua.kpi.tef.toys.repository.ToysRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToysServiceTest {

    @Autowired
    private ToysService toyService;

    @MockBean
    ToysRepository toyRepository;

    @Test
    public void whenCreateNewToyThenSaveToy() throws ToySaveException {
        Toy toy = new Toy();
        toyService.createNewToy(toy);
        Mockito.verify(toyRepository, Mockito.times(1)).save(ArgumentMatchers.any(Toy.class));
    }

    @Test
    public void whenGetToyByIdThenReturnToyByID() throws ToyNotFoundException {
        Toy toy = new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(toy))
                .when(toyRepository)
                .findToyById(1L);
    }

    @Test
    public void whenGetAllToyThenReturnToyList(){
        List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, toyList.size());
        Mockito.doReturn(toyList).when(toyRepository).findAll();
        List<Toy> allToys = toyService.getAllToy();
        assertEquals(3, allToys.size());
    }

    @Test
    public void whenEditToyThenSaveEditedToy(){
        Toy toy = new Toy();
        toyService.editToy(toy);
        Mockito.verify(toyRepository, Mockito.times(1)).save(ArgumentMatchers.any(Toy.class));
    }

    @Test
    public void whenDeleteToyByIdThenInvokeDelete() throws ToyNotFoundException {
        Toy toy = new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(toy))
                .when(toyRepository)
                .findToyById(1L);
        toyService.deleteToyById(1L);
        Mockito.verify(toyRepository, Mockito.times(1)).findToyById(1L);
        Mockito.verify(toyRepository, Mockito.times(1)).delete(toy);
    }

    @Test
    public void whenGetSearchedToysByKindTHenReturnToyList(){
        List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, toyList.size());
        Mockito.doReturn(toyList).when(toyRepository).findToyByKind(Kind.KEYBOARDS);
        List<Toy> searchedList = toyService.getSearchedToyByKind(Kind.KEYBOARDS);
        assertEquals(2, searchedList.size());
        assertEquals(1L, searchedList.get(0).getId());
        assertEquals(2L, searchedList.get(1).getId());
    }

    @Test
    public void getSearchedToysBySize(){
        List<Toy> toyList1 = new ArrayList<>();
        toyList1.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList1.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList1.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, toyList1.size());
        Mockito.doReturn(toyList1).when(toyRepository).findAll();
        List<Toy> searchedList = toyService.getSearchedToysBySize(ToySize.BIG);
    }
}
