package exercise3_2.dao;

import exercise3_2.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/contact_db";
    private static final String USER = "root";
    private static final String PASSWORD = "hapi9527..";

    // 保存联系人
    public static void save(Contact contact) {
        String query = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getAddress());
            pstmt.setString(3, contact.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 可选: 处理SQL异常（例如，记录日志，重新抛出等）
        }
    }

    // 更新联系人
    public static void update(int id, Contact contact) {
        String query = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getAddress());
            pstmt.setString(3, contact.getPhone());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID获取联系人
    public static Contact getById(int id) {
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Contact(rs.getInt("id"),rs.getString("name"), rs.getString("address"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 如果未找到，返回null
    }

    // 获取所有联系人
    public static List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("id"),rs.getString("name"), rs.getString("address"), rs.getString("phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // 删除联系人
    public static void delete(int id) {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}