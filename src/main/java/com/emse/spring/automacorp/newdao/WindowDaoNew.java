package com.emse.spring.automacorp.newdao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDaoNew extends JpaRepository<WindowEntity, Long> {
    List<WindowEntity> findByRoomId(Long roomId);

    @Modifying
    @Query("DELETE FROM WindowEntity w WHERE w.room.id = :roomId")
    void deleteByRoomId(@Param("roomId") Long roomId);
}
