package backend.simulation;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static int idgen = 0;
    private List<Ambulance> amulances;
    private List<Patient> patients;
    private List<HomeBase> homeBases;
    private List<Hospital> hospitals;

    public Simulation() {
        homeBases = generateHomeBases();
        amulances = generateAmbulances(homeBases);
        patients = new ArrayList<>();
        hospitals = generateHospitals();
    }

    private List<Ambulance> generateAmbulances(List<HomeBase> homeBases) {
        List<Ambulance> ambs = new ArrayList<>();
        // just test locations
        for (HomeBase hb : homeBases) {
            ambs.add(new Ambulance(idgen++, hb.getLocation(), hb.getId()));
            hb.houseAmbulance();
        }
        return ambs;
    }

    private List<HomeBase> generateHomeBases() {
        List<HomeBase> hbs = new ArrayList<>();
        // just test locations
        hbs.add(new HomeBase(idgen++, new Point2D(25, 25), 3));
        hbs.add(new HomeBase(idgen++, new Point2D(100, 100), 3));
        return hbs;
    }

    private List<Hospital> generateHospitals() {
        List<Hospital> hs = new ArrayList<>();
        // just test locations
        hs.add(new Hospital(idgen++, new Point2D(55, 55)));
        return hs;
    }
}
