package model.regulators;

import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MaxCompositeRegulatorTest {



    @Test
    public void testInputFunction() {
        List<Regulator> RegulatorList = new ArrayList<>();
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        RegulatoryGene Y = new ConcreteRegulatoryGene("IDS",40, 0.9,
                10, true);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        BooleanRegulator regulator2 = new BooleanActivator(11,Y);
        //MAKE A LIST OF TWO REGULATORS
        RegulatorList.add(regulator1);
        RegulatorList.add(regulator2);

        CompositeRegulator compMax = new MaxCompositeRegulator(RegulatorList);
        assertThat(compMax.inputFunction()).isEqualTo(0.); //(max(0,0))
        Y.setProteinConcentration(12);
        assertThat(compMax.inputFunction()).isEqualTo(1.); //(max(0,1))
        X.setSignaled(true);
        assertThat(compMax.inputFunction()).isEqualTo(1.); //(max(1,1))
        Y.setSignaled(false);
        assertThat(compMax.inputFunction()).isEqualTo(1.); //(max(1,0))
    }



}
