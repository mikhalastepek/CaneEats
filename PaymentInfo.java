package edu.bus.bte324.canehub.infoclasses;

import java.util.Date;

public class PaymentInfo {
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public int getCvvCode() {
        return cvvCode;
    }

    public String getExpDate() {
        return expDate;
    }

    private String cardNumber;
    private String cardName;
    private String cardType; //Euro, Visa, Mastercard
    private int cvvCode;
    private String expDate;

    public PaymentInfo(String cardNumber, String cardName, String cardType, int cvvCode, String expDate) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardType = cardType;
        this.cvvCode = cvvCode;
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "cardNumber='" + "masked ****" + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cvvCode=" + "Masked CVV Code" +
                ", expDate=" + expDate +
                '}';
    }

    public String toStringForFile() {
        return  cardNumber + " , " + cardName + " , "+ cardType + " , " + cvvCode +" , " + expDate ;
    }
}
