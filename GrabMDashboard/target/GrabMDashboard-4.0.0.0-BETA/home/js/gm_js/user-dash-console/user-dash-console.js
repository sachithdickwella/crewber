$(document).ready(function () {
    $(".select2").select2();
    subscription_chart(650,1200);
});

var subscription_chart = function (subscribed,unsubscribed) {

    Morris.Donut({
        element: 'donut-example',
        data: [
            {label: "Subscribed users", value: subscribed},
            {label: "Yet to subscribe", value: unsubscribed}
        ],
        backgroundColor: '#ffff',
        labelColor: '#070707',
        colors: [
            '#437DCC',
            '#ea645d'
        ]
    });
};
