package model.genes;

import model.regulators.Regulator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteRegulatoryGeneTest {


    @Test
    public void testGetInitialProteinConcentration(){
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
        10.1, true);
        assertEquals(10.1, reg.getInitialProteinConcentration());
        assertFalse(reg.getInitialProteinConcentration()==0.1);
        var reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getInitialProteinConcentration()==8.);
    }


    @Test
    public void testGetMaximalProduction(){
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertEquals(5.1, reg.getMaximalProduction());
        assertFalse(reg.getMaximalProduction()==0.1);
        var reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getMaximalProduction()==5.1);
    }


    @Test
    public void testGetDegradationRate(){
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.getDegradationRate()==0.1);
        assertFalse(reg.getDegradationRate()==5.1);
        var reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getDegradationRate()==0.1);
    }


    @Test
    public void testGetName() {
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertThat(reg.getName()).startsWith("IN").endsWith("S");
        var reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertThat(reg2.getName()).isEqualTo ("P53");

    }

    @Test
    public void testIsSignaled() {
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.isSignaled());
        var reg2 = new ConcreteRegulatoryGene("P53",5.1, 0.15,
                12.1, true);
        assertNotEquals(true, reg2.isSignaled());
    }

    @Test
    public void testSetSignaled() {
        var reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.isSignaled());
        reg.setSignaled(false);
        assertNotEquals(true, reg.isSignaled());

        var reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.isSignaled());
        reg2.setSignaled(true);
        assertNotEquals(false, reg2.isSignaled());
    }

    @Test
    public void testGetRegulator() {

    }

    @Test
    public void testSetRegulator() {

    }


}
