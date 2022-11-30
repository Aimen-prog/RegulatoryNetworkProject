package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HillRepressorTest {

    @Test
    public void testGetInfo(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        HillRegulator reg2 = new HillRepressor(4.,3.,X);
        assertThat(reg2.getInfo()).startsWith("4.0 3.0 ").endsWith("INS");
    }

    @Test
    public void testInputFunction(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, true);
        RegulatoryGene Y = new ConcreteRegulatoryGene("DSS",20, 0.5,
                10, true);
        HillRegulator regulator1 = new HillRepressor(2.,6.,X);
        HillRegulator regulator2 = new HillRepressor(4.,5.,Y);
        assertThat(regulator1.inputFunction()).isEqualTo(3.8903777989106943E-4);
        assertThat(regulator2.inputFunction()).isEqualTo(9.98302885095338E-5);
    }







}
