package model.genes;

import model.regulators.Regulator;

public class ConstantRegulatoryGene implements RegulatoryGene{
  private double proteinConcentration;
  private final double initialProteinConcentration;
  private final String name;
  private boolean isSignaled;

  public ConstantRegulatoryGene(String name, double proteinConcentration, boolean isSignaled) {
    this.proteinConcentration = proteinConcentration;
    this.initialProteinConcentration = proteinConcentration;
    this.name = name;
    this.isSignaled = isSignaled;
  }

  @Override
  public Regulator getRegulator() {
    return null;
  }

  @Override
  public double getInitialProteinConcentration() {
    return initialProteinConcentration;
  }

  @Override
  public double getProteinConcentration() {
    return proteinConcentration;
  }

  @Override
  public void setProteinConcentration(double proteinConcentration) {
    this.proteinConcentration = proteinConcentration;
  }

  public String getName() {
    return name;
  }

  @Override
  public void update(double duration) {

  }

  @Override
  public void setRegulator(Regulator regulator) {

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
  public double getMaximalProduction() {
    return 0;
  }

  @Override
  public double getDegradationRate() {
    return 0;
  }

}
