import java.util.ArrayList;
import java.util.Scanner;

public class librarybookmanagement {

    private Node front, rear;
    private int queueSize;
    private ArrayList <String> log;
    private int booksIn;
    private int booksOut;

    private class Node {
        int isbn;
        String tajuk;
        String pengarang;
        int tahun;
        Node next;
    }

    public librarybookmanagement(){
        front = null;
        rear = null;
        queueSize = 0;
        log = new ArrayList<>();
        booksIn = 0;
        booksOut = 0;

    }

    public boolean isEmpty(){
        return (queueSize == 0);
    }

    public void insert(int isbn, String tajuk, String pengarang, int tahun){
        Node oldRear = rear;
        rear = new Node();
        rear.isbn = isbn;
        rear.tajuk = tajuk;
        rear.pengarang = pengarang;
        rear.tahun = tahun;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
        } else {
            oldRear.next = rear;
        }
        queueSize++;
        booksIn++;
        String logMessage = "ISBN: " + isbn + ", Tajuk: " + tajuk + ", Pengarang: " + pengarang + ", Tahun: " + tahun + " added to library";
        log.add(logMessage);
        System.out.println("\n" + logMessage);
    }

    public void display(){
        if (isEmpty()) {
            System.out.println("\nLibrary is empty. No books to show.");
            return;
        }
        Node current = front;
        System.out.println("\nBooks in library:");
        int i = 1;
        while (current != null) {
            System.out.println("\nBook " + i + ": \nISBN: " + current.isbn + "\nTajuk: " + current.tajuk + "\nPengarang: " + current.pengarang + "\nTahun: " + current.tahun);
            current = current.next;
            i++;
        }
    }

    public void remove(int isbnToRemove) {
        if (isEmpty()) {
            System.out.println("\nLibrary is empty. No books to remove.");
            return;
        }
    
        Node current = front;
        Node previous = null;
    
        while (current != null) {
            if (current.isbn == isbnToRemove) {
                if (previous == null) {
                    front = current.next;
                } else {
                    previous.next = current.next;
                }
    
                if (current == rear) {
                    rear = previous;
                }
    
                queueSize--;
                booksOut++;
                String logMessage = "Book with ISBN " + isbnToRemove + " removed from library.";
                log.add(logMessage);
                System.out.println("\n" + logMessage);
                return;
            }
            previous = current;
            current = current.next;
        }
    
        System.out.println("\nBook with ISBN " + isbnToRemove + " not found in the library.");
    }

    public void search(int isbnToSearch) {
        if (isEmpty()) {
            System.out.println("\nLibrary is empty. No books to search.");
            return;
        }
    
        Node current = front;
        boolean found = false;
    
        while (current != null) {
            if (current.isbn == isbnToSearch) {
                System.out.println("\nBook found:");
                System.out.println("ISBN: " + current.isbn);
                System.out.println("Tajuk: " + current.tajuk);
                System.out.println("Pengarang: " + current.pengarang);
                System.out.println("Tahun: " + current.tahun);
                found = true;
                break;
            }
            current = current.next;
        }
    
        if (!found) {
            System.out.println("\nBook with ISBN " + isbnToSearch + " not found in the library.");
        }
    }

    public void categorizeByAuthor(String authorName) {
        if (isEmpty()) {
            System.out.println("\nLibrary is empty. No books to categorize.");
            return;
        }
    
        Node current = front;
        int count = 0;
    
        while (current != null) {
            if (current.pengarang.equalsIgnoreCase(authorName)) {
                if (count == 0) {
                    System.out.println("\nBooks by author " + authorName + ":");
                }
                System.out.println("\nISBN: " + current.isbn + "\nTajuk: " + current.tajuk + "\nTahun: " + current.tahun);
                count++;
            }
            current = current.next;
        }
    
        if (count == 0) {
            System.out.println("\nNo books found by author " + authorName + " in the library.");
        }
    }

    public void categorizeByYear(int publicationYear) {
        if (isEmpty()) {
            System.out.println("\nLibrary is empty. No books to categorize.");
            return;
        }
    
        Node current = front;
        int count = 0;
    
        while (current != null) {
            if (current.tahun == publicationYear) {
                if (count == 0) {
                    System.out.println("\nBooks published in year " + publicationYear + ":");
                }
                System.out.println("\nISBN: " + current.isbn + "\nTajuk: " + current.tajuk + "\nPengarang: " + current.pengarang);
                count++;
            }
            current = current.next;
        }
    
        if (count == 0) {
            System.out.println("\nNo books found published in year " + publicationYear + " in the library.");
        }
    }
    
    public void displayLog() {
        if (log.isEmpty()) {
            System.out.println("\nNo operations logged.");
            return;
        }
        System.out.println("\nLibrary operations log:");
        for (String entry : log) {
            System.out.println(entry);
        }
        System.out.println("\nTotal books inserted: " + booksIn);
        System.out.println("Total books removed: " + booksOut);
    }

    public static void main(String[] args) {
        librarybookmanagement queue = new librarybookmanagement();
        Scanner inp = new Scanner(System.in);
        
        int choice;
        do {
            System.out.println("\nLibrary Book Management:");
            System.out.println("1. Insert Books");
            System.out.println("2. Display All Books");
            System.out.println("3. Remove Book by isbn");
            System.out.println("4. Search a Book by isbn");
            System.out.println("5. Search by Categories");
            System.out.println("6. Display Log");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = inp.nextInt();

            switch (choice) {
                case 1:
                System.out.println("\nHow many books would you like to insert? (insert numbers from 1 to 10)");
                int times = inp.nextInt();
                for (int i = 0; i < times; i++) {
                    System.out.print("\nBook " + (i+1) + ": \nEneter ISBN id: ");
                    int isbn = inp.nextInt();
                    System.out.print("Enter book title: ");
                    String tajuk = inp.next();
                    System.out.print("Enter author of the book: ");
                    String pengarang = inp.next();
                    System.out.print("Enter the year published: ");
                    int tahun = inp.nextInt();
                    queue.insert(isbn, tajuk, pengarang, tahun);
                }
                    break;

                case 2:
                    queue.display();
                    break;

                case 3:
                    System.out.print("\nEnter ISBN of the book to remove: ");
                    int isbnToRemove = inp.nextInt();
                    queue.remove(isbnToRemove);
                    break;

                case 4:
                    System.out.print("\nEnter ISBN of the book to search: ");
                    int isbnToSearch = inp.nextInt();
                    queue.search(isbnToSearch);
                    break;

                case 5:
                    System.out.println("\nSearch by Categories:");
                    System.out.println("1. Search by Author");
                    System.out.println("2. Search by Year");
                    System.out.print("Enter your choice: ");
                    int search = inp.nextInt();
                    
                    switch (search) {
                        case 1:
                            System.out.print("\nEnter author name to categorize books: ");
                            String authorName = inp.next();
                            queue.categorizeByAuthor(authorName);
                            break;

                        case 2:
                            System.out.print("\nEnter publication year to categorize books: ");
                            int publicationYear = inp.nextInt();
                            queue.categorizeByYear(publicationYear);
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                    break;

                case 6:
                    queue.displayLog();
                    break;

                case 7:
                    System.out.println("Bye Bye :(");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 7);
    }
}