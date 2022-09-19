package guru.springframework.demo.bootstrap;

import guru.springframework.demo.model.Author;
import guru.springframework.demo.model.Book;
import guru.springframework.demo.model.Publisher;
import guru.springframework.demo.repositories.AuthorRepository;
import guru.springframework.demo.repositories.BookRepository;
import guru.springframework.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "123456");
        Publisher publisher = new Publisher("Planeta", "C/blblbl", "Barcelona", "España", "28080");
        //Hay que guardar el publisher inmediatamente despues de haberlo creado
        //ya que sino el libro no encontrara el publisher y dara error
        publisherRepository.save(publisher);

        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);


        Author author1 = new Author("Rod", "Johnson");
        Book book1 = new Book("J2EE Development", "456789");

        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);


        authorRepository.save(author1);
        bookRepository.save(book1);


        System.out.println("Bootstrap inicializated");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }


}
