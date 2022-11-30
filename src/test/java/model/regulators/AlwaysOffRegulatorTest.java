package model.regulators;
import model.genes.ConcreteRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AlwaysOffRegulatorTest {

    @Test
    public void testGetInfo(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",80, 0.9,
                50, false);
        Regulator regulator = new AlwaysOffRegulator();
        X.setRegulator(regulator);
        assertThat(regulator.getInfo()).isNull();
    }
    @Test
    public void testInputFunction(){
        RegulatoryGene X = new ConcreteRegulatoryGene("INS",81, 0.9,
                50, true);
        Regulator regulator = new AlwaysOffRegulator();
        X.setRegulator(regulator);
        assertThat(regulator.inputFunction()).isEqualTo(0.);

    }
}
