package com.mgiandia.library.loans.service;

import com.mgiandia.library.loans.domain.Item;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestScoped
public class CatalogService {
    @Inject
    protected EntityManager em;

    public Set<Item> getItems(int bookId) {
        List<Item> items = em.createNamedQuery("itemsByBookId",Item.class).setParameter("bookId",bookId).getResultList();
        return new HashSet<Item>(items);
    }
}
