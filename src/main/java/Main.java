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


































 import java.util.*;

public class Main {



  

///////////////////////////////////////////////////////////////// 1

    public static Queue<Integer> getSequenceLengths(Queue<Character> q) {

        Queue<Integer> counts = new Queue<Integer>();

        if (q.isEmpty()) return counts;



        Queue<Character> temp = copyQ(q);

        int count = 1;

        char current = temp.remove();



        while (!temp.isEmpty()) {

            if (temp.head() == current) {

                count++;

                temp.remove();

            } else {

                counts.insert(count);

                current = temp.remove();

                count = 1;

            }

        }

        counts.insert(count);

        return counts;

    }



///////////////////////////////////////////////////////////////// 2

    public static boolean hasDuplicates(Queue<String> q) {

        Queue<String> temp = copyQ(q);

        while (!temp.isEmpty()) {

            String current = temp.remove();

            if (isIn(temp, current)) {

                return true;

            }

        }

        return false;

    }



///////////////////////////////////////////////////////////////// 3

    public static Queue<Integer> removeAllDuplicates(Queue<Integer> q) {

        Queue<Integer> result = new Queue<Integer>();

        Queue<Integer> temp = copyQ(q);



        while (!temp.isEmpty()) {

            int val = temp.remove();

            if (!isIn(result, val)) {

                result.insert(val);

            }

        }

        return result;

    }



///////////////////////////////////////////////////////////////// 4

    public static void sortQueue(Queue<Integer> q) {

        Queue<Integer> helper = new Queue<Integer>();

        int size = getSize(q);



        for (int i = 0; i < size; i++) {

            int min = findMinAndRemove(q);

            helper.insert(min);

        }



        while (!helper.isEmpty()) {

            q.insert(helper.remove());

        }

    }



///////////////////////////////////////////////////////////////// 5

    public static Queue<Integer> mergeSortedQueues(Queue<Integer> q1, Queue<Integer> q2) {

        Queue<Integer> merged = new Queue<Integer>();

        Queue<Integer> temp1 = copyQ(q1);

        Queue<Integer> temp2 = copyQ(q2);



        while (!temp1.isEmpty() && !temp2.isEmpty()) {

            if (temp1.head() < temp2.head()) {

                merged.insert(temp1.remove());

            } else {

                merged.insert(temp2.remove());

            }

        }



        while (!temp1.isEmpty()) merged.insert(temp1.remove());

        while (!temp2.isEmpty()) merged.insert(temp2.remove());



        return merged;

    }



/////////////////////////////////////////////////////////////////

// פעולות עזר

    public static <T> Queue<T> copyQ(Queue<T> q) {

        Queue<T> temp = new Queue<T>();

        Queue<T> copy = new Queue<T>();

        while (!q.isEmpty()) {

            T val = q.remove();

            temp.insert(val);

            copy.insert(val);

        }

        while (!temp.isEmpty()) {

            q.insert(temp.remove());

        }

        return copy;

    }



    public static <T> boolean isIn(Queue<T> q, T val) {

        Queue<T> temp = copyQ(q);

        while (!temp.isEmpty()) {

            if (temp.remove().equals(val)) return true;

        }

        return false;

    }



    public static int getSize(Queue<Integer> q) {

        int count = 0;

        Queue<Integer> temp = copyQ(q);

        while (!temp.isEmpty()) {

            temp.remove();

            count++;

        }

        return count;

    }



    private static int findMinAndRemove(Queue<Integer> q) {

        int min = q.head();

        int size = getSize(q);

        for (int i = 0; i < size; i++) {

            int val = q.remove();

            if (val < min) min = val;

            q.insert(val);

        }

        boolean removed = false;

        for (int i = 0; i < size; i++) {

            int val = q.remove();

            if (val == min && !removed) {

                removed = true;

            } else {

                q.insert(val);

            }

        }

        return min;

    }



