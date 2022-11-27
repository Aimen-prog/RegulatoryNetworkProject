package model.events;

import model.file.writer.EventVisitor;
import model.file.writer.GeneVisitor;
import model.genes.RegulatoryGene;

import java.util.List;

public class SetSignaledEvent extends AbstractSimulationEvent {

    private boolean newSignaledValue ;

    public SetSignaledEvent(List<RegulatoryGene> genes , double time , boolean newSignaledValue){
        super(genes, time);
        this.newSignaledValue = newSignaledValue;

    }

    @Override
    protected void updateGene(RegulatoryGene gene) {
        gene.setSignaled(newSignaledValue);
    }

    @Override
    public String getInfo() {
        return String.valueOf(newSignaledValue);
    }

    public String accept(EventVisitor eventVisitor ){
        return eventVisitor.visit(this);
    }
}
