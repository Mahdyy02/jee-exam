document.addEventListener("DOMContentLoaded", function () {
    const specialityList = document.getElementById("speciality-list");

    fetch("/WebApp-1.0-SNAPSHOT/get-specialities")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.length === 0) {
                specialityList.innerHTML = "<p>No specialities found.</p>";
                return;
            }

            data.forEach(speciality => {
                const specialityItem = document.createElement("div");
                specialityItem.className = "exam-item";
                specialityItem.innerHTML = `
                    <p><strong>ID:</strong> ${speciality.id}</p>
                    <p><strong>Name:</strong> ${speciality.name}</p>
                `;
                specialityList.appendChild(specialityItem);
            });
        })
        .catch(error => {
            console.error("Error fetching speciality data:", error);
            specialityList.innerHTML = "<p>Failed to load speciality data. Please try again later.</p>";
        });
});
