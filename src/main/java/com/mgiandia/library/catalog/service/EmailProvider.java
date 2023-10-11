package com.mgiandia.library.catalog.service;

import com.mgiandia.library.catalog.contacts.EmailMessage;

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
