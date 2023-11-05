package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("collect_video")
public class CollectVideo {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String collectId;
  private String userId;
  private String videoId;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  private long playCount;
  @TableField("deleted")
  private int deleted;
}
