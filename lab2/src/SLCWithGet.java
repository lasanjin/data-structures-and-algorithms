/**
 * @version 2018.02.06
 * @authors Joakim and Sanjin
 */
public class SLCWithGet<E extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {

    /**
     * Avmarkera kod på rad 13, 72-76 och 84 om du vill köra
     * en delvis förbättras version av SLC.
     */
    //private E previous;
    private int size;

    public SLCWithGet() {
        super();
    }

    /**
     * @param element the object to add into the list
     * @return true if <E> element is added, otherwise false.
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }

        size++; //Vi gör detta 1 gång.

        //Om listan är tom så lägg till head.
        if (isEmpty()) {
            head = new Entry(element, null);
            return true;
        }

        //Om element < head, så head = element.
        if (element.compareTo(head.element) < 1) {
            head = new Entry(element, head);
            return true;
        }

        Entry previous = head;
        Entry current = head.next;

        //Jämför vidare. Om element < current, så previous = element.
        while (current != null) {
            if (element.compareTo(current.element) < 1) {
                previous.next = new Entry(element, current);
                return true;
            }

            previous = current;
            current = current.next;
        }

        //"Störst" element sist.
        previous.next = new Entry(element, null);
        return true;
    }

    /**
     * @param e dummy element to compare to.
     * @return element if element is in list, otherwise null;
     */
    @Override
    public E get(E e) {
        if (e == null) {
            return null;
        }
        /*
        if (previous != null && e.compareTo(previous) == 0) {
            return previous;
        }
        */

        Entry current = head;

        while (current != null) {

            if (e.compareTo(current.element) == 0) {

                //previous = current.element;
                return current.element;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * @return size of list.
     */
    @Override
    public int size() {
        return size;
    }
}
