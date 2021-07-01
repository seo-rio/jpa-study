package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //==비즈니스 로직==//
    public void cancel() {
        // 재고 수량을 원상 복구 해준다.
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        // 주문 가격 * 주문 수량
        return getOrderPrice() * getCount();
    }
}
