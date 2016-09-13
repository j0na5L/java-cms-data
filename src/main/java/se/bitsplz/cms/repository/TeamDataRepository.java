package se.bitsplz.cms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.bitsplz.cms.model.TeamData;


public interface TeamDataRepository extends PagingAndSortingRepository<TeamData, Long>{
   
   @Query("select t from TeamData t where t.teamId = ?1")
   TeamData findTeamByTeamId(int teamId);
   
   
   @Query("select max(t.teamId) from TeamData t")
   Integer findTeamByMaxTeamId();
   
   
   @Transactional
   TeamData removeByTeamId(int teamId);
}
