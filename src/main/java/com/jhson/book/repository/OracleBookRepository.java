package com.jhson.book.repository;

import com.jhson.book.domain.Book;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OracleBookRepository implements BookRepository {

    private final DataSource dataSource;

    public OracleBookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book save(Book book) {

        String sql = "insert into book(title, content) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getContent());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next())
                book.setId(rs.getString(1));

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
        return book;
    }

    @Override
    public List<Book> finaAll() {
        String sql = "select * from book";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();

        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("content"));
                books.add(book);
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

        return books;
    }

    @Override
    public Optional<Book> findByTitle(String title) {

        String sql = "select * from book where title=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Optional<Book> opt_book = Optional.empty();;

        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("content"));
                opt_book = Optional.of(book);
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

        return opt_book;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

}
