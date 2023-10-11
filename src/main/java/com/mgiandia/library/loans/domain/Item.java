package com.mgiandia.library.loans.domain;

import com.mgiandia.library.catalog.domain.Book;
import jakarta.persistence.*;

import com.mgiandia.library.loans.LibraryException;
import com.mgiandia.library.loans.util.SystemDate;

/**
 * Το αντίτυπο ενός βιβλίου.
 * @author Νίκος Διαμαντίδης
 *
 */
@NamedQuery(
        name="itemsByBookId",
        query="SELECT i FROM Item i WHERE i.bookId = :bookId"
)
@Entity
@Table(name="items")
public class Item {
    @Id
    @Column(name="itemno")
    private int itemNumber = 0;
    
    /*@ManyToOne(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE}    
            ) 
    @JoinColumn(name="bookno")
    private Book book;*/

    @Column(name="bookId")
    private Integer bookId;
    
    @Enumerated(EnumType.STRING)
    @Column(name="itemstate")
    private ItemState state = ItemState.NEW;

    /**
     * Προκαθορισμένος κατασκευαστής.
     */
    public Item() { }


    /**
     * Βοηθητικός κατασκευαστής.
     * Δέχεται τον αριθμό εισαγωγής ως παράμετρο.
     * @param itemNumber Ο αριθμός εισαγωγής
     */
    public Item(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Θέτει τον αριθμό εισαγωγής του αντιτύπου.
     * Ο αριθμός εισαγωγής προσδιορίζει μοναδικά κάθε αντίτυπο.
     * @param itemNumber Ο αριθμός εισαγωγής.
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * Επιστρέφει τον αριθμό εισαγωγής του αντιτύπου.
     * Ο αριθμός εισαγωγής προσδιορίζει μοναδικά κάθε αντίτυπο.
     * @return Ο αριθμός εισαγωγής
     */
    public int getItemNumber() {
        return itemNumber;
    }


    /**
     * Θέτει το βιβλίο του αντιτύπου.
     * @param book  Το βιβλίο του αντιτύπου
     * @see Book#addItem(Item)
     */
    /*public void setBook(Book book) {
        if (this.book != null) {
            this.book.friendItems().remove(this);
        }
        this.book = book;
        if (this.book != null) {
            this.book.friendItems().add(this);
        }
    }*/

    /**
     * Επιστρέφει το βιβλίο του αντιτύπου.
     * @return Το βιβλίο του αντιτύπου
     */
    /*public Book getBook() {
        return book;
    }*/


    /**
     * Επιστρέφει την κατάσταση του αντιτύπου.
     * @return Η κατάσταση του αντιτύπου
     */
    public ItemState getState() {
        return state;
    }


    /**
     * θέτει την κατάσταση του αντιτύπου.
     * Προσοχή στην ορατότητα.
     * Δεν είναι δημόσια και δεν μπορεί οποιαδήποτε
     * κλάση να αλλάξει την κατάσταση
     * του αντιτύπου.
     * @param state Η κατάσταση του αντιτύπου.
     */
    protected void setState(ItemState state) {
        this.state = state;
    }




    /**
     * Πραγματοποιεί το δανεισμό του αντιτύπου για
     * κάποιο δανειζόμενο και επιστρέφει το δανεισμό.
     * Εάν ο δανειζόμενος είναι {@code null} τότε επιστρέφει{@code null}.
     * Εάν ο δανειζόμενος δεν δικαιούται να δανειστεί
     * κάποιο αντίτυπο τότε δεν πραγματοποιείται ο δανεισμός
     * και επιστρέφει {@code null}.
     * Επιστρέφει {@code null} εάν η κατάσταση του
     * αντιτύπου δεν είναι {@code AVAILABLE}
     * Η κατάσταση του αντιτύπου γίνεται {@code LOANED}
     * @param borrower Ο δανειζόμενος
     * @return Το αντικείμενο του δανεισμού.
     */
    public Loan borrow(Borrower borrower) {
        if (borrower == null) {
            return null;
        }

        if (!borrower.canBorrow()) {
            return null;
        }

        if (getState() != ItemState.AVAILABLE) {
            return null;
        }

        Loan loan = new Loan();
        loan.setItem(this);
        loan.setBorrower(borrower);
        loan.setLoanDate(SystemDate.now());
        setState(ItemState.LOANED);
        return loan;
    }

    /**
     * Αλλάζει την κατάσταση του αντιτύπου σε διαθέσιμο ({@code AVAILABLE}).
     */
    public void available() {
        if (getState().equals(ItemState.LOST)) {
            throw new LibraryException();
        }
        if (getState().equals(ItemState.WITHDRAWN)) {
            throw new LibraryException();
        }

        setState(ItemState.AVAILABLE);
    }

    /**
     * Το αντίτυπο αποσύρεται και δεν είναι διαθέσιμο για δανεισμό.
     */
    public void withdraw() {
    	
    	if (! getState().equals(ItemState.AVAILABLE)) {
    		throw new LibraryException();
    	}
        setState(ItemState.WITHDRAWN);
    }

    /**
     * Το αντίτυπο έχει χαθεί και δεν είναι διαθέσιμο για δανεισμό.
     */
    public void lost() {
    	if (! getState().equals(ItemState.LOANED)) {
    		throw new LibraryException();
    	}
        setState(ItemState.LOST);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.valueOf(itemNumber);
    }

}
