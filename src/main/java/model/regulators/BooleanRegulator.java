package model.regulators;

import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;

public abstract class BooleanRegulator implements Regulator {
    protected double threshold ;
    protected ConcreteRegulatoryGene gene;

    protected BooleanRegulator(double threshold, RegulatoryGene regulator){
        this.threshold=threshold;
        this.gene.setRegulator((Regulator) regulator);
    }


    protected boolean thresholdIsAttained() {
        return true;
    }
    protected boolean geneIsSignaled() {
        return false;
    }

    public String getInfo() {
        return null;
    }
}
