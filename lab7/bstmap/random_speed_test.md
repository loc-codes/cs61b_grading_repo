## RANDOM SPEED TEST
Enter # strings to insert into the maps: 10
class bstmap.ULLMap: 0.00 sec
class bstmap.BSTMap: 0.00 sec
Java's Built-in TreeMap: 0.00 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n)y

Enter # strings to insert into the maps: 100
class bstmap.ULLMap: 0.00 sec
class bstmap.BSTMap: 0.01 sec
Java's Built-in TreeMap: 0.00 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n)y

Enter # strings to insert into the maps: 1000
class bstmap.ULLMap: 0.04 sec
class bstmap.BSTMap: 0.00 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n)y

Enter # strings to insert into the maps: 10000
class bstmap.ULLMap: 0.38 sec
class bstmap.BSTMap: 0.01 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n)y

Enter # strings to insert into the maps: 100000
--Stack Overflow -- couldn't add 100000 strings of length 15.
class bstmap.BSTMap: 0.06 sec
Java's Built-in TreeMap: 0.05 sec
Java's Built-in HashMap: 0.04 sec
Would you like to try more timed-tests? (y/n)y


Enter # strings to insert into the maps: 30000
class bstmap.ULLMap: 3.04 sec
class bstmap.BSTMap: 0.01 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.02 sec

Enter # strings to insert into the maps: 40000
--Stack Overflow -- couldn't add 40000 strings of length 15.
class bstmap.BSTMap: 0.02 sec
Java's Built-in TreeMap: 0.02 sec
Java's Built-in HashMap: 0.01 sec
Would you like to try more timed-tests? (y/n)y

## IN ORDER SPEED TESTS
Enter # strings to insert into the maps: 10
class bstmap.ULLMap: 0.00 sec
class bstmap.BSTMap: 0.00 sec
Java's Built-in TreeMap: 0.00 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n): y

Enter # strings to insert into the maps: 100
class bstmap.ULLMap: 0.01 sec
class bstmap.BSTMap: 0.01 sec
Java's Built-in TreeMap: 0.00 sec
Java's Built-in HashMap: 0.01 sec
Would you like to try more timed-tests? (y/n): y

Enter # strings to insert into the maps: 1000
class bstmap.ULLMap: 0.04 sec
class bstmap.BSTMap: 0.02 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n): y

Enter # strings to insert into the maps: 10000
class bstmap.ULLMap: 0.64 sec
class bstmap.BSTMap: 0.52 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.00 sec
Would you like to try more timed-tests? (y/n): y

Enter # strings to insert into the maps: 100000
--Stack Overflow -- couldn't add 100000 strings.
--Stack Overflow -- couldn't add 100000 strings.
Java's Built-in TreeMap: 0.07 sec
Java's Built-in HashMap: 0.04 sec
Would you like to try more timed-tests? (y/n): y

Enter # strings to insert into the maps: 30000
class bstmap.ULLMap: 7.68 sec
class bstmap.BSTMap: 2.84 sec
Java's Built-in TreeMap: 0.01 sec
Java's Built-in HashMap: 0.01 sec

BSTMap is more performant than ULLMap for In Order Insertion, but less so than in Random Insertion
Built In Tree map has some optimisation that handles In Order Insertion performance degradation that BSTMap doesn't
Might be because TreeMap a red-black tree its more performant?