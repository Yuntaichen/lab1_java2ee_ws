package com.labs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;


@WebService(serviceName = "FindStudentsService")
public class StudentWebService {
    @Resource(lookup = "jdbc/ws_students_db")
    private DataSource dataSource;

    @WebMethod(operationName = "getStudentsByFields")
    public LinkedHashSet<Student> getStudentsByFields(@WebParam(name = "fieldValue") String[] searchArgs) {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.getStudentsByFields(searchArgs);
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}