package com.sola.instagram.util;

import java.util.ArrayList;
import java.util.Iterator;

public class PaginatedCollection<E> implements Iterable<E>{
	ArrayList<E> list;
	PaginationIterator<E> iterator;
	
	public PaginatedCollection(ArrayList<E> list, PaginationIterator<E> iterator){
		this.list 	  = list;
		this.iterator = iterator;
	}
	
    public Iterator<E> iterator() {        
        return iterator.reset();
    }

    public int size() {        
        return list.size();
    }
    
    public E get(int index) {        
        return list.get(index);
    }    
}
