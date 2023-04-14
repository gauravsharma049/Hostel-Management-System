
const paymentStart = () => {

    let amount = $("#amount").val();
    let hostellerId = $("#hostellerId").val();

    if (amount == null || amount == "") {
        swal("Invalid Amount !!", "enter a valid amount", "error");
        return;
    }

    $.ajax({
        url: '/paymentTest/test',
        data: JSON.stringify({ amount: amount, currency: "INR", receipt: "receipt#1", payment_capture: 1 }),
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data.status == "created") {
                var options = {
                    "key": "rzp_test_vuSN2rntCawDA3",
                    "amount": data.amount, // amount in paise
                    "name": "Hostel ERP",
                    "currency": data.currency,
                    "description": "Pay Your hostel fees",
                    "image": "https://e7.pngegg.com/pngimages/953/232/png-clipart-payment-gateway-computer-icons-e-commerce-payment-system-payment-blue-text.png",
                    "order_id": data.id, // order id
                    "handler": function (response) {
                        // handle the payment response
                        paymentStatus(amount, hostellerId, true, response.razorpay_payment_id);
                        console.log(response);
                    },
                    "prefill": {
                        "name": userProfile.name,
                        "email": userProfile.email,
                        "contact": userProfile.contact
                    },
                    "notes": {
                        "address": ""
                    },
                    "theme": {
                        "color": "#4e73df"
                    }
                };
                var rzp1 = new Razorpay(options);
                    rzp1.on('payment.failed', function (response) {
                        // console.log(response.error.code);
                        // console.log(response.error.description);
                        // console.log(response.error.source);
                        // console.log(response.error.step);
                        // console.log(response.error.reason);
                        // console.log(response.error.metadata.order_id);
                        // console.log(response.error.metadata.payment_id);
                        paymentStatus(amount, hostellerId, false)
                    });
                    rzp1.open();
               
            }
        },
        error: function (e) {
            swal("Payment Failed", "Oops Someting Went Wrong !", "error");
            console.log(e);
        }
    });
}

const paymentStatus = (amount,hostellerId, status, paymentId) => {

    $.ajax({
        url: '/payment',
        data: JSON.stringify({amount: amount, hostellerId: hostellerId, paymentStatus: status, paymentId: paymentId}),
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            swal("Payment Success!!", "Payment has been successfully made to the beneficiary account!", "success");
            console.log(data);
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    });
    setTimeout(() => {
        window.location.replace("/payment");
    }, 3000);
   
}

const pay = () => {
    let amount = $("#amount").val();
    let hostellerId = $("#hostellerId").val();

    if (amount == null || amount == "") {
        swal("Invalid Amount !!", "enter a valid amount", "error");
        return;
    }
    
    $.ajax({
        url: '/payment',
        data: JSON.stringify({amount: amount, hostellerId: hostellerId, paymentStatus: true, paymentId: "paymentId"}),
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            swal("Payment Success!!", "Payment has been successfully made to the beneficiary account!", "success");
            console.log(data);
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    });

}
