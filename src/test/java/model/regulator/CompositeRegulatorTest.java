package model.regulator;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeRegulatorTest {



    @Test
    public void testInputFunction() {
        List<Regulator> regulatorList = new ArrayList<>();
        Regulator reg1 = new AlwaysOnRegulator();
        regulatorList.add(reg1);
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, true);
        BooleanRegulator reg2 = new BooleanActivator(4,gene);
        regulatorList.add(reg2);


        CompositeRegulator comp = new MaxCompositeRegulator(regulatorList);
        assertThat(comp.inputFunction()).isEqualTo(1.);

    }











}
