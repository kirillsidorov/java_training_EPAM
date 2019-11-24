package ua.nure.sidorovk.practice8.db.entity;

import java.util.Objects;

public class Team {
    private String nameTeam;
    private int id;

    private Team(String name) {
        this.nameTeam = name;
    }

    public static Team createTeam(String name) {
        return new Team(name);
    }

    public String getName() {
        return this.nameTeam;
    }

    public void setName(String teamX) {
        this.nameTeam = teamX;
    }

    @Override
    public String toString() {
        return this.nameTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;
        return this.nameTeam.equals(team.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTeam);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
