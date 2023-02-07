package com.kkui;


import com.kkui.command.LfCommand;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.w3c.dom.events.Event;


public final class LinkSelect extends JavaPlugin {
    public static final LinkSelect INSTANCE = new LinkSelect();

    private LinkSelect() {
        super(new JvmPluginDescriptionBuilder("com.kkui.LinkSelect", "0.1.1")
                .name("LinkSelect")
                .author("kkui")
                .info("这是一个提供阿里云盘搜索以及在线观看插件")
                .build());
    }

    @Override
    public void onEnable() {
        //注册指令
        CommandManager.INSTANCE.registerCommand(LfCommand.INSTANCE,true);
    }

}

