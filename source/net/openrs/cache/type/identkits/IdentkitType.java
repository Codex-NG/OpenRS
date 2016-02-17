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
package net.openrs.cache.type.identkits;

import java.nio.ByteBuffer;

import net.openrs.cache.type.Type;

/**
 * @author Kyle Friz
 * 
 * @since Jun 11, 2015
 */
public class IdentkitType implements Type {

	private final int id;
	private int[] modelIDs;
	private short[] oriTextureColors;
	private short[] modColors;
	private short[] oriColors;
	private int equipSlot = -1;
	private short[] modTextureColors;
	private int[] models = new int[] { -1, -1, -1, -1, -1 };
	private boolean aBool = false;

	public IdentkitType(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.openrs.cache.type.Type#decode(java.nio.ByteBuffer)
	 */
	@Override
	public void decode(ByteBuffer buffer) {
		while (true) {
			int opcode = buffer.get() & 0xFF;
			if (opcode == 0)
				break;

			if (opcode == 1) {
				this.equipSlot = buffer.get() & 0xFF;
			} else if (opcode == 2) {
				int length = buffer.get() & 0xFF;
				this.modelIDs = new int[length];

				for (int index = 0; index < length; ++index) {
					this.modelIDs[index] = buffer.getShort() & 0xFFFF;
				}
			} else if (opcode == 3) {
				this.aBool = true;
			} else if (40 == opcode) {
				int length = buffer.get() & 0xFFFF;
				this.oriColors = new short[length];
				this.modColors = new short[length];

				for (int index = 0; index < length; ++index) {
					this.oriColors[index] = (short) (buffer.getShort() & 0xFFFF);
					this.modColors[index] = (short) (buffer.getShort() & 0xFFFF);
				}
			} else if (41 == opcode) {
				int length = buffer.get() & 0xFF;
				this.oriTextureColors = new short[length];
				this.modTextureColors = new short[length];

				for (int index = 0; index < length; ++index) {
					this.oriTextureColors[index] = (short) (buffer.getShort() & 0xFFFF);
					this.modTextureColors[index] = (short) (buffer.getShort());
				}
			} else if (opcode >= 60 && opcode < 70) {
				this.models[opcode - 60] = buffer.getShort() & 0xFFFF;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.openrs.cache.type.Type#getID()
	 */
	@Override
	public int getID() {
		return id;
	}

	/**
	 * @return the modelIDs
	 */
	public int[] getModelIDs() {
		return modelIDs;
	}

	/**
	 * @return the oriTextureColors
	 */
	public short[] getOriTextureColors() {
		return oriTextureColors;
	}

	/**
	 * @return the modColors
	 */
	public short[] getModColors() {
		return modColors;
	}

	/**
	 * @return the oriColors
	 */
	public short[] getOriColors() {
		return oriColors;
	}

	/**
	 * @return the equipSlot
	 */
	public int getEquipSlot() {
		return equipSlot;
	}

	/**
	 * @return the modTextureColors
	 */
	public short[] getModTextureColors() {
		return modTextureColors;
	}

	/**
	 * @return the models
	 */
	public int[] getModels() {
		return models;
	}

	/**
	 * @return the aBool
	 */
	public boolean isaBool() {
		return aBool;
	}

}
