package model.regulators;

import model.genes.RegulatoryGene;

public abstract class HillRegulator implements Regulator {
    protected final double HillCoef ;
    protected final double activationCoef ;
    protected RegulatoryGene gene;

    protected HillRegulator(double HillCoef, double activationCoef, RegulatoryGene regulator){
        this.HillCoef=HillCoef;
        this.activationCoef =activationCoef;
        this.gene= regulator;
    }

    public double getHillCoef(){
        return HillCoef;
    }

    public double getActivationCoef(){
        return activationCoef;
    }

    public RegulatoryGene getHillRegulator(){
        return gene;
    }


    @Override
    public String getInfo() {
        return getHillCoef() + " " + getActivationCoef() + " "+ getHillRegulator().getName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HillRegulator that = (HillRegulator) o;
        return Double.compare(that.HillCoef, HillCoef) == 0 && gene.equals(that.gene);
    }

}
