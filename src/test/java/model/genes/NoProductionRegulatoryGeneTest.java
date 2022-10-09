package model.genes;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoProductionRegulatoryGeneTest {
  public static final double EPSILON = 0.0000001;

  private ConstantRegulatoryGene geneX;
  private ConstantRegulatoryGene geneY;
  private ConstantRegulatoryGene geneZ;

  @BeforeEach
  void initializeGenes(){
    geneX = new ConstantRegulatoryGene("X", 1, true);
    geneY = new ConstantRegulatoryGene("Y", 2, false);
    geneZ = new ConstantRegulatoryGene("Z", 3, false);
  }

  @Test
  void testGetProteinConcentration_afterConstruction(){
    assertThat(geneX.getProteinConcentration()).isCloseTo(1, within(EPSILON));
    assertThat(geneY.getProteinConcentration()).isCloseTo(2, within(EPSILON));
    assertThat(geneZ.getProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetInitialProteinConcentration_afterConstruction(){
    assertThat(geneX.getInitialProteinConcentration()).isCloseTo(1, within(EPSILON));
    assertThat(geneY.getInitialProteinConcentration()).isCloseTo(2, within(EPSILON));
    assertThat(geneZ.getInitialProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetInitialProteinConcentration_afterSettingProteinConcentration() {
    geneX.setProteinConcentration(11);
    assertThat(geneX.getInitialProteinConcentration()).isCloseTo(11, within(EPSILON));
    geneY.setProteinConcentration(10);
    assertThat(geneY.getInitialProteinConcentration()).isCloseTo(2, within(EPSILON));
    geneZ.setProteinConcentration(2);
    assertThat(geneZ.getInitialProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetProteinConcentration_afterSettingProteinConcentration() {
    geneX.setProteinConcentration(11);
    assertThat(geneX.getProteinConcentration()).isCloseTo(11, within(EPSILON));
    geneY.setProteinConcentration(10);
    assertThat(geneY.getProteinConcentration()).isCloseTo(10, within(EPSILON));
    geneZ.setProteinConcentration(21);
    assertThat(geneZ.getProteinConcentration()).isCloseTo(21, within(EPSILON));
  }

  @Test
  void testGetName(){
    assertThat(geneX.toString()).isEqualTo("X");
    assertThat(geneY.toString()).isEqualTo("Y");
    assertThat(geneZ.toString()).isEqualTo("Z");
  }

  @Test
  void testIsSignaled(){
    assertThat(geneX.isSignaled()).isTrue();
    assertThat(geneY.isSignaled()).isFalse();
    assertThat(geneZ.isSignaled()).isFalse();
  }

  @Test
  void testSetSignaled() {
    assertThat(geneX.isSignaled()).isTrue();
    geneX.setSignaled(false);
    assertThat(geneX.isSignaled()).isFalse();

    assertThat(geneY.isSignaled()).isFalse();
    geneY.setSignaled(true);
    assertThat(geneY.isSignaled()).isTrue();

    assertThat(geneZ.isSignaled()).isFalse();
    geneX.setSignaled(true);
    assertThat(geneX.isSignaled()).isTrue();
  }


}

