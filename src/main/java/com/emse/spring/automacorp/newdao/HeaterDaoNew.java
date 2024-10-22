package com.emse.spring.automacorp.newdao;

import com.emse.spring.automacorp.model.HeaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeaterDaoNew extends JpaRepository<HeaterEntity, Long> {
    @Modifying
    @Query("DELETE FROM HeaterEntity h WHERE h.room.id = :roomId")
    void deleteByRoomId(@Param("roomId") Long roomId);
}