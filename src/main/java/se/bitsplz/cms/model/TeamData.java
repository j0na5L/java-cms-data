package se.bitsplz.cms.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class TeamData extends AbstractEntityData{
   
   @Column(unique = true, updatable = false)
   private int teamId;
   @Column(nullable = false)
   private String teamName;
   @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
   private Collection<UserData> users;
   @Transient
   private static final int MAX_USERS = 10;
   @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
   private Collection<WorkItemData> workItems;
   
   @Enumerated(EnumType.STRING)
   private TeamStatus teamStatus;
   
   public enum TeamStatus{
      ACTIVE, INACTIVE;
   }
   
   
   protected TeamData(){
   }
   
   
   public TeamData(int teamId, String teamName){
      this.teamId = teamId;
      this.teamName = teamName;
      this.teamStatus = TeamStatus.INACTIVE;
      this.workItems = new HashSet();
      this.users = new HashSet();
   }
   
   
   public TeamStatus getStatus(){
      return this.teamStatus;
   }
   
   
   public void setStatus(TeamStatus teamStatus){
      this.teamStatus = teamStatus;
   }
   
   
   public void setTeamName(String finalTeamName){
      this.teamName = finalTeamName;
   }
   
   
   public String getTeamName(){
      return this.teamName;
   }
   
   
   public void addUserToTeam(UserData user){
      this.users.add(user);
   }
   
   
   public void addWorkItem(WorkItemData workItem){
      this.workItems.add(workItem);
   }
   
   
   public Collection<WorkItemData> getWorkItems(){
      return new HashSet(this.workItems);
   }
   
   
   public Collection<UserData> getUsers(){
      return new HashSet(this.users);
   }
   
   
   public int getMaxUsers(){
      return MAX_USERS;
   }
   
   
   public int getTeamId(){
      return this.teamId;
   }
   
   
   public void removeUser(UserData user){
      this.users.remove(user);
   }
   
   
   public void setTeamId(int finalTeamId){
      this.teamId = finalTeamId;
   }
   
   
   public void updateValues(TeamData inputTeam){
      this.teamName = inputTeam.teamName;
      this.teamStatus = inputTeam.teamStatus;
      this.users = inputTeam.users;
      this.workItems = inputTeam.workItems;
   }
   
   
   @Override
   public int hashCode(){
      int result = 11;
      result += 37 * this.teamId;
      result += 37 * this.teamName.hashCode();
      result += 37 * this.teamStatus.hashCode();
      return result;
   }
   
   
   @Override
   public boolean equals(Object other){
      if(this == other){
         return true;
      }
      if(other instanceof TeamData){
         TeamData otherTeam = (TeamData)other;
         return this.teamId == otherTeam.teamId && this.teamName.equals(otherTeam.teamName) && this.teamStatus.equals(otherTeam.teamStatus);
      }
      return false;
   }
   
   
   public String shortToString(){
      return this.teamName;
   }
   
   
   @Override
   public String toString(){
      return "Team{" + "teamId='" + this.teamId + '\'' + ", teamName'" + this.teamName + '\'' + ", status='" + this.teamStatus + '\'' + '}';
   }
   
   // @Override
   // public String toString(){
   // return this.teamId + " " + this.teamName + ", Status: " + this.teamStatus;
   // }
   
}
