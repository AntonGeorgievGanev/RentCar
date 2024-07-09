function price() {
    let selectedCar = document.getElementById('carId');
    let selectedCarOption = selectedCar.options[selectedCar.selectedIndex];
    let selectedCarOptionText = selectedCarOption.text;

    if (selectedCarOptionText !== "Select Car") {
        let allDataArr = selectedCarOptionText.split(" | ");
        let lastIndex = allDataArr[allDataArr.length - 1];
        let onlyPriceWithDollar = lastIndex.split(": ")[1];
        var onlyPrice = parseFloat(onlyPriceWithDollar.split(" ")[0]);
        document.getElementById('carPrice').textContent = 'Car Price: ' + onlyPriceWithDollar;
    } else {
        var onlyPrice = 0;
        document.getElementById('carPrice').textContent = 'Car Price: ';
    }

    let selectedExtra = document.getElementById('extraId');
    let selectedExtraOption = selectedExtra.options[selectedExtra.selectedIndex];
    let selectedExtraText = selectedExtraOption.text;

    if (selectedExtraText !== "Select extra") {
        var extraPrice = parseFloat(selectedExtraText.split(": ")[1].split(" ")[0]);
        document.getElementById('extraPrice').textContent = 'Extra Price: ' + selectedExtraText.split(": ")[1];
    } else {
        var extraPrice = 0;
        document.getElementById('extraPrice').textContent = 'Extra Price: ';
    }

    calculateTotalPrice(onlyPrice, extraPrice);
}

function calculateTotalPrice(pricePerDay, extraPrice) {
    let pickUpDate = document.getElementById('pickUpDate').value;
    let dropOffDate = document.getElementById('dropOffDate').value;

    if (pickUpDate && dropOffDate) {
        let pickUpDateTime = new Date(pickUpDate);
        let dropOffDateTime = new Date(dropOffDate);

        let timeDifference = dropOffDateTime - pickUpDateTime;
        let daysDifference = timeDifference / (1000 * 60 * 60 * 24) + 1;

        if (daysDifference > 0) {
            let totalPrice = (daysDifference * pricePerDay) + extraPrice;
            let totalPriceElement = document.getElementById('totalPrice');
            totalPriceElement.textContent = 'Total Price: $' + totalPrice.toFixed(2);
        } else {
            alert('Drop off date must be after pick-up date.');
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('pickUpDate').addEventListener('change', price);
    document.getElementById('dropOffDate').addEventListener('change', price);
    document.getElementById('extraId').addEventListener('change', price);
});     function price() {
    let selectedCar = document.getElementById('carId');
    let selectedCarOption = selectedCar.options[selectedCar.selectedIndex];
    let selectedCarOptionText = selectedCarOption.text;

    if (selectedCarOptionText !== "Select Car") {
        let allDataArr = selectedCarOptionText.split(" | ");
        let lastIndex = allDataArr[allDataArr.length - 1];
        let onlyPriceWithDollar = lastIndex.split(": ")[1];
        var onlyPrice = parseFloat(onlyPriceWithDollar.split(" ")[0]);
        document.getElementById('carPrice').textContent = 'Car Price: ' + onlyPriceWithDollar;
    } else {
        var onlyPrice = 0;
        document.getElementById('carPrice').textContent = 'Car Price: ';
    }

    let selectedExtra = document.getElementById('extraId');
    let selectedExtraOption = selectedExtra.options[selectedExtra.selectedIndex];
    let selectedExtraText = selectedExtraOption.text;

    if (selectedExtraText !== "Select extra") {
        var extraPrice = parseFloat(selectedExtraText.split(": ")[1].split(" ")[0]);
        document.getElementById('extraPrice').textContent = 'Extra Price: ' + selectedExtraText.split(": ")[1];
    } else {
        var extraPrice = 0;
        document.getElementById('extraPrice').textContent = 'Extra Price: ';
    }

    calculateTotalPrice(onlyPrice, extraPrice);
}

function calculateTotalPrice(pricePerDay, extraPrice) {
    let pickUpDate = document.getElementById('pickUpDate').value;
    let dropOffDate = document.getElementById('dropOffDate').value;

    if (pickUpDate && dropOffDate) {
        let pickUpDateTime = new Date(pickUpDate);
        let dropOffDateTime = new Date(dropOffDate);

        let timeDifference = dropOffDateTime - pickUpDateTime;
        let daysDifference = timeDifference / (1000 * 60 * 60 * 24) + 1;

        if (daysDifference > 0) {
            let totalPrice = (daysDifference * pricePerDay) + extraPrice;
            let totalPriceElement = document.getElementById('totalPrice');
            totalPriceElement.textContent = 'Total Price: $' + totalPrice.toFixed(2);
        } else {
            alert('Drop off date must be after pick-up date.');
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('pickUpDate').addEventListener('change', price);
    document.getElementById('dropOffDate').addEventListener('change', price);
    document.getElementById('extraId').addEventListener('change', price);
});