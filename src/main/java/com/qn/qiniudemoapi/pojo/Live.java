package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@TableName("live")
public class Live {
  @TableId(type=IdType.ASSIGN_ID)
  private String id;
  private String liveId;
  private String userId;
  private String title;
  private String desc;
  private String cover;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  private Date endTime;
  private int maxPeople;
}
