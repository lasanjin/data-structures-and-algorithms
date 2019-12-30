import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class TestFil {

    SLCWithGet<Integer> list;
    SplayWithGet<Integer> splay;
    SplayWithGet<String> splayString;
    AVLwithGet<Integer> avlTree;
    Random rand = new Random();

    Integer lastAdded;

    public static void main(String[] args) {

        System.out.println("         ______------::::::::    Running Test File    ::::::::-------_______");

        new TestFil();

    }

    public TestFil() {
        //testSLC();
        //testSplay();
        //testSplayString();
        //testAvl();

    }

    private void testSLC() {
        list = new SLCWithGet<>();

        fillWithData(list);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("        _____-----:::::: END TEST :::::-.-----_____");
    }

    private void testSplay() {
        splay = new SplayWithGet<>();

//        fillWithData(splay);
        splay.add(86);
        splay.add(69);
        splay.add(13);
        splay.add(12);
        splay.add(9);
        splay.add(66);
        splay.add(52);
        splay.add(46);
        splay.add(18);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);
        splay.add(49);

        lastAdded = 64;

        System.out.println(splay.toString());

        System.out.println("We want to get " + lastAdded + " from tree.");

        splay.get(lastAdded);

        System.out.println(splay.toString());

    }

    private void testSplayString() {
        splayString = new SplayWithGet<>();

        splayString.add("hej");
        splayString.add("heja");
        splayString.add("hejb");
        splayString.add("hejd");
        splayString.add("hejaa");
        splayString.add("he");
        splayString.add("heeeej");
        splayString.add("hejab");
        splayString.add("hejolk");
        splayString.add("hejtr");
        splayString.add("haa");
        splayString.add("htgf");

        System.out.println(splayString.toString());

        splayString.get("he");

        System.out.println(splayString.toString());


    }

    private void testAvl() {
        avlTree = new AVLwithGet<>();

        fillWithData(avlTree);

        System.out.println(avlTree.toString());

        avlTree.get(lastAdded);

        System.out.println(avlTree.toString());

    }

    private void fillWithData(Collection collection) {
        Integer some = 0;
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(100);

            collection.add(n);

            some = n;
        }
        lastAdded = some;
    }

}

