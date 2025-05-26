import java.time.LocalDate;
public class BorrowRecord {
	private String bookId;
	private String memberId;
	private LocalDate borrowDate;
	private LocalDate returnDate;

	public BorrowRecord (String bookId,String memberId,LocalDate borrowDate) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.borrowDate = LocalDate.now();
		this.returnDate = null;
	}

	public void returnBookTime() {
		this.returnDate = LocalDate.now();
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getBookId() {
		return bookId;
	}

	public String getMemberId() {
		return memberId;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public String toString() {
    String returnDateStr = (returnDate == null) ? "Not returned" : returnDate.toString();
    return String.format("Book ID: %-10s Member ID: %-10s Borrowed: %-12s Returned: %-12s",
            bookId, memberId, borrowDate, returnDateStr);
    }



}