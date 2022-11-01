package model.events;

import model.genes.RegulatoryGene;

import java.util.List;

public class SetSignaledEvent extends AbstractSimulationEvent {

    SetSignaledEvent(List<RegulatoryGene> genes , double time , boolean newSignaledValue){
        super(genes, time);
        for (RegulatoryGene gene : genes ){
            gene.setSignaled(newSignaledValue);
        }

    }

    @Override
    protected void updateGene(RegulatoryGene gene) {
        gene.update(getTime());
    }

    @Override
    public String getInfo() {
        String str = "";
        for (RegulatoryGene gene : getGenes() ){
            str += gene.isSignaled() + " " ;
        }
        return "SetSignaledEvent "+ str;
    }
}
