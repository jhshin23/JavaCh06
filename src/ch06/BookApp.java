package ch06;
//Page394, P02, Object method override overload
class Book {
	private String author, title, buyer;
	public Book(String author, String title, String buyer) {
		this.author = author; 
		this.title = title; 
		this.buyer = buyer;
	}
	
	public String getTitle() { return title; }
	public String getAuthor() { return author; }

	@Override
	public String toString() {
		return buyer + "이 구입한 도서: " + author + "의 " + title;
	}
	
	public boolean equals(Book b) {
		if (getTitle().equals(b.getTitle()) && getAuthor().equals(b.getAuthor()))
			return true;
		else return false;
	}
}
public class BookApp {

	public static void main(String[] args) {
		Book a = new Book("황기태", "명품자바", "김하진");
		Book b = new Book("황기태", "명품자바", "하여린");
		
		System.out.println(a);
		System.out.println(b);
		
		if (a.equals(b))
			System.out.println("같은 책");
		else
			System.out.println("다른 책");
	}

}
