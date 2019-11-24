package ua.nure.sidorovk.practice8.db;

import ua.nure.sidorovk.practice8.db.entity.Team;
import ua.nure.sidorovk.practice8.db.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBManager {
    private static DBManager dbManager;
    private static Locale locale = new Locale("en", "US");
    private static ResourceBundle resource = ResourceBundle.getBundle( "app", locale);
    private static final String CONNECTION_URL = resource.getString("connection.url");
    private static final String INSERT_USER = "INSERT INTO users (login) VALUES (?)";
    private static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?)";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String GET_TEAM_BY_NAME = "SELECT * FROM teams WHERE name=?";
    private static final String SET_TEAMS_FOR_USER = "INSERT INTO users_teams VALUES (?, ?)";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    private static final String UPDATE_TEAM_NAME = "UPDATE teams SET name=? WHERE teams.id=?";
    private static final String GET_USER_TEAMS = "SELECT g.id, g.name FROM users_teams ug\n" +
            "JOIN users u ON ug.user_id = u.id\n" +
            "JOIN teams g ON ug.team_id = g.id\n" +
            "WHERE u.id = ?";

    private Connection connection;

    private DBManager() {
        connection = getConnection();
    }

    public static synchronized DBManager getInstance( ) {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAllUsers() {
        List<User> resultList= new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                User user = User.createUser(name);
                user.setId(id);
                resultList.add(user);
            }
        } catch (SQLException e) {
            e.getSQLState();
        }
        return resultList;
    }

    public void insertUser(User user) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,user.getLogin());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate entry " + user.getLogin() + " for key 'login'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTeam(Team team) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_TEAM,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,team.getName());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    team.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry " + team.getName() + " for key 'name'");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Team> findAllTeams() {
        List<Team> resultList= new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TEAMS);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                Team team = Team.createTeam(name);
                team.setId(id);
                resultList.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public User getUser(String login) {
        try (PreparedStatement ps=connection.prepareStatement(GET_USER_BY_LOGIN)){
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.createUser(rs.getString(2));
                user.setId(rs.getInt(1));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Team getTeam(String name) {
        try (PreparedStatement ps=connection.prepareStatement(GET_TEAM_BY_NAME)){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Team team = Team.createTeam(rs.getString(2));
                team.setId(rs.getInt(1));
                return team;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException n){
            System.out.println("Team not fund");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTeamsForUser(User user, Team... teams){
        try (Connection con = getConnection()) {
            assert con != null;
            con.setAutoCommit(false);
            executeSQL(user, con, teams);
            con.setAutoCommit(true);
        }catch (SQLTransactionRollbackException te){
            System.out.println("SQLTransactionRollbackException");
        }catch (SQLException e){
            System.out.println("Can't set groups");
        }
    }

    private static void executeSQL(User user, Connection con, Team[] teams) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SET_TEAMS_FOR_USER)) {
            for (Team t : teams) {
                if (t != null) {
                    ps.setInt(1, user.getId());
                    ps.setInt(2, t.getId());
                    ps.execute();
                }
            }
            con.commit();
        }catch(SQLException ex) {
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    public List<Team> getUserTeams(User user) {
        List<Team> list = new ArrayList<>();
        try (PreparedStatement ps=connection.prepareStatement(GET_USER_TEAMS)){
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                Team team = Team.createTeam(name);
                team.setId(rs.getInt(1));
                list.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteTeam(Team team) {
        if(team!=null) {
            try (PreparedStatement ps = connection.prepareStatement(DELETE_TEAM)) {
                ps.setString(1, team.getName());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Team is null, query was not pass");
        }
    }

    public void updateTeam(Team team) {
        if(team!=null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM_NAME)) {
                ps.setString(1, team.getName());
                ps.setInt(2, team.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Can't update group: " + team.getName());
            }
        }else{
            System.out.println("Team is null, query was not pass");
        }
    }

}