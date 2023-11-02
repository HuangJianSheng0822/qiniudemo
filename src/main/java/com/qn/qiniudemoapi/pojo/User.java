package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("user")

public class User {

  @TableId(type=IdType.ASSIGN_ID)
  private String id;
  @TableField(value = "email")
  private String email;
  @TableField(value = "pwd")
  private String pwd;
  @TableField(value = "name")
  private String name;
  @TableField(value = "`head_img`")
  private String headImg;
  @TableField(value = "`desc`")
  private String desc;
  @TableField(value = "created",fill = FieldFill.INSERT)
  private Date created;
  @TableField(value = "updated",fill = FieldFill.UPDATE)
  private Date updated;
  @TableField("deleted")
  private int deleted;

}
