package union_find

/**
  * Created by Boris Mitioglov on 27/09/2018.
  */
object UnionFindCLient {
  def main(args: Array[String]): Unit = {
    val weightedQuickUnionFindStructure = new WeightedQuickUnionFindStructure(6)
    weightedQuickUnionFindStructure.union(0, 1)
    weightedQuickUnionFindStructure.union(2, 0)
    weightedQuickUnionFindStructure.union(5, 3)
    weightedQuickUnionFindStructure.union(0, 5)
    weightedQuickUnionFindStructure.union(5, 4)
    weightedQuickUnionFindStructure.printIndices()
    weightedQuickUnionFindStructure.printObjectArray()
    weightedQuickUnionFindStructure.printSizeArray()
    println("Max elem in group = " + weightedQuickUnionFindStructure.findMaxElem(0))
    println(weightedQuickUnionFindStructure.isAllConnected)
    println(weightedQuickUnionFindStructure.connected(4, 0))

  }
}

class WeightedQuickUnionFindStructure(n: Int) extends QuickUnionFindStructure(n){
  private val sizeArray: Array[Int] = Array.ofDim[Int](n)
  private val maxArray: Array[Int] = Array.ofDim[Int](n)

  for (i <- 0 until n) {
    sizeArray(i) = 1
    maxArray(i) = i
  }

  override def union(p: Int, q: Int): Unit = {
    val i = root(p)
    val j = root(q)
    if (!connected(p, q)) unionCount += 1
    val unitedTreeSize = sizeArray(i) + sizeArray(j)
    if (sizeArray(j) >= sizeArray(i)) {
      sizeArray(j) = unitedTreeSize
      id(i) = j
      if (p > maxArray(j)) maxArray(j) = p
    } else {
      sizeArray(i) = unitedTreeSize
      id(j) = i
      if (q > maxArray(i)) maxArray(i) = q
    }
  }

  override def root(i: Int): Int = {
    var x: Int = i
    while (x != id(x)) {
      x = id(x)
    }
    //improvement
    if (x != id(i)) sizeArray(id(i)) -= 1
    id(i) = x
    //
    x
  }

  def printSizeArray(): Unit = {
    print ("Size Array:\t\t ")
    for (i <- sizeArray.indices) {
      print(sizeArray(i) + " ")
    }
    println()
  }

  def printMaxArray(): Unit = {
    print ("Max Array:\t\t ")
    for (i <- maxArray.indices) {
      print(maxArray(i) + " ")
    }
    println()
  }

  def findMaxElem(i: Int): Int = {
    maxArray(root(i))
  }

}

class QuickUnionFindStructure(n: Int) {
  protected val id: Array[Int] = Array.ofDim[Int](n)
  protected var unionCount: Int = 0

  //init
  for (i <- 0 until n) {
    id(i) = i
  }

  def root(i: Int): Int = {
    var x: Int = i
    while (x != id(x)) {
      x = id(x)
    }
    x
  }

  def connected(p: Int, q: Int): Boolean = {
    root(p) == root(q)
  }

  def union(p: Int, q: Int): Unit = {
    val i = root(p)
    val j = root(q)
    if (!connected(p, q)) unionCount += 1
    id(i) = j
  }

  def isAllConnected: Boolean = {
    unionCount == id.length - 1
  }

  def printObjectArray(): Unit = {
    print ("Object Array:\t ")
    for (i <- id.indices) {
      print(id(i) + " ")
    }
    println()
  }

  def printIndices(): Unit = {
    print ("Indices:\t\t ")
    for (i <- 0 until n) {
      print(i + " ")
    }
    println()
  }
}
