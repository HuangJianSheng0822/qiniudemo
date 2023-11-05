package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("video")
public class Video {

  @TableId(type= IdType.ASSIGN_ID)
  private String id;
  private String userId;
  private String title;
  private String coverUrl;
  private String videoId;
  private String typeId;
  private String tags;
  private String description;
  private Long playback;
  @JsonFormat(pattern = "yy-MM-dd HH:mm")
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  @JsonFormat(pattern = "yy-MM-dd HH:mm")
  @TableField(value = "updated",fill = FieldFill.UPDATE)
  private Date updated;
  @TableField("deleted")
  private int deleted;
}
