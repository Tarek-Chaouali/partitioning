package com.partitioning.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils class for partitioning test usage .
 * @author Tarek CHAOUALI
 *
 */
public class PartitioningUtils {

	public static List<String> _NULL_ITEMS_LIST =  null  ;
	public static List<String> _EMPTY_ITEMS_LIST =  new ArrayList<String>()  ; 



	/**
	 * create a list of items with specified list size
	 * @param listSize the list size .
	 * @return a list with specified list size .
	 */
	public static List<String> createItemsList( int listSize){
		List<String > items = new ArrayList<String>()  ; 
		for (int i = 0 ; i< listSize  ; i++){
			items.add("item"+i) ; 
		}
		return items  ; 
	}
	
	/**
	 * create the expecting resutl of partitioning one item list 
	 * @return a list with specified list size .
	 */
	public static List<List<String>>  createOneItemListAfterPartitioning(){
		List<String> oneItemList  =  createItemsList(1) ;
		List<List<String>> oneItemListPartitioned = new ArrayList<List<String>>() ; 
		oneItemListPartitioned.add(oneItemList) ; 
		return oneItemListPartitioned ; 
	}
	

}
