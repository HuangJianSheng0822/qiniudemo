package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("live_user")
public class LiveUser {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String liveId;
  private String userId;
  private int typeId;
  private int state;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  private String applyDesc;
}
