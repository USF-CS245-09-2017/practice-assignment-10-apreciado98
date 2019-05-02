import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph {
    public int[][] adjMatrix;
    public int size;

    /*
    Constructs and returns a graph with the number of vertices passed as the argument.
    */
    public GraphImplementation(int vertices){
        adjMatrix = new int[vertices][vertices];
        size = vertices;
    }

    /*
    Adds a directed edge between two vertices from v1 to v2.
    */
    public void addEdge(int v1, int v2){
        adjMatrix[v1][v2]=1; //unweighted
    }

    /*
    Prints (to console) one ordering of vertices such that each directed
    edge (v1, v2) from vertex v1 to vertex v2, v1 comes before v2 in
    the ordering. If such an ordering is not possible (due to cycles, for
    example), this function must indicate so, though it may print a
    partial solution in so doing.
     */
    public List<Integer> topologicalSort(){
        List<Integer> tsort = new ArrayList<>();
        int[] incident = new int[size];

        //initialize incident array with all zeros
        for(int i=0; i<size; i++){
            incident[i]=0;
        }

        //update incident array
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                incident[j] += adjMatrix[i][j];
            }
        }

        //find index of item with zero count
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(incident[j]==0){
                    int[] neigh = neighbors(j);
                    for(int k=0; k<neigh.length; k++){
                        incident[neigh[k]]-=1;
                    }
                    tsort.add(j); //adding the item to tsort
                    incident[j]=-1; //set to flag (-1)
                }
            }
        }
        //print out tsort and return
        System.out.println("Topological Sort: "+tsort);
        return tsort;
    }

    /*
    Returns an array of vertex IDs such that each ID represents a
    vertex which is the destination of the edge origination from the
    argument.
    */
    public int[] neighbors(int vertex){
        List<Integer> neigh=new ArrayList<>();
        for(int i=0; i<adjMatrix.length; i++){
            if(adjMatrix[vertex][i]>0){
                neigh.add(i);
            }
        }
        //the function must return an array
        int[] vertexids = new int[neigh.size()];
        for(int i=0; i<vertexids.length; i++){
            vertexids[i]=neigh.get(i);
        }
        return vertexids;
    }
}