package model.events;

import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SetProteinConcentrationEventTest {

    @Test
    public void testUpdateGenes(){
        List<RegulatoryGene> geneList = new ArrayList<>();
        RegulatoryGene gene1 = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",20, 0.5,
                20, true);
        geneList.add(gene1);
        geneList.add(gene2);
        AbstractSimulationEvent simulation = new SetProteinConcentrationEvent(geneList,10.,15.);
        assertThat(geneList.get(0).getProteinConcentration()).isEqualTo(50.);
        assertThat(geneList.get(1).getProteinConcentration()).isEqualTo(20.);

        simulation.updateGenes();
        assertThat(geneList.get(0).getProteinConcentration()).isEqualTo(15.);
        assertThat(geneList.get(1).getProteinConcentration()).isEqualTo(15.);
    }

}
