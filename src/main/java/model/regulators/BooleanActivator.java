package model.regulators;

public class BooleanActivator extends BooleanRegulator{
    //TODO: task3 begin with this
    @Override
    public double inputFunction() {
        return 1.;
    }


    //affiche les informations du régulateur à sauvegarder
    @Override
    public String getInfo() {
        return null;
    }
}
