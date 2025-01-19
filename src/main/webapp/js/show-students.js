document.addEventListener("DOMContentLoaded", function () {
    const studentList = document.getElementById("student-list");

    // Fetch students from the servlet
    fetch("/WebApp-1.0-SNAPSHOT/get-students")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.length === 0) {
                studentList.innerHTML = "<p>No students found.</p>";
                return;
            }

            // Populate the student list
            data.forEach(student => {
                const studentItem = document.createElement("div");
                studentItem.className = "exam-item";
                studentItem.innerHTML = `
                    <p><strong>ID:</strong> ${student.id}</p>
                    <p><strong>CIN:</strong> ${student.cin}</p>
                    <p><strong>Name:</strong> ${student.name}</p>
                `;
                studentList.appendChild(studentItem);
            });
        })
        .catch(error => {
            console.error("Error fetching student data:", error);
            studentList.innerHTML = "<p>Failed to load student data. Please try again later.</p>";
        });
});
