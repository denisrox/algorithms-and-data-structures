public class Vertex {

    private final String label;
    private boolean visited;
    private Vertex previousVertex=null;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public void setVisited(boolean visited, Vertex previousVertex) {
        this.visited = visited;
        this.previousVertex=previousVertex;
    }

    public boolean getVisited() {
        return visited;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }
}