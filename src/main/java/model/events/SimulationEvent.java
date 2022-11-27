package model.events;

import model.file.writer.EventVisitor;
import model.file.writer.GeneVisitor;
import model.genes.RegulatoryGene;

import java.util.List;

public interface SimulationEvent  {
  void updateGenes();
  double getTime();

  List<RegulatoryGene> getGenes();

  String getInfo();

  String accept(EventVisitor eventVisitor);
}
