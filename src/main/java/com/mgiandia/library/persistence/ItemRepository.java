package com.mgiandia.library.persistence;


import jakarta.enterprise.context.RequestScoped;

import com.mgiandia.library.domain.Item;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class ItemRepository  implements PanacheRepositoryBase<Item, Integer> {

	
}
