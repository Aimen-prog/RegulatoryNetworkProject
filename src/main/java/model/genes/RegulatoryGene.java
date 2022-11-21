package model.genes;

import model.regulators.Regulator;

public interface RegulatoryGene extends Gene{
  Regulator getRegulator();
  void setRegulator(Regulator regulator);
  boolean isSignaled();
  void setSignaled(boolean isSignaled);
}
