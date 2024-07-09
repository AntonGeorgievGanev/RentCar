function price() {
    let selected = document.getElementById('carId');
    let selectedOption = selected.options[selected.selectedIndex];
    let selectedOptionText = selectedOption.text;

    let allDataArr = selectedOptionText.split(" | ");
    let lastIndex = allDataArr[allDataArr.length - 1];

    let onlyPriceWithDollar = lastIndex.split(": ")[1];
    let onlyPrice = parseFloat(onlyPriceWithDollar.split(" ")[0]);

    let carPrice = document.getElementById('carPrice');
    carPrice.textContent = 'Price: ' + onlyPriceWithDollar;

    calculateTotalPrice(onlyPrice);
}

function calculateTotalPrice(pricePerDay) {
    let pickUpDate = document.getElementById('pickUpDate').value;
    let dropOffDate = document.getElementById('dropOffDate').value;

    let selectedExtra = document.getElementById('extraId');
    let selectedExtraOption = selectedExtra.options[selectedExtra.selectedIndex];
    let selectedExtraText = selectedExtraOption.text;
    let extraPrice = 0;

    if (selectedExtraText !== "Select extra") {
        extraPrice = parseFloat(selectedExtraText.split(": ")[1].split(" ")[0]);
        let extraElement = document.getElementById('extraPrice');
        extraElement.textContent = 'Price for extra: ' + extraPrice + " $";
    }

    if (pickUpDate && dropOffDate) {
        let pickUpDateTime = new Date(pickUpDate);
        let dropOffDateTime = new Date(dropOffDate);

        let timeDifference = dropOffDateTime - pickUpDateTime;
        let daysDifference = timeDifference / (1000 * 60 * 60 * 24);

        if (daysDifference >= 0) {
            let totalPrice = (daysDifference * pricePerDay) + extraPrice;
            let totalPriceElement = document.getElementById('totalPrice');
            totalPriceElement.textContent = 'Total Price: ' + totalPrice.toFixed(2) + " $";
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