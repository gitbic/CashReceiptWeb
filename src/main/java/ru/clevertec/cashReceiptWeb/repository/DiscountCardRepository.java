package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, String> {

}