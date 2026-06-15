/*
 * piezoelectric_micropump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:58 by COMSOL 6.3.0.290. */
public class piezoelectric_micropump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/es", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("w_block", "30[mm]");
    model.param().descr("w_block", "\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("depth_block", "30[mm]");
    model.param().descr("depth_block", "\u5e95\u5ea7\u6df1\u5ea6");
    model.param().set("h_block", "5[mm]");
    model.param().descr("h_block", "\u5e95\u5ea7\u539a\u5ea6");
    model.param().set("h_exit", "5*r_inlet");
    model.param().descr("h_exit", "\u5165\u53e3/\u51fa\u53e3\u9ad8\u5ea6");
    model.param().set("h_memb", "1[mm]");
    model.param().descr("h_memb", "\u819c\u9ad8\u5ea6");
    model.param().set("ID", "8[mm]");
    model.param().descr("ID", "\u5706\u76d8\u5f0f\u6267\u884c\u5668\u5185\u5f84");
    model.param().set("OD", "15[mm]");
    model.param().descr("OD", "\u5706\u76d8\u5f0f\u6267\u884c\u5668\u5916\u5f84");
    model.param().set("r_inlet", "1[mm]");
    model.param().descr("r_inlet", "\u6d41\u4f53\u5165\u53e3\u534a\u5f84");
    model.param().set("r_memb", "12[mm]");
    model.param().descr("r_memb", "\u819c\u534a\u5f84");
    model.param().set("r_outlet", "1[mm]");
    model.param().descr("r_outlet", "\u6d41\u4f53\u51fa\u53e3\u534a\u5f84");
    model.param().set("t0", "0.1[mm]");
    model.param().descr("t0", "\u538b\u7535\u5c42\u539a\u5ea6");
    model.param().set("n", "75");
    model.param().descr("n", "\u6267\u884c\u5668\u4e2d\u7684\u5c42\u6570");
    model.param().set("E0", "0.2[V/um]");
    model.param().descr("E0", "\u7535\u573a\u5f3a\u5ea6");
    model.param().set("V0", "E0*t0*n");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");
    model.param().set("frequency", "60[Hz]");
    model.param().descr("frequency", "\u6d3b\u585e\u9a71\u52a8\u9891\u7387");
    model.param().set("high_stress", "5e3");
    model.param().descr("high_stress", "\u8fb9\u754c\u5e94\u529b\uff08\u9ad8\uff09");
    model.param().set("low_stress", "1e-1");
    model.param().descr("low_stress", "\u8fb9\u754c\u5e94\u529b\uff08\u4f4e\uff09");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u957f\u65b9\u4f53 1 - \u6d41\u4f53\u5ba4");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_block", "depth_block", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "h_block-h_memb", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-h_memb/2"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u5706\u67f1\u4f53 1 - \u538b\u7535\u5916\u5f84");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "OD/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "t0*n");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "h_block/2"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u5706\u67f1\u4f53 2 - \u538b\u7535\u5185\u5f84");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "ID/2");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u5dee\u96c6 1 - \u538b\u7535");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").label("\u5706\u67f1\u4f53 3 - \u819c");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r_memb");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "h_memb");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "h_block/2-h_memb"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").label("\u5706\u67f1\u4f53 4 - \u5165\u53e3");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "r_inlet");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "h_exit");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"-(OD+ID)/4", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl4").setIndex("pos", "-h_block/2-h_exit", 2);
    model.component("comp1").geom("geom1").feature().duplicate("cyl5", "cyl4");
    model.component("comp1").geom("geom1").feature("cyl5").label("\u5706\u67f1\u4f53 5 - \u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "r_outlet");
    model.component("comp1").geom("geom1").feature("cyl5").setIndex("pos", "(OD+ID)/4", 0);
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1")
         .label("\u5de5\u4f5c\u5e73\u9762 1 - \u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input")
         .set("blk1", "cyl3", "cyl4", "cyl5", "dif1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(1)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(2)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(3)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(4)", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(5)", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5e76\u96c6 1 - \u56fa\u4f53");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("del1(2)", "del1(5)");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").label("\u5e76\u96c6 2 - \u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("del1(1)", "del1(3)", "del1(4)");
    model.component("comp1").geom("geom1").feature("uni2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6846 1 - \u538b\u7535");
    model.component("comp1").selection("box1").set("xmin", "h_block/2-h_memb/2");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").set("xmin", "-inf");
    model.component("comp1").selection("box1").set("zmin", "h_block/2-h_memb/2");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5dee\u96c6 1 - \u819c");
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_uni1_dom"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1"});
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u6846 2 - \u5165\u53e3");
    model.component("comp1").selection("box2").set("entitydim", 2);
    model.component("comp1").selection("box2").set("xmax", 0);
    model.component("comp1").selection("box2").set("zmax", "-h_block/2-h_exit/2");
    model.component("comp1").selection("box2").set("condition", "inside");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().duplicate("box3", "box2");
    model.component("comp1").selection("box3").label("\u6846 3 - \u51fa\u53e3");
    model.component("comp1").selection("box3").set("xmin", 0);
    model.component("comp1").selection("box3").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").label("\u6846 4 - \u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").selection("box4").set("entitydim", 2);
    model.component("comp1").selection("box4").set("ymax", "min(min(r_inlet,r_outlet),ID)/2");
    model.component("comp1").selection("box4").set("condition", "inside");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u76f8\u90bb 1 - \u6240\u6709\u6d41\u4f53\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_uni2_dom"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u5dee\u96c6 2 - \u6d41\u4f53\u58c1");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"box2", "box3", "box4"});
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").set("entitydim", 2);
    model.component("comp1").selection("box5").set("xmin", "-eps");
    model.component("comp1").selection("box5").set("xmax", "eps");
    model.component("comp1").selection("box5").set("ymin", "-eps");
    model.component("comp1").selection("box5").set("ymax", "eps");
    model.component("comp1").selection("box5").set("zmin", "h_block/2-h_memb-eps");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u4ea4\u96c6 1 - \u6d41\u4f53\u819c");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"dif2", "box5"});
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1 - \u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(18, 25, 30);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat2").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.1);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat1").selection().named("geom1_uni2_dom");
    model.component("comp1").material("mat2").selection().named("box1");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u819c");
    model.component("comp1").material("mat3").selection().named("dif1");

    model.component("comp1").physics("solid").selection().named("geom1_uni1_dom");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("box1");
    model.component("comp1").physics("es").selection().named("box1");
    model.component("comp1").physics("spf").selection().named("geom1_uni2_dom");

    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"200[MPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.45"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2320[kg/m^3]"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box4");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("sel1");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(24);

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", 0.1);
    model.component("comp1").func("rm1").set("slope", 1.2);
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoffactive", true);

    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1")
         .set("V0", "V0*sin(t*frequency*2*pi)*rm1(t*frequency*4/3)");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("box2");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("box3");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("box4");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u5e73\u5747\u503c 1 - \u5165\u53e3");
    model.component("comp1").cpl("aveop1").set("opname", "av_in");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("box2");
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").label("\u5e73\u5747\u503c 2 - \u51fa\u53e3");
    model.component("comp1").cpl("aveop2").set("opname", "av_out");
    model.component("comp1").cpl("aveop2").selection().named("box3");

    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1")
         .set("p0", "if(av_in(w2)>0,-low_stress, high_stress)*(av_in(w2)^2)*av_in(spf.rho)");
    model.component("comp1").physics("spf").feature("inl1").set("FlowDirection", "userdef");
    model.component("comp1").physics("spf").feature("inl1").set("d_u", new int[]{0, 0, 1});
    model.component("comp1").physics("spf").feature("out1")
         .set("p0", "if(av_out(w2)<0,low_stress, -high_stress)*(av_out(w2)^2)*av_out(spf.rho)");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 1 - \u5165\u53e3");
    model.component("comp1").cpl("intop1").set("opname", "int_in");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("box2");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 2 - \u51fa\u53e3");
    model.component("comp1").cpl("intop2").set("opname", "int_out");
    model.component("comp1").cpl("intop2").selection().named("box3");

    model.component("comp1").physics("ge").feature("ge1")
         .label("\u5168\u5c40\u65b9\u7a0b 1 - \u7d2f\u79ef\u6d41\u91cf");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "Q_in", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "Q_int-int_in(w2)", 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u7d2f\u79ef\u6d41\u5165", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "Q_out", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "Q_outt-int_out(-w2)", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u7d2f\u79ef\u6d41\u51fa", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "m^3", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "m^3/s", 0, 0);

    model.component("comp1").multiphysics().create("fsip1", "FluidStructureInteractionPair", 2);
    model.component("comp1").multiphysics("fsip1").set("pairs", new String[]{"ap1"});

    model.component("comp1").selection().create("box6", "Box");
    model.component("comp1").selection("box6").label("\u6846 6 - \u56db\u8fb9\u5f62\u7f51\u683c");
    model.component("comp1").selection("box6").set("entitydim", 2);
    model.component("comp1").selection("box6").set("zmin", "h_block/2-eps");
    model.component("comp1").selection("box6").set("zmax", "h_block/2+eps");
    model.component("comp1").selection("box6").set("condition", "inside");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("box6");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_uni1_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("box1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_uni2_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_uni2_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").label("\u79ef\u5206 3 - \u6d41\u4f53\u819c");
    model.component("comp1").cpl("intop3").set("opname", "int_mem");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("int1");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1")
         .label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 1 - \u6d41\u5165\u6d41\u7387");
    model.component("comp1").probe("var1").set("probename", "flowrate_in");
    model.component("comp1").probe("var1").set("expr", "int_in(w2)");
    model.component("comp1").probe("var1").set("unit", "ml/s");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2")
         .label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 2 - \u6d41\u51fa\u901f\u7387");
    model.component("comp1").probe("var2").set("probename", "flowrate_out");
    model.component("comp1").probe("var2").set("expr", "int_out(-w2)");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5e76\u96c6 1 - \u5165\u53e3\u548c\u51fa\u53e3");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box2", "box3"});

    model.study("std1").feature("time").set("tlist", "range(0,0.025,5)/frequency");
    model.study("std1").feature("time").set("geometricNonlinearity", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 201, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "V");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Dipole");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (es)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "es.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("\u5207\u9762");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("expr", "spf.U");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(1, 3, 4, 5, 7, 11, 12, 16, 17);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u538b\u529b (spf)");
    model.result("pg6").set("data", "surf1");
    model.result("pg6").setIndex("looplevel", 201, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("tran1", "Transparency");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "dset1");
    model.result().numerical("gev3").set("expr", new String[]{"Q_in", "Q_out"});
    model.result().numerical("gev3")
         .set("descr", new String[]{"\u7d2f\u79ef\u6d41\u5165", "\u7d2f\u79ef\u6d41\u51fa"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("expr", new String[]{"Q_in", "Q_out"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u7d2f\u79ef\u6d41\u5165", "\u7d2f\u79ef\u6d41\u51fa"});
    model.result("pg2").run();
    model.result("pg7").run();
    model.result("pg7").label("\u7d2f\u79ef\u6d41\u91cf vs. \u65f6\u95f4");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6d41\u91cf (ul)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("unit", "ul", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "ul", 1);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6d41\u7387 vs. \u65f6\u95f4\u548c\u4f53\u79ef\u5b88\u6052");
    model.result("pg8").set("ylabel", "\u6d41\u7387 (ml/s)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "int_in(w2)", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "ml/s", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u6d41\u5165", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "int_out(-w2)", 1);
    model.result("pg8").feature("glob1").setIndex("unit", "ml/s", 1);
    model.result("pg8").feature("glob1").setIndex("descr", "\u6d41\u51fa", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "int_in(w2)+int_out(w2)+int_mem(-w2)", 2);
    model.result("pg8").feature("glob1").setIndex("unit", "ml/s", 2);
    model.result("pg8").feature("glob1").setIndex("descr", "\u4f53\u79ef\u5b88\u6052", 2);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u901f\u5ea6\u573a");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "log10(spf.U/1[mm/s])");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().named("box4");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf2", "surf1");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").set("expr", "log10(solid.vel/1[mm/s])");
    model.result("pg9").feature("surf2").set("inheritplot", "surf1");
    model.result("pg9").run();
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg9").feature("arws1").set("arrowlength", "normalized");
    model.result("pg9").feature("arws1").set("color", "white");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().named("box4");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("arws2", "arws1");
    model.result("pg9").run();
    model.result("pg9").feature("arws2").set("expr", new String[]{"ut", "vt", "wt"});
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 181, 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u6d41\u4f53\u6d41\u7ebf");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg10").feature("str1").create("col1", "Color");
    model.result("pg10").run();
    model.result("pg10").feature("str1").feature("col1").set("expr", "log10(spf.U/1[mm/s])");
    model.result("pg10").run();
    model.result("pg10").feature("str1").selection().named("uni1");
    model.result("pg10").feature("str1").set("linetype", "ribbon");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").setIndex("looplevel", 181, 0);
    model.result("pg10").run();

    model.title("\u538b\u7535\u5fae\u6cf5");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u7531 Veryst Engineering, LLC. \u7684 Riccardo Vietri\u3001James Ransley \u548c Andrew Spann \u63d0\u4f9b\u3002\u538b\u7535\u5fae\u6cf5\u80fd\u591f\u7cbe\u786e\u63a7\u5236\u6781\u5c0f\u4f53\u79ef\u7684\u6d41\u4f53\u6216\u6c14\u4f53\u7684\u8ba1\u91cf\uff0c\u56e0\u6b64\u5728\u533b\u7597\u9886\u57df\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u4e00\u4e2a\u9002\u7528\u4e8e\u4f4e\u6d41\u7387\u5e94\u7528\u7684\u7b80\u5355\u975e\u5171\u632f\u5fae\u6cf5\uff0c\u5305\u62ec\u5982\u4f55\u5c06\u538b\u7535\u6750\u6599\u4e0e\u6d41-\u56fa\u8026\u5408\u7ed3\u5408\u4f7f\u7528\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u7b80\u5355\u7684\u901f\u5ea6\u76f8\u5173\u516c\u5f0f\u6765\u8003\u8651\u5165\u53e3\u548c\u51fa\u53e3\u8fb9\u754c\u4e0a\u5b58\u5728\u7684\u9600\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("piezoelectric_micropump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
