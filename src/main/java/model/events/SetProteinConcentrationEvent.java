package model.events;

import model.file.writer.EventVisitor;
import model.genes.RegulatoryGene;

import java.util.List;

public class SetProteinConcentrationEvent extends AbstractSimulationEvent{

    private double newConcentration ;

    public SetProteinConcentrationEvent(List<RegulatoryGene> genes , double time , double newConcentration){
        super(genes, time);
        this.newConcentration = newConcentration;
    }

    @Override
    protected void updateGene(RegulatoryGene gene) {
        gene.setProteinConcentration(newConcentration);

    }

    @Override
    public String getInfo() {
        return String.valueOf(newConcentration);
    }

    public String accept(EventVisitor eventVisitor ){
        return eventVisitor.visit(this);
    }



}
