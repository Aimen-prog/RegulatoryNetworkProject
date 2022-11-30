package model.file.serializers.gene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConstantRegulatoryGeneSerializerTest {


    @Test
    public void testSerialize() {
        ConstantRegulatoryGene gene = new ConstantRegulatoryGene("INS",5.1, true);
        ConstantRegulatoryGene gene2 = new ConstantRegulatoryGene("AGR",5.1, true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        assertThat( ConstantRegulatoryGeneSerializer.getInstance().serialize(gene,writer))
                .startsWith("ConstantRegulatoryGene ").endsWith(" true");
        assertThat( ConstantRegulatoryGeneSerializer.getInstance().serialize(gene2,writer))
                .isEqualTo("ConstantRegulatoryGene AGR 5.1 true");
        gene.setSignaled(false);
        assertThat( ConstantRegulatoryGeneSerializer.getInstance().serialize(gene2,writer))
                .isEqualTo("ConstantRegulatoryGene AGR 5.1 true");
    }

    @Test
    public void testDeserialize() {
        String line = "ConstantRegulatoryGene AGR 5.1 true";
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        ConstantRegulatoryGene output_gene =
                ConstantRegulatoryGeneSerializer.getInstance().deserialize(line,reader);
        ConstantRegulatoryGene expected_gene =
                new ConstantRegulatoryGene("AGR", 5.1, true);
        assertEquals(output_gene, expected_gene);
    }


}
