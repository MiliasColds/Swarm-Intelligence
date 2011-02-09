import java.util.*;
import java.io.*;

public class Prog6 {
    public static Scanner sc=new Scanner(System.in);
    static public void main (String args []) throws IOException {
        File f = new File(args[0]);
        int runAgain=1;
        do{
            System.out.println("Enter a line number");
            int row = Integer.parseInt(sc.nextLine());
            System.out.println("Enter a column  number");
            int col = Integer.parseInt(sc.nextLine());
            System.out.println((char)f.getChar(row,col));
            System.out.println("Again? Enter 1- for yes, 2-no for no");
            runAgain=Integer.parseInt(sc.nextLine());
        } while (runAgain==1);

    } // main
} // Prog6

class Node {
    private int character;
    private Node next;

    public Node() {}
    public Node(int character) {
        this.character=character;
        next=null;
    }
    public void assignNext(Node n) {next=n;}
    public int returnChar() {return character;}
    public Node returnNext() { return next;}
} // Node

class File {
    private Node head; 

    public File(String fileName) throws IOException {
        head=null;
        Node rear=null;
        BufferedReader input =
            new BufferedReader (new FileReader (fileName));
        int c=input.read();
        if (c!=-1) { // if there are chars in the file
            head = new Node(c);
            rear = head;
            c=input.read();
            while (c!=-1) { 
                rear.assignNext(new Node(c));
                rear=rear.returnNext();
                c=input.read();
            }
        } // if
    } // Constructor

    public int getChar(int row, int col) {
        return 0;

    } // getChar

} // File
