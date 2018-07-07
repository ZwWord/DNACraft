package gzz_lin.dna.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gzz_lin.dna.capability.CapablilityLoader;
import gzz_lin.dna.capability.IDNACapability;
import gzz_lin.dna.model.DNA;
import gzz_lin.dna.util.DNAManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandDNA extends CommandBase {

	@Override
	public String getName() {

		return "dna";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "command.dna.usage";
	}

	@Override
	public int getRequiredPermissionLevel() {

		return 1;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		List<String> list = null;
		switch (args.length) {
		case 1:
			list = new ArrayList<>();
			list.add("get");
			list.add("set");
			break;
		case 2:
			PlayerList playerList = server.getPlayerList();
			String[] playerNames = playerList.getOnlinePlayerNames();
			list = CommandBase.getListOfStringsMatchingLastWord(args, playerNames);
			break;
		case 3:
			list = new ArrayList<>();
			HashSet<DNA> dna = DNAManager.getDNA();
			for (DNA d : dna) {
				list.add(d.getName());
			}
			break;
		default:
			break;
		}
		return list;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 3 || (("set".equals(args[0]) && args.length < 4))
				|| (("get".equals(args[0]) && args.length > 3))) {
			throw new WrongUsageException("command.dna.usage");
		}
		EntityPlayer player = args.length > 2 ? CommandBase.getPlayer(server, sender, args[1])
				: CommandBase.getCommandSenderAsPlayer(sender);
		DNA dna = DNAManager.getDNAFromName(args[2]);
		if (player != null&&dna!=null ) {
			IDNACapability capability=player.getCapability(CapablilityLoader.dna, null);
			if("set".equals(args[0])) {
				int value = -1;
				try {
					value = Integer.valueOf(args[3]);
				} catch (NumberFormatException e) {
					return;
				}
				
				if(capability.hasDNA(dna)) {
					capability.setValue(dna, value);
				}else {
					capability.addDNA(dna, value);
				}
				DNAManager.updata((EntityPlayerMP)player);
			}
			else if("get".equals(args[0])) {
				if(capability.hasDNA(dna)) {
					sender.sendMessage(new TextComponentString(dna.getName()+":"+capability.getValue(dna)));
				}
			}
			

		}
	}
}
