package com.example.AssetTracking.domain;

import common.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Generated;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static common.StringUtil.*;

/**
 * Create an asset.
 */
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "asset")
public class Asset implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "cost")
    @NotNull(message = "The Title of asset can not be blank")
    @Digits(integer = 100, fraction = 4, message = "Only enter upto 100 integer values and 4 decimal values")
    private BigDecimal cost;

    @Column(name = "depreciation_rate")
    @NotNull(message = "The Title of asset can not be blank")
    @Digits(integer = 3, fraction = 4, message = "Only enter upto 3 integer values and 4 decimal values")
    private BigDecimal depreciationRate;

    @Column(name = "purchase_date")
    @PastOrPresent(message = "The Purchase date can only be of past or present")
    private LocalDate purchaseDate;

    @Column(name = "title")
    @NotBlank(message = "The Title of asset can not be blank")
    @NotNull(message = "The Title of asset can not be blank")
    @Size(min = 3, max = 100)
    private String title;

    /**
     * Hidden to prevent instantiation.
     */
    private Asset() {
        super();
    }

    /**
     * Create an asset having specific attributes.
     *
     * @param title            The title of the asset.
     * @param cost             The cost of the asset.
     * @param depreciationRate The depreciation Rate of the asset.
     * @param purchaseDate     The purchase date of the asset.
     */
    public Asset(String title
            , BigDecimal cost
            , BigDecimal depreciationRate
            , LocalDate purchaseDate) {

        setTitle(title);
        setCost(cost);
        setDepreciationRate(depreciationRate);
        setPurchaseDate(purchaseDate);
    }

    /**
     * Get the cost of the asset.
     *
     * @return the cost of the asset.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Get the depreciation rate of the asset.
     *
     * @return the depreciation rate of the asset.
     */
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    /**
     * Get the id of the asset
     *
     * @return The id of the asset
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the purchase date of the asset.
     *
     * @return The purchase date of the asset.
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Get the title of the asset.
     *
     * @return the title of the asset
     */
    public String getTitle() {
        return title;
    }

    public void setCost(final BigDecimal cost) {
        if (cost == null) {
            throw new NullPointerException("The asset Depreciation Rate can not be blank");
        }

        this.cost = cost;
    }

    public void setDepreciationRate(final BigDecimal depreciationRate) {
        if (depreciationRate == null) {
            throw new NullPointerException("The asset Depreciation Rate can not be blank");
        }

        this.depreciationRate = depreciationRate;
    }

    public void setPurchaseDate(final LocalDate purchaseDate) {
        if (purchaseDate == null) {
            throw new NullPointerException("The asset Date can not be blank");
        }

        this.purchaseDate = purchaseDate;
    }

    public void setTitle(final String title) {
        if (isBlank(title)) {
            throw new NullPointerException("The asset title can not be blank");
        }
        this.title = title;
    }
}
