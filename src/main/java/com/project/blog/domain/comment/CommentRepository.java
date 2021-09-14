package com.project.blog.domain.comment;

import com.project.blog.dto.CommentSaveDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Modifying
    @Query(value="INSERT INTO comment(post_id, text, user_id) VALUES(?1,?2,?3)", nativeQuery = true)
    int commentSave(Integer post, String text, Integer user);

}
