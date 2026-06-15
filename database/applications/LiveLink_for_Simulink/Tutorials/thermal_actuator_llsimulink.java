/*
 * thermal_actuator_llsimulink.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:47 by COMSOL 6.3.0.290. */
public class thermal_actuator_llsimulink {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Simulink\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.component("comp1").label("Thermal Actuator");

    model.param().set("d", "3[um]");
    model.param().descr("d", "Height of the hot arm");
    model.param().set("dw", "15[um]");
    model.param().descr("dw", "Height of the cold arm");
    model.param().set("gap", "3[um]");
    model.param().descr("gap", "Gap between arms");
    model.param().set("wb", "10[um]");
    model.param().descr("wb", "Width of the base");
    model.param().set("wv", "25[um]");
    model.param().descr("wv", "Difference in length between hot arms");
    model.param().set("L", "240[um]");
    model.param().descr("L", "Actuator length");
    model.param().set("L1", "L-wb");
    model.param().descr("L1", "Length of the longest hot arm");
    model.param().set("L2", "L-wb-wv");
    model.param().descr("L2", "Length of the shortest hot arm");
    model.param().set("L3", "L-2*wb-wv-L/48-L/6");
    model.param().descr("L3", "Length of the cold arm, thick part");
    model.param().set("L4", "L/6");
    model.param().descr("L4", "Length of the cold arm, thin part");
    model.param().set("htc_s", "0.04[W/(m*K)]/2[um]");
    model.param().descr("htc_s", "Heat transfer coefficient");
    model.param().set("htc_us", "0.04[W/(m*K)]/100[um]");
    model.param().descr("htc_us", "Heat transfer coefficient, upper surface");
    model.param().set("DV", "5[V]");
    model.param().descr("DV", "Applied voltage");
    model.param().set("alphaps", "2.6e-6[1/K]");
    model.param().descr("alphaps", "Coefficient of thermal expansion");
    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "Strain reference temperature");
    model.param().set("noa", "3");
    model.param().descr("noa", "Number of arms");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "(noa==3)");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L3", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"L-L3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new String[]{"L4", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"L-L3-L4", "dw-d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new String[]{"wb", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new String[]{"L2", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"L-L2", "dw+gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"L-L2-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new String[]{"L1", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"L-L1", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"L-d", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("pos", new String[]{"L-d", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("uni1", 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 19, 20, 21, 22, 23, 28);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 2, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"d", "(dw+d+2*gap)+(dw+gap+d)-2.5*(wb-2*d)-d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"L-L2-wb+d", "d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb+d", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3").setIndex("pos", "d", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r3", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"L-L3/4", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2")
         .set("pos", new String[]{"L-L3/2", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3")
         .set("pos", new String[]{"L-3*L3/4", "dw/2"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 2, 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1", 10);
    model.component("comp1").geom("geom1").feature("sel1").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Ground");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni1", 29);
    model.component("comp1").geom("geom1").feature("sel2").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Applied Voltage");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("uni1", 48);
    model.component("comp1").geom("geom1").feature("sel3").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Third");
    model.component("comp1").geom("geom1").feature("sel3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("sel4").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Upper Surface");
    model.component("comp1").geom("geom1").feature("sel4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("uni1", 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92);
    model.component("comp1").geom("geom1").feature("sel5").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Other Surface");
    model.component("comp1").geom("geom1").feature("sel5").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("uni1", 154);
    model.component("comp1").geom("geom1").feature("sel6").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Tip");
    model.component("comp1").geom("geom1").feature("sel6").set("contributeto", "csel6");
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("uni1", 67, 72, 77);
    model.component("comp1").geom("geom1").feature("sel7").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("Dimples");
    model.component("comp1").geom("geom1").feature("sel7").set("contributeto", "csel7");
    model.component("comp1").geom("geom1").run("endif1");

    model.param().set("noa", "2");

    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "(noa==2)");
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r6").active(false);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r7").active(false);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r8").active(false);
    model.component("comp1").geom("geom1").feature().duplicate("ext3", "ext1");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").runPre("ext3");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().duplicate("wp4", "wp2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").active(false);
    model.component("comp1").geom("geom1").feature().duplicate("ext4", "ext2");
    model.component("comp1").geom("geom1").feature("ext4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").runPre("ext4");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp4");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().duplicate("uni2", "uni1");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ext3", "ext4");
    model.component("comp1").geom("geom1").feature().duplicate("sel8", "sel1");
    model.component("comp1").geom("geom1").runPre("sel8");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("uni2", 28);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").feature().duplicate("sel9", "sel2");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("uni2", 10);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").feature().duplicate("sel10", "sel4");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("uni2", 4);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").feature().duplicate("sel11", "sel5");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection")
         .set("uni2", 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66);
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").feature().duplicate("sel12", "sel6");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").set("uni2", 108);
    model.component("comp1").geom("geom1").run("sel12");
    model.component("comp1").geom("geom1").feature().duplicate("sel13", "sel7");
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").set("uni2", 47, 52, 57);
    model.component("comp1").geom("geom1").run("sel13");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_csel1_bnd", "geom1_csel2_bnd", "geom1_csel3_bnd"});
    model.component("comp1").selection("uni1").label("Surface Contact");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("sigma", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("alpha", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Polysilicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("expr", "1/(2e-5*(1+1.25e-3*(T-298.15)))");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("argders", new String[][]{{"T", "d(1/(2e-5*(1+1.25e-3*(T-298.15))), T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("fununit", "S/m");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("expr", "(3.725*(1-exp(-5.88e-3*(T-125)))+5.548e-4*T)*1e-6");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("argders", new String[][]{{"T", "d((3.725*(1-exp(-5.88e-3*(T-125)))+5.548e-4*T)*1e-6, T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("expr", "1/(-2.2e-11*T^3 + 9e-8*T^2 - 1e-5*T + 0.014)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("argders", new String[][]{{"T", "d(1/(-2.2e-11*T^3 + 9e-8*T^2 - 1e-5*T + 0.014), T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma(T)", "0", "0", "0", "sigma(T)", "0", "0", "0", "sigma(T)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha(T)", "0", "0", "0", "alpha(T)", "0", "0", "0", "alpha(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "169[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "2e-5[\u03a9*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "1.25e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");

    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "DV");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc_s");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "htc_us");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("uni1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eXX", 0, new String[]{"uX-alphaps*(T-T0)"});
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eYY", 0, new String[]{"vY-alphaps*(T-T0)"});
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eZZ", 0, new String[]{"wZ-alphaps*(T-T0)"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Electric Potential (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1")
         .set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom2/pdef1/pcond2/pcond1/pcond2/pg1");
    model.result("pg1").set("weight", 0);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Electric Field (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom3/pdef1/pcond2/pcond1/pg1");
    model.result("pg2").set("weight", 0);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Temperature (ht)");
    model.result("pg3").set("defaultPlotID", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond3/pcond3/pg1");
    model.result("pg3").set("weight", 0);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("defaultPlotID", "stress");
    model.result("pg4").label("Stress (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().named("geom1_csel6_pnt");
    model.result().numerical("pev1").setIndex("expr", "solid.disp", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Point Evaluation 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").setIndex("expr", "T", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Volume Maximum 1");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();

    model.component("comp1").view("view1").camera().set("zoomanglefull", 10.7);
    model.component("comp1").view("view1").camera().setIndex("position", 1033, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -714, 1);
    model.component("comp1").view("view1").camera().set("position", new int[]{1033, -714, -517});
    model.component("comp1").view("view1").camera().setIndex("up", -0.3, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0.25, 1);
    model.component("comp1").view("view1").camera().set("up", new double[]{-0.3, 0.25, -0.9});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", 0.0286, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{0.0286, 0.0256});

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def1").set("scale", 5);
    model.result("pg3").run();

    model.param().set("noa", "3");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").appendResult();
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").appendResult();
    model.result("pg3").run();

    model.title("\u70ed\u5fae\u6267\u884c\u5668\u7684\u7b80\u5316\u6a21\u578b");

    model
         .description("\u672c\u6559\u7a0b\u63cf\u8ff0\u5982\u4f55\u5bf9\u5fae\u578b\u5668\u4ef6\u7684\u7535\u70ed\u6267\u884c\u5668\u5efa\u6a21\uff0c\u4f7f\u7528\u201c\u7126\u8033\u70ed\u201d\u63a5\u53e3\u6a21\u62df\u5fae\u578b\u5668\u4ef6\u52a0\u538b\u540e\u5728\u6267\u884c\u5668\u4e0a\u4ea7\u751f\u7684\u70ed\uff0c\u5e76\u901a\u8fc7\u65b9\u7a0b\u89c6\u56fe\u624b\u52a8\u6dfb\u52a0\u70ed\u81a8\u80c0\u3002\u672c\u4f8b\u4f7f\u7528\u51e0\u4f55\u5e8f\u5217\u4e2d\u7684\u7f16\u7a0b\u64cd\u4f5c\u6765\u8003\u8651\u4e0d\u540c\u7684\u51e0\u4f55\u60c5\u51b5\u3002");

    model.label("thermal_actuator_simplified.mph");

    model.result("pg3").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords3", "L", 0);
    model.component("comp1").probe("pdom1").set("bndsnap3", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "-v");
    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("probename", "maxTemp");
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").set("expr", "T");

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("alpha_dM", "1e-6");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("beta_dK", "1e-6");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ec", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").feature("time").set("tlist", "range(0,1e-6,1e-4)");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_T").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_T").set("scaleval", 300);
    model.sol("sol2").feature("v1").feature("comp1_V").set("scalemethod", "manual");
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");

    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "comp1.ppb1", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Tip displacement", 0);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");

    model.externalInterface().create("sim1", "SimulinkCosimulation");
    model.externalInterface("sim1").set("filename", "thermal_actuator_llsimulink");
    model.externalInterface("sim1").setIndex("inputname", "d", 0);
    model.externalInterface("sim1").setIndex("inputvalue", "3[um]", 0);
    model.externalInterface("sim1").setIndex("inputunit", "m", 0);
    model.externalInterface("sim1").setIndex("inputname", "d", 0);
    model.externalInterface("sim1").setIndex("inputvalue", "3[um]", 0);
    model.externalInterface("sim1").setIndex("inputunit", "m", 0);
    model.externalInterface("sim1").setIndex("inputname", "DV", 0);
    model.externalInterface("sim1").setIndex("outputexpr", "comp1.ppb1", 0);
    model.externalInterface("sim1").setIndex("outputunit", "um", 0);
    model.externalInterface("sim1").setIndex("outputdescr", "Disp", 0);
    model.externalInterface("sim1").setIndex("outputexpr", "comp1.maxTemp", 1);
    model.externalInterface("sim1").setIndex("outputdescr", "Max temp", 1);
    model.externalInterface("sim1").set("study", "std2");
    model.externalInterface("sim1")
         .set("image", "C:\\Users\\parttestusr\\AppData\\Local\\Temp\\csjavabridge38175\\cosimulationimage.png");
    model.externalInterface("sim1").importImage();
    model.externalInterface("sim1").run();
    model.externalInterface("sim1").set("filename", "thermal_actuator_llsimulink");

    model.title("\u70ed\u6267\u884c\u5668\u7684\u5f00\u5173\u63a7\u5236");

    model
         .description("\u672c\u793a\u4f8b\u6a21\u578b\u5305\u542b\u4e00\u4e2a\u7531\u591a\u6676\u7845\u5236\u6210\u7684\u7531\u70ed\u81a8\u80c0\u6fc0\u6d3b\u7684\u53cc\u70ed\u81c2\u70ed\u6267\u884c\u5668\uff0c\u901a\u8fc7\u7126\u8033\u70ed\uff08\u7535\u963b\u52a0\u70ed\uff09\u5f97\u5230\u4f7f\u4e24\u4e2a\u70ed\u81c2\u53d8\u5f62\u4ece\u800c\u4f7f\u6267\u884c\u5668\u53d1\u751f\u4f4d\u79fb\u6240\u9700\u7684\u6e29\u5347\u3002\u4e0e\u51b7\u81c2\u76f8\u6bd4\uff0c\u70ed\u81c2\u7684\u81a8\u80c0\u7a0b\u5ea6\u66f4\u5927\uff0c\u8fd9\u4f1a\u5bfc\u81f4\u6267\u884c\u5668\u5f2f\u66f2\u3002\n\u901a\u8fc7\u4f7f\u7528\u5f00/\u5173\u63a7\u5236\u5668\u6765\u8c03\u8282\u5916\u52a0\u7535\u52bf\uff0c\u53ef\u4ee5\u5c06\u5c16\u7aef\u6700\u5927\u4f4d\u79fb\u63a7\u5236\u5728 39 \u5230 41\u00a0um \u4e4b\u95f4\u3002\n\u8be5\u63a7\u5236\u5668\u901a\u8fc7 Simulink\u00ae \u5b9e\u73b0\u3002\n\u672c\u4f8b\u91cd\u7528\u4e86\u5e73\u53f0\u4ea7\u54c1\u7684\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u7684\u201c\u70ed\u5fae\u6267\u884c\u5668\u7684\u7b80\u5316\u6a21\u578b\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("thermal_actuator_llsimulink.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
