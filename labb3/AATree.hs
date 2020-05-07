{-# OPTIONS -Wall #-}

--------------------------------------------------------------------------------

module AATree (
  AATree,        -- type of AA search trees
  emptyTree,     -- AATree a
  get,           -- Ord a => a -> AATree a -> Maybe a
  insert,        -- Ord a => a -> AATree a -> AATree a
  inorder,       -- AATree a -> [a]
  remove,        -- Ord a => a -> AATree a -> AATree a
  size,          -- AATree a -> Int
  height,        -- AATree a -> Int
  checkTree      -- Ord a => AATree a -> Bool
 ) where

--------------------------------------------------------------------------------

-- AA search trees
--testTree :: AATree Int
--testTree = Node(Node (Node Nil 0 Nil 1) 1 (Node Nil 3 Nil 1) 2) 10 (Node Nil 15 Nil 1) 2     

data AATree a = 
    Nil
  | Node (AATree a) a (AATree a) Int
  deriving (Eq, Show, Read)

{-

returns an empty tree

-}

emptyTree :: AATree a
emptyTree = Nil

{-

the get function recursively searches the tree until it finds
the case where the input value is equal to the node value in the tree
if not found, return nothing
else return the value.
if still not found, check the left and right nodes to check which node
we're gonna continue searching recursively for the node with the input value to the function.

time complexity: O(log n)

-}

get :: Ord a => a -> AATree a -> Maybe a
get _ Nil = Nothing
get w (Node l value r _) 
  | w < value = get w l
  | w == value = Just value
  | w > value = get w r    
  |otherwise = Nothing
           
{-

skew: right rotation to fix invariant when inserting or deleting an element. 
In this case, we're only using skew to fix the invariant when inserting an element (if needed).
Used to preserve the balance in the tree.

time complexity: O(1)


-}

skew :: AATree a -> AATree a
skew (Node (Node ll lv lr lh) v r h)
  | lh == h = Node ll lv (Node lr v r h) lh
skew tree = tree

{-

split: left rotation and level increase to fix invariant when inserting or deleting an element.
In this case, we're only using split to fix the invariant when inserting an element (if needed).
Used to preserve the balance in the tree.

time complexity: O(1)

-}

split :: AATree a -> AATree a
split (Node l v1 (Node rl v2 (Node rrl v3 rrr h3) h2) h1)
  | h1 == h2 && h1 == h3 
  = Node (Node l v1 rl h1) v2 (Node rrl v3 rrr h1) (h1+1) 
split tree = tree


{-

Insert first has to check if there is already a node in the tree with the input value, if it exists then
we just return the input tree. If not, we call our function that takes care of the insertion.

time complexity: O(log n)

-}

insert :: Ord a =>  a -> AATree a -> AATree a
insert val tree
  |get val tree /= Nothing = tree
  |otherwise               = insert' val tree

insert' :: Ord a => a -> AATree a -> AATree a
-- if the tree is empty, just create a new tree with one root node with the value
insert' val Nil = (Node Nil val Nil 1)
-- if the tree has a right childnode but no left childnode
-- then we check if value of that node is greater than input value, then we skew and split the tree
-- and insert the value at that node. Else, we call insert on the right childnode
insert' val (Node Nil v r h)
  |val < v = split $ skew $ Node (Node Nil val Nil h) v r h 
  |otherwise = split $ skew $ Node Nil v (insert' val r) h
insert' val (Node l v Nil h)
-- Same as above, just that we instead do the opposite we do above.
  |val > v = split $ skew $ Node l v (Node Nil val Nil h) h 
  |otherwise = split $ skew $ Node (insert' val l) v Nil h
-- Here we have a tree with both at least one left and right childnode
-- The difference in this case is that we need to call insert on the node since we want to find the correct place for the value to be inserted
-- and we don't know if the node we're currently looking at has children or not, therefore we recursively call insert'.
insert' val (Node l v r h)
  | val < v  = split $ skew $ Node (insert' val l) v r h
  | val > v = split $ skew $ Node l v (insert' val r) h
insert' _ _ = error "error"


{-

inorder recursively searches through the tree and appends all values
together into a list in a sorted manner.
time complexity: O(n)  

-}

inorder :: AATree a -> [a]
inorder Nil = []
inorder (Node l v r _) = inorder l ++ [v] ++ inorder r


{-

size returns the amount of nodes in the tree
time complexity: O(n)

-}

size :: AATree a -> Int
size Nil = 0
size (Node l _ r _) = size l + size r + 1

{-

height checks through every node and compares height
returns total height of the tree
time complexity: O(n)

-}

height :: AATree a -> Int
height Nil = 0
height (Node l _ r _)
 |height l > height r = height l + 1
 |otherwise = height r + 1


--------------------------------------------------------------------------------
-- Optional function

remove :: Ord a => a -> AATree a -> AATree a
remove = error "remove not implemented"

--------------------------------------------------------------------------------
-- Check that an AA tree is ordered and obeys the AA invariants
-- time complexity: 

checkTree :: Ord a => AATree a -> Bool
checkTree root =
  isSorted (inorder root) &&
  all checkLevels (nodes root)
  where
    nodes x
      | isEmpty x = []
      | otherwise = x:nodes (leftSub x) ++ nodes (rightSub x)

-- True if the given list is ordered
-- time complexity: O(n)

isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x:y:xs) = x <= y && isSorted(y:xs)

-- Check if the invariant is true for a single AA node
-- You may want to write this as a conjunction e.g.
--   checkLevels node =
--     leftChildOK node &&
--     rightChildOK node &&
--     rightGrandchildOK node
-- where each conjunct checks one aspect of the invariant

{-

checks if every node in the tree fulfills the tree invariant for height
time complexity: 

-}
checkLevels :: AATree a -> Bool
checkLevels Nil = True
checkLevels tree = (leftOK tree) && (rightOK tree)

{-

checks if the input tree is empty (i.e nil)
time complexity: O(1)

-}

isEmpty :: AATree a -> Bool
isEmpty Nil = True
isEmpty _   = False


{-

  leftSub returns the left tree from a given tree
  time complexity: O(1)

-}

leftSub :: AATree a -> AATree a
leftSub Nil = emptyTree
leftSub (Node l _ _ _) = l


{-

  rightsub returns the right tree from a given tree
  time complexity: O(1)

-}

rightSub :: AATree a -> AATree a
rightSub Nil = emptyTree
rightSub (Node _ _ r _) = r

{-

leftOk checks if the left node's height is not equal to its parent,
returns true if it is not, false it is isn't
time complexity: O(1)

-}

leftOK :: AATree a -> Bool
leftOK (Node (Node _ _ _ h1) _ _ h2) = 
  ((h1 /= h2) && (h1 <= h2) && ((h2 - h1) <= 1))
leftOK _ = True

{-

time complexity: O(1)

-}

rightOK :: AATree a -> Bool
rightOK (Node _ _ (Node _ _ (Node _ _ _ h1) h2) h3) = 
  ((not (h1 == h2 && h1 == h3)) && (h1 <= h2) && ((h2 - h1) <= 1))
rightOK (Node _ _ (Node _ _ _ h1) h2) = 
  ((h1 <= h2) && ((h2 - h1) <= 1))   
rightOK _ = True


--------------------------------------------------------------------------------

