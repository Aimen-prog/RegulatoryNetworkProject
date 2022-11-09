package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CompositeRegulatorTest {



    @Test
    public void testInputFunction() {
        List<Regulator> firstregulatorList = new ArrayList<>();
        Regulator reg1 = new AlwaysOnRegulator();
        firstregulatorList.add(reg1);
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator reg2 = new BooleanActivator(14,gene);
        firstregulatorList.add(reg2);

        CompositeRegulator compMax = new MaxCompositeRegulator(firstregulatorList);
        assertThat(compMax.inputFunction()).isEqualTo(1.);
        CompositeRegulator compMin = new MinCompositeRegulator(firstregulatorList);
        assertThat(compMin.inputFunction()).isEqualTo(0.);


    }











}
