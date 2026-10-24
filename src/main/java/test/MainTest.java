package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        // 1. Création des salles
        Salle salleA = new Salle();
        salleA.setCode("S1");
        salleA.setLibelle("Salle Informatique");
        salleService.create(salleA);

        Salle salleB = new Salle();
        salleB.setCode("S2");
        salleB.setLibelle("Salle Réseau");
        salleService.create(salleB);

        // 2. Création des machines
        Machine m1 = new Machine("M-101", "Dell", salleA);
        Machine m2 = new Machine("M-102", "HP", salleA);
        Machine m3 = new Machine("M-103", "Dell", salleB);

        machineService.create(m1);
        machineService.create(m2);
        machineService.create(m3);

        System.out.println("=== Données insérées avec succès ! ===");

        // 3. Test de la méthode HQL findByMarque
        System.out.println("\n--- Recherche des machines de marque 'Dell' (findByMarque) ---");
        List<Machine> dellMachines = machineService.findByMarque("Dell");
        for (Machine m : dellMachines) {
            System.out.println("Machine trouvée : " + m);
        }

        System.out.println("\n--- Recherche des machines de marque 'HP' (findByMarque) ---");
        List<Machine> hpMachines = machineService.findByMarque("HP");
        for (Machine m : hpMachines) {
            System.out.println("Machine trouvée : " + m);
        }

        util.HibernateUtil.getSessionFactory().close();
    }
}
