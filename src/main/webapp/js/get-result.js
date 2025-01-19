document.addEventListener('DOMContentLoaded', () => {

    const jsonData = document.getElementById('jsonData').getAttribute('data-json');
    const data = JSON.parse(jsonData);
    const tableBody = document.querySelector('tbody');

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
