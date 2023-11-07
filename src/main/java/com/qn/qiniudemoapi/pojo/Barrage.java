package com.qn.qiniudemoapi.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("barrage")
public class Barrage {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  @TableField(value = "`video_id`")
  private String videoId;
  @TableField(value = "`user_id`")
  private String userId;
  @TableField(value = "`content`")
  private String content;
  private String schedule;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;

}
