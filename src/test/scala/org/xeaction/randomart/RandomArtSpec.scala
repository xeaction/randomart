package org.xeaction.randomart

import org.scalatest.WordSpec
import org.scalatest.Matchers

class RandomArtSpec extends WordSpec with Matchers {
  
  "A RandomArt" when {
    "generating art" should {
      "return 9 rows" in {
        val ra = RandomArt()
        val res = ra.generate("d4:d3:fd:ca:c4:d3:e9:94:97:cc:52:21:3b:e4:ba:e9")
        
        res.length should be (9)
      }
      "have 17 columns in each row" in {
        val ra = RandomArt()
        val res = ra.generate("d4:d3:fd:ca:c4:d3:e9:94:97:cc:52:21:3b:e4:ba:e9")
        res.forall { _.length == 17 } should be (true)
      }
    }
  }
}