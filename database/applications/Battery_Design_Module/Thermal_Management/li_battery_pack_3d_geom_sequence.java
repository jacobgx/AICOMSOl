/*
 * li_battery_pack_3d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class li_battery_pack_3d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.param().set("N_fins_model", "3");
    model.param().descr("N_fins_model", "\u6a21\u578b\u4e2d\u51b7\u5374\u7fc5\u7247\u7684\u6570\u91cf");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u957f\u65b9\u4f53 1 - \u7535\u6c60");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 2, 100});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").label("\u9635\u5217 1  - \u7535\u6c60");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 4, 0});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("arr1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"1", "N_fins_model", "1"});
    model.component("comp1").geom("geom1").feature("arr2").label("\u9635\u5217 2  - \u7535\u6c60");
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{0, 6, 0});
    model.component("comp1").geom("geom1").feature("arr2").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u957f\u65b9\u4f53 2 - \u51b7\u5374\u7fc5\u7247");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{100, 2, 100});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 2, 0});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{13, 2, 20});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{-13, 2, 0});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new int[]{50, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5408\u96c6 1 - \u51b7\u5374\u7fc5\u7247");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk2", "blk3", "mir1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", 2.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel1")
         .label("\u6d41\u9053\u677f\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{1, 32});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{3, -2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{1, 25});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{6, -2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{1, 18});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new int[]{9, -2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new int[]{1, 11});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{12, -2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new int[]{1, 4});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new int[]{15, -2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new int[]{60, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("pos", new int[]{13, 39});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new int[]{13, 30});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new int[]{73, 49});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c1", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c2", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input")
         .set("del1", "r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("displx", "3,6,9,12");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("disply", "-7,-14,-21,-28");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("pos", new int[]{82, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("size", new int[]{1, 8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("pos", new int[]{85, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new int[]{1, 15});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("pos", new int[]{88, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10").set("size", new int[]{1, 22});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10").set("pos", new int[]{91, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r10");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r11").set("size", new int[]{1, 29});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r11").set("pos", new int[]{94, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r11").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r11");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("pos", new int[]{0, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").label("\u62c9\u4f38 1 - \u6d41\u9053\u677f\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").label("\u9635\u5217 3 - \u51b7\u5374\u7fc5\u7247");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new String[]{"1", "N_fins_model", "1"});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new int[]{0, 6, 0});
    model.component("comp1").geom("geom1").feature("arr3").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("arr4", "Array");
    model.component("comp1").geom("geom1").feature("arr4").selection("input").named("ext1");
    model.component("comp1").geom("geom1").feature("arr4").label("\u9635\u5217 4 - \u6d41\u9053\u677f\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("arr4").set("fullsize", new String[]{"1", "N_fins_model", "1"});
    model.component("comp1").geom("geom1").feature("arr4").set("displ", new int[]{0, 6, 0});
    model.component("comp1").geom("geom1").feature("arr4").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr4");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("blk4")
         .label("\u957f\u65b9\u4f53 4 - \u51fa\u53e3\u6d41\u52a8\u8fde\u63a5\u5668\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new int[]{8, 18, 16});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{-10, 0, 2});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("blk5")
         .label("\u957f\u65b9\u4f53 5 - \u5165\u53e3\u6d41\u52a8\u8fde\u63a5\u5668\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new int[]{8, 16, 16});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new int[]{102, 0, 2});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("arr3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk4", "blk5");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u51b7\u5374\u7fc5\u7247");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"arr3"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"arr4"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6d41\u52a8\u5ba4");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"arr4", "blk4", "blk5"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u6d41\u52a8\u5ba4\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 450);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 38);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3")
         .label("\u51fa\u53e3\u6d41\u52a8\u8fde\u63a5\u5668\u6d41\u9053\u7684\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 17);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5411\u7535\u5806\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 122);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u51e0\u4f55");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u6240\u6709\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel5"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u5916\u90e8\u70ed\u901a\u91cf\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("difsel2")
         .set("subtract", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").feature("difsel3").label("\u6d41\u9053\u677f\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"blk4", "blk5"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3")
         .label("\u6d41\u9053\u677f\u6d41\u9053\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"difsel3"});
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4")
         .label("\u6d41\u52a8\u8fde\u63a5\u5668\u6d41\u9053\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"blk4", "blk5"});
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1")
         .label("\u6d41\u9053\u677f\u6d41\u9053\u7684\u5165\u53e3/\u51fa\u53e3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel3", "adjsel4"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("adjsel5", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel5")
         .label("\u6d41\u9053\u677f\u6d41\u9053\u7684\u5165\u53e3/\u51fa\u53e3\u7684\u8fb9");
    model.component("comp1").geom("geom1").feature("adjsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel5").set("input", new String[]{"intsel1"});
    model.component("comp1").geom("geom1").feature("adjsel5").set("outputdim", 1);
    model.component("comp1").geom("geom1").run("adjsel5");
    model.component("comp1").geom("geom1").create("difsel4", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel4")
         .label("\u5212\u5206\u8fb9\u754c\u5c42\u7f51\u683c\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel4").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel4").set("subtract", new String[]{"sel1", "sel2", "sel3"});

    model.title(null);

    model.description("");

    model.label("li_battery_pack_3d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
