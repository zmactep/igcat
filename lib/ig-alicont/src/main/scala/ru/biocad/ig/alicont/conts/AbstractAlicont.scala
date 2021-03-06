package ru.biocad.ig.alicont.conts

import scala.collection.mutable
import ru.biocad.ig.alicont.common.Matrix

/**
 * Created with IntelliJ IDEA.
 * User: pavel
 * Date: 27.11.13
 * Time: 22:53
 */
abstract class AbstractAlicont(maxheight : Int, query_string : String, score_matrix : Array[Array[Double]]) {
  protected val _query   = query_string
  protected val _score   = score_matrix
  protected val _strings = new mutable.Stack[String]()
  protected val _matrix  = new Matrix(query_string.size, maxheight)

  def push(s : String) : Unit
  def pop() : Unit
  def alignment() : (Double, (String, String))
  def target : String = _strings.reverse.mkString("")

  def query : String = _query
}
