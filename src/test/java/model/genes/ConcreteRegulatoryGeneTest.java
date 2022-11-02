package model.genes;

import model.regulators.AlwaysOnRegulator;
import model.regulators.Regulator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteRegulatoryGeneTest {
    @Test
    public void testSetProteinConcentration() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        reg.setProteinConcentration(reg.getInitialProteinConcentration());
        assertEquals(10.0,reg.getProteinConcentration());
        reg.setProteinConcentration(15.0);
        assertEquals(15.0,reg.getProteinConcentration());
        assertNotEquals(10.0,reg.getProteinConcentration());
        reg.setProteinConcentration(-1.0);
        assertEquals(0.,reg.getProteinConcentration());
    }
    @Test
    public void testGetProteinConcentration() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        assertEquals(0.0,reg.getProteinConcentration());
        reg.setProteinConcentration(reg.getInitialProteinConcentration());
        assertEquals(10.0,reg.getProteinConcentration());
        reg.setProteinConcentration(15.0);
        assertEquals(15.0,reg.getProteinConcentration());
        assertNotEquals(10.0,reg.getProteinConcentration());
    }


    @Test
    public void testGetInitialProteinConcentration(){
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
        10.1, true);
        assertEquals(10.1, reg.getInitialProteinConcentration());
        assertFalse(reg.getInitialProteinConcentration()==0.1);
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getInitialProteinConcentration()==8.);
    }


    @Test
    public void testGetName() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertThat(reg.getName()).startsWith("IN").endsWith("S");
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertThat(reg2.getName()).isEqualTo ("P53");

    }
    @Test
    public void testToString() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.0, true);
        assertThat(reg.toString()).startsWith("RegulatoryGene{name= INS").endsWith("true}");
        reg.setSignaled(false);
        assertThat(reg.toString()).startsWith("RegulatoryGene{name= INS").endsWith("false}");
    }

    @Test
    public void testIsSignaled() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.isSignaled());
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",5.1, 0.15,
                12.1, true);
        assertTrue(reg2.isSignaled());
    }

    @Test
    public void testSetSignaled() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.isSignaled());
        reg.setSignaled(false);
        assertNotEquals(true, reg.isSignaled());

        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.isSignaled());
        reg2.setSignaled(true);
        assertNotEquals(false, reg2.isSignaled());
    }

    @Test
    public void testGetMaximalProduction(){
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertEquals(5.1, reg.getMaximalProduction());
        assertFalse(reg.getMaximalProduction()==0.1);
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getMaximalProduction()==5.1);
    }

    @Test
    public void testGetDegradationRate(){
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(reg.getDegradationRate()==0.1);
        assertFalse(reg.getDegradationRate()==5.1);
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(reg2.getDegradationRate()==0.1);
    }
    @Test
    public void testSetRegulator() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        reg.setRegulator(new AlwaysOnRegulator());
        assertNotNull(reg.getRegulator());
        assertEquals(1.0, reg.getRegulator().inputFunction());
    }

    @Test
    public void testGetRegulator() {
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        assertNull(reg.getRegulator());
        reg.setRegulator(new AlwaysOnRegulator());
        assertNotNull(reg.getRegulator());
        assertEquals(1.0, reg.getRegulator().inputFunction());
    }
    @Test
    public void testUpdate(){
        RegulatoryGene reg = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        reg.setProteinConcentration(reg.getInitialProteinConcentration());
        reg.update(0.5);
        assertEquals(10.5, reg.getProteinConcentration());
        reg.setRegulator(new AlwaysOnRegulator());
        reg.update(0.5);
        assertEquals(10.875,reg.getProteinConcentration());

    }

}
