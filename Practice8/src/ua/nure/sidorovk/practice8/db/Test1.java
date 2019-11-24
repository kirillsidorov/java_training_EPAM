package ua.nure.sidorovk.practice8.db;

import ua.nure.sidorovk.practice8.db.entity.Team;
import ua.nure.sidorovk.practice8.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws SQLException {
        DBManager dbm = DBManager.getInstance();

        User user = User.createUser("user");
        Team teamA = Team.createTeam("A");
        Team teamB = Team.createTeam("B");
        Team teamC = Team.createTeam("C");
        Team teamD = Team.createTeam("D");
        dbm.insertUser(user);
//        dbm.insertTeam(teamA);
//        dbm.insertTeam(teamB);
//        dbm.insertTeam(teamC);
//        dbm.insertTeam(teamD);
        user = dbm.getUser(user.getLogin());
        teamA = dbm.getTeam(teamA.getName());
        teamB = dbm.getTeam(teamB.getName());
        teamC = dbm.getTeam(teamC.getName());
        teamD = dbm.getTeam(teamD.getName());

        System.out.println("try to set team A for user1");
        dbm.setTeamsForUser(user, teamA);

        List<Team> list = dbm.getUserTeams(user);

        System.out.println("A = " + list.toString());

        try {
            System.out.println("try to set team B, C, D, A for user");
            dbm.setTeamsForUser(user, teamB, teamC, teamD, teamA);
        } catch (Exception ex) {
            System.out.println("fail");
        }

        List<Team> list2 = dbm.getUserTeams(user);
        System.out.println("A =  " + list2.toString());

        System.out.println("try to set team B for user");
        dbm.setTeamsForUser(user, teamB);
        List<Team> list3 = dbm.getUserTeams(user);

        System.out.println("A, B = " + list3.toString());


        try {
            System.out.println("try to set team C, D, B for user");
            dbm.setTeamsForUser(user, teamC, teamD, teamB);
        } catch (Exception ex) {
            System.out.println(" fail2");
        }

        List<Team> list4 = dbm.getUserTeams(user);
        System.out.println("A, B = " + list4.toString());

        System.out.println("try to set team C + D for user");
        dbm.setTeamsForUser(user, teamC, teamD);

        List<Team> list5 = dbm.getUserTeams(user);
        //teamA, teamB, teamC, teamD
        System.out.println("A, B, C, D = " + list5.toString());



    }
}
