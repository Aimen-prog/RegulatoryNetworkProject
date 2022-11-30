package model.events;

import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SetSignaledEventTest {

    @Test
    public void testUpdateGenes(){
        List<RegulatoryGene> geneList = new ArrayList<>();
        RegulatoryGene gene1 = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",20, 0.5,
                20, true);
        geneList.add(gene1);
        geneList.add(gene2);
        AbstractSimulationEvent simulation2 = new SetSignaledEvent(geneList,10.,true);
        assertFalse(geneList.get(0).isSignaled());
        assertTrue(geneList.get(1).isSignaled());
        simulation2.updateGenes();
        assertTrue(geneList.get(0).isSignaled());
        assertTrue(geneList.get(1).isSignaled());
    }

}
