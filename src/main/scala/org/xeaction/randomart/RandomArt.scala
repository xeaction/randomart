package org.xeaction.randomart

import scala.annotation.tailrec

object RandomArt {
  def apply() = new RandomArt
  def apply(x: Int, y: Int) = {
    require(x > 0, "X must be greater than 0")
    require(y > 0, "Y must be greater than 0")
    require(x % 2 == 1, "X must be odd")
    require(y % 2 == 1, "Y must be odd")
    new RandomArt(x, y)
  }
}

class RandomArt(xSize: Int = 17, ySize: Int = 9, withBorder: Boolean = true) {
    
  val initialRoom = List.fill(xSize * ySize)(0)
  
  val initialX = (xSize - 1) / 2
  val initialY = (ySize - 1) / 2
  
  val charMap = List(' ', '.', 'o', '+', '=', '*', 'B', 'O', 'X', '@', '%', '&', '#', '/', '^', 'S', 'E')
  
  def generate(hex: String): Seq[String] = {
    @tailrec
    def genRecursive(binaryPairs: List[String], room: List[Int], x: Int, y: Int): List[Int] = {
      binaryPairs match {
        case List() => room.updated(p(initialX, initialY), 15).updated(p(x, y), 16)
        case head :: tail => {
          val (newX, newY) = move(head, x, y)
          val newPos = p(newX, newY)
          val posVal = room(newPos)
          val newRoom = room.updated(newPos, posVal + 1)
          genRecursive(tail, newRoom, newX, newY)
        }
      }
    }
    room2display(genRecursive(hex2BinaryPairs(hex), initialRoom, initialX, initialY))
  }
  
  private[xeaction] def room2display(room: List[Int]): List[String] = {
    room.sliding(xSize, xSize).map(intList => intList.map(charMap).mkString).toList
  }
  
  private[xeaction] def move(binaryPair: String, x: Int, y: Int): (Int, Int) = {
    val newX = x + (if (binaryPair(1) == '0') -1 else 1)
    val newY = y + (if (binaryPair(0) == '0') -1 else 1)
    (if (newX == xSize || newX < 0) x else newX, if (newY == ySize || newY < 0) y else newY)
  }
  
  private[xeaction] def p(x: Int, y: Int) = x + xSize * y
  
  private[xeaction] def hex2BinaryPairs(hex: String): List[String] = {
    val bytes = hex.split(":").map(Integer.parseInt(_, 16).toByte)
    val binaryStrs = bytes.map(byte => "%8s".format((byte & 0xFF).toBinaryString).replace(' ', '0'))
    binaryStrs.toList.flatMap(_.sliding(2, 2).toList.reverse)
  }
}