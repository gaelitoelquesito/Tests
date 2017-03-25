package es.minetsii.worldgeneration;


import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class Generator extends ChunkGenerator {

	public byte[][] generateBlockSections (World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid) {
		byte[][] result = new byte[world.getMaxHeight() / 16][];

		int realX = chunkX * 16;
		int realZ = chunkZ * 16;

		for (int x = 0 ; x < 16 ; x++) {
			for (int z = 0 ; z < 16 ; z++) {

				int maxY = (int) Math.log((x + realX) * (z + realZ));

				for (int y = 0 ; y < 256 ; y++) {
					if (maxY < -3 + y) setBlock(result, x, z, y, (byte) 1);
					else if (maxY < y) setBlock(result, x, z, y, (byte) 3);
					else if (maxY == y) setBlock(result, x, z, y, (byte) 2);
				}
			}
		}

		return result;
	}


	private void setBlock (byte[][] result, int x, int y, int z, byte type) {
		if (result[y >> 4] == null) {
			result[y >> 4] = new byte[4096];
		}
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = type;
	}

}
