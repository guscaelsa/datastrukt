module Main where
import System.Environment
import Route
--import RouteGUI
import Graph

main :: IO ()
main = do
  args <- getArgs
  if length args /= 2
    then error ">:("
	else return ()

  Right stops <- readStops (args !! 0)
  Right lines <- readLines (args !! 1)
  let graph = toGraph stops lines
  putStrLn "asd"

-- toGraph = addLines l s g = addLines l (addStops s empty)
toGraph = undefined

--addStops :: [Stop] -> Graph id weight -> Graph id weight
addStops [] g = g
addStops (x:xs) g = addStops xs (addNode (name x) g)

--addLines :: [LineTable] -> Graph id weight -> Graph id weight
addLines [] g = g
addLines (ln:lns) g = addLines lns (go ln g)
  where
  --go :: LineTable -> Graph id weight -> Graph id weight
  go ln g
    | xs == [] = edge
    | otherwise = go ln' edge
    where
    x:y:xs = stops ln
    ln' = LineTable {lineNumber = lineNumber ln, stops = (y:xs)}
    edge = addEdge (stopName x) ((fromIntegral . time) y) (stopName y) g

