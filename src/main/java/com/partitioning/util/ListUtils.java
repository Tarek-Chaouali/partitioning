package com.partitioning.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author User
 *
 */
public class ListUtils {
	/**
	 * LOGGER.
	 */
	public static Logger LOGGER = LoggerFactory.getLogger(ListUtils.class) ; 

	public static <T> void printList(List<List<T>> result){
		StringBuilder strBuilder = new StringBuilder() ; 
		strBuilder.append("[") ; 
		for (List<T> list : result){
			strBuilder.append("[") ; 
			for ( T item  : list){
				strBuilder.append(item.toString()) ; 
				strBuilder.append(",") ; 
			}
			strBuilder.append("] , ") ; 
		}
		strBuilder.append("]") ; 
		LOGGER.info(strBuilder.toString());
	}
}
