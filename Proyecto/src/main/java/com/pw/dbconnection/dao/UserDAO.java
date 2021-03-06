/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pw.dbconnection.dao;

import com.pw.dbconnection.models.User;
import com.pw.dbconnection.utils.DbConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author magoc
 */
public class UserDAO {
    
//    CREATE TABLE `pw02`.`user` (
//    `idUser` INT NOT NULL AUTO_INCREMENT,
//    `username` VARCHAR(45) NULL,
//    `password` VARCHAR(45) NULL,
//    PRIMARY KEY (`idUser`),
//    UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
//    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
    
    
//    USE `pw02`;
//    DROP procedure IF EXISTS `signIn`;
//
//    DELIMITER $$
//    USE `pw02`$$
//    CREATE PROCEDURE `signIn` (
//    IN `pUsername` varchar(45),
//    IN `pPassword` varchar(45)
//    )
//    BEGIN
//    INSERT INTO `user`
//    (`username`,
//    `password`)
//    VALUES
//    (pUsername,
//    pPassword);
//
//    END$$
//
//    DELIMITER ;


    public static int signInUser(User user) {
        try {
            Connection con = DbConnection.getConnection();
            CallableStatement statement = con.prepareCall("call SP_AgregaUR(?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCorreo());
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
//    USE `pw02`;
//    DROP procedure IF EXISTS `logIn`;
//
//    DELIMITER $$
//    USE `pw02`$$
//    CREATE PROCEDURE `logIn` (
//    IN `pUsername` varchar(45),
//    IN `pPassword` varchar(45)
//    )
//    BEGIN
//    SELECT u.`idUser` ID,
//        u.`username`
//    FROM `user` u
//    WHERE
//            u.username = pUsername
//    AND
//            u.password = pPassword;
//    END$$
//
//    DELIMITER ;


    public static User logInUser(User user) {
        try {
            Connection con = DbConnection.getConnection();
            CallableStatement statement = con.prepareCall("call SP_VerUR(?,?)");
            statement.setString(2, user.getUsername());
            statement.setString(1, user.getPassword());

            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                String username = result.getString(2);
                String foto = result.getString(3);

                return new User(id, username, foto);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
