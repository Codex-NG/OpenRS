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
package net.openrs.cache.type;

import net.openrs.cache.Cache;
import net.openrs.cache.type.enums.EnumType;
import net.openrs.cache.type.enums.EnumTypeList;
import net.openrs.cache.type.identkits.IdentkitType;
import net.openrs.cache.type.identkits.IdentkitTypeList;
import net.openrs.cache.type.items.ItemType;
import net.openrs.cache.type.items.ItemTypeList;
import net.openrs.cache.type.npcs.NpcType;
import net.openrs.cache.type.npcs.NpcTypeList;
import net.openrs.cache.type.objects.ObjectType;
import net.openrs.cache.type.objects.ObjectTypeList;
import net.openrs.cache.type.overlays.OverlayType;
import net.openrs.cache.type.overlays.OverlayTypeList;
import net.openrs.cache.type.sequences.SequenceType;
import net.openrs.cache.type.sequences.SequenceTypeList;
import net.openrs.cache.type.spotanims.SpotAnimType;
import net.openrs.cache.type.spotanims.SpotAnimTypeList;
import net.openrs.cache.type.underlays.UnderlayType;
import net.openrs.cache.type.underlays.UnderlayTypeList;
import net.openrs.cache.type.varbits.VarBitType;
import net.openrs.cache.type.varbits.VarBitTypeList;
import net.openrs.cache.type.varclients.VarClientType;
import net.openrs.cache.type.varclients.VarClientTypeList;
import net.openrs.cache.type.varclientstrings.VarClientStringType;
import net.openrs.cache.type.varclientstrings.VarClientStringTypeList;
import net.openrs.cache.type.varplayers.VarPlayerType;
import net.openrs.cache.type.varplayers.VarPlayerTypeList;

/**
 * @author Kyle Friz
 * 
 * @since May 26, 2015
 */
public class TypeListManager {

	private static ItemTypeList item = new ItemTypeList();
	private static NpcTypeList npc = new NpcTypeList();
	private static VarBitTypeList varbit = new VarBitTypeList();
	private static VarPlayerTypeList varp = new VarPlayerTypeList();
	private static VarClientTypeList varc = new VarClientTypeList();
	private static VarClientStringTypeList varcstr = new VarClientStringTypeList();
	private static IdentkitTypeList ident = new IdentkitTypeList();
	private static UnderlayTypeList under = new UnderlayTypeList();
	private static OverlayTypeList over = new OverlayTypeList();
	private static EnumTypeList enm = new EnumTypeList();
	private static ObjectTypeList obj = new ObjectTypeList();
	private static SpotAnimTypeList anim = new SpotAnimTypeList();
	private static SequenceTypeList seq = new SequenceTypeList();

	public static void initialize(Cache cache) {
		item.initialize(cache);
		ident.initialize(cache);
		npc.initialize(cache);
		varp.initialize(cache);
		varbit.initialize(cache);
		varc.initialize(cache);
		varcstr.initialize(cache);
		under.initialize(cache);
		over.initialize(cache);
		enm.initialize(cache);
		obj.initialize(cache);
		anim.initialize(cache);
		seq.initialize(cache);
	}
	
	public static void print() {
		item.print();
		ident.print();
		npc.print();
		varp.print();
		varbit.print();
		varc.print();
		varcstr.print();
		under.print();
		over.print();
		enm.print();
		obj.print();
		anim.print();
		seq.print();
	}

	public static ItemType lookupItem(int id) {
		return item.list(id);
	}

	public static IdentkitType lookupIdentkit(int id) {
		return ident.list(id);
	}

	public static NpcType lookupNpc(int id) {
		return npc.list(id);
	}

	public static VarBitType lookupVarBit(int id) {
		return varbit.list(id);
	}

	public static VarPlayerType lookupVarPlayer(int id) {
		return varp.list(id);
	}

	public static VarClientType lookupVarClient(int id) {
		return varc.list(id);
	}

	public static VarClientStringType lookupVarClientString(int id) {
		return varcstr.list(id);
	}

	public static UnderlayType lookupUnder(int id) {
		return under.list(id);
	}

	public static OverlayType lookupOver(int id) {
		return over.list(id);
	}

	public static EnumType lookupEnum(int id) {
		return enm.list(id);
	}

	public static ObjectType lookupObject(int id) {
		return obj.list(id);
	}

	public static SpotAnimType lookupSpotAnim(int id) {
		return anim.list(id);
	}

	public static SequenceType lookupSequence(int id) {
		return seq.list(id);
	}

}
