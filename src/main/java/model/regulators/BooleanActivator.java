package model.regulators;

import model.genes.RegulatoryGene;

public class BooleanActivator extends BooleanRegulator{


    public BooleanActivator(double threshold, RegulatoryGene regulator){
        super(threshold,regulator);
    }
    @Override
    public double inputFunction() {
        return 1.;
    }

}
