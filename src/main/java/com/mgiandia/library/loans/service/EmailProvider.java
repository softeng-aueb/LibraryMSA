package com.mgiandia.library.loans.service;

import com.mgiandia.library.loans.contacts.EmailMessage;

/**
 * Παροχή υπηρεσίας ηλεκτρονικού ταχυδρομείου.
 * @author Νίκος Διαμαντίδης
 *
 */
public interface EmailProvider {
    /**
     * Αποστέλλει μήνυμα ηλεκτρονικού ταχυδρομείου.
     * @param message Το μήνυμα ηλεκτρονικού ταχυδρομείου.
     */
    void sendEmail(EmailMessage message);
}
