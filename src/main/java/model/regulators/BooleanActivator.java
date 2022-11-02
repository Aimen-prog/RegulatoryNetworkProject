package model.regulators;

import model.genes.RegulatoryGene;

public class BooleanActivator extends BooleanRegulator{


    public BooleanActivator(double threshold, RegulatoryGene regulator) {
        super(threshold, regulator);
    }
    @Override
    public double inputFunction() {
        if (geneIsSignaled() & thresholdIsAttained()){
            return 1.0;
        } else {
            return 0.;
        }
    }

}
