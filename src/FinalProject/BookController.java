package FinalProject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookController extends Controller{

    public int command;

    public BookController(Scanner sc) {
        super(sc);
    }
    BookManager bm = new BookManager();


    public void doCommand() {
        switch (command){
            case 0: //뒤로 기능
                System.out.println("뒤로 기능 구현");
                break;
            case 1: //도서 조회
//                System.out.println("도서 조회 구현");
                System.out.println("이름을 입력하세요.");
                String searchBookName = sc.next();
                searchBooks(searchBookName, bm.books);
                break;
                //printBook(bm.books);
                //createCSV();
                //readCSV();
            case 2: //도서 등록
                doAdd();
                createCSV();
                System.out.println("도서 등록 구현");
                break;
            case 3: //도서 삭제
                System.out.println("삭제할 도서의 이름을 입력하세요.");
                String deleteBookName = sc.next();
                deleteBook(deleteBookName, bm.books);
                createCSV();
                break;
            case 4: //대출 도서 조회
                searchBorrowBooks(bm.books);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    public void deleteBook(String deleteBookName, ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            if (deleteBookName.equals(books.get(i).bookName)) {
                System.out.println("삭제되었습니다.");
                books.remove(i);
                return;
            }
        }
        System.out.println("해당하는 도서를 찾지 못했습니다.");
    }
    public void borrowBook(String bookName) {
        for (Book book : bm.books) {
            if (book.bookName.equals(bookName) && book.possibleUse) {
                book.possibleUse = false;
                System.out.println(bookName + "이(가) 대출되었습니다.");
                return;
            }
        }
        System.out.println(bookName + "은(는) 대출이 불가능합니다.");
    }

    public void searchBorrowBooks(ArrayList<Book> books){
        System.out.println("대출 가능한 책 목록입니다/");
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).possibleUse){
                System.out.println(books.get(i).bookName);
            }
        }
    }

    public void searchBooks(String searchBookName, ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            if (searchBookName.equals(books.get(i).bookName)) {
                System.out.println("존재하는 책입니다.");
                System.out.println("대출 가능 여부: " + books.get(i).possibleUse);
                return;
            }
        }
        System.out.println("해당하는 도서를 찾지 못했습니다.");
    }

    private void doAdd() {
        Book book = new Book();

        System.out.println("제목:");
        book.bookName = sc.next();
        sc.nextLine();

        book.possibleUse = true;

        System.out.println("출판사:");
        book.publisher = sc.nextLine();
        sc.nextLine();

        System.out.println("출간 날짜 (yyyy-MM-dd) :");
        String putDate = sc.nextLine();
        book.setPublishDate(putDate);

        System.out.println("등록이 완료되었습니다");
        bm.addBook(book);
        printBook(bm.books);

    }

    public void printBook(ArrayList<Book> books){
        for (int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            System.out.println("제목: " + book.bookName);
            System.out.println("출간 날짜: " + book.publishDate);
            System.out.println("출판사: " + book.publisher);
            System.out.println("--------------------");
        }
    }

    public void readCSV(){
        BufferedReader br = null;
        String line;
        String path = "books.csv";

        try{
            br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] temp = line.split(",");

                for(int i = 0; i < temp.length; i++){
                    System.out.print(temp[i]);
                    if(i != temp.length - 1)
                        System.out.print(", ");
                    else System.out.println();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }
    public void createCSV() {
        try {
            String header = "Book Name,Possible Use,Publisher,PublishDate\n";
            StringBuilder content = new StringBuilder();
            for (Book book : bm.books) {
                content.append(book.bookName).append(",").append(book.possibleUse)
                .append(",").append(book.publisher).append("\n");
            }
            File file = new File("books.csv");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(header);
            bufferedWriter.write(content.toString());
            bufferedWriter.close();
            System.out.println("csv 생성완료");
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
