package model.events;

import model.genes.RegulatoryGene;

import java.util.List;

public abstract class AbstractSimulationEvent implements SimulationEvent {
    private double time ;
    private List<RegulatoryGene> genes;

    protected AbstractSimulationEvent(List<RegulatoryGene> genes, double time){
        this.time =time;
        this.genes=genes;
    }

    public double getTime(){
        return time;
    }

    public List<RegulatoryGene> getGenes(){
        return genes;
    }

    public void updateGenes(){
        for (RegulatoryGene gene : genes ){
            updateGene(gene);
        }
    }

    abstract protected void updateGene(RegulatoryGene gene);
}
