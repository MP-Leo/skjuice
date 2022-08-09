package br.com.skjuice.repositories;

import br.com.skjuice.entities.Product;
import br.com.skjuice.entities.ProductPage;
import br.com.skjuice.entities.ProductRequisition;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepositorie {


    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepositorie(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilters(ProductPage productPage,
                                            ProductRequisition productSearchCriteria){

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(productSearchCriteria, productRoot);

        criteriaQuery.where(predicate);
        setOrder(productPage, criteriaQuery, productRoot);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        typedQuery.setMaxResults(productPage.getPageSize());

        Pageable pageable = getPeageable(productPage);

        long productCount = getProductCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, productCount);
    }


    private Predicate getPredicate(ProductRequisition productRequisition,
                                   Root<Product> productRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(productRequisition.getDescription())){
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    productRoot.get("description")),
                            "%".concat(productRequisition.getDescription().toLowerCase().concat("%")))
            );
        }

        if(Objects.nonNull(productRequisition.getName())){
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    productRoot.get("name")),
                            "%".concat(productRequisition.getName().toLowerCase().concat("%")))
            );
        }

        if(Objects.nonNull(productRequisition.getFlavor())){
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    productRoot.get("flavor")),
                            "%".concat(productRequisition.getFlavor().toLowerCase().concat("%")))
            );
        }

        if(Objects.nonNull(productRequisition.getMinPrice()) || Objects.nonNull(productRequisition.getMaxPrice())){
            predicates.add(
                    criteriaBuilder.between(
                            productRoot.get("price"),
                            productRequisition.getMinPrice(), productRequisition.getMaxPrice())
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ProductPage productPage, CriteriaQuery<Product> criteriaQuery, Root<Product> productRoot) {
        if(productPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSortBy())));
        }
    }

    private Pageable getPeageable(ProductPage productPage) {
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        return PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);

    }


    private long getProductCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
