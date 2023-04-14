package com.hostel.controller.paymentController;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class MakePayment {
    @GetMapping("/paymentTest")
    public String paymentTest(){
        return "pay/paymentTest";
    }

    // @Autowired
    // private RazorpayClient razorpayClient;

    @Value("${razorpay.keyId}")
    private String keyId;
    
    @Value("${razorpay.keySecret}")
    private String keySecret;

    @PostMapping("/paymentTest/test")
    @ResponseBody
    public String initiatePayment(@RequestBody PaymentRequest paymentRequest) throws RazorpayException {
        
        RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", paymentRequest.getAmount() * 100);
        options.put("currency", paymentRequest.getCurrency());
        options.put("receipt", paymentRequest.getReceipt());
        options.put("payment_capture", paymentRequest.getPaymentCapture());

        Order order = razorpayClient.Orders.create(options);
        System.out.println(order);
        return order.toString();
    }

//     @PostMapping("/payment/success")
//     public String completePayment(@RequestParam("razorpay_payment_id") String paymentId,
//                                   @RequestParam("razorpay_order_id") String orderId,
//                                   @RequestParam("razorpay_signature") String signature) throws RazorpayException {

//         razorpayClient = new RazorpayClient(keyId, keySecret);

//         JSONObject attributes = new JSONObject();
//         attributes.put("razorpay_payment_id", paymentId);
//         attributes.put("razorpay_order_id", orderId);
//         attributes.put("razorpay_signature", signature);
                                   
//         boolean isValid = razorpayClient.Utility.verifyPaymentSignature(attributes);
//         if(isValid) {
//             // payment successful, add business logic here
//             return "Payment Successful!";
//         } else {
//             // payment failed, add business logic here
//             return "Payment Failed!";
//         }
//     }
}

class PaymentRequest{
    private double amount;
    private String currency;
    private String receipt;
    private boolean paymentCapture;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public boolean getPaymentCapture() {
        return paymentCapture;
    }

    public void setPaymentCapture(boolean paymentCapture) {
        this.paymentCapture = paymentCapture;
    }
}