# Spanning tree protocol (STP) simulation

This program simulates how the spanning tree protocol determines the root bridge, root ports, designated ports and blocked ports. Briges are represented as nodes in a graph and connections are represented as edges. The heart of the program is the [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).

# Example

The following graph is given as the input to the program.

![Graph Image](http://shamalsandeep.com/img/stp_graph.jpg)

The output is 

```
A(NON-ROOT)
--> B[DP](4)--> C[BP](5)--> D[RP](1)

B(NON-ROOT)
--> A[BP](4)--> D[RP](3)

C(NON-ROOT)
--> A[DP](5)--> D[DP](2)--> F[DP](8)--> E[RP](5)

D(NON-ROOT)
--> A[DP](1)--> B[DP](3)--> C[RP](2)--> F[DP](6)

E(NON-ROOT)
--> C[DP](5)--> G[RP](1)

F(NON-ROOT)
--> C[BP](8)--> D[BP](6)--> G[RP](10)

G(ROOT)
--> E[DP](1)--> F[DP](10)
```

# References

Dijkstra's algorithm used in the program is a modified version of the implementation found in the article [Dijkstra’s shortest path algorithm in Java using PriorityQueue](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/) by Akashkumar17
