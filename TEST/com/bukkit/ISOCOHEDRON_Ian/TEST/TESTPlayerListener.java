package com.bukkit.ISOCOHEDRON_Ian.TEST; 
 
import java.io.*; 
import org.bukkit.Location; 
import org.bukkit.entity.*; 
import org.bukkit.event.player.PlayerChatEvent; 
import org.bukkit.event.player.PlayerEvent; 
import org.bukkit.event.player.PlayerListener; 
import org.bukkit.event.player.PlayerMoveEvent; 
import org.bukkit.inventory.*; 
import org.bukkit.material.MaterialData; 
import org.bukkit.*; 

import com.bukkit.ISOCOHEDRON_Ian.TEST.TEST;
 
 
 
 
 
/** 
 * Handle events for all Player related events 
 * @author ISOCOHEDRON_Ian 
 */ 
public class TESTPlayerListener extends PlayerListener { 
 private final TEST plugin; 
 
 public TESTPlayerListener(TEST instance) { 
 plugin = instance; 
 } 
 
 public void onPlayerCommand(PlayerChatEvent event) { 
 Player players = event.getPlayer(); 
 String[] message = event.getMessage().split(" "); 
 if(message[0].equalsIgnoreCase("/clearinventory")) { 
 players.getInventory().clear(); 
 } 
 } 
 
 
 
} 
 