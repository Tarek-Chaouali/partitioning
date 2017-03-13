package com.partitioning;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.List;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.BeforeClass;
import com.partitioning.exception.IllegalListSizeException;
import com.partitioning.impl.ListPartitioningRecursive;
import com.partitioning.impl.ListPartitioningWithSublist;
import com.partitioning.utils.PartitioningUtils;

/**
 *  Test units for {@link ListPartitioningRecursive}
 *  this test unit cover all the possible 
 *  test cases of a list partitioning using 
 *  a sublist predefined function.
 *  
 * @author Tarek CHAOUALI
 *
 */
public class ListPartitioningWithSublistTest {
	/**
	 * The message logger.
	 */
	public static Logger LOGGER = LoggerFactory.getLogger(ListPartitioningWithSublistTest.class) ; 

	/**
	 * {@link ListPartitioningRecursive}
	 */
	static  ListPartitioningWithSublist listPartitioningWithSublist ;


	/**
	 * setups before class execution.
	 */
	@BeforeClass
	public static void setUp(){
		listPartitioningWithSublist = new ListPartitioningWithSublist() ;
	}

	/**
	 * test partitioning a null list .
	 * @throws IllegalListSizeException 
	 */
	@Test
	public void testPartitioningWithNullList(){
		List<List<String>>  partitionedList = null ; 
		try{
			partitionedList = listPartitioningWithSublist.partition(PartitioningUtils._NULL_ITEMS_LIST, 2) ; 
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
			partitionedList = listPartitioningWithSublist.partition(PartitioningUtils._EMPTY_ITEMS_LIST, 2) ; 
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
		List<List<String>> partitionedList = listPartitioningWithSublist.partition(itemList, -2) ;
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
			partitionedList = listPartitioningWithSublist.partition(onItemList, 3) ; 
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
			partitionedList = this.listPartitioningWithSublist.partition(itemList, 3) ; 
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
			partitionedList = this.listPartitioningWithSublist.partition(itemList, 5) ; 
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
			partitionedList = this.listPartitioningWithSublist.partition(itemList, 1) ; 
		}catch(IllegalListSizeException ex){
			LOGGER.error("partitioning one item list failed", ex);
			Assert.fail("partitioning one item list failed , list size is a multiple of the sublist size ");
		}
		assertThat(itemList, hasSize(9));
		assertThat(partitionedList, hasSize(9));
	}

}
