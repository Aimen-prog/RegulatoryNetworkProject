package model.events;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.AlwaysOnRegulator;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRegulator;
import model.regulators.Regulator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class AbstractSimulationEventTest {

    @Test
    public void testUpdateGenes(){
        List<RegulatoryGene> geneList = new ArrayList<>();
        RegulatoryGene gene1 = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",20, 0.5,
                20, false);
        geneList.add(gene1);
        geneList.add(gene2);

        AbstractSimulationEvent simulation = new SetProteinConcentrationEvent(geneList,10.,15.);
        simulation.updateGenes();
        assertThat(geneList.get(1).getProteinConcentration()).isEqualTo(15.);
    }

}
