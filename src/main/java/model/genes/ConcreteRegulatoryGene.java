package model.genes;

import model.file.writer.GeneVisitor;
import model.regulators.AlwaysOnRegulator;
import model.regulators.Regulator;

public class ConcreteRegulatoryGene implements RegulatoryGene {
    private Regulator regulator;
    private double proteinConcentration;
    private double initialProteinConcentration;
    private double maximalProduction;
    private double degradationRate;
    private String name;
    private boolean isSignaled;
    private boolean initialIsSignaled;


    public ConcreteRegulatoryGene(String name, double maximalProduction, double degradationRate,
                                  double initialProteinConcentration, boolean isSignaled){
        this.initialProteinConcentration = initialProteinConcentration;
        this.maximalProduction = maximalProduction;
        this.degradationRate = degradationRate;
        this.name = name;
        this.isSignaled = isSignaled;
        this.initialIsSignaled = isSignaled;
    }

    @Override
    public double getProteinConcentration() {
        return proteinConcentration;
    }

    @Override
    public double getInitialProteinConcentration() {
        return initialProteinConcentration;
    }

    @Override
    public void setProteinConcentration(double proteinConcentration) {
        if (proteinConcentration >= 0.)
            this.proteinConcentration = proteinConcentration;
        else
            this.proteinConcentration = 0.;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Regulator getRegulator() {
        return regulator;
    }

    @Override
    public void setRegulator(Regulator regulator) {
        this.regulator = regulator;
    }

    @Override
    public boolean isSignaled() {
        return isSignaled;
    }

    @Override
    public void setSignaled(boolean isSignaled) {
        this.isSignaled = isSignaled;

    }

    @Override
    public String toString() {
        return "RegulatoryGene{name= " + name +
                ", isSignaled= " + isSignaled +
                '}';
    }

    @Override
    public double getMaximalProduction() {
        return maximalProduction;
    }

    @Override
    public double getDegradationRate() {
        return degradationRate;
    }

    double production() {
        if (regulator == null) {
            return maximalProduction;
        } else {
            return maximalProduction * (regulator.inputFunction());
        }
    }
    double degradation() {
        return degradationRate * proteinConcentration;
    }

    @Override
    public void update(double duration) {
        proteinConcentration = proteinConcentration+duration*(production()- degradation());
    }

    public String getInfo(){
        return getName() +" "+ getMaximalProduction() + " "+ getDegradationRate()
                + " "+ getInitialProteinConcentration()+ " "+ isSignaled();
    }

    public String accept(GeneVisitor visitor ){
        return visitor.visit(this);
    }

    public boolean getInitialIsSignaled(){ return initialIsSignaled;}

}
