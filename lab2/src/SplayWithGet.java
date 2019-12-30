/**
 * @version 2018.02.09
 * @authors Joakim and Sanjin
 */
public class SplayWithGet<E extends Comparable<? super E>>
        extends BinarySearchTree<E>
        implements CollectionWithGet<E> {


    public SplayWithGet() {
        super();
    }

    /**
     * If e is found at node x, we splay x and return the element,
     * else we splay the parent of the external node at which
     * the search terminates unsuccessfully and return null.
     *
     * @param e dummy element to compare to.
     * @return element if element is in tree, otherwise null
     */
    @Override
    public E get(E e) {
        if (e == null || root == null) {
            return null;
        }

        Entry entry = find(e, root);
        Entry parent;
        Entry grandParent;

        //Splay tills entry = root
        while (!equals(entry, root)) {
            parent = entry.parent;
            grandParent = parent.parent;

            if (grandParent == null) {
                belowRoot(parent, entry);
                break;
            }
            if (equals(grandParent.left, parent)) {
                leftOfGrandParent(parent, grandParent, entry);
            } else {
                rightOfGrandParent(parent, grandParent, entry);
            }
            entry = grandParent;
        }

        return e.compareTo(root.element) == 0 ? root.element : null;
    }

    // Entry hänger på root
    private void belowRoot(Entry parent, Entry entry) {
        if (equals(parent.left, entry)) {
            zig(parent);
        } else {
            zag(parent);
        }
    }

    // Entry hänger vänster om grandparent
    private void leftOfGrandParent(Entry parent, Entry grandParent, Entry entry) {
        if (equals(parent.left, entry)) {
            zagZag(grandParent);
        } else {
            zagZig(grandParent);
        }
    }

    // Entry hänger höger om grandparent
    private void rightOfGrandParent(Entry parent, Entry grandParent, Entry entry) {
        if (equals(parent.right, entry)) {
            zigZig(grandParent);
        } else {
            zigZag(grandParent);
        }
    }

    private boolean equals(Entry e1, Entry e2) {
        return e1 != null && e1 == e2;
    }

    /**
     * Modified in order to splay the parent of the external node if
     * the search terminates unsuccessfully in the get method.
     *
     * @param e     element
     * @param entry root
     * @return element's entry if element exists, otherwise element's parent
     */
    @Override
    protected Entry find(E e, Entry entry) {
        int result = e.compareTo(entry.element);

        if (result < 0) {
            if (entry.left != null) {
                return find(e, entry.left);
            }
        }
        if (result > 0) {
            if (entry.right != null) {
                return find(e, entry.right);
            }
        }

        return entry;
    }

    /* Rotera 1 steg i högervarv:
            x'                 y'
           / \                / \
          y'  C     -->      A   x'
         / \                    / \
        A   B                  B   C
    */
    private void zig(Entry x) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;

        x.left = y.left;
        if (x.left != null) {
            x.left.parent = x;
        }

        y.left = y.right;

        y.right = x.right;
        if (y.right != null) {
            y.right.parent = y;
        }

        x.right = y;
    }

    /* Rotera 1 steg i vänstervarv:
            x'                 y'
           / \                / \
          A   y'     -->     x'  C
             / \            / \
            B   C          A   B
    */
    private void zag(Entry x) {
        Entry y = x.right;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;

        x.right = y.right;
        if (x.right != null) {
            x.right.parent = x;
        }

        y.right = y.left;

        y.left = x.left;
        if (y.left != null) {
            y.left.parent = y;
        }

        x.left = y;
    }

    /* Rotera vänster sedan höger:
            x'                  z'
           / \                /   \
          y'  D     -->      y'    x'
         / \                / \   / \
        A   z'             A   B C   D
           / \
          B   C
    */
    private void zagZig(Entry x) {
        Entry y = x.left;
        Entry z = x.left.right;
        E temp = x.element;
        x.element = z.element;
        z.element = temp;

        y.right = z.left;
        if (y.right != null) {
            y.right.parent = y;
        }

        z.left = z.right;

        z.right = x.right;
        if (z.right != null) {
            z.right.parent = z;
        }

        x.right = z;
        z.parent = x;
    }

    /* Rotera höger sedan vänster:
            x'                  z'
           / \                /   \
          A   y'     -->     x'    y'
             / \            / \   / \
            z   D          A   B C   D
           / \
          B   C
     */
    private void zigZag(Entry x) {
        Entry y = x.right;
        Entry z = x.right.left;
        E temp = x.element;
        x.element = z.element;
        z.element = temp;

        y.left = z.right;
        if (y.left != null) {
            y.left.parent = y;
        }

        z.right = z.left;

        z.left = x.left;
        if (z.left != null) {
            z.left.parent = z;
        }

        x.left = z;
        z.parent = x;
    }

    /* Rotera 2 steg i vänstervarv:
            x'                  z
           / \                 / \
          G   y'     -->      y'  C
             / \             / \
            A   z           x'  B
               / \         / \
              B   C       G   A
    */
    private void zigZig(Entry x) {
        Entry y = x.right;
        Entry z = x.right.right;
        E temp = x.element;

        // Byt plats på x och z's element
        x.element = z.element;
        z.element = temp;

        x.right = z.right;// z.right = C
        if (x.right != null) {
            x.right.parent = x;// C.parent = z
        }

        y.right = z.left;// y.right = B
        if (y.right != null) {
            y.right.parent = y;// B.parent = y
        }

        z.right = y.left;// x.right = A
        if (y.left != null) {
            y.left.parent = z;// A.parent = x
        }

        z.left = x.left;//x.left = G
        if (z.left != null) {
            z.left.parent = z;
        }

        x.left = y;
        y.left = z;
    }

    /* Rotera 2 steg i högervarv:
            x'                  z
           / \                 / \
          y'  D      -->      A   y'
         / \                     / \
        z   C                   B   x'
       / \                         / \
      A   B                       C   D
    */
    private void zagZag(Entry x) {
        Entry y, z;
        y = x.left;
        z = x.left.left;
        E e = x.element;

        //Byt plats på x och y's element
        x.element = z.element;
        z.element = e;

        x.left = z.left; // hänger A på toppen
        if (x.left != null) {
            x.left.parent = x;
        }

        y.left = z.right; // hänger B på y'
        if (y.left != null) {
            y.left.parent = y;
        }

        z.left = y.right; // hänger C på x'
        if (z.left != null) {
            z.left.parent = z;
        }

        z.right = x.right; // hänger D på x'
        if (z.right != null) {
            z.right.parent = z;
        }

        x.right = y;
        y.right = z;
    }
}
