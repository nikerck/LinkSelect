package com.kkui.command;

import com.kkui.LinkSelect;
import com.kkui.sources.MaoLiPan;
import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JSimpleCommand;

import java.io.IOException;
import java.util.List;


/**
 * @Author kkui
 * @Date 2022/8/17
 * @Description 查询指令
 */
public final class lfCommand extends JSimpleCommand {
    public static final lfCommand INSTANCE = new lfCommand();

    private lfCommand(){
        super(LinkSelect.INSTANCE,"lf");
        setDescription("根据名字查询资源");
        setPrefixOptional(true);
    }


    /**
     * 根据名字查询资源
     * @param context 上下文
     * @param name 资源名
     */
    @Handler
    public void foo(CommandContext context, String name) throws IOException {
        MaoLiPan maoLiPan = MaoLiPan.getMaoLiPan();
        maoLiPan.find(name);
        List<String> itemName = maoLiPan.getName();
        List<String> aLiYuLink = maoLiPan.getAliyuLink();
        for (int i =0 ; i<10 ; i++){
            context.getSender().sendMessage(itemName.get(i) + "\n" + aLiYuLink.get(i));
        }

    }
}
