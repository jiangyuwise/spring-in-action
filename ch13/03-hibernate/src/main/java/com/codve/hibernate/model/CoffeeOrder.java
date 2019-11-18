package com.codve.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/18 16:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "T_ORDER")
public class CoffeeOrder extends BaseEntity {

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;

    public enum OrderState {
        INIT, PAID, BREWING, BREWED, TAKEN, CANCELED
    }



}
