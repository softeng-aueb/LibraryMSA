package com.mgiandia.library.persistence;


import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.RequestScoped;

import com.mgiandia.library.loans.domain.Item;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;

@RequestScoped
public class ItemRepository  implements PanacheRepositoryBase<Item, Integer> {

    /**
     * Could be left unimplemented as a placeholder to be filled by the programmer (or chat GPT)
     * @param bookId
     * @return
     */
    public List<Item> findAssociatedItemsForBook(int bookId){
        return list("select i from Item i where i.book.id = :bookId", Parameters.with("bookId", bookId).map());
    }
	
}
