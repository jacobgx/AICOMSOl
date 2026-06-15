/*
 * atmospheric_corrosion_busbar.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:39 by COMSOL 6.3.0.290. */
public class atmospheric_corrosion_busbar {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Atmospheric_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("\u87ba\u6813");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("quickplane", "yz");
    model.geom("part1").feature("wp1").geom().create("pol1", "Polygon");
    model.geom("part1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.geom("part1").feature("wp1").geom().feature("pol1").set("x", "2 0 0 2.5 0 0 5.9 5.9 2.5 2.5 2");
    model.geom("part1").feature("wp1").geom().feature("pol1").set("y", "0 0 16 16 16 20 16.7 16 16 0.5 0");
    model.geom("part1").run("wp1");
    model.geom("part1").feature().create("rev1", "Revolve");
    model.geom("part1").feature("rev1").set("angtype", "full");
    model.geom("part1").feature("rev1").set("origfaces", false);
    model.geom("part1").run("rev1");
    model.geom("part1").create("wp2", "WorkPlane");
    model.geom("part1").feature("wp2").set("unite", true);
    model.geom("part1").feature("wp2").set("quickz", 21.4);
    model.geom("part1").feature("wp2").geom().create("pol1", "Polygon");
    model.geom("part1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.geom("part1").feature("wp2").geom().feature("pol1").set("x", "0 0 0 -3.98372");
    model.geom("part1").feature("wp2").geom().feature("pol1").set("y", "0 4.6 4.6 2.3");
    model.geom("part1").feature("wp2").geom().run("pol1");
    model.geom("part1").feature("wp2").geom().create("rot1", "Rotate");
    model.geom("part1").feature("wp2").geom().feature("rot1").selection("input").set("pol1");
    model.geom("part1").feature("wp2").geom().feature("rot1").set("rot", "range(0,60,360)");
    model.geom("part1").feature("wp2").geom().run("rot1");
    model.geom("part1").feature("wp2").geom().create("uni1", "Union");
    model.geom("part1").feature("wp2").geom().feature("uni1").selection("input").set("rot1");
    model.geom("part1").feature("wp2").geom().feature("uni1").set("intbnd", false);
    model.geom("part1").run("wp2");
    model.geom("part1").feature().create("ext1", "Extrude");
    model.geom("part1").feature("ext1").set("workplane", "wp2");
    model.geom("part1").feature("ext1").selection("input").set("wp2");
    model.geom("part1").feature("ext1").setIndex("distance", 5, 0);
    model.geom("part1").feature("ext1").set("reverse", true);
    model.geom("part1").run("ext1");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").feature("uni1").selection("input").set("ext1", "rev1");
    model.geom("part1").feature("uni1").set("intbnd", false);
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("\u87ba\u6bcd");
    model.geom("part2").lengthUnit("mm");
    model.geom("part2").create("wp1", "WorkPlane");
    model.geom("part2").feature("wp1").set("unite", true);
    model.geom("part2").feature("wp1").set("quickplane", "yz");
    model.geom("part2").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part2").feature("wp1").geom().feature("r1").set("size", new double[]{2.253, 3.38});
    model.geom("part2").feature("wp1").geom().feature("r1").set("pos", new int[]{2, 8});
    model.geom("part2").feature("wp1").geom().run("r1");
    model.geom("part2").feature("wp1").geom().create("cha1", "Chamfer");
    model.geom("part2").feature("wp1").geom().feature("cha1").selection("pointinsketch").set("r1", 1, 4);
    model.geom("part2").feature("wp1").geom().feature("cha1").set("dist", 0.4);
    model.geom("part2").feature("wp1").geom().run("cha1");
    model.geom("part2").feature("wp1").geom().create("cha2", "Chamfer");
    model.geom("part2").feature("wp1").geom().feature("cha2").selection("pointinsketch").set("cha1", 5);
    model.geom("part2").feature("wp1").geom().feature("cha2").set("dist", 0.26);
    model.geom("part2").feature("wp1").geom().run("cha2");
    model.geom("part2").feature("wp1").geom().create("r2", "Rectangle");
    model.geom("part2").feature("wp1").geom().feature("r2").set("size", new double[]{1.15, 0.7});
    model.geom("part2").feature("wp1").geom().feature("r2").set("pos", new double[]{3.103, 10.68});
    model.geom("part2").feature("wp1").geom().run("r2");
    model.geom("part2").feature("wp1").geom().create("r3", "Rectangle");
    model.geom("part2").feature("wp1").geom().feature("r3").set("size", new double[]{0.6, 0.7});
    model.geom("part2").feature("wp1").geom().feature("r3").set("pos", new double[]{3.653, 9.98});
    model.geom("part2").feature("wp1").geom().run("r3");
    model.geom("part2").feature("wp1").geom().create("dif1", "Difference");
    model.geom("part2").feature("wp1").geom().feature("dif1").selection("input").set("cha2");
    model.geom("part2").feature("wp1").geom().feature("dif1").selection("input2").set("r2", "r3");
    model.geom("part2").run("wp1");
    model.geom("part2").feature().create("rev1", "Revolve");
    model.geom("part2").feature("rev1").set("workplane", "wp1");
    model.geom("part2").feature("rev1").selection("input").set("wp1");
    model.geom("part2").feature("rev1").set("angtype", "full");
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").label("\u6cd5\u5170 1");
    model.geom("part3").lengthUnit("mm");
    model.geom("part3").create("blk1", "Block");
    model.geom("part3").feature("blk1").set("size", new double[]{16, 22.5, 3});
    model.geom("part3").feature("blk1").set("pos", new int[]{-8, -9, 13});
    model.geom("part3").run("blk1");
    model.geom("part3").create("cyl1", "Cylinder");
    model.geom("part3").feature("cyl1").set("r", 3.3);
    model.geom("part3").feature("cyl1").set("h", 3);
    model.geom("part3").feature("cyl1").set("pos", new int[]{0, 0, 13});
    model.geom("part3").run("cyl1");
    model.geom("part3").create("blk2", "Block");
    model.geom("part3").feature("blk2").set("size", new double[]{16, 3.75, 2.4});
    model.geom("part3").feature("blk2").set("pos", new double[]{-8, 13.5, 13});
    model.geom("part3").run("blk2");
    model.geom("part3").create("cyl2", "Cylinder");
    model.geom("part3").feature("cyl2").set("r", 2);
    model.geom("part3").feature("cyl2").set("h", 3);
    model.geom("part3").feature("cyl2").set("pos", new double[]{8, 17.25, 13});
    model.geom("part3").run("cyl2");
    model.geom("part3").create("dif1", "Difference");
    model.geom("part3").feature("dif1").selection("input").set("blk1");
    model.geom("part3").feature("dif1").selection("input2").set("cyl1");
    model.geom("part3").run("dif1");
    model.geom("part3").create("dif2", "Difference");
    model.geom("part3").feature("dif2").selection("input").set("blk2");
    model.geom("part3").feature("dif2").selection("input2").set("cyl2");
    model.geom("part3").run("dif2");
    model.geom("part3").create("mir1", "Mirror");
    model.geom("part3").feature("mir1").selection("input").set("dif1", "dif2");
    model.geom("part3").feature("mir1").set("keep", true);
    model.geom("part3").feature("mir1").set("pos", new double[]{0, 17.25, 0});
    model.geom("part3").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.geom("part3").run("mir1");
    model.geom("part3").create("uni1", "Union");
    model.geom("part3").feature("uni1").selection("input").set("dif2", "mir1(2)");
    model.geom().create("part4", "Part", 3);
    model.geom("part4").geomRep("comsol");
    model.geom("part4").label("\u6cd5\u5170 2");
    model.geom("part4").lengthUnit("mm");
    model.geom("part4").create("blk1", "Block");
    model.geom("part4").feature("blk1").set("size", new double[]{13, 16, 1.5});
    model.geom("part4").feature("blk1").set("pos", new double[]{-6.5, -8, 11.5});
    model.geom("part4").run("blk1");
    model.geom("part4").create("cyl1", "Cylinder");
    model.geom("part4").feature("cyl1").set("r", 3.3);
    model.geom("part4").feature("cyl1").set("h", 3);
    model.geom("part4").feature("cyl1").set("pos", new double[]{0, 0, 11.5});
    model.geom("part4").run("cyl1");
    model.geom("part4").create("dif1", "Difference");
    model.geom("part4").feature("dif1").selection("input").set("blk1");
    model.geom("part4").feature("dif1").selection("input2").set("cyl1");
    model.geom("part4").run("dif1");
    model.geom("part4").create("wp1", "WorkPlane");
    model.geom("part4").feature("wp1").set("unite", true);
    model.geom("part4").feature("wp1").set("planetype", "faceparallel");
    model.geom("part4").feature("wp1").selection("face").set("dif1", 2);
    model.geom("part4").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("part4").feature("wp1").geom().feature("r1").set("size", new double[]{1.7, 1.5});
    model.geom("part4").feature("wp1").geom().feature("r1").set("pos", new double[]{-4.45, -10});
    model.geom("part4").feature("wp1").geom().run("r1");
    model.geom("part4").feature("wp1").geom().create("c1", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c1").set("type", "curve");
    model.geom("part4").feature("wp1").geom().feature("c1").set("r", 3.5);
    model.geom("part4").feature("wp1").geom().feature("c1").set("angle", 90);
    model.geom("part4").feature("wp1").geom().feature("c1").set("pos", new double[]{-2.75, -6.5});
    model.geom("part4").feature("wp1").geom().feature("c1").set("rot", -90);
    model.geom("part4").feature("wp1").geom().feature("c1").setIndex("layer", 1.5, 0);
    model.geom("part4").feature("wp1").geom().run("c1");
    model.geom("part4").feature("wp1").geom().create("c2", "Circle");
    model.geom("part4").feature("wp1").geom().feature("c2").set("type", "curve");
    model.geom("part4").feature("wp1").geom().feature("c2").set("r", 3.5);
    model.geom("part4").feature("wp1").geom().feature("c2").set("angle", 90);
    model.geom("part4").feature("wp1").geom().feature("c2").set("pos", new double[]{-4.45, -12});
    model.geom("part4").feature("wp1").geom().feature("c2").set("rot", 90);
    model.geom("part4").feature("wp1").geom().feature("c2").setIndex("layer", 1.5, 0);
    model.geom("part4").feature("wp1").geom().run("c2");
    model.geom("part4").feature("wp1").geom().feature().create("del1", "Delete");
    model.geom("part4").feature("wp1").geom().feature("del1").selection("input").init();
    model.geom("part4").feature("wp1").geom().feature("del1").selection("input").init(1);
    model.geom("part4").feature("wp1").geom().feature("del1").selection("input").set("c1", 1, 2);
    model.geom("part4").feature("wp1").geom().feature("del1").selection("input").set("c2", 1, 2);
    model.geom("part4").run("wp1");
    model.geom("part4").feature().create("ext1", "Extrude");
    model.geom("part4").feature("ext1").set("workplane", "wp1");
    model.geom("part4").feature("ext1").selection("input").set("wp1");
    model.geom("part4").feature("ext1").setIndex("distance", 16, 0);
    model.geom("part4").feature("ext1").set("reverse", true);
    model.geom("part4").run("ext1");
    model.geom("part4").create("blk2", "Block");
    model.geom("part4").feature("blk2").set("size", new double[]{23, 52, 1.5});
    model.geom("part4").feature("blk2").set("pos", new double[]{12, -8, 4.3});
    model.geom("part4").run("blk2");
    model.geom("part4").create("uni1", "Union");
    model.geom("part4").feature("uni1").selection("input").set("blk2", "dif1", "ext1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("Cu \u57df");
    model.component("comp1").selection("sel1").set(1, 2, 3, 4);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Al \u57df");
    model.component("comp1").selection("sel2").set(5, 9, 10, 11, 12);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u87ba\u6813 (Zn)");
    model.component("comp1").selection("sel3").set(6, 7, 8);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Cu \u8868\u9762");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("Al \u8868\u9762");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel2"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("Zn \u8868\u9762");
    model.component("comp1").selection("adj3").set("input", new String[]{"sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12);
    model.component("comp1").selection("sel4").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj4").set("input", new String[]{"sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("Cu \u7ec8\u7aef\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(16);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("Al \u7ec8\u7aef\u8fb9\u754c");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(157);
    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").label("\u5185\u8fb9\u754c");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", 0.0026);
    model.component("comp1").selection("cyl1").set("top", 0.011);
    model.component("comp1").selection("cyl1").set("bottom", 0);
    model.component("comp1").selection("cyl1").set("pos", new double[]{0, 0, 0.0075});
    model.component("comp1").selection("cyl1").set("condition", "inside");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5916\u8868\u9762");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj4"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel5", "sel6", "cyl1"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u5916\u90e8 Cu \u8868\u9762");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "dif1"});
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").label("\u5916\u90e8 Al \u8868\u9762");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"adj2", "dif1"});
    model.component("comp1").selection().create("int3", "Intersection");
    model.component("comp1").selection("int3").label("\u5916\u90e8 Zn \u8868\u9762");
    model.component("comp1").selection("int3").set("entitydim", 2);
    model.component("comp1").selection("int3").set("input", new String[]{"adj3", "dif1"});

    model.title("\u6bcd\u7ebf\u677f\u7684\u5927\u6c14\u8150\u8680 - \u6a21\u677f\u6587\u4ef6");

    model
         .description("\u672c\u4f8b\u662f\u201c\u6bcd\u7ebf\u677f\u7684\u5927\u6c14\u8150\u8680\u201d\u6a21\u578b\u4f7f\u7528\u7684\u6a21\u677f MPH \u6587\u4ef6\u3002\u8be5\u51e0\u4f55\u7ed3\u6784\u5305\u542b\u4e00\u4e2a\u6bcd\u7ebf\u677f\u87ba\u6bcd\u3001\u4e00\u4e2a\u87ba\u6813\u548c\u4e24\u4e2a\u6cd5\u5170\uff0c\u5e76\u4f7f\u7528\u201c\u51e0\u4f55\u96f6\u4ef6\u201d\u4e2d\u6307\u5b9a\u7684\u51e0\u4f55\u5e8f\u5217\u3002");

    model.label("atmospheric_corrosion_busbar_geom.mph");

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");
    model.component("comp1").physics().create("cdsh", "CurrentDistributionShell", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("TF", "0.0005", "\u539a\u5ea6\u56e0\u5b50");
    model.param().set("RH", "0.85", "\u76f8\u5bf9\u6e7f\u5ea6");
    model.param()
         .set("d_film", "TF[m]*(24.9+14.8*RH-22.58*RH^2)/(5811.95+23909*RH-3291*RH^2-57990*RH^3+31576*RH^4)", "\u7535\u89e3\u8d28\u819c\u539a\u5ea6");
    model.param()
         .set("sigma", "48250.20-287264*RH+683394*RH^2-811693*RH^3+481365*RH^4-114051*RH^5[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("D_O2", "1.5e-9[m^2/s]", "O2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("O2_solubility", "0.0003*exp(6.59*RH)[mol/m^3]", "O2 \u6eb6\u89e3\u5ea6");
    model.param()
         .set("ilim", "4*F_const*D_O2*O2_solubility/d_film", "O2 \u7684\u6781\u9650\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_Al", "-1[V]", "\u5e73\u8861\u7535\u4f4d\uff0cAl");
    model.param().set("i0_Al", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cAl");
    model.param().set("A_Al", "100[mV]", "Tafel \u659c\u7387\uff0cAl");
    model.param().set("Eeq_Zn", "-0.7[V]", "\u5e73\u8861\u7535\u4f4d\uff0cZn");
    model.param().set("i0_Zn", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cZn");
    model.param().set("A_Zn", "100[mV]", "Tafel \u659c\u7387\uff0cZn");
    model.param().set("Eeq_Cu", "0[V]", "\u5e73\u8861\u7535\u4f4d\uff0cCu");
    model.param().set("i0_Cu", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cCu");
    model.param().set("A_Cu", "100[mV]", "Tafel \u659c\u7387\uff0cCu");
    model.param().set("Eeq_O2", "1[V]", "\u5e73\u8861\u7535\u4f4d\uff0cO2");
    model.param().set("i0_O2_on_Al", "1e-6[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cO2");
    model.param().set("A_O2_on_Al", "-100[mV]", "Tafel \u659c\u7387\uff0cO2");
    model.param().set("i0_O2_on_Zn", "1e-6[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cO2");
    model.param().set("A_O2_on_Zn", "-100[mV]", "Tafel \u659c\u7387\uff0cO2");
    model.param().set("i0_O2_on_Cu", "1e-3[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cO2");
    model.param().set("A_O2_on_Cu", "-100[mV]", "Tafel \u659c\u7387\uff0cO2");

    model.component("comp1").physics("cd").create("cc1", "CurrentConductor", 3);
    model.component("comp1").physics("cd").feature("cc1").selection().all();
    model.component("comp1").physics("cd").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("cd").feature("egnd1").selection().named("sel6");
    model.component("comp1").physics("cd").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("cd").feature("ec1").selection().named("sel5");
    model.component("comp1").physics("cd").feature("ec1").set("Its", "100[A]");
    model.component("comp1").physics("cd").create("ecd1", "ElectrodeNormalCurrentDensity", 2);
    model.component("comp1").physics("cd").feature("ecd1").selection().named("dif1");
    model.component("comp1").physics("cd").feature("ecd1").set("nis", "-cdsh.itot");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Aluminum 3003-H18");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "893[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2730[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.66e7[S/m]"});
    model.component("comp1").material("mat3").label("\u950c");

    model.component("comp1").physics("cdsh").selection().named("adj4");
    model.component("comp1").physics("cdsh").feature("ice1").set("s", "d_film");
    model.component("comp1").physics("cdsh").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cdsh").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cdsh").create("eebii1", "ExternalElectrodeSurface", 2);
    model.component("comp1").physics("cdsh").feature("eebii1").selection().named("int2");
    model.component("comp1").physics("cdsh").feature("eebii1").set("phisext0", "phis");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er1").set("Eeq", "Eeq_Al");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er1").set("i0", "i0_Al");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er1").set("Aa", "A_Al");
    model.component("comp1").physics("cdsh").feature("eebii1").create("er2", "ElectrodeReaction", 2);
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2").set("i0", "i0_O2_on_Al");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2").set("Ac", "A_O2_on_Al");
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("cdsh").feature("eebii1").feature("er2").set("ilim", "ilim");
    model.component("comp1").physics("cdsh").feature().duplicate("eebii2", "eebii1");
    model.component("comp1").physics("cdsh").feature("eebii2").selection().named("int1");
    model.component("comp1").physics("cdsh").feature("eebii2").feature("er1").set("Eeq", "Eeq_Cu");
    model.component("comp1").physics("cdsh").feature("eebii2").feature("er1").set("i0", "i0_Cu");
    model.component("comp1").physics("cdsh").feature("eebii2").feature("er1").set("Aa", "A_Cu");
    model.component("comp1").physics("cdsh").feature("eebii2").feature("er2").set("i0", "i0_O2_on_Cu");
    model.component("comp1").physics("cdsh").feature("eebii2").feature("er2").set("Ac", "A_O2_on_Cu");
    model.component("comp1").physics("cdsh").feature().duplicate("eebii3", "eebii2");
    model.component("comp1").physics("cdsh").feature("eebii3").selection().named("int3");
    model.component("comp1").physics("cdsh").feature("eebii3").feature("er1").set("Eeq", "Eeq_Zn");
    model.component("comp1").physics("cdsh").feature("eebii3").feature("er1").set("i0", "i0_Zn");
    model.component("comp1").physics("cdsh").feature("eebii3").feature("er1").set("Aa", "A_Zn");
    model.component("comp1").physics("cdsh").feature("eebii3").feature("er2").set("i0", "i0_O2_on_Zn");
    model.component("comp1").physics("cdsh").feature("eebii3").feature("er2").set("Ac", "A_O2_on_Zn");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/cdsh", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cdsh", true);
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6781\u7535\u4f4d vs. \u63a5\u5730 (cd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "cd.phis");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cdsh)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "cdsh.Evsref");
    model.result("pg2").feature("surf1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u7535\u4f4d (cdsh)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "phil2");
    model.result("pg3").feature("surf1").set("descr", "\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u8150\u8680\u7535\u6d41\u5bc6\u5ea6 (cdsh)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "cdsh.iloc_er1");
    model.result("pg4").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6c27\u8fd8\u539f\u7535\u6d41\u5bc6\u5ea6 (cdsh)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "cdsh.iloc_er2");
    model.result("pg5").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();

    model.title("\u6bcd\u7ebf\u677f\u7684\u5927\u6c14\u8150\u8680");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u6bcd\u7ebf\u677f\u7684\u5927\u6c14\u7535\u5076\u8150\u8680\uff0c\u5176\u4e2d\u5305\u542b\u4e00\u4e2a\u94dc\u8d28\u6cd5\u5170\u548c\u4e00\u4e2a\u94dd\u5408\u91d1\u8d28\u6cd5\u5170\uff0c\u4e0e\u950c\u8d28\u87ba\u6bcd\u53ca\u87ba\u6813\u76f8\u63a5\u89e6\u3002\u201c\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u201d\u63a5\u53e3\u7528\u4e8e\u6c42\u89e3\u7535\u6781\u57df\u4e2d\u7684\u7535\u4f4d\uff0c\u201c\u7535\u6d41\u5206\u5e03\uff0c\u58f3\u201d\u63a5\u53e3\u7528\u4e8e\u6c42\u89e3\u7535\u89e3\u8d28\u8584\u5c42\u4e2d\u7684\u7535\u89e3\u8d28\u7535\u4f4d\u3002\n\n\u7535\u89e3\u8d28\u819c\u7684\u539a\u5ea6\u548c\u4f20\u5bfc\u7387\u4e0e\u5468\u56f4\u7a7a\u6c14\u7684\u76f8\u5bf9\u6e7f\u5ea6\u6709\u5173\u3002\u53d7\u9650\u6c27\u7684\u8fd8\u539f\u7535\u6d41\u5bc6\u5ea6\u4e0e\u6a21\u578b\u4e2d\u7684\u6c27\u6c14\u6269\u6563\u548c\u6eb6\u89e3\u5ea6\u6709\u5173\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("atmospheric_corrosion_busbar.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
