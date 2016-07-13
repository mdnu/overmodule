package com.github.mdnu.overmodule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import sx.blah.discord.api.*;
import sx.blah.discord.util.*;
import sx.blah.discord.handle.impl.events.*;
import sx.blah.discord.handle.obj.*;

public class MessageListener {
	private IMessage message;
	private String messageContent;
	private IChannel channel;
	private final Map<String, String> mapMap = new HashMap<>();
	private final Map<String, String> cmdMap = new HashMap<>();
	private final List<String> fullCmd = new ArrayList<>();
	public String workingDir = System.getProperty("user.dir") + "\\maps\\";
	public String help = "";
	
	/** Constructor */
	public MessageListener() {
		fillMap();
		fillCmdMap();
		fillFullCmd();
		
		help += "valid commands:\n";
		for (String cmd : fullCmd) {
			help += ("!" + cmd + "\n");
		}
	}
	
	@EventSubscriber
	public void onMessage(MessageReceivedEvent event) throws HTTP429Exception, DiscordException, MissingPermissionsException, IOException  {
		message = event.getMessage();
		messageContent = message.getContent();
		channel = message.getChannel();
		
		if (messageContent.charAt(0) == '!') {
			messageContent = messageContent.trim().replaceAll("!", "");
			if (cmdMap.containsKey(messageContent)) {
				sendMessage(cmdMap.get(messageContent));
			} else if (mapMap.containsKey(messageContent)) {
				sendMessage(messageContent + " map (click to enlarge):");
				channel.sendFile(new File(workingDir + mapMap.get(messageContent)));
			} else if (messageContent.equals("help")) {
				this.help();
			} else if (messageContent.contains("counter")) {
				messageContent = messageContent.trim().replaceAll("counterpick ", "");
				sendMessage("counterpick feature not yet implemented.");
			} else if (messageContent.contains("idealteam")) {
				messageContent = messageContent.trim().replaceAll("idealteam", "");
				sendMessage("ideal team feature not yet implemented.");
				/** ideal team feature should take in two arguments, 1. offense vs. defense, 2. the map **/
			}
		}
	}
	
	/**
	 * Creates !help dialogue.
	 * @throws HTTP429Exception
	 * @throws DiscordException
	 * @throws MissingPermissionsException
	 */
	public void help() throws HTTP429Exception, DiscordException, MissingPermissionsException {
		sendMessage(help);
	}
	
	/**
	 * Helper method for sending messages.
	 * @param message
	 * @param channel
	 */
	public void sendMessage(String message, IChannel channel) {
		try {
			new MessageBuilder(OverModule.client).withChannel(channel).withContent(message).build();
		} catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		sendMessage(message, this.channel);
	}
	
	/** Loads map images. */
	private void fillMap() {
		for (Maps map : Maps.values()) {
			mapMap.put(map.toString(), map.toString() + ".jpg");
		}
	}
	
	/** Loads map commands. */
	private void fillCmdMap() {
		String cmdHelp = "commands:\n";
		for (String cmd : mapMap.keySet()) {
			cmdHelp += ("!" + cmd + "\n");
		}
		cmdMap.put("cmds", cmdHelp);
	}
	
	/** Loads all commands. */
	private void fillFullCmd() {
		fullCmd.add("help");
		fullCmd.addAll(cmdMap.keySet());
		fullCmd.addAll(mapMap.keySet());
	}
}
