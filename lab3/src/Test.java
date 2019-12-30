public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        DirectedGraph dg = fillDirectedGraph(new DirectedGraph(6));
    }

    private DirectedGraph fillDirectedGraph(DirectedGraph dg) {
        dg.addEdge(new BusEdge(1,3,1,"A"));
        dg.addEdge(new BusEdge(4,6,2,"B"));
        dg.addEdge(new BusEdge(2,5,3,"C"));
        dg.addEdge(new BusEdge(3,6,4,"D"));
        dg.addEdge(new BusEdge(1,4,5,"E"));
        dg.addEdge(new BusEdge(3,4,5,"F"));
        dg.addEdge(new BusEdge(2,3,5,"G"));
        dg.addEdge(new BusEdge(3,5,6,"H"));
        dg.addEdge(new BusEdge(5,6,6,"I"));
        dg.addEdge(new BusEdge(1,2,6,"J"));

        return dg;
    }
}