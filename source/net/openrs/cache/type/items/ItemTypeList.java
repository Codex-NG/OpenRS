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
package net.openrs.cache.type.items;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Preconditions;

import net.openrs.cache.Archive;
import net.openrs.cache.Cache;
import net.openrs.cache.Container;
import net.openrs.cache.ReferenceTable;
import net.openrs.cache.ReferenceTable.ChildEntry;
import net.openrs.cache.ReferenceTable.Entry;
import net.openrs.cache.type.CacheIndex;
import net.openrs.cache.type.ConfigArchive;
import net.openrs.cache.type.TypeList;
import net.openrs.cache.type.TypePrinter;

/**
 * @author Kyle Friz
 * 
 * @since May 27, 2015
 */
public class ItemTypeList implements TypeList<ItemType> {

	private ItemType[] items;

	private Logger logger = Logger.getLogger(ItemTypeList.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sean.openrs.type.TypeList#initialize(com.sean.openrs.Cache)
	 */
	@Override
	public void initialize(Cache cache) {
		int count = 0;
		try {
			Container container = Container.decode(cache.getStore().read(CacheIndex.REFERENCE, CacheIndex.CONFIGS));
			ReferenceTable table = ReferenceTable.decode(container.getData());
			
			Entry entry = table.getEntry(ConfigArchive.ITEM);
			Archive archive = Archive.decode(cache.read(CacheIndex.CONFIGS, ConfigArchive.ITEM).getData(), entry.size());
			
			items = new ItemType[entry.capacity()];
			for (int id = 0; id < entry.capacity(); id++) {
				ChildEntry child = entry.getEntry(id);
				if (child == null)
					continue;

				ByteBuffer buffer = archive.getEntry(child.index());
				ItemType type = new ItemType(id);
				type.decode(buffer);
				items[id] = type;
				count++;
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error Loading ItemType(s)!", e);
		}
		logger.info("Loaded " + count + " ItemType(s)!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sean.openrs.type.TypeList#list(int)
	 */
	@Override
	public ItemType list(int id) {
		Preconditions.checkArgument(id >= 0, "ID can't be negative!");
		Preconditions.checkArgument(id < items.length, "ID can't be greater than the max item id!");
		return items[id];
	}

	@Override
	public void print() {
		Arrays.stream(items).filter(Objects::nonNull).forEach((ItemType t) -> {
			TypePrinter.print(t, "items/");
		});
	}

}
