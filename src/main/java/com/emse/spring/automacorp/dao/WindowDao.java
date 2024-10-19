package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WindowDao extends JpaRepository<WindowEntity, Long>, WindowDaoCustom {
    List<WindowEntity> findByRoomId(Long roomId);

    @Modifying
    @Query("delete from WindowEntity w where w.room.id = ?1")
    void deleteByRoom(Long roomId);
}