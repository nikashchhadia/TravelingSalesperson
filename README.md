# traveling-salesperson: solutions to the traveling salesperson problem

-----------

PROJECT TITLE: Traveling Salesperson Problem

-----------
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find an optimal solution to the Traveling Salesperson Problem

-----------
DATE: 12/10/21

-----------
AUTHOR: Nikash Chhadia

-----------
ALGORITHM DESCRIPTIONS:

-----------
insertNearest(): This method adds a Point into the list immediately after the point it is closest to. My algorithm for this method first checks if the tour is empty, and adds the new point to the front if it is. Otherwise, it will move through the linked list and check if each point in the list's node is closer to the new point than the closest so far. If it is, it will store that node as the new closest one so far. After getting to the last node, it will insert the new point as a node right after the node that was stored as the closest to it. Finally, size is incremented. The Big-O complexity of this algorithm is O(N^2), since it parses through N nodes for N different new points, making it N times N, or N^2.

-----------    
insertSmallest(): This method adds a point into the list at the location that causes the total distance of the Tour to increase the least. My algorithm for this method first checks if the tour has either just one or zero points, and will add the new point after or as the front node, respectively. Otherwise, it will move through the linked list and check if the increase in distance when the new point is placed between two points is less than the lowest so far. It does this by adding the distances between the first & new point and the new point & second point, and subtracting the distance between just the first and second points. If this is the new lowest increase in 
distance, it will store the current node as the smallest so far. After getting to the last node, it will insert the new point as a node right after the node that was stored to be where the smallest distance increase would occur. Finally, size is incremented. The Big-O complexity of this algorithm is also O(N^2), since it similarly parses through N nodes for N different new points, making it N times N, or N^2.
