package com.qn.qiniudemoapi.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("comment")
public class Comment {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String content;
  @TableField(value = "`user_id`")
  private String userId;
  @TableField(value = "`video_id`")
  private String videoId;
  @TableField(value = "`like_count`")
  private long likeCount;
  @TableField(value = "`root_id`")
  private String rootId;
  @TableField(value = "`to_id`")
  private String toId;
  @TableField(value = "`to_name`")
  private String toName;
  @JsonFormat(pattern = "yy-MM-dd HH:mm")
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  @JsonFormat(pattern = "yy-MM-dd HH:mm")
  @TableField(value = "updated",fill = FieldFill.UPDATE)
  private Date updated;
  @TableField("deleted")
  private int deleted;



}
