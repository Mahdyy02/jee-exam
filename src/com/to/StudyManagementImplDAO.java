    package com.to;

    import java.util.Date;

    import com.control.HibernateUtil;

    public class StudyManagementImplDAO implements StudyManagementDAO {

        public boolean addStudent(Student student){ 
            return HibernateUtil.save(student);
        }

        public boolean addSpeciality(Speciality speciality){         
            return HibernateUtil.save(speciality);
        }

        public boolean addExam(Exam exam){ 
            return HibernateUtil.save(exam);
        }

        public Student findStudentById(String idStudent){ 
            return (Student) HibernateUtil.find(Student.class, idStudent); 
        }
        
        public Exam findExamById(int idExam){ 
            return (Exam) HibernateUtil.find(Exam.class, idExam); 
        }

        public boolean addSession(Date dateOfTheExam, Student studentFound, Exam examFound){ 

            Session session = new Session(examFound, studentFound, dateOfTheExam);
            return HibernateUtil.save(session);
        }

        public Session findSession(Date dateOfTheExam, String idStudent, int idExam){ 
            CompositeKey compositeKey = new CompositeKey(idExam, idStudent);
            return (Session) HibernateUtil.find(Session.class, compositeKey);     
        }
        
        public boolean update(Session sessionfound){ 
            return HibernateUtil.update(sessionfound);
        }

    }
