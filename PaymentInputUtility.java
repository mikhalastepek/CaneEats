package edu.bus.bte324.canehub.utilities;

import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.util.Scanner;

public class PaymentInputUtility {
    
    public static PaymentInfo getPaymentInfoFromConsole()
    {

        Scanner cardScanner = new Scanner(System.in);
        System.out.println("Input Payment Info");
        System.out.print("Input the Card Number :");
        String cardNum = cardScanner.nextLine();
        System.out.print("Input the Card Name :");
        String cardName = cardScanner.nextLine();
        System.out.println("Input the Card Type :");
        String cardType = cardScanner.nextLine();
        System.out.println("Input the Card CVV :");
        String cardCVV = cardScanner.nextLine();
        System.out.println("Input the expiration Date:");
        String expDate = cardScanner.nextLine();
        
        PaymentInfo paymentInfo = new PaymentInfo(cardNum,cardName,cardType,Integer.parseInt(cardCVV),expDate);

        return paymentInfo;
    }
    
}
