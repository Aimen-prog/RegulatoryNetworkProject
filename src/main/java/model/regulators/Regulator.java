package model.regulators;

import model.file.writer.RegulatorVisitor;

public interface Regulator {
  double inputFunction();
  public String getInfo();
  String accept(RegulatorVisitor visitor);
}
