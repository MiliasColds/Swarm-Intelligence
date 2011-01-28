package com.bukkit.ISOCOHEDRON_Ian.TEST; 
 
import java.io.*; 
import java.util.HashMap; 
 
import org.bukkit.Server; 
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player; 
import org.bukkit.event.Event; 
import org.bukkit.event.Event.Priority; 
import org.bukkit.plugin.PluginDescriptionFile; 
import org.bukkit.plugin.PluginLoader; 
import org.bukkit.plugin.PluginManager; 
import org.bukkit.plugin.java.JavaPlugin; 

import com.bukkit.ISOCOHEDRON_Ian.TEST.TESTBlockListener;
import com.bukkit.ISOCOHEDRON_Ian.TEST.TESTPlayerListener;
 
/** 
 * TEST for Bukkit 
 * 
 * @author ISOCOHEDRON_Ian 
 */ 
public class TEST extends JavaPlugin { 
	 private final TESTPlayerListener playerListener = new TESTPlayerListener(this); 
	 private final TESTBlockListener blockListener = new TESTBlockListener(this); 
	 private final HashMap debugees = new HashMap(); 
	 
	 public TEST(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) throws IOException { 
	 super(pluginLoader, instance, desc, folder, plugin, cLoader); 
	 // TODO: Place any custom initialisation code here 
	 
	 // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled 
	 
	 } 
 
 
 
	 public void onEnable() { 
		 World W = getServer().getWorlds()[0];
		 
		 BlockDump(W);
		 
		 
		 // Register our events 
		 PluginManager pm = getServer().getPluginManager(); 
		 getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACED, blockListener, Priority.Normal, this); 
		 pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Normal, this); 
		 // EXAMPLE: Custom code, here we just output some info so we can check all is well 
		 PluginDescriptionFile pdfFile = this.getDescription(); 
		 System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" ); 
	 } 
	 public void onDisable() { 
		 // TODO: Place any custom disable code here 
		 
		 // NOTE: All registered events are automatically unregistered when a plugin is disabled 
		 
		 // EXAMPLE: Custom code, here we just output some info so we can check all is well 
		 System.out.println("Goodbye world!"); 
	 } 
	 public Object isDebugging(final Player player) { 
		 if (debugees.containsKey(player)) { 
			 return debugees.get(player); 
		 } else { 
			 return false; 
		 } 
	 } 
	 
	 public void setDebugging(final Player player, final boolean value) { 
		 debugees.put(player, value); 
	 } 
	 
	 
	 private void BlockDump(World w){
		 
		 for(int i=1;i<256;i++){
			 for(int j=1;j<256;j++){
				 for(int k=1;k<128;k++){
					 Block b = w.getBlockAt(i,j,k);
					 System.out.println(b.toString());
				 }
			 }
		 }
		 
	 }
 }

 