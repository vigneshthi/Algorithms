package main.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SeparateChainingLiteHashST<Key, Value> {

    private int n;       // number of key-value pairs
    private int m;       // hash table size
    private Node[] st;   // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    // create separate chaining hash table
    public SeparateChainingLiteHashST() {
        this(997);
    } 

    // create separate chaining hash table with m lists
    public SeparateChainingLiteHashST(int m) {
        this.m = m;
        st = new Node[m];
    } 


    // hash value between 0 and m-1
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    } 

    // return number of key-value pairs in symbol table
    public int size() {
        return n;
    } 

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    } 

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        //System.out.println("key: " + key + " hash: " + i);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        n++;
        st[i] = new Node(key, val, st[i]);
    }
    
    
    // delete key (and associated value) from the symbol table
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }

    // return all keys as an Iterable
    public Iterable<Key> keys()  {
    		Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.add((Key) x.key);
            }
        }
        return queue;
    }
    
    /*Function for helping testing collision and hash function*/
    public Map<Integer, Integer> binsCount() {
    		Map<Integer, Integer> binCollisionCount = new HashMap();
    		for (int i = 0; i < m; i++) {
    			int count = 0;
                for (Node x = st[i]; x != null; x = x.next) {
                    count++;
                }
                binCollisionCount.put(i, count);
            }
    		return binCollisionCount;
    }

    public static void main(String[] args) { 
        // SeparateChainingLiteHashST<String, Integer> st = new SeparateChainingLiteHashST<String, Integer>(97);
        
//        BirthdayProblem birthdayProblem = new BirthdayProblem();
//        List<Integer> numbersGeneratedBeforeFirstDuplicate = birthdayProblem.runExperiments();
//        birthdayProblem.printResults(numbersGeneratedBeforeFirstDuplicate);
//        
        System.out.println();
        
        //CouponCollectorProblem couponCollectorProblem = new CouponCollectorProblem(4194304); // 2^22 = 4194304
        CouponCollectorProblem couponCollectorProblem = new CouponCollectorProblem(4194304);
        List<Integer> numbersGeneratedBeforeAllPossibleValues = couponCollectorProblem.runExperiments();
        couponCollectorProblem.printResults(numbersGeneratedBeforeAllPossibleValues);
        
        
    }

}