import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{

    private String name;
    private List<Edge> adjecencyList;
    private double distance = Double.MAX_VALUE;
    private boolean isVisited;
    private Vertex predecessor;

    public Vertex(String name, List<Edge> adjecencyList) {
        this.name = name;
        this.adjecencyList = adjecencyList;
    }

    public Vertex(String name) {
        this.name = name;
        this.adjecencyList = new ArrayList<Edge>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getAdjecencyList() {
        return adjecencyList;
    }

    public void setAdjecencyList(List<Edge> adjecencyList) {
        this.adjecencyList = adjecencyList;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.distance, otherVertex.distance);
    }

    public void addNeighbour(Edge edge) {
        adjecencyList.add(edge);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
