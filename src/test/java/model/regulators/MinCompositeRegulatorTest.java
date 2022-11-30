package model.regulators;

import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MinCompositeRegulatorTest {

    @Test
    public void testInputFunction() {
        List<Regulator> RegulatorList = new ArrayList<>();

        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        Regulator regulator2 = new AlwaysOnRegulator();
        //MAKE A LIST OF TWO REGULATORS
        RegulatorList.add(regulator1);
        RegulatorList.add(regulator2);

        CompositeRegulator compMin = new MinCompositeRegulator(RegulatorList);
        assertThat(compMin.inputFunction()).isEqualTo(0.); //(min(0,1))
        X.setSignaled(true);
        assertThat(compMin.inputFunction()).isEqualTo(1.); //(min(1,1))


    }


}
