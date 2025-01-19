document.addEventListener('DOMContentLoaded', async () => {
    const tableBody = document.querySelector('tbody');
    tableBody.innerHTML = "";

    try {
        const response = await fetch('/WebApp-1.0-SNAPSHOT/get-student-statistics');

        if (!response.ok) {
            throw new Error(`Failed to fetch data: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();

        console.log(data);

        data.forEach(item => {
            const row = `
              <tr>
                <td>${item.speciality}</td>
                <td>${item.mean}</td>
              </tr>
            `;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error('Error fetching student statistics:', error);
        tableBody.innerHTML = `<tr><td colspan="2">Error loading data</td></tr>`;
    }
});
