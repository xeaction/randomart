randomart
=========

Scala implementation of the OpenSSH randomart algorithm based on Aaron Toponce's [blog post](https://pthree.org/2013/05/30/openssh-keys-and-the-drunken-bishop/)

Usage:

```scala
import org.xeaction.randomart._

val randomArt = RandomArt()
val art = randomArt.generate("d4:d3:fd:ca:c4:d3:e9:94:97:cc:52:21:3b:e4:ba:e9")

art.foreach(println)
```

outputs

```
             o . 
         . .o.o .
        . o .+.. 
       .   ...=o+
        S  . .+B+
            oo+o.
           o  o. 
          .      
           E     
```
