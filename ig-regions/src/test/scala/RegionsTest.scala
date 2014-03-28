import java.io.File
import ru.biocad.ig.alicont.algorithms.AlgorithmType
import ru.biocad.ig.regions.annotators.RegionAnnotator
import ru.biocad.ig.alicont.common.Scoring
import ru.biocad.ig.common.sequence.SequenceType
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created with IntelliJ IDEA.
 * User: mactep
 * Date: 01.11.13
 * Time: 15:36
 */
class RegionsTest extends FlatSpec with Matchers {
  val fasta = new File(getClass.getResource("/VDJH_train.fasta").toURI)
  val kabat = new File(getClass.getResource("/VDJH_train.kabat").toURI)
  val matrix = Scoring.loadMatrix("data/matrix/NUC4.4.txt")

  "Region Annotator" should "align right" in {
    val a = new RegionAnnotator("VDJH", SequenceType.NUCLEO, fasta, kabat, matrix = matrix)
    val query = "CAGGTGCAGCTGGTGCAGTCTGGGGCTGAGGTGAAGAAGCCTGGGGCCTCAGTGAAGGTCTCCTGCAAGGCTTCTGGATACACCTTCACCGGCTACTATATGCACTGGGTGCGACAGGCCCCTGGACAAGGGCTTGAGTGGATGGGACGGATCAACCCTAACAGTGGTGGCACAAACTATGCACAGAAGTTTCAGGGCAGGGTCACCAGTACCAGGGACACGTCCATCAGCACAGCCTACATGGAGCTGAGCAGGCTGAGATCTGACGACACGGTCGTGTATTACTGTGCGAGAGATAGTAGCTCCCACTATACCCTGAATACTTCCAGCACTGGGGCCAGGGCACCCTGGTCACCGTCTCCTCAG"

    val res = a.alignment(query)
    res.head.score should be (1830)
  }

  it should "annotate right" in {
    val a = new RegionAnnotator("VDJH", SequenceType.NUCLEO, fasta, kabat, matrix = matrix)
    val query = "CAGGTGCAGCTGGTGCAGTCTGGGGCTGAGGTGAAGAAGCCTGGGGCCTCAGTGAAGGTCTCCTGCAAGGCTTCTGGATACACCTTCACCGGCTACTATATGCACTGGGTGCGACAGGCCCCTGGACAAGGGCTTGAGTGGATGGGACGGATCAACCCTAACAGTGGTGGCACAAACTATGCACAGAAGTTTCAGGGCAGGGTCACCAGTACCAGGGACACGTCCATCAGCACAGCCTACATGGAGCTGAGCAGGCTGAGATCTGACGACACGGTCGTGTATTACTGTGCGAGAGATAGTAGCTCCCACTATACCCTGAATACTTCCAGCACTGGGGCCAGGGCACCCTGGTCACCGTCTCCTCAG"

    val res = a.regions(query).foldLeft("")((s, i) => s + i)
    res should be ("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111222222222222222222222222222222222222222222333333333333333333333333333333333333333333333333333444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444555555555555555555555555555555555555556666666666666666666666666666666666")
  }

  it should "annotate right (local)" in {
    val a = new RegionAnnotator("VDJH", SequenceType.NUCLEO, fasta, kabat, matrix = matrix, algo = AlgorithmType.LOCAL)
    val query = "GCAGCTGGTGCAGTCTGGGGCTGAGGTGAAGAAGCCTGGGGCCTCAGTGAAGGTCTCCTGCAAGGCTTCTGGATACACCTTCACCGGCTACTATATGCACTGGGTGCGACAGGCCCCTGGACAAGGGCTTGAGTGGATGGGACGGATCAACCCTAACAGTGGTGGCACAAACTATGCACAGAAGTTTCAGGGCAGGGTCACCAGTACCAGGGACACGTCCATCAGCACAGCCTACATGGAGCTGAGCAGGCTGAGATCTGACGACACGGTCGTGTATTACTGTGCGAGAGATAGTAGCTCCCACTATACCCTGAATACTTCCAGCACTGGGGCCAGGGCACCCTGGTCACCGTCTCCTCAG"

    val res = a.regions(query).foldLeft("")((s, i) => s + i)
    res should be ("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111222222222222222222222222222222222222222222333333333333333333333333333333333333333333333333333444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444555555555555555555555555555555555555556666666666666666666666666666666666")
  }
}
