package com.zappos.ilovezappos;

/**
 * Created by Dhruvil on 28-01-2017.
 */

public class ProductInfo {

    public String thumbnailImageUrl;
    public String brandName;
    public String originalPrice;
    public String price;
    public String percentOff;
    public String productName;
    public String type;
    public boolean differ;
    public String productUrl;

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDiffer(){
        this.differ = differ;
    }
    public boolean getDiffer() {
        return differ;
    }



    public ProductInfo(String r, String bn, String op, String p, String po, String pn, String t, boolean d, String pu) {
        this.thumbnailImageUrl = r;
        this.brandName = bn;
        this.originalPrice = op;
        this.price = p;
        this.percentOff = po;
        this.productName = pn;
        this.type = t;
        this.differ = d;
        this.productUrl = pu;
    }
}
