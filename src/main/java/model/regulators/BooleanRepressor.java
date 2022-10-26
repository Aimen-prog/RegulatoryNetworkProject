package model.regulators;

import model.genes.RegulatoryGene;

public class BooleanRepressor extends BooleanRegulator{
    public BooleanRepressor(double threshold, RegulatoryGene regulator) {
        super(threshold, regulator);
    }

    @Override
    public double inputFunction() {
        if (geneIsSignaled() & thresholdIsAttained()){
            return 0.0;
        } else {
            return 1.;
        }
    }

}
