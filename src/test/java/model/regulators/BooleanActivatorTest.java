package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BooleanActivatorTest {

    @Test
    public void testGetInfo(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        BooleanRegulator reg2 = new BooleanActivator(14,X);
        assertThat(reg2.getInfo()).startsWith("14.0").endsWith("INS");
    }
    @Test
    public void testInputFunction(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, true);
        RegulatoryGene Y = new ConcreteRegulatoryGene("DSS",20, 0.5,
                10, true);
        BooleanRegulator regulator1 = new BooleanActivator(14,X);
        BooleanRegulator regulator2 = new BooleanActivator(14,Y);
        //threshold attained ( <C )and signaled
        assertThat(regulator1.inputFunction()).isEqualTo(1.0);
        //threshold not attained
        assertThat(regulator2.inputFunction()).isEqualTo(0.0);
        //threshold attained (eq C) and signaled
        Y.setProteinConcentration(14);
        assertThat(regulator2.inputFunction()).isEqualTo(1.0);
        //not signaled
        Y.setSignaled(false);
        assertThat(regulator2.inputFunction()).isEqualTo(0.0);
    }
}