    public static <T> Queue<T> build(T[] arr) {

        Queue<T> q = new Queue<T>();

        for (T item : arr) {

            q.insert(item);

        }

        return q;

    }



/////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

//1

        Queue<Character> q1 = build(new Character[]{'c', 'c', 'a', 'c'});

        System.out.println("1. Sequences [c,c,a,c]: " + getSequenceLengths(q1));



//2

        Queue<String> q2 = build(new String[]{"apple", "banana", "apple"});

        System.out.println("2. Has duplicates? " + hasDuplicates(q2));



//3

        Queue<Integer> q3 = build(new Integer[]{1, 3, 5, 3, 1, 9});

        System.out.println("3. Clean duplicates: " + removeAllDuplicates(q3));



//4

        Queue<Integer> q4 = build(new Integer[]{10, 2, 8, 1, 5});

        sortQueue(q4);

        System.out.println("4. Sorted queue: " + q4);



//5

        Queue<Integer> m1 = build(new Integer[]{1, 3, 5});

        Queue<Integer> m2 = build(new Integer[]{2, 4, 6});

        System.out.println("5. Merged: " + mergeSortedQueues(m1, m2));

    }

}















import java.util.*;

public class QueueTasks {

    public static Queue<Integer> sequenceLengths(Queue<Character> q) {
        Queue<Integer> result = new LinkedList<Integer>();
        if (q.isEmpty()) return result;

        while (!q.isEmpty()) {
            char current = q.remove();
            int count = 1;
            while (!q.isEmpty() && q.peek() == current) {
                q.remove();
                count++;
            }
            result.add(count);
        }
        return result;
    }

    public static boolean hasDuplicates(Queue<String> q) {
        Queue<String> temp = new LinkedList<String>();
        boolean found = false;

        while (!q.isEmpty()) {
            String val = q.remove();
            if (isIn(temp, val)) {
                found = true;
            }
            temp.add(val);
        }

        while (!temp.isEmpty()) {
            q.add(temp.remove());
        }

        return found;
    }

    public static boolean isIn(Queue<String> q, String val) {
        boolean exists = false;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            String current = q.remove();
            if (current.equals(val)) {
                exists = true;
            }
            q.add(current);
        }
        return exists;
    }

    public static void removeDuplicates(Queue<Integer> q) {
        Queue<Integer> helper = new LinkedList<Integer>();

        while (!q.isEmpty()) {
            int current = q.remove();
            helper.add(current);
            removeAll(q, current);
        }

        while (!helper.isEmpty()) {
            q.add(helper.remove());
        }
    }

    public static void removeAll(Queue<Integer> q, int val) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int x = q.remove();
            if (x != val) {
                q.add(x);
            }
        }
    }
}









import java.util.*;
public class Main {
    
    public static boolean tree(BinNode<Integer> tree1) {
        boolean bool = false ;
        if (tree1 == null) {
            return true;
        }
        if (tree1.getRight()!=null){
         if (tree1.getLeft()!=null){
             bool = true;
         }  
        }
        if (tree1.getLeft()!=null){
             bool = true;
         } 
        return tree(tree1.getLeft()) && tree(tree1.getRight());
    }
}





import java.util.*;
public class Main {

class Range {
    int low;
    int high;

    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }
}

class Node {
    Range value;
    Node left;
    Node right;

    public Node(Range value) {
        this.value = value;
    }
}

public class RangeTree {

    public static boolean order(Node t) {
        if (t == null){
            return true;
        }
        Range c = t.value;

        if (t.left != null) {
            Range left = t.left.value;

            if (left.low != c.low || left.high > c.high)
                return false;
        }

        if (t.right != null) {
            Range right = t.right.value;

            if (right.high != c.high || right.low < c.low)
                return false;
        }

        if (t.left != null && t.right != null) {
            if (t.left.value.high >= t.right.value.low)
                return false;
        }

        return order(t.left) && order(t.right);
    }
}
}
