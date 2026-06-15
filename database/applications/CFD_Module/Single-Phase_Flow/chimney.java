/*
 * chimney.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class chimney {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowSST", "geom1");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("fw", "10[m]");
    model.param().descr("fw", "\u5de5\u5382\u6a21\u5757\u5bbd\u5ea6");
    model.param().set("fh", "8[m]");
    model.param().descr("fh", "\u5de5\u5382\u6a21\u5757\u9ad8\u5ea6");
    model.param().set("fwh", "3[m]");
    model.param().descr("fwh", "\u5de5\u5382\u7a97\u6237\u9ad8\u5ea6");
    model.param().set("fn", "5");
    model.param().descr("fn", "\u6a21\u5757\u6570");
    model.param().set("fd", "30[m]");
    model.param().descr("fd", "\u5de5\u5382\u6df1\u5ea6");
    model.param().set("c_d", "3[m]");
    model.param().descr("c_d", "\u70df\u56f1\u76f4\u5f84");
    model.param().set("c_h", "25[m]");
    model.param().descr("c_h", "\u70df\u56f1\u9ad8\u5ea6");
    model.param().set("swf", "0.2");
    model.param().descr("swf", "\u7b8d\u6761\u5bbd\u5ea6\u56e0\u5b50");
    model.param().set("sp", "5*c_d");
    model.param().descr("sp", "\u7b8d\u6761\u8282\u8ddd");
    model.param().set("sn", "3");
    model.param().descr("sn", "\u7b8d\u6761\u6570");
    model.param().set("d_w", "fw*fn+fw*4");
    model.param().descr("d_w", "\u57df\u5bbd\u5ea6");
    model.param().set("d_l", "100[m]");
    model.param().descr("d_l", "\u57df\u957f\u5ea6");
    model.param().set("d_b", "20[m]");
    model.param().descr("d_b", "\u5efa\u7b51\u7269\u524d\u9762\u7684\u7a7a\u95f4");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "U_in");
    model.func("an1").set("expr", "20*(x/15)^0.27");
    model.func("an1").set("fununit", "m/s");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").setIndex("plotargs", 50, 0, 1);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "fh", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "fw", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "fh-fwh", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "fw", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{5, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"fw", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "fd", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5de5\u5382\u5efa\u7b51");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "fh-fwh");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "c_d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"fw*fn/2", "fd/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "c_h", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "sn>0");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("ext2", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", "c_d/2", 0, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", "c_d/2*(1+swf)", 1, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").set("rot", "range(0,360/sn,359)");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn", 0);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*2", 1);
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*3", 2);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*4", 3);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*5", 4);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*6", 5);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*7", 6);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "sp/8/sn*8", 7);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8", 0);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*2", 1);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*3", 2);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*4", 3);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*5", 4);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*6", 5);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*7", 6);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("twist", "360/sn/8*8", 7);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("arr1")
         .set("fullsize", new String[]{"1", "1", "ceil(c_h/sp*sn)"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "-sp/sn"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"d_w", "d_l", "d_h"});

    model.param().set("d_h", "c_h*1.5", "");

    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-(d_w-fw*fn)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-d_b", 1);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp4").selection("face").set("ext2", 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("c1").set("r", "c_d/2+1");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "-c_d/2-1", 0, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "-c_d*2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "d_l-d_b-fd/2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "c_d*2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "d_l-d_b-fd/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "c_d/2+1", 3, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("uni1").selection("input")
         .set("c1", "pol1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("c2").set("r", "c_d/2*(1+swf+0.1)");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "c_h*1.1", 0);
    model.component("comp1").geom("geom1").run("ext4");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp5").set("quicky", "d_l/2");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("ext4");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "par1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1", "ext1", "ext2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 2, 3);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u70df\u56f1");
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u7b8d\u6761");
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("mcd1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u70df\u56f1\u548c\u7b8d\u6761");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel2", "csel3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("mcd1", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"csel1", "sel1"});
    model.component("comp1").geom("geom1").feature("unisel2").label("\u5de5\u5382\u548c\u5730\u9762");
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u8fb9\u754c\u5c42\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"csel2", "csel3", "unisel2"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U_in(z)");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(1, 4, 5, 167);
    model.component("comp1").physics("spf").feature("open1").set("Uref", "20[m/s]");
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("IsotropicDiffusion", true);
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("RANSIsotropicDiffusion", true);
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("delid", 0.15);
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("delidRANS", 0.15);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel3_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_csel2_bnd");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Fstr_y", "intop1(spf.T_trac_uy)+intop1(spf.T_trac_dy)");
    model.component("comp1").variable("var1").set("Fstr_x", "intop1(spf.T_trac_dx)+intop1(spf.T_trac_ux)");
    model.component("comp1").variable("var1").set("Fch_y", "intop2(spf.T_tracy)");
    model.component("comp1").variable("var1").set("Fch_x", "intop2(spf.T_tracx)");
    model.component("comp1").variable("var1").set("F_y", "Fstr_y+Fch_y");
    model.component("comp1").variable("var1").set("F_x", "Fstr_x+Fch_x");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_unisel3");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_csel3_bnd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 0.15);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().named("geom1_unisel3");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "fw", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "fw", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "sn", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 3", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5185\u58c1");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset("surf1").set("data", "dset3");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 77, 91, 92, 93, 94, 95, 96, 97, 99, 101, 103, 105, 108, 158, 159, 160, 161, 162, 163, 164, 165, 166);
    model.result().dataset("surf2").set("data", "dset3");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 98, 100, 102, 104, 106, 107, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature().create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").setIndex("looplevel", 2, 0);
    model.result("pg2").feature("slit1").set("upexpr", "up(p)");
    model.result("pg2").feature("slit1").set("downexpr", "down(p)");
    model.result("pg2").feature("slit1").set("titletype", "none");
    model.result("pg2").feature("slit1").set("smooth", "internal");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").label("\u58c1\u5206\u8fa8\u7387\uff0c\u5185\u58c1");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg3").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u5782\u76f4\u901f\u5ea6\u5207\u9762");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("quickxnumber", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("inheritplot", "slc1");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("sel1").selection().named("geom1_csel2_bnd");
    model.result("pg4").run();
    model.result("pg4").create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").set("upexpr", "up(spf.U)");
    model.result("pg4").feature("slit1").set("downexpr", "down(spf.U)");
    model.result("pg4").feature("slit1").set("inheritplot", "slc1");
    model.result("pg4").feature("slit1").create("sel1", "Selection");
    model.result("pg4").feature("slit1").feature("sel1").selection().named("geom1_csel3_bnd");
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("sel1").selection().set(3);
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("mtrl1").set("family", "rock");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg4").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6c34\u5e73\u901f\u5ea6\u5207\u9762");
    model.result("pg5").run();
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickznumber", 1);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u538b\u529b\u5207\u9762");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").set("expr", "p");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").run();
    model.result("pg6").feature("slit1").set("upexpr", "up(p)");
    model.result("pg6").feature("slit1").set("downexpr", "down(p)");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").set("expr", "1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u5de5\u5382\u70df\u56f1\u5468\u56f4\u7684\u6e4d\u6d41");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5de5\u5382\u70df\u56f1\u5468\u56f4\u7684\u6e4d\u6d41\u6d41\u52a8\uff0c\u7814\u7a76\u4e86\u7528\u4e8e\u51cf\u5c11\u5f3a\u98ce\u6761\u4ef6\u4e0b\u6da1\u6d41\u5927\u91cf\u8131\u843d\u7684\u87ba\u65cb\u578b\u7b8d\u6761\u5bf9\u6d41\u578b\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("chimney.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
