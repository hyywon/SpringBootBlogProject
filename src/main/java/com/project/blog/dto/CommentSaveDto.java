package com.project.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveDto {
    private Integer postId;
    private String text;
    private Integer userId;
}
