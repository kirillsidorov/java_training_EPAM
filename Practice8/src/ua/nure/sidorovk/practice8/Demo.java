package ua.nure.sidorovk.practice8;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.sidorovk.practice8.db.DBManager;
import ua.nure.sidorovk.practice8.db.entity.Team;
import ua.nure.sidorovk.practice8.db.entity.User;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) throws SQLException {
        DBManager dbManager = DBManager.getInstance();

        //Part1
        assert dbManager != null;
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
        System.out.println("===========================");

        // Part 2
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));
        printList(dbManager.findAllTeams());
        // teams ==> [teamA, teamB, teamC]

        System.out.println("===========================");

        // Part 3
        User userPetrov = dbManager.getUser("petrov");
        User userIvanov = dbManager.getUser("ivanov");
        User userObama = dbManager.getUser("obama");

        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");


        // method setTeamsForUser must implement transaction!
        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

        for (User user : dbManager.findAllUsers()) {
            System.out.println(user.getLogin());
            printList(dbManager.getUserTeams(user));
            System.out.println("~~~~~");
        }
        // teamA
        // teamA teamB
        // teamA teamB teamC

        System.out.println("===========================");

        // Part 4

        // on delete cascade!
        dbManager.deleteTeam(teamA);
        printList(dbManager.getUserTeams(userObama));

        // Part 5
        teamC.setName("teamX");
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());
        // teams ==> [teamB, teamX]



        //after work rollback changes
        teamA = Team.createTeam("teamA");
        dbManager.insertTeam(teamA);
        dbManager.deleteTeam(teamB);
        dbManager.deleteTeam(teamC);
        Team t= dbManager.getTeam("teamX");
        dbManager.deleteTeam(t);

    }
}