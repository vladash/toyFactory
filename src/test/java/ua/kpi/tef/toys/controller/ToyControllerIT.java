package ua.kpi.tef.toys.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.ToySaveException;
import ua.kpi.tef.toys.pojo.enums.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class ToyControllerIT {

    private static final Kind KIND_KEYBOARDS = Kind.KEYBOARDS;
    private static final ToySize SIZE_MEDIUM = ToySize.MEDIUM;

    @Autowired
    private ToysService toysService;

    @Autowired
    private ToyController toyController;

    @Test
    void whenGetAllListOfInstrumentsThenReturnAllList() throws ToySaveException {

        final List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toysService.createNewToy(toyList.get(0));
        toysService.createNewToy(toyList.get(1));
        toysService.createNewToy(toyList.get(2));
        List<Toy> toyList1 = toyController.getAllListOfToys();

        Assert.assertEquals(3, toyList1.size());
        Assert.assertEquals(Optional.of(1L), Optional.of(toyList1.get(0).getId()));
        Assert.assertEquals(Optional.of(2L), Optional.of(toyList1.get(1).getId()));
        Assert.assertEquals(Optional.of(3L), Optional.of(toyList1.get(2).getId()));
    }

    @Test
    void whenGetSearchedToysByKindThenReturnSearchedList() throws ToySaveException {

        final List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toysService.createNewToy(toyList.get(0));
        toysService.createNewToy(toyList.get(1));
        toysService.createNewToy(toyList.get(2));

        List<Toy> toyList1 = toyController.getSearchedToysByKind(KIND_KEYBOARDS);

        Assert.assertEquals(2, toyList1.size());
        Assert.assertEquals(Optional.of(1L), Optional.of(toyList1.get(0).getId()));
        Assert.assertEquals(Optional.of(2L), Optional.of(toyList1.get(1).getId()));
    }

    @Test
    void whenGetSearchedInstrumentsBySizeThenReturnSearchedList() throws ToySaveException {

        final List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toysService.createNewToy(toyList.get(0));
        toysService.createNewToy(toyList.get(1));
        toysService.createNewToy(toyList.get(2));

        List<Toy> toyList1 = toyController.getSearchedToysBySize(SIZE_MEDIUM);

        Assert.assertEquals(1, toyList1.size());
        Assert.assertEquals(Optional.of(3L), Optional.of(toyList1.get(0).getId()));
    }

    @Test
    void whenSaveNewToyThenInvokeCreateNewToy() throws ToySaveException {
        Toy toy = new Toy(1L, "ball", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Toy newToy = toyController.saveNewToy(toy);

        Assert.assertEquals(toy.getName(), newToy.getName());
        Assert.assertEquals(toy.getKind(), newToy.getKind());
        Assert.assertEquals(toy.getSoundSource(), newToy.getSoundSource());
        Assert.assertEquals(toy.getMotion(), newToy.getMotion());
        Assert.assertEquals(toy.getToyMaterial(), newToy.getToyMaterial());
        Assert.assertEquals(toy.getCoating(), newToy.getCoating());
        Assert.assertEquals(toy.getToySize(), newToy.getToySize());
        Assert.assertEquals(toy.getAvailableStatus(), newToy.getAvailableStatus());
        Assert.assertEquals(toy.getAvailableAmount(), newToy.getAvailableAmount());
        Assert.assertEquals(toy.getPrice(), newToy.getPrice());
    }

    @Test
    void whenGetToyByIdThenInvokeGetToyById() throws ToyNotFoundException, ToySaveException {
        final List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "bear", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "ball", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "car", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toysService.createNewToy(toyList.get(0));
        toysService.createNewToy(toyList.get(1));
        toysService.createNewToy(toyList.get(2));

        Toy toy1 = toyController.getToyById(1L);
        Toy toy2 = toyController.getToyById(2L);
        Toy toy3 = toyController.getToyById(3L);

        Assert.assertEquals(Optional.of(1L), Optional.of(toy1.getId()));
        Assert.assertEquals("bear", toy1.getName());
        Assert.assertEquals(Optional.of(2L), Optional.of(toy2.getId()));
        Assert.assertEquals("ball", toy2.getName());
        Assert.assertEquals(Optional.of(3L), Optional.of(toy3.getId()));
        Assert.assertEquals("car", toy3.getName());
    }


    @Test
    void whenEditNewToyThenInvokeEditToy() throws ToySaveException {
        //GIVEN
        Toy oldToy = new Toy(1L, "bear", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Toy editToy = new Toy(1L, "car", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 18L, BigDecimal.TEN);

        Toy oldTestToy = toyController.saveNewToy(oldToy);
        Assert.assertEquals(Optional.of(1L), Optional.of(editToy.getId()));
        Assert.assertEquals(oldToy.getName(), oldTestToy.getName());
        Assert.assertEquals(oldToy.getKind(), oldTestToy.getKind());
        Assert.assertEquals(oldToy.getSoundSource(), oldTestToy.getSoundSource());
        Assert.assertEquals(oldToy.getAvailableStatus(), oldTestToy.getAvailableStatus());
        Assert.assertEquals(oldToy.getAvailableAmount(),oldTestToy.getAvailableAmount());
        //WHEN
        Toy newInstrument = toyController.editNewToy(editToy);
        Assert.assertEquals(Optional.of(1L), Optional.of(editToy.getId()));
        Assert.assertEquals(editToy.getName(), newInstrument.getName());
        Assert.assertEquals(editToy.getKind(), newInstrument.getKind());
        Assert.assertEquals(editToy.getSoundSource(), newInstrument.getSoundSource());
        Assert.assertEquals(editToy.getAvailableStatus(), newInstrument.getAvailableStatus());
        Assert.assertEquals(editToy.getAvailableAmount(), newInstrument.getAvailableAmount());
    }

    @Test
    void whenDeleteToyThenInvokeDeleteToyById() throws ToySaveException {

        final List<Toy> toyList = new ArrayList<>();
        toyList.add( new Toy(1L, "bear", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(2L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        toyList.add( new Toy(3L, "ball", Kind.KEYBOARDS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));

        toysService.createNewToy(toyList.get(0));
        toysService.createNewToy(toyList.get(1));
        toysService.createNewToy(toyList.get(2));

        List<Toy> instrumentList1 = toyController.getAllListOfToys();
        Assert.assertEquals(3, instrumentList1.size());

        toyController.deleteToy(1L);
        List<Toy> instrumentList2 = toyController.getAllListOfToys();

        Assert.assertEquals(2, instrumentList2.size());

        toyController.deleteToy(3L);
        List<Toy> instrumentList3 = toyController.getAllListOfToys();

        Assert.assertEquals(1, instrumentList3.size());
    }

    @After
    public void tearDown(){
        toyController.deleteToy(1L);
        toyController.deleteToy(2L);
        toyController.deleteToy(3L);
    }

}