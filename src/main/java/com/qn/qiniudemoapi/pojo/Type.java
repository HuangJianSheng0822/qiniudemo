package com.qn.qiniudemoapi.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("type")
public class Type {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  @TableField(value = "name")
  private String name;
}
