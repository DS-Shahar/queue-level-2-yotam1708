import java.util.*;
public class Main {


/////////////////////////////////////////////////////////////////1
    public static Node<Integer> Mizog(Node<Integer> lst1, Node<Integer> lst2) {
        Node<Integer> h = new Node<>(-1);
        Node<Integer> p = h;
        
        while (lst1 != null && lst2 != null) {
            if (lst1.getValue() <= lst2.getValue()) {
                p.setNext(new Node<>(lst1.getValue()));
                lst1 = lst1.getNext();
            } else {
                p.setNext(new Node<>(lst2.getValue()));
                lst2 = lst2.getNext();
            }
            p = p.getNext();
        }
        
        if (lst1 != null) {
            p.setNext(lst1);
        } else {
            p.setNext(lst2);
        }
        
        return h.getNext();
    }

/////////////////////////////////////////////////////////////////2
    public static Node<Integer> selectionSort(Node<Integer> lst) {
        Node<Integer> h = new Node<>(-1);
        Node<Integer> p = h;
        Node<Integer> current = lst;
        
        while (current != null) {
            int minVal = findMin(current);
            current = removeFirst(current, minVal);
            p.setNext(new Node<>(minVal));
            p = p.getNext();
        }
        return h.getNext();
    }

/////////////////////////////////////////////////////////////////3
    public static int sumDistances(Node<Integer> head, int key) {
        if (head == null) return -1;

        int length = 0;
        Node<Integer> current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }

        int firstIdx = -1;
        int lastIdx = -1;
        current = head;
        
        for (int i = 0; i < length; i++) {
            if (current.getValue() == key) {
                if (firstIdx == -1) {
                    firstIdx = i;
                }
                lastIdx = i;
            }
            current = current.getNext();
        }

        if (firstIdx == -1) return -1;
        
        int distStart = firstIdx;
        int distEnd = (length - 1) - lastIdx;
        return distStart + distEnd;
    }

/////////////////////////////////////////////////////////////////4
    public static boolean isDif(Node<Integer> head) {
        Node<Integer> current = head;
        while (current != null) {
            if (exists(current.getNext(), current.getValue())) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

/////////////////////////////////////////////////////////////////5
    public static Node<Integer> getList(Node<Integer> head) {
        Node<Integer> h = new Node<>(-1);
        Node<Integer> p = h;
        Node<Integer> current = head;
        
        while (current != null) {
            if (!exists(h.getNext(), current.getValue())) {
                p.setNext(new Node<>(current.getValue()));
                p = p.getNext();
            }
            current = current.getNext();
        }
        return h.getNext();
    }

/////////////////////////////////////////////////////////////////
//פעולות עזר
    private static boolean exists(Node<Integer> h, int val) {
        Node<Integer> current = h;
        while (current != null) {
            if (current.getValue() == val) return true;
            current = current.getNext();
        }
        return false;
    }

    private static int findMin(Node<Integer> h) {
        int min = h.getValue();
        Node<Integer> current = h;
        while (current != null) {
            if (current.getValue() < min) {
                min = current.getValue();
            }
            current = current.getNext();
        }
        return min;
    }

    private static Node<Integer> removeFirst(Node<Integer> h, int val) {
        if (h == null) return null;
        if (h.getValue() == val) return h.getNext();
        
        Node<Integer> current = h;
        while (current.getNext() != null) {
            if (current.getNext().getValue() == val) {
                current.setNext(current.getNext().getNext());
                return h;
            }
            current = current.getNext();
        }
        return h;
    }

    public static Node<Integer> build(int[] a) {
        Node<Integer> h = new Node<>(-1);
        Node<Integer> p = h;
        for (int i = 0; i < a.length; i++) {
            p.setNext(new Node<>(a[i]));
            p = p.getNext();
        }
        return h.getNext();
    }

/////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
// Test 1
        Node<Integer> m1 = build(new int[]{1,3,5});
        Node<Integer> m2 = build(new int[]{2,4,6,6,7});
        System.out.println("Mizog: " + Mizog(m1, m2));

// Test 2
        Node<Integer> s1 = build(new int[]{10,2,8,1,5});
        System.out.println("selection-sort: " + selectionSort(s1));

// Test 3
        Node<Integer> d1 = build(new int[]{5,8,3,8,2,9});
        System.out.println("Sum Distances: " + sumDistances(d1,8));

// Test 4
        System.out.println("Is dif? " + isDif(d1));

        // Test 5: Distinct
        System.out.println("new list: " + getList(d1));
    }
}
