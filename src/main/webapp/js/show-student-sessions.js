document.addEventListener("DOMContentLoaded", function () {
    const sessionList = document.getElementById("session-list");

    fetch("/WebApp-1.0-SNAPSHOT/get-student-schedule")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.length === 0) {
                sessionList.innerHTML = "<p>No sessions found.</p>";
                return;
            }

            function formatDateTime(dateString) {
                const date = new Date(dateString);

                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, '0');
                const day = String(date.getDate()).padStart(2, '0');
                const formattedDate = `${year}-${month}-${day}`;

                let hours = date.getHours();
                let minutes = String(date.getMinutes()).padStart(2, '0');
                const formattedTime = `${hours}:${minutes}`;

                return `${formattedDate} ${formattedTime}`;
            }

            data.forEach(session => {
                const sessionItem = document.createElement("div");
                sessionItem.className = "exam-item";
                const formattedDate = formatDateTime(session.Date);
                sessionItem.innerHTML = `
                    <p><strong>Exam:</strong> ${session.Exam}</p>
                    <p><strong>Student:</strong> ${session.Student}</p>
                    <p><strong>Mark:</strong> ${session.Mark}</p>
                    <p><strong>Classroom:</strong> ${session.Classroom}</p>
                    <p><strong>Date:</strong> ${formattedDate}</p>
                `;
                sessionList.appendChild(sessionItem);
            });
        })
        .catch(error => {
            console.error("Error fetching sessions:", error);
            sessionList.innerHTML = "<p>Failed to load sessions. Please try again later.</p>";
        });
});
