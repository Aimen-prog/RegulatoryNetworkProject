package model.genes;

import model.regulators.Regulator;

public class ConcreteRegulatoryGene implements RegulatoryGene {
    @Override
    public double getProteinConcentration() {
        return 0;
    }

    @Override
    public double getInitialProteinConcentration() {
        return 0;
    }

    @Override
    public void setProteinConcentration(double proteinConcentration) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void update(double duration) {

    }

    @Override
    public double getMaximalProduction() {
        return 0;
    }

    @Override
    public double getDegradationRate() {
        return 0;
    }

    @Override
    public Regulator getRegulator() {
        return null;
    }

    @Override
    public void setRegulator(Regulator regulator) {

    }

    @Override
    public boolean isSignaled() {
        return false;
    }

    @Override
    public void setSignaled(boolean isSignaled) {

    }
}
