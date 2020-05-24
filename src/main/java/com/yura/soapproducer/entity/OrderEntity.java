package com.yura.soapproducer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private Double price;

    public OrderEntity() {
    }

    public OrderEntity(Builder builder) {
        this.id = builder.id;
        this.userEntity = builder.userEntity;
        this.price = builder.price;
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderEntity that = (OrderEntity) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(userEntity, that.userEntity) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEntity, price);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", userEntity=" + userEntity +
                ", price=" + price +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private UserEntity userEntity;
        private Double price;

        private Builder() {

        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
