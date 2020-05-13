module ScratchGraph where

import Graph


addEdge' a w b g = addEdge b w a (addEdge a w b g)

testGraph =
  addEdge' 4 0.4 6 $
  addEdge' 3 0.2 4 $
  addEdge' 3 0.3 2 $
  addEdge' 4 0.3 5 $
  addEdge' 5 0.2 2 $
  addEdge' 1 0.1 5 $
  addEdge' 1 0.1 2 $
  addNode 1 $
  addNode 2 $
  addNode 3 $
  addNode 4 $
  addNode 5 $
  addNode 6 $
  empty
