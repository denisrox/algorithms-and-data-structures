
import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    private int size;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>();
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
        this.size = 0;
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
        size++;
    }

    public int getSize() {
        return size;
    }

    public void addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String anotherLabel : others) {
            addEdge(startLabel, anotherLabel);
        }
    }

    public void addEdge(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid vertex labels");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;
    }

    private int indexOf(String startLabel) {
        for (int i = 0; i < size; i++) {
            if (vertexList.get(i).getLabel().equals(startLabel)) {
                return i;
            }
        }

        return -1;
    }

    public void display() {
        System.out.println("-------");
        System.out.println("Graph size is " + getSize());
        for (int i = 0; i < size; i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < size; j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
        System.out.println("-------");
    }


    /**
     * англ. breadth-first search, BFS
     * @param startLabel
     */
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Queue<Vertex> queue = new ArrayDeque<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(queue, vertex);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }

        resetVertexState();
    }


    public ArrayList<Vertex> searchBfs(String startLabel, String endLabel){
        ArrayList<Vertex> wayToThePoint=new ArrayList<>();
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }
        int endIndex = indexOf(startLabel);
        if (endIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + endIndex);
        }
        Queue<Vertex> queue = new ArrayDeque<>();
        Vertex vertex = vertexList.get(startIndex);
        //visitVertex(queue, vertex);
        queue.add(vertex);
        vertex.setVisited(true);
        Vertex previousVertex=vertex;

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                //visitVertex(queue, vertex);
                queue.add(vertex);
                vertex.setVisited(true,previousVertex);
                if(vertex.getLabel()==endLabel)
                {
                    //wayBack();
                    while (vertex!=null)
                    {
                        wayToThePoint.add(vertex);
                        vertex=vertex.getPreviousVertex();
                    }
                    resetVertexState();
                    return wayToThePoint;
                }
            } else {
                queue.remove();
                previousVertex=queue.peek();
            }
        }
        resetVertexState();
        return null;
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.println(vertex.getLabel());
        queue.add(vertex);
        vertex.setVisited(true);
    }

    /**
     * англ. Depth-first search, DFS
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid startLabel: " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(stack, vertex);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.println(vertex.getLabel());
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private Vertex getNearUnvisitedVertex(Vertex currentVertex) {
        int currentIndex = vertexList.indexOf(currentVertex);
        for (int i = 0; i < size; i++) {
            if (adjMat[currentIndex][i] && !vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }

        return null;
    }


}