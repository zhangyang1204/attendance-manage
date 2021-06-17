package cn.zyity.attendancemanage.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {
    public final int MESSAGE_NORMAL = 1;
    public final int MESSAGE_SIGNIN = 2;
    public final int MESSAGE_DISCUSSION = 3;
    private int type;
    private int messageId;//指该消息指向其他表中记录的id，并非该消息的id
    private String ownerName;
    private String ownerImgUrl;
    private String content;
    private int ownerId;
    private String ownerRole;



}