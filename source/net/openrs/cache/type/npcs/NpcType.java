/**
 * Copyright (c) 2015 Kyle Friz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.openrs.cache.type.npcs;

import java.nio.ByteBuffer;

import net.openrs.cache.type.Type;
import net.openrs.util.ByteBufferUtils;

/**
 * @author Kyle Friz
 * 
 * @since May 26, 2015
 */
public class NpcType implements Type {

	private final int id;
	private short[] recolorToFind;
	private int anInt2156 = 32;
	private String name = "null";
	private short[] recolorToReplace;
	private int[] models;
	private int[] models_2;
	private int stanceAnimation = -1;
	private int anInt2165 = -1;
	private int tileSpacesOccupied = -1;
	private int walkAnimation = -1;
	private short[] retextureToReplace;
	private int rotate90RightAnimation = -1;
	private boolean aBool2170 = true;
	private int resizeX = -1;
	private int contrast = 0;
	private int rotate180Animation = -1;
	private int anInt2174 = -1;
	private String[] options = new String[5];
	private boolean renderOnMinimap = true;
	private int combatLevel = -1;
	private int rotate90LeftAnimation = -1;
	private int resizeY = 957515136;
	private boolean hasRenderPriority = false;
	private int ambient = 0;
	private int headIcon = -1;
	private int anInt2184 = -1;
	private int[] anIntArray2185;
	private short[] retextureToFind;
	private int anInt2187 = -1;
	private boolean isClickable = true;
	private int anInt2189 = -1;
	private boolean aBool2190 = false;

	public NpcType(int id) {
		this.id = id;
	}

	@Override
	public void decode(ByteBuffer buffer) {
		while (true) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0)
				return;

