package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BooleanRegulatorTest {

    @Test
    public void testGetInfo(){
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator reg2 = new BooleanActivator(14,gene);
        assertThat(reg2.getInfo()).startsWith("14.0 INS"); //.endsWith("S");
    }
}
