package model.file.writer;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.genes.ConcreteRegulatoryGene;
import model.genes.ConstantRegulatoryGene;

public interface EventVisitor {

    public String visit(SetSignaledEvent event ) ;
    public String visit(SetProteinConcentrationEvent event ) ;
}
