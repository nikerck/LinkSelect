package com.kkui.command;

import com.kkui.LinkSelect;
import com.kkui.messageModel.MsgLink;
import com.kkui.messageModel.MsgSimple;
import com.kkui.sources.MaoLiPan;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.message.data.SingleMessage;


import java.io.IOException;
import java.util.List;


/**
 * @Author kkui
 * @Date 2022/8/17
 * @Description 查询指令
 */
public final class LfCommand extends JSimpleCommand {
    public static final LfCommand INSTANCE = new LfCommand();

    private LfCommand(){
        super(LinkSelect.INSTANCE,"lf");
        setDescription("根据名字查询阿里云盘资源");
        setPrefixOptional(true);
    }


    /**
     * 根据名字查询资源
     * @param
     * @param name 资源名
     */
    @Handler
    public void foo(CommandContext commandContext, String name) {

        MessageChain chain = new MessageChainBuilder()
                .append(new QuoteReply(commandContext.getOriginalMessage()))
                .append("设置延迟，防止过量爬虫,等吧")
                .build();
        commandContext.getSender().sendMessage(chain);

        MaoLiPan maoLiPan = MaoLiPan.getMaoLiPan();
        maoLiPan.find(name);
        List<MsgLink> aLiYuLink = maoLiPan.getAliyuLink();
        String msg = null;
        for (int i =0 ; i<aLiYuLink.size() ; i++){
            msg += aLiYuLink.get(i).getLink() +"\n";
        }
        commandContext.getSender().sendMessage(msg);

    }


}
