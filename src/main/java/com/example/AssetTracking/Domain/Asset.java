package com.example.AssetTracking.Domain;

import Common.Model;
import Common.StringUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "asset")
public class Asset extends Model {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "cost")
    @NotNull  (message = "The Title of asset can not be blank")
    @Digits(integer =100,fraction = 4, message = "Only enter upto 100 integer values and 4 decimal values")
    private BigDecimal cost;

    @Column(name = "depreciation_rate")
    @NotNull  (message = "The Title of asset can not be blank")
    @Digits(integer = 3, fraction = 4, message = "Only enter upto 3 integer values and 4 decimal values")
    private BigDecimal depreciationRate;

    @Column(name = "purchase_date")
    @PastOrPresent (message = "The Purchase date can only be of past or present")
    private LocalDate purchaseDate;

    @Column(name = "title")
    @NotBlank (message = "The Title of asset can not be blank")
    @NotNull  (message = "The Title of asset can not be blank")
    @Size(min = 3,max = 100)
    private String title;

    public Asset(){
        super();
    }

    public Asset(BigDecimal cost, BigDecimal depreciationRate, LocalDate purchaseDate, String title) {
        setCost(cost);
        setDepreciationRate(depreciationRate);
        setPurchaseDate(purchaseDate);
        setTitle(title);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setCost(final BigDecimal cost) {
        if(cost==null){
            throw new NullPointerException("The asset Depreciation Rate can not be blank");
        }

        this.cost=cost;
    }

    public void setDepreciationRate(final BigDecimal depreciationRate) {
        if(depreciationRate ==null){
            throw new NullPointerException("The asset Depreciation Rate can not be blank");
        }

        this.depreciationRate = depreciationRate;
    }

    public void setPurchaseDate(final LocalDate purchaseDate) {
        if(purchaseDate == null){
            throw new NullPointerException("The asset Date can not be blank");
        }

        this.purchaseDate = purchaseDate;
    }

    public void setTitle(final String title) {
        if(StringUtil.isBlank(title)) {
            throw new NullPointerException("The asset title can not be blank");
        }
        this.title = title;
    }
}
