package com.ejb;

import com.dao.StudyManagementDAO;
import com.dao.StudyManagementImplDAO;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserAuthentication {

    @EJB
    private StudyManagementDAO daoManager;

    public boolean authenticateStudent(String username, String password) {
        try {

            int id = Integer.parseInt(username);
            int pwd = Integer.parseInt(password);

            Student s = daoManager.findStudentById(id);

            return pwd == s.getCin();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateAdmin(String username, String password) {
        try {

            return username.equals("admin") && password.equals("admin");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
