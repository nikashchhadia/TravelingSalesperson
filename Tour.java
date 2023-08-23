import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem.
 * 
 * @author  Nikash Chhadia
 * @version 12/10/2021
 */

public class Tour implements TourInterface
{
    //instance variables
    private int size;
    private ListNode front;

    //constructor
    public Tour()
    {
        size = 0;
        front = null;
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return size;
    }

    //append Point p to the end of the list
    public void add(Point p)
    {
        if(size == 0) //if tour is empty, make the new point the front of the tour
            front = new ListNode(p);
        else
        {
            ListNode current = front; //track current node while cycling through tour
            while(current.next != null) //keep going until the last node in tour
            {
                current = current.next;
            }
            current.next = new ListNode(p); //set the last node in tour's next variable to the new point
        }
        size++; //increment size since a new point is added to the tour
    }

    //print every node in the list 
    public void print()
    {
        if(size == 0) //doesn't print anything if tour is empty
            return;
        ListNode current = front; //track current node while cycling through tour
        while(current.next != null) //keep going until the last node in tour
        {
            System.out.println(current.data); //print each node while cycling through tour
            current = current.next;
        }
        System.out.println(current.data); //print the last node of the tour
    }

    //draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        if(size == 0) //doesn't draw anything if tour is empty
            return;
        ListNode current = front; //track current node while cycling through tour
        while(current.next != null) //keep going until the last node in tour
        {
            g.fillOval((int)(current.data.getX())-2,(int)(current.data.getY())-2,5,5); //draw each node while cycling through tour
            g.drawLine((int)(current.data.getX()),(int)(current.data.getY()),
                (int)(current.next.data.getX()),(int)(current.next.data.getY())); //connect each node while cycling through tour
            current = current.next;
        }
        g.fillOval((int)(current.data.getX())-2,(int)(current.data.getY())-2,5,5); //draw last node
        g.drawLine((int)(current.data.getX()),(int)(current.data.getY()),
            (int)(front.data.getX()),(int)(front.data.getY())); //connect last to first node
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        double sum = 0.0; //variable to track total distance
        if(size <= 1) //distance is zero with 1 or less point
            return sum;
        ListNode current = front; //track current node while cycling through tour
        while(current.next != null) //keep going until the last node in tour
        {
            sum += current.data.distance(current.next.data); //add distance between nodes while cycling through tour
            current = current.next;
        }
        sum += current.data.distance(front.data); //add distance between first and last node
        return sum;
    }

    //add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {
        if(size == 0) //if tour is empty, make the new point the front of the tour
            front = new ListNode(p);
        else
        {
            ListNode current = front; //track current node while cycling through tour
            ListNode nearest = front; //track node closest to p while cycling through tour
            while(current.next != null) //keep going until the last node in tour
            {
                if(current.data.distance(p) < nearest.data.distance(p)) //check if current node is closer to p than the nearest one so far
                    nearest = current; //if yes, set current node to the nearest one so far
                current = current.next;
            }
            if(current.data.distance(p) < nearest.data.distance(p)) //check last node to see if it's closer
                nearest = current; //if yes, set last node to the nearest one
            nearest.next = new ListNode(p, nearest.next); //inserts the new point right after its nearest point in the tour
        }
        size++; //increment size since a new point is added to the tour
    }

    //add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    {
        if(size == 0) //if tour is empty, make the new point the front of the tour
        {
            front = new ListNode(p);
        }
        else if(size == 1) //if tour has just one point, make the new point linked to the front
        {
            front.next = new ListNode(p);
        }
        else
        {
            ListNode current = front; //track current node while cycling through tour
            ListNode smallest = front; //track node where linking p will result in the least tour distance increase
            while(current.next != null) //keep going until the last node in tour
            {
                if(p.distance(current.data) + p.distance(current.next.data) - current.data.distance(current.next.data)
                < p.distance(smallest.data) + p.distance(smallest.next.data) - smallest.data.distance(smallest.next.data))
                //check if current node adds less to total tour distance than the smallest so far by finding 
                //difference between orginal distance and distance if p were to be inserted after the current node,
                //and comparing it to the same difference but with the smallest so far
                    smallest = current; //if yes, set current node to the smallest one so far
                current = current.next;
            }
            if(p.distance(current.data) + p.distance(front.data) - current.data.distance(front.data)
            < p.distance(smallest.data) + p.distance(smallest.next.data) - smallest.data.distance(smallest.next.data))
            //check in between last and first node to see if it adds less
                smallest = current; //if yes, set last node to the smallest one
            smallest.next = new ListNode(p, smallest.next); //inserts new point right after node where it would add the least distance to the tour
        }
        size++;
    }

    //This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }
    }
}