package ru.biocad.ig.alicont.conts.simple

import ru.biocad.ig.alicont.algorithms.simple.SemiglobalAlignment
import ru.biocad.ig.alicont.conts.SimpleAlicont

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 27.11.13
 * Time: 23:37
 */
class AlicontSemiglobal(maxheight : Int, query : String, gap : Double, score_matrix : Array[Array[Double]])
  extends SimpleAlicont(maxheight : Int, query : String, gap : Double, score_matrix : Array[Array[Double]]) {

  def push(s : String) : Unit = {
    _strings.push(s)
    SemiglobalAlignment.extendMatrix(s, _query, _gap, _score, _matrix)
  }

  def alignment() : (Double, (String, String)) = {
    SemiglobalAlignment.traceback(target, _query, _gap, _score, _matrix)
  }
}