package com.partitioning;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.*;

import com.partitioning.exception.IllegalListSizeException;
import com.partitioning.impl.ListPartitioningRecursive;
import com.partitioning.utils.PartitioningUtils;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *  Test units for {@link ListPartitioningRecursive}
 *  this test unit cover all the possible 
 *  test cases of a list partitioning using 
 *  a recursive meschanism.
 *  
 * @author Tarek CHAOUALI
 *
 */
public class ListPartitioningRecursiveTest  {

	/**
	 * The message logger.
	 */
	public static Logger LOGGER = LoggerFactory.getLogger(ListPartitioningRecursiveTest.class) ; 

	/**
	 * {@link ListPartitioningRecursive}
	 */
	 static ListPartitioningRecursive listPartitioningRecursive ;

	/**
	 * setups before class execution.
	 */
	@BeforeClass
	public static void setUp(){
		listPartitioningRecursive = new ListPartitioningRecursive() ;
	}

	/**
	 * test partitioning a null list .
	 * @throws IllegalListSizeException 
	 */
	@Test
	public void testPartitioningWithNullList(){
		List<List<String>>  partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(PartitioningUtils._NULL_ITEMS_LIST, 2) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning  null value ", ex);
			Assert.fail("partitioning a null  item list failed" );
		}
		assertThat(partitionedList, IsEmptyCollection.empty());
	}
	/**
	 *  test partitioning an empty list
	 */
	@Test
	public void testPartitioningWithEmptyList(){

		List<List<String>> partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(PartitioningUtils._EMPTY_ITEMS_LIST, 2) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning  list failed", ex);
			Assert.fail("partitioning an empty  item list failed" );
		}
		assertThat(partitionedList, IsEmptyCollection.empty());
	}

	/**
	 * test partitioning a valid list 
	 * using a non valid sublist size .
	 * @throws  IllegalListSizeException if the size of the sublist if illegal.
	 */
	@Test(expected=IllegalListSizeException.class)
	public void testPartitioningWithIllegalSublistSize() throws IllegalListSizeException{
		List<String> itemList = PartitioningUtils.createItemsList(3)  ; 
		List<List<String>> partitionedList = listPartitioningRecursive.partition(itemList, -2) ;
		Assert.fail("partitioning a list with invalid sublist size" );
	}

	/**
	 * test partitioning a list which its size 
	 * is lower then the sub list size .
	 */
	@Test
	public void testPartitioningWithOneitemList(){
		List<String> onItemList = PartitioningUtils.createItemsList(1)  ; 
		List<List<String>> partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(onItemList, 3) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning one item list failed", ex);
			Assert.fail("partitioning one item list failed");
		}
		assertThat(onItemList, hasSize(1));
		assertThat (partitionedList , is(PartitioningUtils.createOneItemListAfterPartitioning())) ; 
		//test if the  the only sublist is equal the  input list .
		assertThat(partitionedList.get(0), is(onItemList));

	}

	/**
	 * test partitioning a list which its size 
	 * is greater than the sublist size .
	 */
	@Test
	public void testPartitioningListWhichItsSizeIsgreater(){
		List<String> itemList = PartitioningUtils.createItemsList(5)  ; 
		List<List<String>> partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(itemList, 3) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning one item list failed", ex);
			Assert.fail("partitioning one item list failed , list size is greater than the sublist size ");
		}
		assertThat(itemList, hasSize(5));
		assertThat(partitionedList, hasSize(2));
	}

	/**
	 * 
	 * test partitioning a list which its size 
	 * is lower than the sublist size .
	 */
	@Test
	public void testPartitioningListWhichItsSizeIsLower(){
		List<String> itemList = PartitioningUtils.createItemsList(2)  ; 
		List<List<String>> partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(itemList, 5) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning one item list failed", ex);
			Assert.fail("partitioning one item list failed , list size is lower than the sublist size ");
		}
		assertThat(itemList, hasSize(2));
		assertThat(partitionedList, hasSize(1));
		//test if the  the only sublist is equal the  input list .
		assertThat(partitionedList.get(0), is(itemList));
	}

	/**
	 * 
	 * test partitioning a list which its size 
	 * is a multiple of the the sublist size .
	 */
	@Test
	public void testPartitioningListWhichItsSizeIsAMultipleOfSublistSize(){
		List<String> itemList = PartitioningUtils.createItemsList(9)  ; 
		List<List<String>> partitionedList = null ; 
		try{
			partitionedList = listPartitioningRecursive.partition(itemList, 1) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning one item list failed", ex);
			Assert.fail("partitioning one item list failed , list size is a multiple of the sublist size ");
		}
		assertThat(itemList, hasSize(9));
		assertThat(partitionedList, hasSize(9));
	}

}
