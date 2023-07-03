import java.util.Comparator;
import java.util.Queue;

public class QueueObject implements Comparable<QueueObject> {
    public Vertice vertex;
    public int priority;
    public QueueObject(Vertice v,int p){
        this.vertex=v;
        this.priority=p;
    }


    @Override
    public int compareTo(QueueObject o) {
        if (this.priority==o.priority){
            return 0;
        }
        return -1;
    }
}
