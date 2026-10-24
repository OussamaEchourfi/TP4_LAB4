package services;

import entities.Machine;
import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MachineServiceTest {

    private MachineService machineService;
    private SalleService salleService;

    @BeforeEach
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();
    }

    @Test
    public void testCreateAndFindByMarque() {
        Salle salle = new Salle();
        salle.setCode("S_TEST");
        salle.setLibelle("Salle Test");
        salleService.create(salle);

        String testMarque = "LenovoTest";
        Machine machine = new Machine("M_TEST_1", testMarque, salle);
        boolean created = machineService.create(machine);
        Assertions.assertTrue(created, "L'insertion de la machine doit réussir");

        List<Machine> machines = machineService.findByMarque(testMarque);
        Assertions.assertNotNull(machines);
        Assertions.assertFalse(machines.isEmpty(), "La liste ne doit pas être vide");
        Assertions.assertTrue(machines.stream().allMatch(m -> testMarque.equals(m.getMarque())),
                "Toutes les machines retournées doivent avoir la marque recherchée");
    }

    @Test
    public void testCreateMultipleTransaction() {
        Salle salle = new Salle();
        salle.setCode("S_BATCH");
        salle.setLibelle("Salle Batch");
        salleService.create(salle);

        Machine m1 = new Machine("M_BATCH_1", "Acer", salle);
        Machine m2 = new Machine("M_BATCH_2", "Acer", salle);

        boolean success = machineService.createMultiple(List.of(m1, m2));
        Assertions.assertTrue(success, "L'insertion en lot dans une seule transaction doit réussir");

        List<Machine> acers = machineService.findByMarque("Acer");
        Assertions.assertTrue(acers.size() >= 2, "Les machines insérées en transaction doivent être persistées");
    }

    @Test
    public void testTransactionRollbackOnError() {
        // Tenter de persister une entité invalide (ex: null) doit déclencher le rollback et lever une RuntimeException
        Assertions.assertThrows(RuntimeException.class, () -> {
            machineService.create(null);
        }, "Une RuntimeException doit être levée en cas d'erreur transactionnelle");
    }
}
