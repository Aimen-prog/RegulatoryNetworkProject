package model.genes;

import model.file.writer.GeneVisitor;
import model.regulators.Regulator;

public interface RegulatoryGene extends Gene{
  Regulator getRegulator();
  void setRegulator(Regulator regulator);
  boolean isSignaled();
  void setSignaled(boolean isSignaled);

  String accept(GeneVisitor visitor);
}
