document.addEventListener('DOMContentLoaded', () => {
    fetch('/WebApp-1.0-SNAPSHOT/get-specialities')
        .then(response => response.json())
        .then(data => {
            const dropdown = document.getElementById('specialityDropdown');
            data.forEach(speciality => {
                const option = document.createElement('option');
                option.value = speciality.id;
                option.textContent = speciality.name;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching specialities:', error));
});