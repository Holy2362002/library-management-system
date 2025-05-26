import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.*;
public class LibrarySystem {

    private static final String BFILE = "book.txt";
    private static final String MFILE = "member.txt";
    private static final String BOFILE = "borrow.txt";
	public static void main(String[] args) {
		Library library = new Library();
		Scanner sc = new Scanner(System.in);

        if(Files.exists(Paths.get(BFILE))) {
            library.readBookFile(BFILE);
        }

        if(Files.exists(Paths.get(MFILE))) {
            library.readMemberFile(MFILE);
        }

        if(Files.exists(Paths.get(BOFILE))) {
            library.readBorrowFile(BOFILE);
        }

        // library.readBookFile(BFILE);
        // library.readMemberFile(MFILE);
        

		while(true) {
			System.out.println("\n===== Library Management System =====");
            System.out.println("0. Add Auto Library");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Members");
            System.out.println("7. Exit");
            System.out.println("8. View AllBorrowBook");
            System.out.println("9. Save Book To File");
            System.out.println("10.Save Member To File");
            System.out.println("11.Save BorrowRecord To File");
            System.out.println();
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            
            switch(choice) {
            case 0 : library.autoLibrary();
                break;
            case 1 : 
            	sc.nextLine();
            	System.out.print("Enter your BookId : ");
            	String id = sc.nextLine();
            	System.out.print("Enter your BookTitle : ");
            	String title = sc.nextLine();
            	System.out.print("Enter your AuthorName : ");
            	String author = sc.nextLine();
            	library.addBook(new Book(id,title,author));
            	break;
            case 2:
            	sc.nextLine();
            	System.out.print("Enter your MemberId : ");
            	String memberId = sc.nextLine();
            	System.out.print("Enter your Memeber Name : ");
            	String memberName = sc.nextLine();
            	library.registrationMember(new Member(memberId,memberName));
            	break;
            case 3 :
            	System.out.println("Borrow Book : ");
            	sc.nextLine();
            	System.out.print("Enter your BookId : ");
            	String bId = sc.nextLine();
            	System.out.print("Enter your MemeberID : ");
            	String memId = sc.nextLine();
            	String borrow = library.borrowBook(bId,memId);
                System.out.println(borrow);
            	break;
            case 4 :
            	System.out.println("Return Book : ");
            	sc.nextLine();
            	System.out.print("Enter Your BookId : ");
            	String bookId = sc.nextLine();
            	System.out.print("Enter your MemeberID : ");
            	String mId = sc.nextLine();
            	library.returnBook(bookId,mId);
            	break;
            case 5:
            	library.viewAllBook();
            	break;
            case 6:
            	library.viewAllMember();
            	break;
            case 7:
            	System.out.println("Exit");
            	return;
            case 8:
                library.viewBorrowReturnBook();
                break;
            case 9:
                library.saveBookFile(BFILE);
                break;
            case 10:
                library.saveMemberFile(MFILE);
                break;
            case 11:
                library.saveBorrowFile(BOFILE);
                break;
            default : System.out.println("Invalid choice");

            }
		}
		


	}
}