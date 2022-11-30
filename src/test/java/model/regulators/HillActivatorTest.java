package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HillActivatorTest {


    @Test
    public void testGetInfo(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        HillRegulator reg2 = new HillActivator(4.,2.,X);
        assertThat(reg2.getInfo()).startsWith("4.0 2.0 ").endsWith("INS");
    }


    @Test
    public void testInputFunction(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, true);
        RegulatoryGene Y = new ConcreteRegulatoryGene("DSS",20, 0.5,
                10, true);
        HillRegulator regulator1 = new HillActivator(2.,6.,X);
        HillRegulator regulator2 = new HillActivator(4.,5.,Y);
        assertThat(regulator1.inputFunction()).isEqualTo(0.9858044164037855);
        assertThat(regulator2.inputFunction()).isEqualTo(0.9411764705882353);
    }


}
