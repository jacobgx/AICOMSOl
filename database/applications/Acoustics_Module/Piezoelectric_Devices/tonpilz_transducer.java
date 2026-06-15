/*
 * tonpilz_transducer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class tonpilz_transducer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/es", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").view("view1").set("showgrid", false);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Rwater", "40[mm]", "\u6c34\u57df\u534a\u5f84");
    model.param().set("Rpml", "10[mm]", "PML \u5c42\u539a\u5ea6");
    model.param().set("a", "25[mm]", "\u6d3b\u585e\u5934\u534a\u5f84");
    model.param().set("Zeval", "-10[m]", "\u65b9\u5411\u6027\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("Vrms", "1[V]", "\u5747\u65b9\u6839\u9a71\u52a8\u7535\u538b");
    model.param().set("V0", "sqrt(2)*Vrms", "\u96f6\u5230\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("f0min", "1[kHz]", "\u6700\u5c0f\u5de5\u4f5c\u9891\u7387");
    model.param().set("f0max", "40[kHz]", "\u6700\u5927\u5de5\u4f5c\u9891\u7387");
    model.param().set("f0step", "1[kHz]", "\u9891\u7387\u6b65\u8fdb");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{4, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{0, 25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{2, 20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{0, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{6, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new int[]{2, 15});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new int[]{2, 8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{4, 7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layer", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layer", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layer", 2, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 5, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 2, 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 5, 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 2, 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 7, 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 20, 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 7, 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Rwater+Rpml");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "Rpml", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(90,90,270)");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6c34\u57df - \u5185\u90e8");
    model.component("comp1").selection("sel1").set(3, 4, 22, 32);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6c34\u57df - PML");
    model.component("comp1").selection("sel2").set(1, 2, 21, 31);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u94dd");
    model.component("comp1").selection("sel3").set(5, 6, 23, 33);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u94a2");
    model.component("comp1").selection("sel4").set(7, 8, 17, 18, 19, 20, 24, 29, 30, 34, 35, 36);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("+Z \u5411\u6781\u5316\u7684\u538b\u7535");
    model.component("comp1").selection("sel5").set(9, 10, 13, 14, 25, 27, 37, 39);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("-Z \u5411\u6781\u5316\u7684\u538b\u7535");
    model.component("comp1").selection("sel6").set(11, 12, 15, 16, 26, 28, 38, 40);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6c34\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u538b\u7535\u57df");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u56fa\u4f53\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("com2", "Complement");
    model.component("comp1").selection("com2").label("\u975e PML \u57df");
    model.component("comp1").selection("com2").set("input", new String[]{"sel2"});
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(28, 29, 38, 39, 45, 46, 93, 99, 103, 145, 151, 155);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u7535\u538b\u8fb9\u754c");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(33, 34, 43, 44, 96, 102, 148, 154);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(19, 20, 88, 164);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u5916\u573a\u8fb9\u754c");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10").set(9, 10, 82, 125);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel10");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(14, 15, 85, 128);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop3").selection().set(46);

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "pi", "0"});
    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel2");
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "0.5");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "5");

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
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Steel AISI 4340");
    model.component("comp1").material("mat3").set("family", "steel");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat4").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat4").label("Lead Zirconate Titanate (PZT-4)");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.9);
    model.component("comp1").material("mat4").set("roughness", 0.1);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.23e-011[1/Pa]", "-4.05e-012[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.05e-012[1/Pa]", "1.23e-011[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.31e-012[1/Pa]", "-5.31e-012[1/Pa]", "1.55e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.27e-011[1/Pa]"});
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "2.89e-010[C/N]", "0[C/N]", 
         "4.96e-010[C/N]", "0[C/N]", "4.96e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"1475", "0", "0", "0", "1475", "0", "0", "0", "1300"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.38999e+011[Pa]", "7.78366e+010[Pa]", "7.42836e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "7.78366e+010[Pa]", "1.38999e+011[Pa]", "7.42836e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "7.42836e+010[Pa]", "7.42836e+010[Pa]", "1.15412e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "3.0581e+010[Pa]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "15.0804[C/m^2]", "0[C/m^2]", 
         "12.7179[C/m^2]", "0[C/m^2]", "12.7179[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat4").selection().named("uni2");

    model.component("comp1").physics("acpr").selection().named("uni1");
    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("sel10");
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("solid").selection().named("com1");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("sel5");
    model.component("comp1").physics("solid").create("pzm2", "PiezoelectricMaterialModel", 3);
    model.component("comp1").physics("solid").feature("pzm2").selection().named("sel6");
    model.component("comp1").physics("solid").feature("pzm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel9");
    model.component("comp1").physics("es").selection().named("uni2");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("sel7");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("es").feature("pot1").selection().named("sel8");
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("com2");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "1500[m/s]/f0max/6");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel10");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "1500[m/s]/f0max/6/20");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("rho0", "intop3(acpr.rho)", "\u5ba4\u6e29\u6c34\u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("c0", "intop3(acpr.c)", "\u5ba4\u6e29\u6c34\u4e2d\u7684\u58f0\u901f");
    model.component("comp1").variable("var1")
         .set("Zaco", "intop2(p)/intop2(acpr.iomega*(w+eps))/(rho0*c0)", "\u6bd4\u58f0\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("pext_1", "pext(0,0,-1)", "1 m \u5904\u7684\u5916\u573a\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("prms", "sqrt(0.5*pext_1*conj(pext_1))[Pa]", "1 m \u5904\u7684\u5747\u65b9\u6839\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("TVR", "20*log10(prms/Vrms/1[uPa/V])", "\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR)");
    model.component("comp1").variable("var1")
         .set("pext_Zeval", "pext(0,0,Zeval[1/m])", "Zeval \u5904\u7684\u5916\u573a\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("Ifront", "0.5*pext_Zeval*conj(pext_Zeval)[Pa^2]/(rho0*c0)", "Zeval \u5904\u7684\u8f74\u4e0a\u5f3a\u5ea6");
    model.component("comp1").variable("var1")
         .set("Ptot", "intop1(down(acpr.Ix)*acpr.nx+down(acpr.Iy)*acpr.ny+down(acpr.Iz)*acpr.nz)", "\u603b\u8f90\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Iave", "Ptot/(4*pi*Zeval^2)", "Zeval \u5904\u7684\u5355\u6781\u6e90\u5e73\u5747\u5f3a\u5ea6");
    model.component("comp1").variable("var1").set("Di", "Ifront/Iave", "\u5f3a\u5ea6\u65b9\u5411\u6027");
    model.component("comp1").variable("var1")
         .set("DI", "10*log10(Di)", "Tonpilz \u578b\u6362\u80fd\u5668\u7684\u6307\u5411\u6027\u6307\u6570");
    model.component("comp1").variable("var1").set("k0", "2*pi*freq/c0", "\u6ce2\u6570");
    model.component("comp1").variable("var1")
         .set("DI_fl_pist", "10*log10((k0*a)^2/(1-2*besselj(1,2*k0*a)/(2*k0*a)))", "\u5e26\u51f8\u7f18\u6d3b\u585e\u7684\u6307\u5411\u6027\u6307\u6570");
    model.component("comp1").variable("var1")
         .set("SPL_Zeval", "intop3(subst(acpr.efc1.Lp_pext,x,0,y,0,z,Zeval))", "Zeval \u5904\u7684\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("SPL_rel", "acpr.efc1.Lp_pext-SPL_Zeval", "Zeval \u5904\u76f8\u5bf9\u4e8e 0 dB \u7684\u58f0\u538b\u7ea7");

    model.study("std1").feature("freq").set("punit", "kHz");
    model.study("std1").feature("freq").set("plist", "range(f0min,f0step,f0max)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 40, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 40, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 40, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 40, 0);
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("view", "new");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg4").feature("rp1").set("thetadisc", 40);
    model.result("pg4").feature("rp1").set("phidisc", 60);
    model.result("pg4").feature("rp1").set("grid", "fine");
    model.result("pg4").feature("rp1").set("colortable", "Rainbow");
    model.result("pg4").feature("rp1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("data", "dset1");
    model.result("pg5").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg5").feature("rp1").set("thetadisc", 40);
    model.result("pg5").feature("rp1").set("phidisc", 60);
    model.result("pg5").feature("rp1").set("colortable", "Cividis");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u5916\u573a\u538b\u529b (acpr)");
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").label("\u5916\u573a\u58f0\u538b\u7ea7 xy \u5e73\u9762 (acpr)");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 40, 0);
    model.result("pg7").label("\u5e94\u529b (solid)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg7").feature("vol1").set("threshold", "manual");
    model.result("pg7").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("vol1").set("colortable", "Rainbow");
    model.result("pg7").feature("vol1").set("colortabletrans", "none");
    model.result("pg7").feature("vol1").set("colorscalemode", "linear");
    model.result("pg7").feature("vol1").set("resolution", "custom");
    model.result("pg7").feature("vol1").set("refine", 2);
    model.result("pg7").feature("vol1").set("colortable", "Prism");
    model.result("pg7").feature("vol1").create("def", "Deform");
    model.result("pg7").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u52bf (es)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("solutionparams", "parent");
    model.result("pg8").feature("mslc1").set("expr", "V");
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg8").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg8").feature("mslc1").set("colortable", "Dipole");
    model.result("pg8").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg8").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg8").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg8").feature("strmsl1").set("titletype", "none");
    model.result("pg8").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg8").feature("strmsl1").set("udist", 0.02);
    model.result("pg8").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg8").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("inheritcolor", false);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("data", "parent");
    model.result("pg8").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg8").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg8").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg8").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("strmsl1").feature("col1").set("colorscalemode", "linear");
    model.result("pg8").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg8").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u7535\u573a (es)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("mslc1", "Multislice");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("solutionparams", "parent");
    model.result("pg9").feature("mslc1").set("expr", "es.normE");
    model.result("pg9").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg9").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg9").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg9").feature("mslc1").set("colortable", "Prism");
    model.result("pg9").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg9").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("data", "parent");
    model.result("pg9").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg9").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg9").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg9").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg9").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg9").feature("strmsl1").set("titletype", "none");
    model.result("pg9").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg9").feature("strmsl1").set("udist", 0.02);
    model.result("pg9").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg9").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("inheritcolor", false);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("data", "parent");
    model.result("pg9").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg9").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg9").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg9").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg9").feature("strmsl1").feature("col1").set("colorscalemode", "linear");
    model.result("pg9").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg9").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").set("view", "view1");
    model.result("pg1").run();
    model.result("pg1").feature().remove("surf1");
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("view", "view1");
    model.result("pg2").run();
    model.result("pg2").feature().remove("surf1");
    model.result("pg2").run();
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("mslc1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg2").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("rp1").set("expr", "SPL_rel");
    model.result("pg4").feature("rp1").set("anglerestr", "manual");
    model.result("pg4").feature("rp1").set("thetamin", 90);
    model.result("pg4").feature("rp1").set("thetarange", 90);
    model.result("pg4").feature("rp1").set("sphere", "manual");
    model.result("pg4").feature("rp1").set("radius", "abs(Zeval)");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u5bf9\u6ce2\u675f\u7075\u654f\u5ea6\u6781\u5750\u6807\u56fe");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{1, 10, 40}, 0);
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("expr", "SPL_rel");
    model.result("pg6").feature("rp1").set("anglerestr", "manual");
    model.result("pg6").feature("rp1").set("phimin", -90);
    model.result("pg6").feature("rp1").set("phirange", 180);
    model.result("pg6").feature("rp1").set("refdir", new int[]{0, 0, -1});
    model.result("pg6").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg6").feature("rp1").set("radius", "abs(Zeval)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").label("\u4f4d\u79fb");
    model.result("pg7").set("view", "view1");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").set("expr", "solid.disp");
    model.result("pg7").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("view", "new");
    model.result("pg8").run();
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("exprz", "(1-s)*(-1.02*Rwater)-s*500[mm]");
    model.result().dataset("pc1").set("global", true);
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u8f74\u4e0a\u538b\u529b");
    model.result("pg10").setIndex("looplevelinput", "last", 0);
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u4e0e\u6362\u80fd\u5668\u7684\u8ddd\u79bb (mm)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").selection().set(136);
    model.result("pg10").feature("lngr1").set("expr", "p");
    model.result("pg10").feature("lngr1").set("descr", "\u58f0\u538b");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "-z");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").feature("lngr1").set("legendmethod", "manual");
    model.result("pg10").feature("lngr1").setIndex("legends", "\u57df\u8ba1\u7b97", 0);
    model.result("pg10").run();
    model.result("pg10").create("lngr2", "LineGraph");
    model.result("pg10").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr2").set("linewidth", "preference");
    model.result("pg10").feature("lngr2").set("data", "pc1");
    model.result("pg10").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg10").feature("lngr2").set("expr", "pext(x,y,z)");
    model.result("pg10").feature("lngr2").set("xdata", "expr");
    model.result("pg10").feature("lngr2").set("xdataexpr", "-z");
    model.result("pg10").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg10").feature("lngr2").set("linestyle", "dashed");
    model.result("pg10").feature("lngr2").set("legend", true);
    model.result("pg10").feature("lngr2").set("legendmethod", "manual");
    model.result("pg10").feature("lngr2").setIndex("legends", "\u5916\u90e8\u573a\u8ba1\u7b97", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u6bd4\u58f0\u963b\u6297");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "Z/(\\rho c)");
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "abs(Zaco)", 0);
    model.result("pg11").feature("glob1").setIndex("expr", "real(Zaco)", 1);
    model.result("pg11").feature("glob1").setIndex("expr", "imag(Zaco)", 2);
    model.result("pg11").feature("glob1").set("legendmethod", "manual");
    model.result("pg11").feature("glob1").setIndex("legends", "\u7edd\u5bf9\u503c", 0);
    model.result("pg11").feature("glob1").setIndex("legends", "\u5b9e\u90e8", 1);
    model.result("pg11").feature("glob1").setIndex("legends", "\u865a\u90e8", 2);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u53d1\u5c04\u7535\u538b\u54cd\u5e94");
    model.result("pg12").set("ylabel", "TVR\uff08dB \u76f8\u5bf9 1\\mu Pa/V\uff09");
    model.result("pg12").set("legendpos", "lowerright");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").remove("unit", new int[]{1, 2});
    model.result("pg12").feature("glob1").remove("descr", new int[]{1, 2});
    model.result("pg12").feature("glob1").remove("expr", new int[]{1, 2});
    model.result("pg12").feature("glob1").setIndex("expr", "TVR", 0);
    model.result("pg12").feature("glob1").set("legend", false);
    model.result("pg12").run();
    model.result("pg11").run();
    model.result().duplicate("pg13", "pg11");
    model.result("pg13").run();
    model.result("pg13").label("\u6307\u5411\u6027\u6307\u6570 (DI)");
    model.result("pg13").set("ylabel", "DI (dB)");
    model.result("pg13").set("legendpos", "lowerleft");
    model.result("pg13").run();
    model.result("pg13").feature("glob1").setIndex("expr", "DI", 0);
    model.result("pg13").feature("glob1").setIndex("expr", "DI_fl_pist", 1);
    model.result("pg13").feature("glob1")
         .setIndex("descr", "\u51f8\u7f18\u6d3b\u585e\u7684\u6307\u5411\u6027\u6307\u6570", 1);
    model.result("pg13").feature("glob1").remove("unit", 2);
    model.result("pg13").feature("glob1").remove("descr", 2);
    model.result("pg13").feature("glob1").remove("expr", new int[]{2});
    model.result("pg13").feature("glob1").set("legendmethod", "automatic");
    model.result("pg13").run();
    model.result("pg12").run();
    model.result().duplicate("pg14", "pg12");
    model.result("pg14").run();
    model.result("pg14").label("\u603b\u8f90\u5c04\u529f\u7387");
    model.result("pg14").set("ylabelactive", false);
    model.result("pg14").set("legendpos", "upperleft");
    model.result("pg14").run();
    model.result("pg14").feature("glob1").setIndex("expr", "Ptot", 0);
    model.result("pg14").feature("glob1").setIndex("unit", "mW", 0);
    model.result("pg14").run();
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmax1", 0);
    model.result().dataset("grid1").set("parmin2", -200);
    model.result().dataset("grid1").set("parmax2", 200);
    model.result().dataset("grid1").set("parmax3", -200);
    model.result().dataset("grid1").set("res1", 2);
    model.result().dataset("grid1").set("res2", 150);
    model.result().dataset("grid1").set("res3", 150);
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result("pg15").label("\u5916\u90e8\u538b\u529b\u5207\u9762");
    model.result("pg15").set("titletype", "manual");
    model.result("pg15").set("title", "\u5916\u90e8\u538b\u529b\u573a\u5207\u9762\u56fe");
    model.result("pg15").set("paramindicator", "");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("data", "grid1");
    model.result("pg15").feature("surf1").set("expr", "if(sqrt(x^2+y^2+z^2)>Rwater,pext(x,y,z),NaN)");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").run();
    model.result("pg16").label("\u5916\u90e8\u58f0\u538b\u7ea7\u5207\u9762");
    model.result("pg16").set("titletype", "manual");
    model.result("pg16").set("title", "\u5916\u90e8\u58f0\u538b\u7ea7\u5207\u9762\u56fe");
    model.result("pg16").set("paramindicator", "");
    model.result("pg16").setIndex("looplevel", 20, 0);
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("data", "grid1");
    model.result("pg16").feature("surf1").setIndex("looplevel", 20, 0);
    model.result("pg16").feature("surf1").set("expr", "if(sqrt(x^2+y^2+z^2)>Rwater,acpr.efc1.Lp_pext,NaN)");
    model.result("pg16").feature("surf1").set("colortable", "Rainbow");
    model.result("pg16").feature("surf1").set("colorscalemode", "linear");
    model.result("pg16").run();
    model.result("pg16").create("slc1", "Slice");
    model.result("pg16").feature("slc1").set("expr", "acpr.Lp_t");
    model.result("pg16").feature("slc1").set("quickxnumber", 1);
    model.result("pg16").feature("slc1").set("inheritplot", "surf1");
    model.result("pg16").feature("slc1").create("sel1", "Selection");
    model.result("pg16").feature("slc1").feature("sel1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.result("pg16").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").create("sel1", "Selection");
    model.result("pg1").feature("mslc1").feature("sel1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.result("pg1").run();

    model.title("Tonpilz \u578b\u538b\u7535\u6362\u80fd\u5668");

    model
         .description("Tonpilz \u6362\u80fd\u5668\u7528\u4e8e\u76f8\u5bf9\u4f4e\u9891\u7684\u5927\u529f\u7387\u58f0\u53d1\u5c04\u3002\u8fd9\u662f\u58f0\u5450\u5e94\u7528\u4e2d\u5e38\u7528\u7684\u6362\u80fd\u5668\u914d\u7f6e\u3002\u6362\u80fd\u5668\u7531\u524d\u8d28\u91cf\u5757\u3001\u540e\u8d28\u91cf\u5757\u53ca\u5806\u53e0\u5728\u4e24\u8005\u4e4b\u95f4\u7684\u538b\u7535\u9676\u74f7\u73af\u7ec4\u6210\uff0c\u8fd9\u4e9b\u73af\u901a\u8fc7\u4e2d\u5fc3\u87ba\u6813\u8fde\u63a5\u3002\u672c\u4f8b\u7814\u7a76\u6362\u80fd\u5668\u7684\u9891\u7387\u54cd\u5e94\uff0c\u4ece\u800c\u786e\u5b9a\u8be5\u88c5\u7f6e\u7684\u7ed3\u6784\u54cd\u5e94\u548c\u58f0\u54cd\u5e94\uff0c\u5982\u53d8\u5f62\u3001\u5e94\u529b\u3001\u8f90\u5c04\u538b\u529b\u3001\u58f0\u538b\u7ea7\u3001\u5916\u573a\u675f\u65b9\u5411\u56fe\u3001\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR) \u66f2\u7ebf\u4ee5\u53ca\u58f0\u675f\u7684\u6307\u5411\u6027\u6307\u6570 (DI)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("tonpilz_transducer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
