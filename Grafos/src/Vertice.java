import java.util.ArrayList;

public class Vertice {
    private String data;
    private ArrayList<Edge> edges;//lista de aristas
    private boolean visited;
    public Vertice(String inputData){
        this.data=inputData;
        this.edges=new ArrayList<Edge>();
    }
    public void addEdge(Vertice finalVertice,int weight){//solo el final porque el incial ya lo tengo
        this.edges.add(new Edge(this,finalVertice,weight));
    }
    public void removeEdge(Vertice finalVertice){
        this.edges.removeIf(edge -> edge.getFinalVertice().equals(finalVertice));
    }
    public String getData(){
        return this.data;
    }
    public Vertice getVertex(String data){
        if (this.data.equals(data)){
            return this;
        }
        return null;
    }
    public ArrayList<Edge> getEdges(){
        return this.edges;
    }
    public String print(boolean showWeight){
        String message="";
        if (this.edges.size()==0){
            message +=this.data+"-->";
            return message;
        }
        for (int i=0;i<this.edges.size();i++){
            if(i==0){
                message += this.edges.get(i).getInitialVertice().data+"-->";
            }
            message += this.edges.get(i).getFinalVertice().data;
            if (showWeight){
                message+="("+this.edges.get(i).getWeight()+")";
            }
            if(i !=this.edges.size() -1){
                message += ", ";
            }
        }
        return message;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
