package model.genes;

import model.regulators.AlwaysOnRegulator;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteRegulatoryGeneTest {
    @Test
    public void testSetProteinConcentration() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        assertEquals(10.0,gene.getProteinConcentration());
        gene.setProteinConcentration(15.0);
        assertEquals(15.0,gene.getProteinConcentration());
        assertNotEquals(10.0,gene.getProteinConcentration());
    }
    @Test
    public void testGetProteinConcentration() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        assertEquals(10.0,gene.getProteinConcentration());
        gene.setProteinConcentration(8.0);
        assertEquals(8.0,gene.getProteinConcentration());
        gene.setProteinConcentration(15.0);
        assertEquals(15.0,gene.getProteinConcentration());
        assertNotEquals(8.0,gene.getProteinConcentration());
    }


    @Test
    public void testGetInitialProteinConcentration(){
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
        10.1, true);
        assertEquals(10.1, gene.getInitialProteinConcentration());
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertNotEquals(10.1, gene2.getInitialProteinConcentration(), 0.0);
        assertEquals(5.1, gene2.getInitialProteinConcentration());
    }


    @Test
    public void testGetName() {
        RegulatoryGene regene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertThat(regene.getName()).startsWith("IN").endsWith("S");
        RegulatoryGene regene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertThat(regene2.getName()).isEqualTo ("P53");

    }
    @Test
    public void testToString() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.0, true);
        assertThat(gene.toString()).startsWith("RegulatoryGene{name= INS").endsWith("true}");
        gene.setSignaled(false);
        assertThat(gene.toString()).startsWith("RegulatoryGene{name= INS").endsWith("false}");
    }

    @Test
    public void testIsSignaled() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(gene.isSignaled());
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",5.1, 0.15,
                12.1, true);
        assertTrue(gene2.isSignaled());
    }

    @Test
    public void testSetSignaled() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertTrue(gene.isSignaled());
        gene.setSignaled(false);
        assertNotEquals(true, gene.isSignaled());

        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertFalse(gene2.isSignaled());
        gene2.setSignaled(true);
        assertNotEquals(false, gene2.isSignaled());
    }

    @Test
    public void testGetMaximalProduction(){
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertEquals(5.1, gene.getMaximalProduction());
        assertNotEquals(0.1, gene.getMaximalProduction(), 0.0);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertNotEquals(5.1, gene2.getMaximalProduction(), 0.0);
    }

    @Test
    public void testGetDegradationRate(){
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",5.1, 0.1,
                10.1, true);
        assertEquals(0.1, gene.getDegradationRate());
        assertNotEquals(5.1, gene.getDegradationRate(), 0.0);
        RegulatoryGene reg2 = new ConcreteRegulatoryGene("P53",8., 0.15,
                5.1, false);
        assertNotEquals(0.1, reg2.getDegradationRate(), 0.0);
    }
    @Test
    public void testSetRegulator() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("RNS",6, 0.5,
                10.0, true);
        RegulatoryGene gene3 = new ConcreteRegulatoryGene("FS",16, 1.5,
                10.0, true);
        gene.setRegulator(new AlwaysOnRegulator());
        assertNotNull(gene.getRegulator());
        assertEquals(1.0, gene.getRegulator().inputFunction());
        assertNull(gene2.getRegulator());
        gene2.setRegulator(new BooleanActivator(10,gene3));
        assertNotNull(gene2.getRegulator());

    }

    @Test
    public void testGetRegulator() {
        RegulatoryGene gene = new ConcreteRegulatoryGene("INS",6, 0.5,
                10.0, true);
        RegulatoryGene gene2 = new ConcreteRegulatoryGene("RNS",6, 0.5,
                10.0, true);
        RegulatoryGene gene3 = new ConcreteRegulatoryGene("FS",16, 1.5,
                10.0, true);
        assertNull(gene.getRegulator());
        gene.setRegulator(new AlwaysOnRegulator());
        assertNotNull(gene.getRegulator());
        assertEquals(1.0, gene.getRegulator().inputFunction());
        gene2.setRegulator(new BooleanRepressor(10,gene3));
        assertEquals("BooleanRepressor",gene2.getRegulator().getClass().getSimpleName());
        assertEquals("10.0 FS",gene2.getRegulator().getInfo());

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
