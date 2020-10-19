import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DjkstraAlgo {


    public void computePaths(Vertex source){
        source.setDistance(0);
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        pq.add(source);
        while(!pq.isEmpty()){
            Vertex currentVertex = pq.poll();
            for(Edge edge: currentVertex.getAdjecencyList()){
                Vertex v = edge.getTargetVertex();
                double newDistance = currentVertex.getDistance()+edge.getWeight();
                if(newDistance <v.getDistance()){
                    pq.remove(v);
                    v.setDistance(newDistance);
                    v.setPredecessor(currentVertex);
                    pq.add(v);
                }
            }
        }
    }

    public List<Vertex> getShortestPathTo(Vertex targetVertex){
        List<Vertex> shortestPathToTarget = new ArrayList<Vertex>();
        for(Vertex v = targetVertex;v != null; v=v.getPredecessor()){
            shortestPathToTarget.add(v);
        }
        Collections.reverse(shortestPathToTarget);
        return shortestPathToTarget;
    }
}
