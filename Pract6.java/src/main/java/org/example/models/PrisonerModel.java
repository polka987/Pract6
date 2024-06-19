package models;

import interfaces.Prisoner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PrisonerModel extends PrisonPerson implements Prisoner {
    private static final Logger prisonerLog = Logger.getLogger(CaptainModel.class.getName());

    public PrisonerModel() throws IOException {
        FileHandler fileHandler = new FileHandler("PrisonerLog.log", true);
        prisonerLog.addHandler(fileHandler);
        prisonerLog.setLevel(Level.ALL);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }
    @Override
    public void WorkAtFactory(Integer prisonerID, ArrayList<ArrayList<Object>> heretics) {
        AtomicInteger prisonerIndex = new AtomicInteger(-1);
        AtomicReference<Double> faith = new AtomicReference<>(null);
        heretics.forEach(h -> {
            if(prisonerID.equals(h.getFirst())) {
                prisonerIndex.set(heretics.indexOf(h));
                faith.set((double)h.getLast());
            }
        });
        if(prisonerID>=0 && faith.get() != null){
            heretics.get(prisonerIndex.get()).set(1, faith.get()-4);
            prisonerLog.info("Заключённый работает");
        }
    }

    @Override
    public void WorkForSalvation(ArrayList<ArrayList<Object>> heretics) {
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith + 4);
            }
            catch(Exception ex){
                prisonerLog.severe(ex.getMessage());
            }
        });
    }

    @Override
    public void WorkForSanity(ArrayList<ArrayList<Object>> heretics) {
        ArrayList<Object> hereticsToEnlighten= new ArrayList<>();
        hereticsToEnlighten.addAll(0, heretics);

        hereticsToEnlighten.forEach(h -> {
            try {
                Integer index = heretics.indexOf(h);
                Double faith = (double)heretics.get(index).get(1);
                heretics.get(index).set(1,faith - 5);
            }
            catch(Exception ex){
                prisonerLog.severe(ex.getMessage());
            }
        });
    }
}
