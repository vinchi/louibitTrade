window.addEventListener("load", function(event) {
    let ws1 = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@trade');

    let stockPriceElement = document.getElementById("stock-price");

    let lastPrice = null;

    ws1.onmessage = (event) => {
        let stockObject = JSON.parse(event.data);
        let price = parseFloat(stockObject.p).toFixed(2);
        console.log(price);
        stockPriceElement.innerText = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
//        stockPriceElement1.innerText = "$" + price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
//        stockPriceElement2.innerText = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
//        + (!lastPrice || lastPrice === price ? "" : price > lastPrice ? ' 🠕' : ' 🠗');

        stockPriceElement.style.color = !lastPrice || lastPrice === price ? 'white' : price > lastPrice ? 'green' : 'red';
//        stockPriceElement1.style.color = 'white';
//        stockPriceElement2.style.color = !lastPrice || lastPrice === price ? 'white' : price > lastPrice ? 'green' : 'red';

        lastPrice = price;
    }

    let ws2 = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@miniTicker');
    let stock24hHigh = document.getElementById("24h-high");
    let stock24hLow = document.getElementById("24h-low");
    let stock24hVolume = document.getElementById("24h-volume");
    let stock24hVolumeUsdt = document.getElementById("24h-volume-usdt");

    let lastPrice1 = null;
    let lastPrice2 = null;
    let lastPrice3 = null;
    let lastPrice4 = null;

    ws2.onmessage = (event) => {
        let stockObject = JSON.parse(event.data);

        let price1 = parseFloat(stockObject.h).toFixed(2);
        let price2 = parseFloat(stockObject.l).toFixed(2);
        let price3 = parseFloat(stockObject.v).toFixed(2);
        let price4 = parseFloat(stockObject.q).toFixed(2);

        stock24hHigh.innerText = price1.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
        stock24hLow.innerText = price2.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
        stock24hVolume.innerText = price3.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
        stock24hVolumeUsdt.innerText = price4.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;

        lastPrice1 = price1;
        lastPrice2 = price2;
        lastPrice3 = price3;
        lastPrice4 = price4;
    }

    let ws3 = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@ticker');
    let stock24hChangElement = document.getElementById("24h-change");
    let last24hChangePricePercent = null;

    ws3.onmessage = (event) => {
        let stockObject = JSON.parse(event.data);
        let price1 = parseFloat(stockObject.p).toFixed(2);
        let price2 = parseFloat(stockObject.P).toFixed(2);
        stock24hChangElement.innerText = price1.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "  " + price2 + "%";

        stock24hChangElement.style.color = price1 > 0 ? 'green' : 'red';

        last24hChangePricePercent = price1;
    }

    let ws4 = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@depth10@1000ms');

    ws4.onmessage = (event) => {
        let stockObject = JSON.parse(event.data);
        let bids = stockObject.bids;
        let asks = stockObject.asks;
        let box_1 = document.getElementsByClassName('box1')
        let box_2 = document.getElementsByClassName('box2');
        for(var key in bids) {
            let price = parseFloat(asks[9-key][0]).toFixed(2);
            var elem1 = document.getElementsByClassName("bids");
            elem1[key].innerText = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            $(elem1[key]).click(function() {
                $("#bPrice").val($(this).text());
            });

            let amount = parseFloat(asks[9-key][1]).toFixed(5);
            elem1[key].style.color = "red";
            var elem2 = document.getElementsByClassName("amount");
            elem2[key].innerText = amount;
            let total = parseFloat(price * amount).toFixed(5);
            var elem3 = document.getElementsByClassName("total");
            elem3[key].innerText = total.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");

            var tot = parseInt(total/1000);
            if(tot > 100) {
                tot = 100;
            }

            if(tot > 0) {
                box_1[key].style.width=tot + "%";
                box_1[key].style.backgroundColor="red";
            } else {
                box_1[key].style.backgroundColor='rgb(' + 33 + ', ' + 37 + ', ' + 41 +')';
            }


            let price1 = parseFloat(bids[key][0]).toFixed(2);
            var ele1 = document.getElementsByClassName("asks");
            ele1[key].innerText = price1.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            $(ele1[key]).click(function() {
                $("#bPrice").val($(this).text());
            });
            ele1[key].style.color = "green";


            let amount1 = parseFloat(bids[key][1]).toFixed(5);
            var ele2 = document.getElementsByClassName("amount1");
            ele2[key].innerText = amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            let total1 = parseFloat(price * amount).toFixed(5);
            var ele3 = document.getElementsByClassName("total1");
            ele3[key].innerText = total1;

            var tot1 = parseInt(total1/1000);
            if(tot1 > 100) {
                tot1 = 100;
            }

            if(tot > 0) {
                box_2[key].style.width=tot + "%";
                box_2[key].style.backgroundColor="green";
            } else {
                box_2[key].style.backgroundColor='rgb(' + 33 + ', ' + 37 + ', ' + 41 +')';
            }
        }
    }

    let ws5 = new WebSocket('wss://stream.binance.com:9443/ws/btcusdt@aggTrade');
    var arr = new Array(10);

    ws5.onmessage = (event) => {
        let stockObject = JSON.parse(event.data);
        let p = stockObject.p;//가격
        let a = stockObject.q;//수량
        let t = stockObject.E;//시간
        let millis = new Date(t);
        let hour = millis.getHours().toString().padStart(2, '0');
        let minute = millis.getMinutes().toString().padStart(2, '0');
        let second = millis.getSeconds().toString().padStart(2, '0');
        let times = hour + ":" + minute + ":" + second;

        let price = parseFloat(p).toFixed(2);
        let amount = parseFloat(a).toFixed(5);
        var elem1 = document.getElementsByClassName("time");
        var elem2 = document.getElementsByClassName("tPrice");
        var elem3 = document.getElementsByClassName("tAmount");
        var obj = [times, price, amount];
        arr.push(obj)

        if(arr.length > 10) {
            arr.shift()
        }

        for(var key in arr) {
            elem1[key].innerText = arr[key][0];
            elem2[key].style.color = arr[key][1] > lastPrice ? "green" : "red";
            elem2[key].innerText = arr[key][1].toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            $(elem2[key]).click(function() {
                $("#bPrice").val($(this).text());
            });
            elem3[key].innerText = arr[key][2].toString();

        }
    }

    document.getElementById("custom-slider").addEventListener("input", function(event) {
        let value = event.target.value;
        let available = document.getElementById("available").value;
        let amount = parseInt(value) * parseInt(available.replace(",", "")) / 100;
        document.getElementById("amount").value = amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    });

    document.getElementById("custom-slider1").addEventListener("input", function(event) {
        let value = event.target.value;
        console.group(value);
        document.getElementById("leverage").value = value;
    });

    document.getElementById("custom-slider3").addEventListener("input", function(event) {
        let value = event.target.value;
        let available = document.getElementById("available3").value;
        let amount = parseInt(value) * parseInt(available.replace(",", "")) / 100;
        document.getElementById("amount3").value = amount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    });

    document.getElementById("custom-slider4").addEventListener("input", function(event) {
        let value = event.target.value;
        console.log(value);
        document.getElementById("leverage4").value = value;
    });
});

