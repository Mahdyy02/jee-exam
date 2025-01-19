fetch('/WebApp-1.0-SNAPSHOT/get-student-exams')
    .then(response => response.json())
    .then(data => {
        const examsBySpeciality = {};

        // Group exams by speciality
        data.forEach(exam => {
            const speciality = exam.speciality;
            if (!examsBySpeciality[speciality]) {
                examsBySpeciality[speciality] = [];
            }
            examsBySpeciality[speciality].push(exam);
        });

        const sortedSpecialities = Object.keys(examsBySpeciality).sort();

        const examContainer = document.getElementById('exam-container');
        sortedSpecialities.forEach(speciality => {
            const specialityHeader = document.createElement('h3');
            specialityHeader.textContent = speciality;
            examContainer.appendChild(specialityHeader);

            examsBySpeciality[speciality].forEach(exam => {
                const examItem = document.createElement('div');
                examItem.classList.add('exam-item');
                examItem.innerHTML = `
                    <p><strong>ID:</strong> ${exam.id}</p>
                    <p><strong>Name:</strong> ${exam.name}</p>
                `;
                examContainer.appendChild(examItem);
            });
        });
    })
    .catch(error => console.error('Error fetching exams:', error));
