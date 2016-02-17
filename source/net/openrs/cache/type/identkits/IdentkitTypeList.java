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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Preconditions;

import net.openrs.cache.Archive;
import net.openrs.cache.Cache;
import net.openrs.cache.Constants;
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
 * @since May 26, 2015
 */
public class IdentkitTypeList implements TypeList<IdentkitType> {

	private Logger logger = Logger.getLogger(IdentkitTypeList.class.getName());

	private IdentkitType[] kits;

	@Override
	public void initialize(Cache cache) {
		int count = 0;
		try {
			Container container = Container.decode(cache.getStore().read(CacheIndex.REFERENCE, CacheIndex.CONFIGS));
			ReferenceTable table = ReferenceTable.decode(container.getData());
			
			Entry entry = table.getEntry(ConfigArchive.IDENTKIT);
			Archive archive = Archive.decode(cache.read(CacheIndex.CONFIGS, ConfigArchive.IDENTKIT).getData(), entry.size());
			
			kits = new IdentkitType[entry.capacity()];
			for (int id = 0; id < entry.capacity(); id++) {
				ChildEntry child = entry.getEntry(id);
				if (child == null)
					continue;

				ByteBuffer buffer = archive.getEntry(child.index());
				IdentkitType type = new IdentkitType(id);
				type.decode(buffer);
				kits[id] = type;
				count++;
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error Loading IdentkitType(s)!", e);
		}
		logger.info("Loaded " + count + " IdentkitType(s)!");
	}

	@Override
	public IdentkitType list(int id) {
		Preconditions.checkArgument(id >= 0, "ID can't be negative!");
		Preconditions.checkArgument(id < kits.length, "ID can't be greater than the max identkit id!");
		return kits[id];
	}

	@Override
	public void print() {
		File file = new File(Constants.TYPE_PATH, "idenkits.txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			Arrays.stream(kits).filter(Objects::nonNull).forEach((IdentkitType t) -> {
				TypePrinter.print(t, writer);
			});
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
