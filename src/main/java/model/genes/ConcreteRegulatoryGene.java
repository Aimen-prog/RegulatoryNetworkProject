package model.genes;

import model.file.writer.GeneVisitor;
import model.regulators.Regulator;

public class ConcreteRegulatoryGene implements RegulatoryGene{

    private Regulator regulator;
    private final double maximalProduction;
    private final double degradationRate;
    private final double initialProteinConcentration;
    private double proteinConcentration;
    private final String name;
    private Boolean isSignaled;

    private final boolean initialIsSignaled;

    public ConcreteRegulatoryGene(String name, double maximalProduction, double degradationRate, double initialProteinConcentration, boolean isSignaled) {
        this.maximalProduction = maximalProduction;
        this.degradationRate = degradationRate;
        this.initialProteinConcentration = initialProteinConcentration;
        this.proteinConcentration = initialProteinConcentration;
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
    public void update(double duration) {
        this.proteinConcentration = proteinConcentration + duration * (production() - degradation());
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
    public void setRegulator(Regulator regulator) {
        this.regulator = regulator;
    }

    @Override
    public Regulator getRegulator() {
        return regulator;
    }

    @Override
    public boolean isSignaled() {
        return isSignaled;
    }

    @Override
    public void setSignaled(boolean isSignaled) {
        this.isSignaled = isSignaled;
    }

    private double degradation() {
        return proteinConcentration * degradationRate;
    }

    private double production() {
        if (regulator != null) {
            return regulator.inputFunction() * getMaximalProduction();
        }
        return getMaximalProduction();
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
