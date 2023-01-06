package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public Optional<Order> findPreviousOrder(String orderId) {

		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_LINEITEMS_WITH_ORDERID, orderId); 
		SqlRowSet orderRs = jdbcTemplate.queryForRowSet(SQL_FIND_LATEST_ORDER, orderId); 

		List<LineItem> lineItems = new LinkedList<>(); 
		Order order = new Order(); 

		while(rs.next()) {
			lineItems.add(LineItem.creaLineItem(rs));
		} 

		if(!orderRs.next()) {
			return Optional.empty();
		}
		else {
			order = order.createOrder(orderRs, lineItems);
			return Optional.of(order); 
		}
	}


	public void addLineItem(List<LineItem> lineItems, String orderId) {
		
		List<Object[]> data = lineItems.stream()
		.map(li -> {
			Object[] l = new Object[3];
			l[0] = li.getItem();
			l[1] = li.getQuantity();
			l[2] = orderId;
			return l;
		})
		.toList();

		jdbcTemplate.batchUpdate(SQL_INSERT_INTO_LINEITEMS, data); 
	}

	public boolean insertPurchaseOrder(Order order) {
        return jdbcTemplate.update(SQL_INSERT_INTO_ORDERS, order.getOrderId(), order.getName(), order.getAddress(), order.getEmail(), order.getOrderDate()) > 0;
    }

}
