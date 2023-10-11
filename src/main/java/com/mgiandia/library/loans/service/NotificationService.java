package com.mgiandia.library.loans.service;

import java.util.List;

import com.mgiandia.library.loans.service.EmailProvider;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.mgiandia.library.loans.LibraryException;
import com.mgiandia.library.loans.contacts.EmailAddress;
import com.mgiandia.library.loans.contacts.EmailMessage;
import com.mgiandia.library.loans.domain.Borrower;
import com.mgiandia.library.loans.domain.Loan;
import com.mgiandia.library.loans.persistence.LoanRepository;

/**
 * Υπηρεσία ενημέρωσης καθυστερημένων επιστροφών.
 * Η υπηρεσία που ενημερώνει με μήνυμα ηλεκτρονικού
 * ταχυδρομείου όσους δανειζομένους
 * έχουν καθυστερήσει την επιστροφή κάποιων
 * αντιτύπων.
 * @author Νίκος Διαμαντίδης
 *
 */
@RequestScoped
public class NotificationService {
	
	@Inject
	LoanRepository loanRepository;
	
    private EmailProvider provider;
    
    
    /**
     * Θέτει τον πάροχο του ηλεκτρονικού ταχυδρομείου.
     * @param provider Ο πάροχος ηλεκτρονικού ταχυδρομείου.
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }

    /**
     * Ενημερώνει όσους δανειζομένους.
     * έχουν καθυστερήσει της επιστροφή.
     * κάποιου αντιτύπου.
     */
    @Transactional
	public void notifyBorrowers() {
        if (provider == null) {
            throw new LibraryException();
        }
 
        List<Loan> allLoans = loanRepository.activeLoans();
        
        for (Loan loan : allLoans) {
            if (loan.isOverdue() && loan.getBorrower().getEmail()!=null &&
            		loan.getBorrower().getEmail().isValid()) {
                String message = composeMessage(loan.getItem().getBookId(),
                        -loan.daysToDue());
                sendEmail(loan.getBorrower(),
                        "Καθυστέρηση Αντιτύπου", message);
            }
        }
    }


    private void sendEmail(Borrower borrower,
            String subject, String message) {
        EmailAddress eMail  = borrower.getEmail();
        if (eMail == null || !eMail.isValid()) {
            return;
        }
        
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(eMail);
        emailMessage.setSubject(subject);
        emailMessage.setBody(message);
        provider.sendEmail(emailMessage);
    }


    private String composeMessage(int bookId, long overdue) {
        String message = "Έχετε καθυστερήσει το βιβλίο με Τίτλο ";
        message += bookId;
        message += " για ";
        message += overdue;
        message += " ημέρες ";
        return message;
    }
}
