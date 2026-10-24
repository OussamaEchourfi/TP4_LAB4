package services;

import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalleServiceTest {

    @Test
    public void testCreateSalle() {
        SalleService service = new SalleService();
        Salle salle = new Salle();
        salle.setCode("S2");
        salle.setLibelle("Salle Réseau");

        boolean result = service.create(salle);
        Assertions.assertTrue(result, "La création de la salle doit réussir");
    }
}
