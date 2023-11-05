package com.qn.qiniudemoapi.pojo;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("collect")
public class Collect {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String userId;
  private String name;
  private String cover;
  @TableField(value = "`desc`")
  private String desc;
  private int open;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  @TableField(value = "updated",fill = FieldFill.UPDATE)
  private Date updated;
  @TableField("deleted")
  private int deleted;
}
