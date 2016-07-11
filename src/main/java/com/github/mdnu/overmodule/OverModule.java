package com.github.mdnu.overmodule;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class OverModule implements IModule {
	static IDiscordClient client;
	
	@Override
	public boolean enable(IDiscordClient client) {
		OverModule.client = client;
		client.getDispatcher().registerListener(new MessageListener());
		return true;
	}
	
	@Override
	public void disable() {
	}
	
	@Override
	public String getName() {
		return "overwatch helper module";
	}
	
	@Override
	public String getAuthor() {
		return "thoughtforms";
	}
	
	@Override
	public String getVersion() {
		return "1.0";
	}
	
	@Override
	public String getMinimumDiscord4JVersion() {
		return "2.4.4";
	}
}
