package com.library.service;

import com.library.entity.Book;
import com.library.entity.Reader;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class LibraryService {
    /* ★ 优化前: ArrayList + 线性查找 O(n)
     *   优化后: HashMap  + 哈希查找 O(1)
     *   Book  的键是 ISBN（唯一标识）
     *   Reader 的键是 cardNumber（借阅证号，唯一标识）
     */
    private final Map<String, Book> bookMap = new HashMap<>();
    private final Map<String, Reader> readerMap = new HashMap<>();

    @PostConstruct
    public void initData() {
        bookMap.put("9787302567890", new Book("Java从入门到精通", "张三", "清华大学出版社", "9787302567890", 1));
        bookMap.put("9787301456789", new Book("数据结构与算法", "李四", "北京大学出版社", "9787301456789", 1));
        readerMap.put("123456", new Reader("王五", "123456"));
        readerMap.put("789012", new Reader("赵六", "789012"));
    }

    public String addBook(String title, String author, String publisher, String isbn, int stockQuantity) {
        Book exist = bookMap.get(isbn);       // O(1) 直接定位，无需遍历
        if (exist == null) {
            bookMap.put(isbn, new Book(title, author, publisher, isbn, stockQuantity));
            return "已成功入库图书: " + title;
        }
        if (exist.getAuthor().equals(author) && exist.getTitle().equals(title) && exist.getPublisher().equals(publisher)) {
            exist.setStockQuantity(exist.getStockQuantity() + stockQuantity);
            return "已成功入库图书: " + title;
        }
        return "该isbn在图书馆中已存在，但新增图书与已存在图书的其他字段不一致，无法入库该图书: " + title;
    }

    public String removeBook(String isbn) {
        Book b = bookMap.get(isbn);           // O(1) 直接定位
        if (b == null) return "未找到指定ISBN的图书。";
        if (b.getStockQuantity() > 0) {
            b.setStockQuantity(b.getStockQuantity() - 1);
            return "图书已出库: " + b.getTitle();
        }
        return "该图书库存为空，无法出库。";
    }

    /* searchBooks 需要遍历全部图书做模糊匹配，这是业务需求，无法用 HashMap 优化 */
    public List<Book> searchBooks(String keyword) {
        List<Book> r = new ArrayList<>();
        for (Book b : bookMap.values()) {
            if (b.getTitle().contains(keyword) || b.getAuthor().contains(keyword) || b.getIsbn().contains(keyword)) r.add(b);
        }
        return r;
    }

    public String addReader(String name, String cardNumber) {
        if (!readerMap.containsKey(cardNumber)) {  // O(1) 直接判断
            readerMap.put(cardNumber, new Reader(name, cardNumber));
            return "已成功添加读者: " + name + "  " + cardNumber;
        }
        return "该读者借阅证号(cardNumber) " + cardNumber + " 已存在，请勿重复添加";
    }

    public String borrowBook(String cardNumber, String isbn) {
        Reader r = readerMap.get(cardNumber);   // O(1)
        if (r == null) return "未找到指定借阅证号的读者。";
        Book b = bookMap.get(isbn);             // O(1)
        if (b == null) return "未找到指定ISBN的图书。";
        if (b.getStockQuantity() > 0 && r.getBorrowedBooksCount() < 3 && !r.getBorrowedBooksIsbn().contains(isbn)) {
            b.setStockQuantity(b.getStockQuantity() - 1);
            r.getBorrowedBooksIsbn().add(isbn);
            r.setBorrowedBooksCount(r.getBorrowedBooksCount() + 1);
            return "借阅成功，读者 " + r.getName() + " 借阅了图书 " + b.getTitle();
        }
        if (r.getBorrowedBooksCount() >= 3) return "读者已达到借阅上限，无法借阅更多图书。";
        if (r.getBorrowedBooksIsbn().contains(isbn)) return "该读者已借此书，请勿重复借";
        return "该图书库存为空，无法借阅。";
    }

    public String returnBook(String cardNumber, String isbn) {
        Reader r = readerMap.get(cardNumber);   // O(1)
        if (r == null) return "无此读者，无法执行归还操作";
        if (!r.getBorrowedBooksIsbn().contains(isbn)) return "该读者并未借此书，无法归还";
        Book b = bookMap.get(isbn);             // O(1)
        if (b == null) return "未找到指定ISBN的图书。";
        b.setStockQuantity(b.getStockQuantity() + 1);
        r.getBorrowedBooksIsbn().remove(isbn);
        r.setBorrowedBooksCount(r.getBorrowedBooksCount() - 1);
        return "归还成功，读者 " + r.getName() + " 归还了图书 " + b.getTitle();
    }

    public List<Book> listBooks() { return new ArrayList<>(bookMap.values()); }
    public List<Reader> listReaders() { return new ArrayList<>(readerMap.values()); }
}
