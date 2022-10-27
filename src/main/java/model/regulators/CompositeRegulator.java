package model.regulators;

import java.util.List;

public abstract class CompositeRegulator implements Regulator{
    private List<Regulator> regulators;

    protected CompositeRegulator(List<Regulator> regulators){
        this.regulators = regulators;
    }

    @Override
    public double inputFunction() {
//        for ( Regulator regulator : regulators) {
//            return regulator.inputFunction();
//        }
        return 0;

    }

    //donne la valeur de base de la sortie d’inputFunction (0 ou 1)
    protected double initialValue(){
        return 1.;
    }

    protected double cumulativeValue(double accumulator ,double value) {
        return 1.;

    }







////////////////////////////////////////////////


    @Override
    public String getInfo() {
        return null;
    }
///////////////////////////////////////////////////////////


}
