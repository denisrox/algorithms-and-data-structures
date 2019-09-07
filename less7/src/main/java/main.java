import java.util.ArrayList;
import java.util.Stack;

public class main {
    public static void main(String[] args) {
        testSBfs();
    }
    private static void testSBfs() {
        Graph graph = new Graph(8);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdges("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("E", "H");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");
        System.out.println("=======ТЕСТ 1=========");
        ArrayList<Vertex> wayToPoint1=new ArrayList<>();
        wayToPoint1 = graph.searchBfs("A","B");
        for(int i = wayToPoint1.size();i>0;i--){
            System.out.println(wayToPoint1.get(i-1).getLabel());
        }
        System.out.println("=======ТЕСТ 2=========");
        ArrayList<Vertex> wayToPoint2=new ArrayList<>();
        wayToPoint2 = graph.searchBfs("A","H");
        for(int i = wayToPoint2.size();i>0;i--){
            System.out.println(wayToPoint2.get(i-1).getLabel());
        }
        System.out.println("=======ТЕСТ 3=========");
        ArrayList<Vertex> wayToPoint3=new ArrayList<>();
        wayToPoint3 = graph.searchBfs("A","G");
        for(int i = wayToPoint3.size();i>0;i--){
            System.out.println(wayToPoint3.get(i-1).getLabel());
        }



    }
}
