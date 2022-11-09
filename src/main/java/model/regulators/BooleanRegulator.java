package model.regulators;

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
        if (this.getClass().getName().equals("model.regulators.BooleanActivator")){
            return "BooleanActivator "+ threshold +" " + gene.getName() ;
        } else {return "BooleanRepressor "+ threshold +" " + gene.getName() ;}
    }
}