			if (1 == opcode) {
				int length = buffer.get() & 0xFF;
				models = new int[length];

				for (int idx = 0; idx < length; ++idx) {
					models[idx] = buffer.getShort() & 0xFFFF;
				}

			} else if (2 == opcode) {
				name = ByteBufferUtils.getString(buffer);
			} else if (12 == opcode) {
				tileSpacesOccupied = buffer.get() & 0xFF;
			} else if (opcode == 13) {
				stanceAnimation = buffer.getShort() & 0xFFFF;
			} else if (opcode == 14) {
				walkAnimation = buffer.getShort() & 0xFFFF;
			} else if (15 == opcode) {
				anInt2165 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 16) {
				anInt2189 = buffer.getShort() & 0xFFFF;
			} else if (17 == opcode) {
				walkAnimation = buffer.getShort() & 0xFFFF;
				rotate180Animation = buffer.getShort() & 0xFFFF;
				rotate90RightAnimation = buffer.getShort() & 0xFFFF;
				rotate90LeftAnimation = buffer.getShort() & 0xFFFF;
			} else if (opcode >= 30 && opcode < 35) {
				options[opcode - 30] = ByteBufferUtils.getString(buffer);
				if (options[opcode - 30].equalsIgnoreCase("Hidden")) {
					options[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				int length = buffer.get() & 0xFF;
				recolorToFind = new short[length];
				recolorToReplace = new short[length];

				for (int idx = 0; idx < length; ++idx) {
					recolorToFind[idx] = (short) (buffer.getShort() & 0xFFFF);
					recolorToReplace[idx] = (short) (buffer.getShort() & 0xFFFF);
				}

			} else if (opcode == 41) {
				int length = buffer.get() & 0xFF;
				retextureToFind = new short[length];
				retextureToReplace = new short[length];

				for (int idx = 0; idx < length; ++idx) {
					retextureToFind[idx] = (short) (buffer.getShort() & 0xFFFF);
					retextureToReplace[idx] = (short) (buffer.getShort() & 0xFFFF);
				}

			} else if (60 == opcode) {
				int length = buffer.get() & 0xFF;
				models_2 = new int[length];

				for (int idx = 0; idx < length; ++idx) {
					models_2[idx] = buffer.getShort() & 0xFFFF;
				}

			} else if (opcode == 93) {
				renderOnMinimap = false;
			} else if (95 == opcode) {
				combatLevel = buffer.getShort() & 0xFFFF;
			} else if (97 == opcode) {
				resizeX = buffer.getShort() & 0xFFFF;
			} else if (98 == opcode) {
				resizeY = buffer.getShort() & 0xFFFF;
			} else if (opcode == 99) {
				hasRenderPriority = true;
			} else if (100 == opcode) {
				ambient = buffer.get();
			} else if (101 == opcode) {
				contrast = buffer.get();
			} else if (opcode == 102) {
				headIcon = buffer.getShort() & 0xFFFF;
			} else if (103 == opcode) {
				anInt2156 = buffer.getShort() & 0xFFFF;
			} else if (opcode == 106) {
				anInt2174 = buffer.getShort() & 0xFFFF;
				if (65535 == anInt2174) {
					anInt2174 = -1;
				}

				anInt2187 = buffer.getShort() & 0xFFFF;
				if (65535 == anInt2187) {
					anInt2187 = -1;
				}

				int length = buffer.get() & 0xFF;
				anIntArray2185 = new int[length + 1];

				for (int idx = 0; idx <= length; ++idx) {
					anIntArray2185[idx] = buffer.getShort() & 0xFFFF;
					if (anIntArray2185[idx] == 65535) {
						anIntArray2185[idx] = -1;
					}
				}

			} else if (107 == opcode) {
				isClickable = false;
			} else if (opcode == 109) {
				aBool2170 = false;
			} else if (opcode == 111) {
				aBool2190 = true;
			} else if (opcode == 112) {
				anInt2184 = buffer.get() & 0xFF;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.openrs.cache.type.Type#encode()
	 */
	@Override
	public ByteBuffer encode() {
		ByteBuffer buffer = ByteBuffer.allocate(1132);
		return (ByteBuffer) buffer.flip();
	}

	public int getID() {
		return id;
	}

	/**
	 * @return the recolorToFind
	 */
	public short[] getRecolorToFind() {
		return recolorToFind;
	}

	/**
	 * @return the anInt2156
	 */
	public int getAnInt2156() {
		return anInt2156;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the recolorToReplace
	 */
	public short[] getRecolorToReplace() {
		return recolorToReplace;
	}

	/**
	 * @return the models
	 */
	public int[] getModels() {
		return models;
	}

	/**
	 * @return the models_2
	 */
	public int[] getModels_2() {
		return models_2;
	}

	/**
	 * @return the stanceAnimation
	 */
	public int getStanceAnimation() {
		return stanceAnimation;
	}

	/**
	 * @return the anInt2165
	 */
	public int getAnInt2165() {
		return anInt2165;
	}

	/**
	 * @return the tileSpacesOccupied
	 */
	public int getTileSpacesOccupied() {
		return tileSpacesOccupied;
	}

	/**
	 * @return the walkAnimation
	 */
	public int getWalkAnimation() {
		return walkAnimation;
	}

	/**
	 * @return the retextureToReplace
	 */
	public short[] getRetextureToReplace() {
		return retextureToReplace;
	}

	/**
	 * @return the rotate90RightAnimation
	 */
	public int getRotate90RightAnimation() {
		return rotate90RightAnimation;
	}

	/**
	 * @return the aBool2170
	 */
	public boolean isaBool2170() {
		return aBool2170;
	}

	/**
	 * @return the resizeX
	 */
	public int getResizeX() {
		return resizeX;
	}

	/**
	 * @return the contrast
	 */
	public int getContrast() {
		return contrast;
	}

	/**
	 * @return the rotate180Animation
	 */
	public int getRotate180Animation() {
		return rotate180Animation;
	}

	/**
	 * @return the anInt2174
	 */
	public int getAnInt2174() {
		return anInt2174;
	}

	/**
	 * @return the options
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * @return the renderOnMinimap
	 */
	public boolean isRenderOnMinimap() {
		return renderOnMinimap;
	}

	/**
	 * @return the combatLevel
	 */
	public int getCombatLevel() {
		return combatLevel;
	}

	/**
	 * @return the rotate90LeftAnimation
	 */
	public int getRotate90LeftAnimation() {
		return rotate90LeftAnimation;
	}

	/**
	 * @return the resizeY
	 */
	public int getResizeY() {
		return resizeY;
	}

	/**
	 * @return the hasRenderPriority
	 */
	public boolean isHasRenderPriority() {
		return hasRenderPriority;
	}

	/**
	 * @return the ambient
	 */
	public int getAmbient() {
		return ambient;
	}

	/**
	 * @return the headIcon
	 */
	public int getHeadIcon() {
		return headIcon;
	}

	/**
	 * @return the anInt2184
	 */
	public int getAnInt2184() {
		return anInt2184;
	}

	/**
	 * @return the anIntArray2185
	 */
	public int[] getAnIntArray2185() {
		return anIntArray2185;
	}

	/**
	 * @return the retextureToFind
	 */
	public short[] getRetextureToFind() {
		return retextureToFind;
	}

	/**
	 * @return the anInt2187
	 */
	public int getAnInt2187() {
		return anInt2187;
	}

	/**
	 * @return the isClickable
	 */
	public boolean isClickable() {
		return isClickable;
	}

	/**
	 * @return the anInt2189
	 */
	public int getAnInt2189() {
		return anInt2189;
	}

	/**
	 * @return the aBool2190
	 */
	public boolean isaBool2190() {
		return aBool2190;
	}

}
