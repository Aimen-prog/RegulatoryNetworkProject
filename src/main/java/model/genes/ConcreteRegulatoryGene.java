package model.genes;

import model.regulators.Regulator;

public class ConcreteRegulatoryGene implements RegulatoryGene {
    private Regulator regulator;
    private double proteinConcentration;
    private double initialProteinConcentration;
    private double maximalProduction;
    private double degradationRate;
    private String name;
    private boolean isSignaled;


    public ConcreteRegulatoryGene(String name, double maximalProduction, double degradationRate,
                                  double initialProteinConcentration, boolean isSignaled){
        this.initialProteinConcentration = initialProteinConcentration;
        this.maximalProduction = maximalProduction;
        this.degradationRate = degradationRate;
        this.name = name;
        this.isSignaled = isSignaled;
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
        this.proteinConcentration = proteinConcentration;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(double duration) {

    }

    @Override
    public double getMaximalProduction() {
        return maximalProduction;
    }

    @Override
    public double getDegradationRate() {
        return degradationRate;
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

    double production() {
        return 1;
    }
    double degradation() {
        return 1;
    }


}
