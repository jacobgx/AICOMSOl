/*
 * ultrasound_flow_meter_generic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class ultrasound_flow_meter_generic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("Uin", "10[m/s]", "\u5165\u53e3\u5904\u80cc\u666f\u5e73\u5747\u6d41\u7684\u5e73\u5747\u901f\u5ea6");
    model.param().set("p0", "1[atm]", "\u80cc\u666f\u5e73\u5747\u6d41\u538b\u529b");
    model.param().set("rho0", "998[kg/m^3]", "\u80cc\u666f\u5e73\u5747\u6d41\u5bc6\u5ea6");
    model.param().set("c0", "1481[m/s]", "\u58f0\u901f");
    model.param().set("f0", "2.5e6[Hz]", "\u8f7d\u6ce2\u4fe1\u53f7\u9891\u7387");
    model.param().set("omega0", "2*pi*f0", "\u8f7d\u6ce2\u4fe1\u53f7\u89d2\u9891\u7387");
    model.param().set("T0", "1/f0", "\u8f7d\u6ce2\u4fe1\u53f7\u5468\u671f");
    model.param().set("lam0", "c0/f0", "\u8f7d\u6ce2\u4fe1\u53f7\u6ce2\u957f");
    model.param().set("D", "5[mm]", "\u4e3b\u7ba1\u9053\u76f4\u5f84");
    model.param().set("L", "4*D", "\u4e3b\u7ba1\u9053\u957f\u5ea6");
    model.param().set("alpha", "45[deg]", "\u6362\u80fd\u5668\u7ba1\u9053\u503e\u89d2");
    model.param().set("D_transducer", "2[mm]", "\u6362\u80fd\u5668\u76f4\u5f84");
    model.param()
         .set("L_transducer", "D/cos(alpha)+3/2*D_transducer/cos(alpha)*tan(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param().set("L2", "D/sin(alpha)", "\u4e3b\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param()
         .set("L1", "0.5*(L_transducer-L2)", "\u652f\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param()
         .set("nLx", "cos(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08x \u5206\u91cf\uff09");
    model.param()
         .set("nLy", "0", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08y \u5206\u91cf\uff09");
    model.param()
         .set("nLz", "sin(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08z \u5206\u91cf\uff09");
    model.param().set("A", "0.1[mm/s]", "\u901f\u5ea6\u4fe1\u53f7\u5e45\u5ea6");
    model.param().set("Nlam", "L_transducer/lam0", "\u6362\u80fd\u5668\u7ba1\u9053\u4e2d\u7684\u6ce2\u957f\u6570");
    model.param()
         .set("DT_calc", "5.01E-8[s]", "\u8ba1\u7b97\u7684\u65f6\u95f4\u5dee\uff08\u5df2\u77e5\u6d41\u52a8\uff09");
    model.param()
         .set("DT_simulated", "4.86E-8[s]", "\u6a21\u62df\u7684\u65f6\u95f4\u5dee\uff08\u6765\u81ea\u4e00\u7ef4\u56fe\u5f62\uff09");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "D/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layername", "\u5c42 1", 0);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "0.5*D", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "D_transducer/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_transducer");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"L/2", "0", "-L_transducer/2"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "alpha");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"L/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "rot1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1, 3, 5, 7, 9, 11);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").set("fin", 2, 3, 4, 5);
    model.component("comp1").geom("geom1").run("cmd1");

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

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 6, 16);
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6d41\u4f53\u5165\u53e3");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6d41\u4f53\u51fa\u53e3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(19);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6e90");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(10);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u63a5\u6536\u5668");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(14);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u58c1");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(3, 4, 7, 8, 9, 11, 12, 13, 17, 18);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel3");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c - CFD");
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature().move("swe1", 5);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - CFD");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").physics().create("cwe", "ConvectedWaveEquation", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/cwe", false);

    model.component("comp1").physics("cwe").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("cwe").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("cwe").create("imp1", "AcousticImpedance", 2);
    model.component("comp1").physics("cwe").feature("imp1").selection().set(1, 19);

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "vn");
    model.func("an1").set("expr", "A*sin(omega0*t)*exp(-(f0*(t-3*T0))^2)");
    model.func("an1").set("args", "t");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").set("fununit", "m/s");

    model.component("comp1").physics("cwe").create("nvel1", "NormalVelocity", 2);
    model.component("comp1").physics("cwe").feature("nvel1").selection().named("sel4");
    model.component("comp1").physics("cwe").feature("nvel1").set("nvel", "vn(t)");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel5");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 3);

    model.component("comp1").sorder("quadratic");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "ultrasound_flow_meter_generic_upstream_signal.txt");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setEntry("funcnames", "col2", "p_upstream");
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").set("interp", "piecewisecubic");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c - \u58f0\u5b66");
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "lam0/1.5");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "lam0/2");
    model.component("comp1").mesh("mesh2").feature("ftet1").set("optsmall", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").set("optlevel", "high");
    model.component("comp1").mesh("mesh2").run();

    model.component("comp1").multiphysics().create("bffc1", "BackgroundFluidFlowCoupling", 3);
    model.component("comp1").multiphysics("bffc1").selection().all();
    model.component("comp1").multiphysics("bffc1").set("delta_diff", "1e-4");

    model.study().create("std2");
    model.study("std2").create("mapp", "Mapping");
    model.study("std2").feature("mapp").set("solnum", "auto");
    model.study("std2").feature("mapp").set("notsolnum", "auto");
    model.study("std2").feature("mapp").set("outputmap", new String[]{});
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/cwe", false);
    model.study("std2").feature("mapp").setSolveFor("/multiphysics/bffc1", true);
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/cwe", false);
    model.study("std2").label("\u7814\u7a76 2 - \u6620\u5c04");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("mapp").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/cwe", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/bffc1", false);
    model.study("std3").label("\u7814\u7a76 - \u58f0\u5b66");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").set("tlist", "range(0,T0/12,30*T0)");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").feature("time").setEntry("outputmap", "spf", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "spf", "sel1;sel4;sel5");
    model.study("std3").feature("time").setEntry("outputmap", "cwe", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "cwe", "sel1;sel4;sel5");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(6, 10, 14);
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset("cln1").set("method", "pointdir");
    model.result().dataset("cln1").set("pdpoint", new String[]{"L/2", "0", "0"});
    model.result().dataset("cln1").set("pddir", new String[]{"nLx", "nLy", "nLz"});
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u80cc\u666f\u6d41\u901f");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u80cc\u666f\u6d41\u901f\u66f2\u7ebf");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(7);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u5e73\u5747\u6d41\u66f2\u7ebf\uff0cCFD", 0);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("data", "dset2");
    model.result("pg2").feature("lngr2").set("expr", "bffc1.u_mapx");
    model.result("pg2").feature("lngr2").set("descr", "\u6620\u5c04\u7684\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u5e73\u5747\u6d41\u66f2\u7ebf\uff0c\u6620\u5c04", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u5b66\u538b\u529b");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "p2");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("expr", new String[]{"0", "p2", "0"});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 61, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 91, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 121, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 151, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u901f");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 241, 0);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "cwe.v_inst");
    model.result("pg4").feature("surf1").set("descr", "\u58f0\u901f\u5927\u5c0f");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 6);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u58f0\u5b66\u5f3a\u5ea6");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 241, 0);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "cwe.Ii_mag");
    model.result("pg5").feature("surf1").set("descr", "\u77ac\u65f6\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg5").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg5").feature("surf1").set("resolution", "custom");
    model.result("pg5").feature("surf1").set("refine", 6);
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"cwe.Iix", "cwe.Iiy", "cwe.Iiz"});
    model.result("pg5").feature("arws1").set("descr", "\u77ac\u65f6\u5f3a\u5ea6");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("logrange", 400);
    model.result("pg5").feature("arws1").set("arrowcount", 1000);
    model.result("pg5").feature("arws1").set("color", "white");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6cbf\u6362\u80fd\u5668\u8f74\u7684\u538b\u529b");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{121}, 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "p2");
    model.result("pg6").feature("lngr1").set("resolution", "extrafine");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u63a5\u6536\u5668\u5904\u7684\u538b\u529b\u4fe1\u53f7");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "aveop1(p2)", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "Pa", 0);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u5411\u4e0b\u6e38\u4f20\u64ad\u65f6\u7684\u538b\u529b", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "p_upstream(t)", 1);
    model.result("pg7").feature("glob1").setIndex("unit", "Pa", 1);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u5411\u4e0a\u6e38\u4f20\u64ad\u65f6\u7684\u538b\u529b \uff08\u5df2\u5bfc\u5165\uff09", 1);
    model.result("pg7").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").set("data", "cln1");
    model.result().numerical("int1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("int1")
         .setIndex("expr", "1/(cwe.c0-(nLx*cwe.u0x+nLy*cwe.u0y+nLz*cwe.u0z))-1/(cwe.c0+(nLx*cwe.u0x+nLy*cwe.u0y+nLz*cwe.u0z))", 0);
    model.result().numerical("int1").setIndex("unit", "", 0);
    model.result().numerical("int1").setIndex("descr", "", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev1").setIndex("expr", "L2/(DT_calc*cos(alpha))*(-1+sqrt(1+DT_calc^2*c0^2/L2^2))", 0);
    model.result().numerical("gev1").setIndex("unit", "", 0);
    model.result().numerical("gev1").setIndex("descr", "", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("gev1")
         .setIndex("expr", "L2/(DT_simulated*cos(alpha))*(-1+sqrt(1+DT_simulated^2*c0^2/L2^2))", 1);
    model.result().numerical("gev1").setIndex("unit", "", 1);
    model.result().numerical("gev1").setIndex("descr", "", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl2");
    model.result().numerical("gev1").setResult();
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", "0.7e-5");
    model.result("pg7").set("xmax", "1.1e-5");
    model.result("pg7").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledcoordinatesystems", new String[]{"ab1"});

    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "dset1");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "dset1");
    model.result("pg3").feature("surf2").set("expr", "spf.delta_w");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "white");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u901a\u7528\u4f20\u64ad\u65f6\u95f4\u6cd5\u8d85\u58f0\u6ce2\u6d41\u91cf\u8ba1");

    model
         .description("\u5bf9\u4e8e\u4f7f\u7528\u6d41\u4f53\u4f20\u8f93\u6750\u6599\u6216\u80fd\u91cf\u7684\u5404\u79cd\u60c5\u51b5\uff0c\u4e86\u89e3\u6d41\u4f53\u7684\u8fd0\u52a8\u901f\u5ea6\u90fd\u975e\u5e38\u91cd\u8981\u3002\u4f20\u64ad\u65f6\u95f4\u6cd5\u6216\u6e21\u8d8a\u65f6\u95f4\u6cd5\u53ef\u7528\u4e8e\u786e\u5b9a\u6d41\u901f\uff0c\u5176\u4e2d\uff0c\u8d85\u58f0\u6ce2\u4fe1\u53f7\u5728\u7ba1\u9053\u5185\u7684\u4e3b\u6d41\u4e0a\u4f20\u8f93\uff0c\u4ece\u800c\u975e\u4fb5\u5165\u6027\u5730\u786e\u5b9a\u5176\u901f\u5ea6\u3002\u7531\u4e8e\u8d85\u58f0\u6ce2\u4fe1\u53f7\u7684\u4f20\u8f93\u4e0e\u4e3b\u6d41\u6210\u4e00\u5b9a\u7684\u89d2\u5ea6\uff0c\u5f53\u5176\u5728\u4e3b\u6d41\u65b9\u5411\u4f20\u64ad\u65f6\uff0c\u4f20\u64ad\u901f\u5ea6\u5feb\u4e8e\u58f0\u901f\uff1b\u53cd\u4e4b\uff0c\u5176\u4f20\u64ad\u901f\u5ea6\u6162\u4e8e\u58f0\u901f\u3002\u4e24\u4e2a\u65b9\u5411\u4e0a\u7684\u4f20\u64ad\u65f6\u95f4\u5dee\u968f\u4e3b\u6d41\u901f\u5ea6\u5448\u7ebf\u6027\u589e\u52a0\u3002\u8fd9\u79cd\u6d41\u91cf\u8ba1\u6709\u5f88\u591a\u7528\u9014\uff0c\u5728\u5de5\u4e1a\u9886\u57df\u7684\u7528\u9014\u5c24\u4e3a\u7a81\u51fa\u3002\n\n\u901a\u8fc7\u672c\u6559\u7a0b\uff0c\u60a8\u53ef\u4ee5\u4e86\u89e3\u5982\u4f55\u4f7f\u7528 COMSOL Multiphysics\u00ae \u4eff\u771f\u8f6f\u4ef6\u6a21\u62df\u901a\u7528\u7684\u6e7f\u5f0f\u77ac\u6001\u8d85\u58f0\u6ce2\u6d41\u91cf\u8ba1\u3002\u672c\u4f8b\u5efa\u7acb\u7684\u6a21\u578b\u6c42\u89e3\u4fe1\u53f7\u5411\u4e0b\u6e38\u4f20\u64ad\u7684\u77ac\u6001\u95ee\u9898\u3002\u9996\u5148\uff0c\u6211\u4eec\u4f7f\u7528\u201cCFD \u6a21\u5757\u201d\u8ba1\u7b97\u6d41\u91cf\u8ba1\u4e2d\u7684\u7a33\u6001\u80cc\u666f\u6d41\u3002\u9884\u5148\u8ba1\u7b97\u5411\u4e0a\u6e38\u4f20\u64ad\u7684\u4fe1\u53f7\uff0c\u7136\u540e\u5c06\u5176\u4f5c\u4e3a\u6570\u636e\u5bfc\u5165\uff1b\u5e76\u4f7f\u7528\u5230\u8fbe\u65f6\u95f4\u5dee\u8ba1\u7b97\u4e3b\u6d41\u901f\u5ea6\u3002\u63a5\u7740\uff0c\u4f7f\u7528\u201c\u58f0\u5b66\u6a21\u5757\u201d\u7684\u201c\u8d85\u58f0\u201d\u8282\u70b9\u4e0b\u7684\u201c\u5bf9\u6d41\u6ce2\u52a8\u65b9\u7a0b\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u3002\u8be5\u63a5\u53e3\u57fa\u4e8e\u95f4\u65ad\u4f3d\u8fbd\u91d1\u6cd5 (DG-FEM)\uff0c\u4e13\u7528\u4e8e\u5206\u6790\u77ac\u6001\u9ad8\u9891\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("ultrasound_flow_meter_generic.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
