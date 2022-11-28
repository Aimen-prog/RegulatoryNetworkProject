package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;

public abstract class BooleanRegulator implements Regulator {
    protected double threshold ;
    protected RegulatoryGene gene;

    protected BooleanRegulator(double threshold, RegulatoryGene regulator){
        this.threshold=threshold;
        this.gene= regulator;
    }


    protected boolean thresholdIsAttained() {
        return gene.getProteinConcentration() > threshold;
    }
    protected boolean geneIsSignaled() {
        return gene.isSignaled();
    }
    @Override
    public String getInfo() {
        return threshold + " "+ gene.getName();
    }



}







