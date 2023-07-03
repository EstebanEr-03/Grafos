import java.util.*;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private boolean isDirected;
    private boolean isWeighted;

    public Grafo(boolean isDirected, boolean isWeighted) {//Inicializar si es direccionado o que
        this.vertices=new ArrayList<>();
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
    }

    public void addVertice(String data){//añade vertice, recibe datos de string, crea vertice y añade lista
        Vertice newVertice=new Vertice(data);
        this.vertices.add(newVertice);
    }
    public void addEdge(Vertice initialVertice,Vertice finalVertice,int weight){//añadir una arista, recibe uno incial uno final y el peso
        if (!this.isWeighted){//si el grafico no tiene peso el peso entonces se pone en uno
            weight=1;
        }

        vertices.get(vertices.indexOf(initialVertice)).addEdge(finalVertice,weight);

        initialVertice.addEdge(finalVertice,weight);

        if (!this.isDirected){//si no es direccionado
            finalVertice.addEdge(initialVertice,weight);
        }
    }

    public void addEdge(String initialVertice,String finalVertice,int weight){//String para recibir directamente de los textfield
        if (!this.isWeighted){
            weight=1;
        }

        getVertexByValue(initialVertice).addEdge(getVertexByValue(finalVertice),weight);

        if (!this.isDirected){
            getVertexByValue(finalVertice).addEdge(getVertexByValue(initialVertice),weight);//inversa
        }
    }
    public void removeEdge(Vertice initialVertice,Vertice finalVertice){
        initialVertice.removeEdge(finalVertice);
        if (!this.isDirected){
            finalVertice.removeEdge(initialVertice);
        }
    }
    public void removeVertice(Vertice vertice){
        this.vertices.remove(vertice);
    }
    public ArrayList<Vertice> getVertices(){
        return this.vertices;
    }
    public boolean isWeighted(){
        return this.isWeighted;
    }
    public boolean isDirected(){
        return this.isDirected;
    }
    public Vertice getVertexByValue(String value){//como paso un string lo que hace es buscar
        for (Vertice v:this.vertices){
            if (v.getData().equals(value)){
                return v;
            }
        }
        return null;
    }
    public ArrayList<Vertice> depthFirstSearch(Vertice startVertex) {
        ArrayList<Vertice> visitedVertices = new ArrayList<>();
        Stack<Vertice> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertice currentVertex = stack.pop();

            if (!visitedVertices.contains(currentVertex)) {
                visitedVertices.add(currentVertex);

                ArrayList<Edge> edges = currentVertex.getEdges();
                for (Edge edge : edges) {
                    Vertice neighbor = edge.getFinalVertice();
                    stack.push(neighbor);
                }
            }
        }

        return visitedVertices;
    }
    public ArrayList<Vertice> breadthFirstSearch(Vertice startVertex) {
        ArrayList<Vertice> visitedVertices = new ArrayList<>();
        Queue<Vertice> queue = new LinkedList<>();

        queue.add(startVertex);
        visitedVertices.add(startVertex);

        while (!queue.isEmpty()) {
            Vertice currentVertex = queue.poll();

            ArrayList<Edge> edges = currentVertex.getEdges();
            for (Edge edge : edges) {
                Vertice neighbor = edge.getFinalVertice();
                if (!visitedVertices.contains(neighbor)) {
                    queue.add(neighbor);
                    visitedVertices.add(neighbor);
                }
            }
        }

        return visitedVertices;
    }
    public Dictionary[] Dijsktra(Vertice start){
        Dictionary<String,Integer> distances= new Hashtable<>();
        Dictionary<String,Vertice> previous=new Hashtable<>();
        PriorityQueue<QueueObject> queue=new PriorityQueue<>();
        Vertice startingVertex= vertices.get(vertices.lastIndexOf(start));
        queue.add(new QueueObject(startingVertex, 0));
        for (Vertice v: vertices){
            if (v !=startingVertex){
                distances.put(v.getData(),Integer.MAX_VALUE);
            }
            previous.put(v.getData(),new Vertice("NULL"));
        }
        distances.put(startingVertex.getData(),0);
        while (queue.size()!=0){
            Vertice current=queue.poll().vertex;
            for (Edge e:current.getEdges()){
                Integer alternative=distances.get(current.getData())+e.getWeight();
                String neighborValue=e.getFinalVertice().getData();
                if (alternative < distances.get(neighborValue)){
                    distances.put(neighborValue,alternative);
                    previous.put(neighborValue,current);
                    queue.add(new QueueObject(e.getInitialVertice(),distances.get(neighborValue)));

                }
            }
        }
        return new Dictionary[]{distances,previous};
    }
    public String dijkstraResultPrinter(Dictionary[] d){
        String result ="";
        result += "Distances:\n";
        for (Enumeration keys = d[0].keys(); keys.hasMoreElements();){
            String nextKey=keys.nextElement().toString();
            Vertice nextVertex=(Vertice) d[1].get(nextKey);
            result+=(nextKey + ": "+nextVertex.getData()+"\n");

        }
        return result;
    }

}
