package com.mgiandia.library.loans.service;

import com.mgiandia.library.loans.domain.Item;
import com.mgiandia.library.loans.domain.ItemState;
import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

/**
 * Mostly stateless class (service), that incorporates domain logic that need to be moved from the extracted domain model.
 * You can introduce one class per extracted domain model
 */
@RequestScoped
public class CatalogService {


    /**
     * Provide as parameter the id of the entity, as well as other attributes required by the moved logic.
     * The master entity, as well as its fields would be available in the context of the original domain call.
     * Otherwise, set placeholders here to be filled by the programmer (use chat GPT to fill them?)
     *
     * Retrieve all associated items for the given master entity id and use the same name as in the Book class
     * @param bookId
     * @return
     */
    public List<Item> getAvailableItems(List<Item> items){

        List<Item> availableItems = new ArrayList<>();
        for(Item i: items){
            if (i.getState().equals(ItemState.AVAILABLE)){
                availableItems.add(i);
            }
        }
        return availableItems;

    }

}
