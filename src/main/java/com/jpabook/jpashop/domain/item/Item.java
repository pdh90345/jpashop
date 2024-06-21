package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
// Inheritance : 엔터티 상속관계를 정의하는데 사용(상위 클래스에 위치)
// stratch : 데이터베이스에 매핑할 때 사용할 전략
// SINGLE_TABLE : 하나의 테이블에 저장
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 상위 클래스에서 사용, 상속된 엔터티를 구분하기 위한 구분 컬럼
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    /*
     * stock 증가, settor 대신 이 안에서 quantity를 변경하는게 좋다
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
