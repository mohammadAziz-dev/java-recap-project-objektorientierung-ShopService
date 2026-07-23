import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo{
    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        //return orders;
        return new ArrayList<>(orders); //This prevents outside code from directly changing the repository's internal list.
    }

    @Override
    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    @Override
    public void updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id().equals(updatedOrder.id())) {
                orders.set(i, updatedOrder);
                return;
            }
        }
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }
}
