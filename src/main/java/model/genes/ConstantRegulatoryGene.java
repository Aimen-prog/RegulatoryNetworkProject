package model.genes;

import model.file.writer.GeneVisitor;
import model.regulators.Regulator;

import java.util.Objects;

public class ConstantRegulatoryGene implements RegulatoryGene{
  private double proteinConcentration;
  private final double initialProteinConcentration;
  private final String name;
  private boolean isSignaled;
  private boolean initialIsSignaled;

  public ConstantRegulatoryGene(String name, double proteinConcentration, boolean isSignaled) {
    if (proteinConcentration < 0.)
      throw new IllegalArgumentException("The concentration cannot be negative!");
    else
      this.initialProteinConcentration = proteinConcentration;
    this.proteinConcentration = proteinConcentration;
    this.name = name;
    this.isSignaled = isSignaled;
    this.initialIsSignaled = isSignaled;
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
    if (proteinConcentration < 0.)
      throw new IllegalArgumentException("The concentration cannot be negative!");
    else
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


  public String accept(GeneVisitor visitor ){
    return visitor.visit(this);
  }

  public boolean getInitialIsSignaled(){ return initialIsSignaled;}


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ConstantRegulatoryGene that = (ConstantRegulatoryGene) o;
    return Double.compare(that.proteinConcentration, proteinConcentration) == 0
            && Double.compare(that.initialProteinConcentration, initialProteinConcentration) == 0
            && isSignaled == that.isSignaled
            && initialIsSignaled == that.initialIsSignaled
            && name.equals(that.name);
  }
}
