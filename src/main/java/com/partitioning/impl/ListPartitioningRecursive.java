package com.partitioning.impl;
import java.util.ArrayList;
import java.util.List;

import com.partitioning.exception.IllegalListSizeException;
/**
 * Perform a list partitioning using
 *  a recursive mechanism.
 * @author Tarek CHAOUALI.
 *
 */
public class ListPartitioningRecursive {


	/**
	 * partitioning a  list into a list of sub lists 
	 * @param list the input list to be partitioned
	 * @param sublistSize the size of the sublist.
	 * @return a list of sub list or null if the list is null .
	 */
	public <T> List<List<T>> partition(List<T> list , int sublistSize ) throws IllegalListSizeException{
		if ( sublistSize <=0 ){
			throw new IllegalListSizeException() ;
		}
		List<List<T>> partitionedList= new ArrayList<List<T>>() ; 
		int counter = 0  ; 
		if( list != null && !list.isEmpty() ){
			grabSubList ( partitionedList , list , counter , sublistSize ) ;
		}
		return partitionedList ; 
	}

	/**
	 * grab the sub list from the input list .
	 * @param partitionedList the result list .
	 * @param list the list to paritionate .
	 * @param counter a counter to loop through the list items.
	 * @param subListSize the sublist size .
	 */
	private <T>void grabSubList (List<List<T>>  partitionedList  , List<T> list , Integer counter , int subListSize ){
		List<T> subList= new ArrayList<T>() ; 
		int i = 0  ; 
		while  ( i< subListSize && counter <list.size() ){
			subList.add(list.get(counter))   ;
			i++ ; 
			counter ++ ; 
		}
		partitionedList.add(subList) ; 
		if ( counter< list.size()){
			grabSubList ( partitionedList  ,list ,counter ,  subListSize ) ; 
		}
	}
}
