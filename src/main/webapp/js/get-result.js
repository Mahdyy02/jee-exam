document.addEventListener('DOMContentLoaded', () => {


    let jsonDataElement = document.getElementById('jsonData');
    let jsonData = jsonDataElement.getAttribute('data-json');
    let data = JSON.parse(jsonData);
    let tableBody = document.querySelector('tbody');

    tableBody.innerHTML = "";

    data.forEach(item => {
        const row = `
      <tr>
        <td>${item.speciality}</td>
        <td>${item.mean}</td>
      </tr>
    `;
        tableBody.innerHTML += row;
    });

});
