package com.mgiandia.library.loans.service;

import com.mgiandia.library.loans.domain.Item;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestScoped
public class CatalogService {
    @Inject
    protected EntityManager em;

    @Transactional
    public Set<Item> getItems(Integer bookId) {
        List<Item> items = em.createNamedQuery("itemsByBookId",Item.class).setParameter("bookId",bookId).getResultList();
        return new HashSet<Item>(items);
    }

    @Transactional
    public void addItem(Item item, Integer bookId) {
        if (item != null) {
            item.setBookId(bookId);
        }
    }

    @Transactional
    public void removeItem(Item item) {
        if (item != null) {
            item.setBookId((Integer)null);
        }
    }
}
