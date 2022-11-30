package model.file.serializers.gene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteRegulatoryGeneSerializerTest {


    @Test
    public void testSerialize() {
        ConcreteRegulatoryGene gene = new ConcreteRegulatoryGene("INS", 5.1, 0.1,
                10.1, true);
        ConcreteRegulatoryGene gene2 = new ConcreteRegulatoryGene("AGR", 5.1, 0.1,
                10.1, true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        assertThat(ConcreteRegulatoryGeneSerializer.getInstance().serialize(gene, writer))
                .startsWith("ConcreteRegulatoryGene ").endsWith(" true");
        assertThat(ConcreteRegulatoryGeneSerializer.getInstance().serialize(gene2, writer))
                .isEqualTo("ConcreteRegulatoryGene AGR 10.1 5.1 0.1 true");
        gene.setSignaled(false);
        assertThat(ConcreteRegulatoryGeneSerializer.getInstance().serialize(gene2, writer))
                .isEqualTo("ConcreteRegulatoryGene AGR 10.1 5.1 0.1 true");
    }


    @Test
    public void testDeserialize() {
        String line = "ConcreteRegulatoryGene AGR 10.1 5.1 0.1 true";
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        ConcreteRegulatoryGene output_gene = ConcreteRegulatoryGeneSerializer.getInstance().deserialize(line,reader);
        ConcreteRegulatoryGene expected_gene = new ConcreteRegulatoryGene("AGR", 5.1, 0.1,
                10.1, true);
        assertEquals(output_gene, expected_gene);
    }
}