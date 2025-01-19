document.addEventListener("DOMContentLoaded", function () {
    const studentDropdown = document.getElementById("studentDropdown");
    const examDropdown = document.getElementById("examDropdown");

    fetch("/WebApp-1.0-SNAPSHOT/get-students")
        .then(response => response.json())
        .then(data => {
            data.forEach(student => {
                const option = document.createElement("option");
                option.value = student.id;
                option.textContent = student.name;
                studentDropdown.appendChild(option);
            });
        })
        .catch(error => console.error("Error fetching students:", error));

    studentDropdown.addEventListener("change", function () {
        const studentID = studentDropdown.value;

        examDropdown.innerHTML = '';

        if (studentID) {
            fetch(`/WebApp-1.0-SNAPSHOT/get-sessions`)
                .then(response => response.json())
                .then(data => {
                    const filteredSessions = data.filter(session => session.StudentID == studentID);
                    filteredSessions.forEach(session => {
                        const option = document.createElement("option");
                        option.value = session.ExamID;
                        option.textContent = session.Exam;
                        examDropdown.appendChild(option);
                    });
                })
                .catch(error => console.error("Error fetching exams:", error));
        }
    });
});
