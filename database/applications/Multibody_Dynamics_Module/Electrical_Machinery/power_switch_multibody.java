/*
 * power_switch_multibody.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:22 by COMSOL 6.3.0.290. */
public class power_switch_multibody {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Electrical_Machinery");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature("ccc").set("CoilName", "1");
    model.study("std1").feature("ccc").set("outputmap", new String[]{});
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").setSolveFor("/physics/mf", true);
    model.study("std1").feature("ccc").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("l1", "2[cm]", "\u51e0\u4f55\u53c2\u6570 l1");
    model.param().set("l2", "2[cm]", "\u51e0\u4f55\u53c2\u6570 l2");
    model.param().set("l3", "2[cm]", "\u51e0\u4f55\u53c2\u6570 l3");
    model.param().set("l4", "2[cm]", "\u51e0\u4f55\u53c2\u6570 l4");
    model.param().set("l5", "2[cm]", "\u51e0\u4f55\u53c2\u6570 l5");
    model.param().set("d1", "2[cm]", "\u51e0\u4f55\u53c2\u6570 d1");
    model.param().set("h1", "2[cm]", "\u51e0\u4f55\u53c2\u6570 h1");
    model.param().set("h2", "2[cm]", "\u51e0\u4f55\u53c2\u6570 h2");
    model.param().set("g1", "5[mm]", "\u51e0\u4f55\u53c2\u6570 g1");
    model.param().set("g2", "5[mm]", "\u51e0\u4f55\u53c2\u6570 g2");
    model.param().set("g3", "5[mm]", "\u51e0\u4f55\u53c2\u6570 g3");
    model.param().set("d0", "5[mm]", "\u51e0\u4f55\u53c2\u6570 d0");
    model.param().set("d2", "4[cm]", "\u51e0\u4f55\u53c2\u6570 d2");
    model.param().set("a_coil", "1[mm^2]", "\u7ebf\u5708\u5bfc\u7ebf\u622a\u9762\u79ef");
    model.param().set("filling", "0.9", "\u7ebf\u5708\u586b\u5145\u56e0\u5b50");
    model.param().set("mass", "400[g]", "\u67f1\u585e\u8d28\u91cf");
    model.param().set("k0", "2[N/mm]", "\u5f39\u7c27\u5e38\u6570");
    model.param().set("x0", "5[mm]", "\u5f39\u7c27\u9884\u5e94\u529b\u4f4d\u79fb");
    model.param().set("maxdisp", "0.95*d0", "\u6700\u5927\u4f4d\u79fb");
    model.param().set("maxdisp", "0.9*d0");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u94c1\u82af\u622a\u9762");
    model.geom("part1").create("r1", "Rectangle");
    model.geom("part1").feature("r1").set("size", new String[]{"l1+g1+d1+g2+l2", "1"});
    model.geom("part1").feature("r1").setIndex("size", "h1+l3", 1);
    model.geom("part1").feature("r1").set("pos", new String[]{"0", "-(h1+l3)"});
    model.geom("part1").run("r1");
    model.geom("part1").create("r2", "Rectangle");
    model.geom("part1").feature("r2").set("size", new String[]{"g1+d1+g2", "h1"});
    model.geom("part1").feature("r2").set("pos", new String[]{"l1", "-h1"});
    model.geom("part1").run("r2");
    model.geom("part1").create("dif1", "Difference");
    model.geom("part1").feature("dif1").selection("input").set("r1");
    model.geom("part1").feature("dif1").selection("input2").set("r2");
    model.geom("part1").run("dif1");
    model.geom("part1").create("mir1", "Mirror");
    model.geom("part1").feature("mir1").set("pos", new String[]{"0", "d0/2"});
    model.geom("part1").feature("mir1").set("axis", new int[]{0, 1});
    model.geom("part1").feature("mir1").selection("input").set("dif1");
    model.geom("part1").feature("mir1").set("keep", true);
    model.geom("part1").run("mir1");
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("\u7ebf\u5708\u622a\u9762");
    model.geom("part2").create("r1", "Rectangle");
    model.geom("part2").feature("r1").set("size", new String[]{"d1", "l5"});
    model.geom("part2").feature("r1").set("pos", new String[]{"l1+g1", "0"});
    model.geom("part2").run("r1");
    model.geom("part2").create("c1", "Circle");
    model.geom("part2").feature("c1").set("r", "g1+d1");
    model.geom("part2").feature("c1").set("angle", 90);
    model.geom("part2").feature("c1").set("pos", new String[]{"l1", "l5"});
    model.geom("part2").feature("c1").setIndex("layer", "d1", 0);
    model.geom("part2").run("c1");
    model.geom("part2").create("r2", "Rectangle");
    model.geom("part2").feature("r2").set("size", new String[]{"l1", "d1"});
    model.geom("part2").feature("r2").set("pos", new String[]{"0", "l5+g1"});
    model.geom("part2").run("r2");
    model.geom("part2").create("del1", "Delete");
    model.geom("part2").feature("del1").selection("input").init(2);
    model.geom("part2").feature("del1").selection("input").set("c1", 1);
    model.geom("part2").run("del1");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").label("\u5b9e\u4f53\u96f6\u4ef6");
    model.geom("part3").create("wp1", "WorkPlane");
    model.geom("part3").feature("wp1").set("unite", true);
    model.geom("part3").feature("wp1").set("quickplane", "xz");
    model.geom("part3").feature("wp1").geom().create("pi1", "PartInstance");
    model.geom("part3").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.geom("part3").feature("wp1").geom().feature("pi1").set("part", "part1");
    model.geom("part3").run("wp1");
    model.geom("part3").feature().create("ext1", "Extrude");
    model.geom("part3").feature("ext1").set("workplane", "wp1");
    model.geom("part3").feature("ext1").selection("input").set("wp1");
    model.geom("part3").feature("ext1").label("\u975e\u7ebf\u6027\u94c1\u82af");
    model.geom("part3").feature("ext1").set("selresult", true);
    model.geom("part3").feature("ext1").set("selresultshow", "all");
    model.geom("part3").feature("ext1").setIndex("distance", "l5", 0);
    model.geom("part3").feature("ext1").set("reverse", true);
    model.geom("part3").run("ext1");
    model.geom("part3").create("sel1", "ExplicitSelection");
    model.geom("part3").feature("sel1").label("\u4e0a\u94c1\u82af");
    model.geom("part3").feature("sel1").selection("selection").set("ext1", 2);
    model.geom("part3").run("sel1");
    model.geom("part3").create("wp2", "WorkPlane");
    model.geom("part3").feature("wp2").set("unite", true);
    model.geom("part3").feature("wp2").set("quickz", "-h1");
    model.geom("part3").feature("wp2").geom().create("pi1", "PartInstance");
    model.geom("part3").feature("wp2").geom().feature("pi1").set("selkeepnoncontr", false);
    model.geom("part3").feature("wp2").geom().feature("pi1").set("part", "part2");
    model.geom("part3").run("wp2");
    model.geom("part3").feature().create("ext2", "Extrude");
    model.geom("part3").feature("ext2").set("workplane", "wp2");
    model.geom("part3").feature("ext2").selection("input").set("wp2");
    model.geom("part3").feature("ext2").label("\u7ebf\u5708");
    model.geom("part3").feature("ext2").set("selresult", true);
    model.geom("part3").feature("ext2").set("selresultshow", "all");
    model.geom("part3").feature("ext2").setIndex("distance", "d2", 0);
    model.geom("part3").run("ext2");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_ext1.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_ext2.dom", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"l1+g1+d1+g2+l2+2[cm]", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "l5+g1+d1+g3+2[cm]", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "h1+l3+d0+h2+l4+4[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-h1-l3-2[cm]"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "pi1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("uni1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface").set("uni1", 24, 33, 10, 11, 7);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").selection("offsetvertex").set("pard1", 34);
    model.component("comp1").geom("geom1").feature("wp1").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp1").selection("originvertex").set("pard1", 34);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cro1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("cro1", 1, 2, 4, 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("off1", "Offset");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("off1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("off1").selection("input")
         .set("del1", 2, 3, 5, 8, 9, 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("off1").set("keep", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("off1").set("distance", "d0");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("off1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "d1+2*d0", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "d1+2*d0", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext1").selection("vertex").set("pard1", 11);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("top", "d0+h2+l4");
    model.component("comp1").geom("geom1").feature("cylsel1").set("bottom", "d0");
    model.component("comp1").geom("geom1").feature("cylsel1")
         .set("pos", new String[]{"0", "l5+g1+d1+g3+2[cm]", "0"});
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").named("cylsel1");
    model.component("comp1").geom("geom1").run("cmd1");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u67f1\u585e");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_pi1_sel1", "geom1_cylsel1"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u56fa\u5b9a\u57df");
    model.component("comp1").selection("box1").set("zmax", "-d0");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u53d8\u5f62\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"uni1", "box1"});
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4e0a\u90e8\u57df");
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u4e2d\u95f4\u57df");
    model.component("comp1").selection("dif1").set("add", new String[]{"com1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u53d8\u5f62\u57df\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"com1"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u67f1\u585e\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("\u56fa\u5b9a\u57df\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj3").set("input", new String[]{"box1"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u67f1\u585e\u4e0a\u7684\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj3"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4e0a\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(16);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"int1", "sel2"});
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").label("\u52a8\u8fb9\u754c");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u4fa7\u8fb9\u754c");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3")
         .add(1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 18, 21, 24, 27, 31, 38, 41, 45, 51, 52, 53, 54, 56, 59, 62, 65, 97, 105, 108, 111, 118, 125, 126, 127, 128, 129);
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").label("\u94c1\u82af\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj4").set("input", new String[]{"geom1_pi1_ext1_dom"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").label("\u8fb9\u754c\u5c42\u7f51\u683c");
    model.component("comp1").selection("dif2").set("add", new String[]{"adj4"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"sel3"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat2").label("Soft Iron (With Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").label("\u7ebf\u5708\u6750\u6599");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_ext2_dom");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_ext1_dom");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection()
         .set(9, 20, 40, 60, 70, 88, 109, 119);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "d0*0.5");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection()
         .set(3, 6, 8, 11, 13, 14, 16, 17, 19, 21, 24);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(7, 12, 15, 18, 22, 25);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_pi1_ext1_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 7);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.2[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().named("com1");
    model.component("comp1").common("free1").set("stiffeningFactor", "100");
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacement");
    model.component("comp1").common("pnmd1").selection().named("sel3");
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacement");
    model.component("comp1").common("disp1").selection().named("uni2");
    model.component("comp1").common().duplicate("disp2", "disp1");
    model.component("comp1").common("disp2").selection().named("int2");
    model.component("comp1").common("disp2").set("prescribedMeshDisplacement", new String[]{"0", "0", "disp"});

    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("geom1_pi1_ext2_dom");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "10[V]");
    model.component("comp1").physics("mf").feature("coil1").set("N", "filling*d1*d2/a_coil");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "a_coil");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").set("fl", 4);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(97);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection().set(31);
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").feature("als1").selection().named("geom1_pi1_ext1_dom");
    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 3);
    model.component("comp1").physics("mf").feature("fcal1").selection().named("geom1_pi1_sel1");
    model.component("comp1").physics("mf").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("mbd").selection().named("uni1");
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").feature("rd1").selection().named("uni1");
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "mass");
    model.component("comp1").physics("mbd").feature("rd1").create("af1", "AppliedForce", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("af1")
         .set("Ft", new String[]{"0", "0", "min(mf.Forcez_0+k0*(x0-disp),0)"});
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("prj1").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("prj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("prj1").create("lk1", "Locking", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("lk1").set("u_min", "-maxdisp");
    model.component("comp1").physics("mbd").feature("prj1").feature("lk1")
         .set("translationalLockingParameters", "UserDefined");
    model.component("comp1").physics("mbd").feature("prj1").feature("lk1")
         .set("p_u1", "(0.01*mbd.prj1.lk1.Eequ)*mbd.diag/10");
    model.component("comp1").physics("mbd").feature("prj1").feature("lk1")
         .set("p_u2", "mbd.prj1.lk1.p_u1*10[ms]/50");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("disp", "mbd.rd1.w");
    model.component("comp1").variable("var1").descr("disp", "\u67f1\u585e\u7684\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("vel", "d(mbd.rd1.w,t)");
    model.component("comp1").variable("var1").descr("vel", "\u67f1\u585e\u7684\u901f\u5ea6");

    model.study("std1").label("\u7814\u7a76 1\uff08\u9884\u5904\u7406\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("ccc").setSolveFor("/frame/spatial1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1")
         .label("\u9884\u5904\u7406\uff1a\u5f52\u4e00\u5316\u6c14\u9699\u53c2\u6570\u5316\u548c\u7ebf\u5708\u65b9\u5411");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "1");
    model.result("pg1").feature("vol1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("filt1").set("expr", "y<x");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set(97);
    model.result("pg1").feature("str1")
         .set("expr", new String[]{"mf.coil1.eCoilx", "mf.coil1.eCoily", "mf.coil1.eCoilz"});
    model.result("pg1").feature("str1")
         .set("descr", "\u7ebf\u5708\u65b9\u5411 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("color", "yellow");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mf", true);
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std2").label("\u7814\u7a76 2\uff08\u77ac\u6001\uff09");
    model.study("std2").feature("time").set("tlist", "range(0,0.005,0.1) range(0.15,0.05,1)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_A").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_A").set("scaleval", "1e-3");
    model.sol("sol2").feature("v1").feature("comp1_mf_psi").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mf_coil1_ICoil_ode").set("scalemethod", "manual");
    model.sol("sol2").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("t1").set("atolglobal", 0.01);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 39, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4f4d\u79fb (mbd)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 39, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("expr", "mbd.disp");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("def1", "Deform");
    model.result("pg3").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u901f\u5ea6 (mbd)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 39, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg4").feature("vol1").set("descractive", true);
    model.result("pg4").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg4").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg4").feature("vol1").set("rangecoloractive", "on");
    model.result("pg4").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg4").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg4").feature("vol1").set("colortable", "Cyclic");
    model.result("pg4").feature("vol1").set("colorlegend", false);
    model.result("pg4").feature("vol1").set("colortabletype", "discrete");
    model.result("pg4").feature("vol1").set("smooth", "none");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").feature().create("def1", "Deform");
    model.result("pg4").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg4").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg4").feature("arwl1").set("placement", "elements");
    model.result("pg4").feature("arwl1").set("data", "parent");
    model.result("pg4").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg4").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg4").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 39, 0);
    model.result("pg5").label("\u52a8\u7f51\u683c");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "volume");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 25);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("data", "dset2");
    model.result("pg1").feature("vol1").setIndex("looplevel", 11, 0);
    model.result("pg1").feature("vol1").set("expr", "(z-Z)/disp");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("com1");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("xcoord", 0);
    model.result("pg2").feature("mslc1").set("ycoord", 0);
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("xcoord", 0);
    model.result("pg2").feature("strmsl1").set("ycoord", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "mir1");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u6d41\u5bc6\u5ea6\u548c\u78c1\u901a\u7ebf");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "mf.Jy");
    model.result("pg6").feature("surf1").set("unit", "A/mm^2");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", -2);
    model.result("pg6").feature("surf1").set("rangecolormax", 2);
    model.result("pg6").feature("surf1").set("colortable", "WaveLight");
    model.result("pg6").run();
    model.result().dataset("mir1").set("hasvar", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "mf.Jy*sign(mir1x)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"mf.Bx", "mf.Bz"});
    model.result("pg6").feature("str1").set("startmethod", "coord");
    model.result("pg6").feature("str1").set("xcoord", "range(-0.018,0.004,0.018)");
    model.result("pg6").feature("str1").set("ycoord", 0);
    model.result("pg6").feature("str1").set("looptol", 0.1);
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature("col1").set("rangecoloractive", true);
    model.result("pg6").feature("str1").feature("col1").set("rangecolormax", 2);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "alternating");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 29, 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u94c1\u82af\u635f\u8017");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("edges", false);
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"mf.Jx", "mf.Jy", "mf.Jz"});
    model.result("pg7").feature("str1").selection().set(97);
    model.result("pg7").run();
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("data", "dset2");
    model.result("pg7").feature("vol1").setIndex("looplevel", 11, 0);
    model.result("pg7").feature("vol1").set("expr", "mf.Qrh");
    model.result("pg7").feature("vol1").set("unit", "W/dm^3");
    model.result("pg7").feature("vol1").set("colortable", "GrayBody");
    model.result("pg7").feature("vol1").set("rangecoloractive", true);
    model.result("pg7").feature("vol1").set("rangecolormax", 60);
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().named("geom1_pi1_ext1_dom");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("vol2", "vol1");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").setIndex("looplevel", 21, 0);
    model.result("pg7").feature("vol2").create("trn1", "Transformation");
    model.result("pg7").run();
    model.result("pg7").feature("vol2").feature("trn1").set("move", new double[]{-0.1, 0, 0});
    model.result("pg7").run();
    model.result("pg7").feature("vol2").set("inheritplot", "vol1");
    model.result("pg7").feature().duplicate("vol3", "vol2");
    model.result("pg7").run();
    model.result("pg7").feature("vol3").setIndex("looplevel", 23, 0);
    model.result("pg7").run();
    model.result("pg7").feature("vol3").feature("trn1").set("move", new double[]{-0.2, 0, 0});
    model.result("pg7").run();
    model.result("pg7").create("tlan1", "TableAnnotation");
    model.result("pg7").feature("tlan1").set("source", "localtable");
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0.03, 0, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0.07, 0, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "50[ms]", 0, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", -0.07, 1, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0.07, 1, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "100[ms]", 1, 3);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", -0.17, 2, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0.07, 2, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "200[ms]", 2, 3);
    model.result("pg7").feature("tlan1").set("showpoint", false);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("view", "new");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("titletype", "label");
    model.result("pg8").label("\u5f00\u5173\u524d\u7684\u7cfb\u7edf\u52a8\u529b\u5b66");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("data", "dset2");
    model.result("pg8").feature("glob1").setIndex("looplevelinput", "interp", 0);
    model.result("pg8").feature("glob1").setIndex("interp", "range(0,0.005,0.05)", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "mf.ICoil_1*mf.RCoil_1/mf.VCoil_1", 0);
    model.result("pg8").feature("glob1")
         .setIndex("descr", "\u5f52\u4e00\u5316\u4e3a\u76f4\u6d41\u7535\u6d41\u7684\u7535\u6d41", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "1+disp/maxdisp", 1);
    model.result("pg8").feature("glob1").setIndex("descr", "\u5f52\u4e00\u5316\u7684\u95f4\u9699\u5927\u5c0f", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "1-exp(-t/50[ms])", 2);
    model.result("pg8").feature("glob1")
         .setIndex("descr", "\u7406\u60f3\u7684 RL \u5f52\u4e00\u5316\u7535\u6d41", 2);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u5f00\u5173\u8fc7\u7a0b\u4e2d\u7684\u7cfb\u7edf\u52a8\u529b\u5b66");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("interp", "range(0,0.005,0.1)", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "4*mass*d(vel,t)*vel/at(1,mf.ICoil_1*mf.VCoil_1)", 2);
    model.result("pg9").feature("glob1")
         .setIndex("descr", "\u673a\u68b0\u529f\u7387\uff0c\u5f52\u4e00\u5316\u4e3a\u76f4\u6d41\u7ebf\u5708\u529f\u7387", 2);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u5b8c\u6574\u7684\u7cfb\u7edf\u52a8\u529b\u5b66");
    model.result("pg10").set("legendpos", "middleright");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("looplevelinput", "all", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "", 2);
    model.result().dataset().create("int1", "Integral");
    model.result().dataset("int1").set("intsurface", true);
    model.result().dataset("int1").set("intvolume", true);
    model.result().dataset("int1").set("data", "dset2");
    model.result().dataset("int1").set("showlevel", "off");
    model.result().dataset("int1").selection().geom("geom1", 3);
    model.result().dataset("int1").selection().named("geom1_pi1_ext2_dom");
    model.result("pg10").run();
    model.result("pg10").create("glob2", "Global");
    model.result("pg10").feature("glob2").set("markerpos", "datapoints");
    model.result("pg10").feature("glob2").set("linewidth", "preference");
    model.result("pg10").feature("glob2").setIndex("expr", "4*mf.Qrh/at(1,mf.ICoil_1*mf.VCoil_1)", 0);
    model.result("pg10").feature("glob2").set("data", "int1");
    model.result("pg10").feature("glob2")
         .setIndex("descr", "\u611f\u5e94\u7535\u6d41\u635f\u8017\uff0c\u5f52\u4e00\u5316\u4e3a\u76f4\u6d41\u7ebf\u5708\u529f\u7387", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u9501\u7d27\u529b");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("ylabelactive", false);
    model.result("pg11").set("twoyaxes", true);
    model.result("pg11").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("data", "parent");
    model.result("pg11").feature("glob1").set("expr", new String[]{});
    model.result("pg11").feature("glob1").set("descr", new String[]{});
    model.result("pg11").feature("glob1").setIndex("expr", "mbd.prj1.lk1.F_u", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "\u9501\u7d27\u529b", 0);
    model.result("pg11").run();
    model.result("pg11").feature("glob2").set("data", "parent");
    model.result("pg11").feature("glob2").set("expr", new String[]{});
    model.result("pg11").feature("glob2").set("descr", new String[]{});
    model.result("pg11").feature("glob2").setIndex("expr", "1+disp/maxdisp", 0);
    model.result("pg11").feature("glob2").setIndex("descr", "\u5f52\u4e00\u5316\u95f4\u9699\u5927\u5c0f", 0);
    model.result("pg11").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").label("\u901f\u5ea6");
    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u7535\u6e90\u5f00\u5173\u7684\u7535\u52a8\u529b\u5b66 - \u591a\u4f53\u7248\u672c");

    model
         .description("\u8fc7\u7535\u6d41\u6216\u8fc7\u8f7d\u7b49\u7535\u6c14\u4e8b\u4ef6\u53ef\u80fd\u4f1a\u4e25\u91cd\u635f\u574f\u7535\u8def\u6216\u7535\u529b\u7ebf\u3002\u4e3a\u4e86\u907f\u514d\u66f4\u6362\u5173\u952e\u96f6\u4ef6\u6240\u4ea7\u751f\u7684\u9ad8\u6602\u8d39\u7528\uff0c\u53ef\u4ee5\u5b89\u88c5\u7535\u6c14\u5f00\u5173\u65ad\u8def\u5668\u3002\u4e00\u65e6\u8fbe\u5230\u4e34\u754c\u7535\u6d41\uff0c\u8fd9\u4e9b\u65ad\u8def\u5668\u5c31\u4f1a\u901a\u8fc7\u79fb\u52a8\u6d3b\u585e\uff0c\u673a\u68b0\u5730\u5207\u65ad\uff08\u6d6a\u6d8c\uff09\u7535\u6d41\u3002\u5982\u679c\u4f7f\u7528\u4fdd\u9669\u4e1d\u6765\u4fdd\u62a4\u5468\u56f4\u7684\u7535\u6c14\u96f6\u90e8\u4ef6\uff0c\u4fdd\u9669\u4e1d\u5728\u6fc0\u6d3b\u540e\u5fc5\u987b\u66f4\u6362\uff0c\u800c\u65ad\u8def\u5668\u5219\u4e0d\u540c\uff0c\u5b83\u53ef\u4ee5\u590d\u4f4d\u3002\n\u672c\u6559\u7a0b\u7684\u4e3b\u8981\u76ee\u7684\u662f\u63a2\u8ba8\u4e00\u79cd\u65ad\u8def\u5668\uff08\u78c1\u529b\u5f00\u5173\uff09\u7684\u5de5\u4f5c\u539f\u7406\u548c\u4e00\u4e9b\u53ef\u80fd\u7684\u5efa\u6a21\u89e3\u51b3\u65b9\u6848\u3002\u78c1\u529b\u5f00\u5173\u662f\u4e00\u79cd\u673a\u7535\u88c5\u7f6e\uff0c\u5176\u4e2d\uff0c\u94c1\u5236\u67f1\u585e\u901a\u8fc7\u5468\u56f4\u7ebf\u5708\u4e2d\u6d41\u52a8\u7684\u7535\u6d41\u6240\u4ea7\u751f\u7684\u78c1\u5438\u529b\u8fdb\u884c\u79fb\u52a8\u3002\u5173\u95ed\u9a71\u52a8\u7535\u6d41\u53ef\u5c06\u5f00\u5173\u590d\u4f4d\u5230\u521d\u59cb\u72b6\u6001\u3002\n\n\u8be5\u6a21\u578b\u6a21\u62df\u5728\u78c1\u529b\u3001\u611f\u5e94\u7535\u6d41\u548c\u7528\u4e8e\u4fdd\u6301\u6d3b\u585e\u5e73\u8861\u4f4d\u7f6e\u7684\u5f39\u7c27/\u7ea6\u675f\u673a\u6784\u7684\u5f71\u54cd\u4e0b\u7684\u521a\u4f53\u52a8\u529b\u5b66\u3002\u4e00\u4e2a\u94dc\u7ebf\u5708\u653e\u7f6e\u5728\u4e0b\u90e8 E \u78c1\u82af\u7684\u4e2d\u592e\u82af\u67f1\u4e0a\uff0c\u8fd9\u4e2a E \u78c1\u82af\u4fdd\u6301\u56fa\u5b9a\u3002\u5f53\u7535\u6d41\u5728\u7ebf\u5708\u4e2d\u6d41\u52a8\u65f6\uff0c\u4f1a\u5bf9\u4e0a\u90e8 E \u78c1\u82af\uff08\u79fb\u52a8\u6d3b\u585e\uff09\u65bd\u52a0\u5438\u5f15\u529b\uff0c\u8fd9\u4e2a E \u78c1\u82af\u901a\u8fc7\u9884\u5e94\u529b\u5f39\u7c27\u4fdd\u6301\u5728\u539f\u4f4d\u3002\u5f53\u5438\u5f15\u529b\u8fbe\u5230\u9608\u503c\u65f6\uff0c\u6d3b\u585e\u671d\u4e0b\u90e8 E \u78c1\u82af\u79fb\u52a8\uff0c\u5173\u95ed\u6c14\u9699\u3002\u6a21\u578b\u9610\u8ff0\u4e86\u5982\u4f55\u6b63\u786e\u6a21\u62df\u4e0e\u5f39\u7c27\u521a\u5ea6\u76f8\u5173\u7684\u8fd0\u52a8\u548c\u5173\u95ed\u65f6\u95f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("power_switch_multibody.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
