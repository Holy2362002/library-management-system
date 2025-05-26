import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.nio.file.*;

public class Library {

	private List<Book> books = new ArrayList<>();
	List<Member> members = new ArrayList<>();
	List<BorrowRecord> record = new ArrayList<>();

	public void autoLibrary() {
		books.add(new Book("B001", "Java: The Complete Reference", "Herbert Schildt"));
		books.add(new Book("B002", "Effective Java", "Joshua Bloch"));
		books.add(new Book("B003", "Head First Java", "Kathy Sierra"));
		books.add(new Book("B004", "Clean Code", "Robert C. Martin"));
		books.add(new Book("B005", "Thinking in Java", "Bruce Eckel"));

		
	    members.add(new Member("M001", "Alice Johnson"));
	    members.add(new Member("M002", "Bob Smith"));
	    members.add(new Member("M003", "Charlie Nguyen"));
	    members.add(new Member("M004", "Diana Lee"));
	    members.add(new Member("M005", "Ethan Kim"));
		
		
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void removeBook(String bookId) {
		var btype = books.removeIf(a -> a.getId().equals(bookId));
		if(btype) {
			System.out.println("Delete Book");
		}else {
			System.out.println("Book not found");
		}	
	}

	public void removeMember(String memberId) {
		var mtype = members.removeIf(a -> a.getMemberId().equals(memberId));
		if(mtype) {
			System.out.println("Delete Member");
		}else {
			System.out.println("Member not found");
		}
	}

	public void registrationMember(Member member) {
		members.add(member);
	}

	public String borrowBook(String bookId,String memberId) {
		Book book = books.stream().filter(a -> a.getId().equalsIgnoreCase(bookId)).findFirst().orElse(null);

		if(book == null) {
			return "Book not found";
		}

		if(!book.getAvaliable()) {
			return "Book is Already borrow";
		}

		Member member = members.stream().filter(a -> a.getMemberId().equalsIgnoreCase(memberId)).findFirst().orElse(null);
		if(member == null) {
			return "Member not found";
		}

		BorrowRecord records = new BorrowRecord(bookId,memberId,LocalDate.now());
		record.add(records);

		book.setAvaliable(false);
		return "Book Borrow Successfully";
	}

	public void returnBook(String bookId,String memberId) {

		BorrowRecord records = record.stream().filter(a -> 
			a.getBookId().equalsIgnoreCase(bookId) &&
			a.getMemberId().equalsIgnoreCase(memberId)
		).findFirst().orElse(null);

		if(records == null) {
			System.out.println("Record is not found");
			return;
		}

		records.returnBookTime();

		books.stream().filter(a -> a.getId().equals(bookId)).
		findFirst().ifPresent(a -> a.setAvaliable(true));
	}

	public void viewBorrowReturnBook() {
		if(record.isEmpty()) {
			System.out.println("Borrow Book is empty::");
		}else {
			record.stream().forEach(System.out::println);
		}
	}

	public void viewAllBook() {
		if(books.isEmpty()) {
			System.out.println("Book is empty.Please add Book::");
		}else {
			books.stream().forEach(System.out::println);
		}
	}

	public void viewAllMember() {
		if(members.isEmpty()) {
			System.out.println("Member is empty.Please add member::");
		}else {
			members.stream().forEach(System.out::println);
		}
	}

	public void saveBookFile(String fileName) {
		try {
			List<String> lines = books.stream().
			map(a -> String.format("%s,%s,%s,%s",
				a.getId(),a.getTitle(),a.getAuthor(),a.getAvaliable())).toList();
			Files.write(Path.of(fileName),lines);
			System.out.println("Book File Saved");
		}catch(Exception e) {
			System.out.println("File not found ");
		}

	}

	public void readBookFile(String fileName) {
		try {
			List<String> lines = Files.readAllLines(Path.of(fileName));
			for(String str : lines) {
				String [] parts = str.split(",");
				Book book = new Book(parts[0],parts[1],parts[2]);
				book.setAvaliable(Boolean.parseBoolean(parts[3]));
				books.add(book);
			}
			System.out.println("Read Successful");
		}catch (Exception e) {
			System.out.println("File not found");
		}

	}

	public void saveMemberFile(String fileName) {
		try {
			List<String> lines = members.stream().
			map(a -> String.format("%s,%s",a.getMemberId(),a.getName())).toList();
			Files.write(Path.of(fileName),lines);
			System.out.println("Member Files Saved");
		}catch(Exception e) {
			System.out.println("File not found");
		}
	}

	public void readMemberFile(String fileName) {
		try {
			List<String> lines = Files.readAllLines(Path.of(fileName));
			for(String str : lines) {
				String [] parts = str.split(",");
				Member member = new Member(parts[0],parts[1]);
				members.add(member);
			}
			System.out.println("Member Files Read");
		}catch(Exception e) {
			System.out.println("Files Not found");
		}
	}
	public void readBorrowFile(String fileName) {
		try {
			List<String> lines = Files.readAllLines(Path.of(fileName));
			for(String str : lines) {
				String [] parts = str.split(",");
				BorrowRecord records = new BorrowRecord(parts[0],parts[1],LocalDate.parse(parts[2]));
				records.setReturnDate(LocalDate.parse(parts[3]));
				record.add(records);
				
			}
			System.out.println("Borrow Files Read");
		}catch(Exception e) {
			System.out.println("Files Not found");
		}
	}

	public void saveBorrowFile(String fileName) {
		try{
			List<String> lines = record.stream().
			map(a -> String.format("%s,%s,%s,%s",
				a.getBookId(),a.getMemberId(),a.getBorrowDate(),a.getReturnDate())).toList();
			Files.write(Path.of(fileName),lines);
			System.out.println("Borrow Files Saved");
		}catch (Exception e) {
			System.out.println("Files not found");
		}
	}


}