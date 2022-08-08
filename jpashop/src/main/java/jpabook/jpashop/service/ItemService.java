package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	@Transactional
	public void updateItem(Long id, String name, int price) { //변경감지를 통해 데이터를 변경
//		Item findItem = itemRepository.findOne(itemId);
//		findItem.change(price, name, stockQuantity);   set을 일일이 하는것보다 이와같이 의미있게 만드는게 더 좋다.
//		findItem.setPrice(param.getPrice());
//		findItem.setName(param.getName());
//		findItem.setStockQuantity(param.getStockQuantity());
//		return findItem;
		Item item = itemRepository.findOne(id);
		item.setName(name);
		item.setPrice(price);
	}
	
	public List<Item> findItems() {
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

}
