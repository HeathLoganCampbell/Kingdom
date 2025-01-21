package dev.cobblesword.classic.pathfind;

import dev.cobblesword.classic.map.Location;
import dev.cobblesword.classic.map.Map;
import dev.cobblesword.classic.map.Tile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PathFind
{
    @Getter
    private int goalX, goalY;
    @Getter
    private int currentX, currentY;

    @Getter
    private List<Node> path = new ArrayList<>();

    public void setGoal(int x, int y)
    {
        this.goalX = x;
        this.goalY = y;
    }

    public void setCurrent(int x, int y)
    {
        this.currentX = x;
        this.currentY = y;
    }

    public List<Node> aStar(Node start, Node target)
    {
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.calculateHeuristic(target);
        openList.add(start);

        while(!openList.isEmpty()){
            Node n = openList.peek();
            if(n == target)
            {
                ArrayList<Node> nodes = new ArrayList<>();

                while(n.parent != null){
                    nodes.add(n);
                    n = n.parent;
                }

                nodes.add(n);
                Collections.reverse(nodes);

                return nodes;
            }

            for(Node.Edge edge : n.neighbors)
            {
                Node m = edge.node;
                if(!openList.contains(m) && !closedList.contains(m))
                {
                    m.parent = n;
                    m.f = m.calculateHeuristic(target);
                    openList.add(m);
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public void updatePath(Map map)
    {
        Node[][] nodes = new Node[map.getWidth()][map.getHeight()];
        for (int x = 0; x < map.getWidth(); x++)
        {
            for (int y = 0; y < map.getHeight(); y++)
            {
                nodes[x][y] = new Node(x, y);
            }
        }

        int[][] directions = {
                {0, 1},  // Right
                {1, 0},  // Down
                {0, -1}, // Left
                {-1, 0}  // Up
        };

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                if (nodes[x][y] != null)
                {
                    for (int[] dir : directions)
                    {
                        int nx = x + dir[0];
                        int ny = y + dir[1];

                        // Check if neighbor is within bounds and walkable
                        if (nx >= 0 && ny >= 0 && nx < nodes.length && ny < nodes[0].length)
                        {
                            Tile tile = map.getTile(nx, ny);
                            if(tile == null)
                            {
                                nodes[x][y].addBranch(nodes[nx][ny]);
                            }
                        }
                    }
                }
            }
        }

        Node currentNode = nodes[this.currentX][this.currentY];
        Node targetNode = nodes[this.goalX][this.goalY];

        this.path =  aStar(currentNode, targetNode);
    }
}
