package com.partitioning.impl;
import java.util.ArrayList;
import java.util.List;

import com.partitioning.exception.IllegalListSizeException;
/**
 * partitionning the a list into a  list of sub lists using 
 * the predefined sublist function 
 * Please refer to the  documentation of the java.util.collections
 * For further details about the sublist function.
 * 
 * @author User
 *
 */
public class ListPartitioningWithSublist {
	/**
	 * partitioning a list into a list of sublists .
	 * @param list the input list .
	 * @param size the size of sublist.
	 * @return a list of sub list or empty list if the list is null .
	 * @throws IllegalListSizeException  if the  size of the sublist is not valid.
	 */
	public <T> List<List<T>> partition(List<T> list , int size ) throws IllegalListSizeException{
		if ( size <=0 ){
			throw new IllegalListSizeException() ;
		}
		List<List<T>> result= new ArrayList<List<T>>() ; 
		int i = 0 ; 
		if ( list != null ){
			while ( i<list.size() ){
				int bornSup =  i+ size < list.size() ? i+size : list.size(); 
				List<T> sublist =  list.subList(i, bornSup) ; 
				result.add(sublist) ; 
				i+=size ; 
			}
		}
		return result ;
	}
}
