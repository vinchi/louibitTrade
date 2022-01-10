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
});