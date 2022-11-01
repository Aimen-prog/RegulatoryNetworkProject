package model.events;

import model.genes.RegulatoryGene;

import java.util.List;

public class SetProteinConcentrationEvent extends AbstractSimulationEvent{

    SetProteinConcentrationEvent(List<RegulatoryGene> genes , double time , double newConcentration){
        super(genes, time);
        for (RegulatoryGene gene : genes ){
            gene.setProteinConcentration(newConcentration);
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
            str += gene.getProteinConcentration() + " " ;
        }
        return "SetProteinConcentrationEvent "+ str;
    }
}
