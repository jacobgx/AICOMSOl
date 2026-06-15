/*
 * duct_right_angled_bend.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class duct_right_angled_bend {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").insertFile("duct_right_angled_bend_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("f_max", "1300[Hz]");
    model.param().descr("f_max", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("lambda_min", "343[m/s]/f_max");
    model.param().descr("lambda_min", "\u6700\u5c0f\u6ce2\u957f");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").geom(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel2").set(12);

    model.component("comp1").view("view1").set("renderwireframe", false);

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

    model.nodeGroup().create("grp1", "Physics", "acpr");
    model.nodeGroup("grp1").label("\u5165\u53e3\u7aef\u53e3");

    model.component("comp1").physics("acpr").create("port1", "Port", 2);

    model.nodeGroup("grp1").add("port1");

    model.component("comp1").physics("acpr").feature("port1").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", 1);
    model.component("comp1").physics("acpr").create("port2", "Port", 2);

    model.nodeGroup("grp1").add("port2");

    model.component("comp1").physics("acpr").feature("port2").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port2").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port2").set("m_rect", 1);
    model.component("comp1").physics("acpr").create("port3", "Port", 2);

    model.nodeGroup("grp1").add("port3");

    model.component("comp1").physics("acpr").feature("port3").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port3").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port3").set("n_rect", 1);
    model.component("comp1").physics("acpr").create("port4", "Port", 2);

    model.nodeGroup("grp1").add("port4");

    model.component("comp1").physics("acpr").feature("port4").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port4").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port4").set("m_rect", 1);
    model.component("comp1").physics("acpr").feature("port4").set("n_rect", 1);
    model.component("comp1").physics("acpr").create("port5", "Port", 2);

    model.nodeGroup("grp1").add("port5");

    model.component("comp1").physics("acpr").feature("port5").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port5").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port5").set("m_rect", 2);

    model.nodeGroup().create("grp2", "Physics", "acpr");
    model.nodeGroup("grp2").label("\u51fa\u53e3\u7aef\u53e3");

    model.component("comp1").physics("acpr").create("port6", "Port", 2);

    model.nodeGroup("grp2").add("port6");

    model.component("comp1").physics("acpr").feature("port6").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port6").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").create("port7", "Port", 2);

    model.nodeGroup("grp2").add("port7");

    model.component("comp1").physics("acpr").feature("port7").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port7").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port7").set("m_rect", 1);
    model.component("comp1").physics("acpr").create("port8", "Port", 2);

    model.nodeGroup("grp2").add("port8");

    model.component("comp1").physics("acpr").feature("port8").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port8").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port8").set("n_rect", 1);
    model.component("comp1").physics("acpr").create("port9", "Port", 2);

    model.nodeGroup("grp2").add("port9");

    model.component("comp1").physics("acpr").feature("port9").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port9").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port9").set("m_rect", 1);
    model.component("comp1").physics("acpr").feature("port9").set("n_rect", 1);
    model.component("comp1").physics("acpr").create("port10", "Port", 2);

    model.nodeGroup("grp2").add("port10");

    model.component("comp1").physics("acpr").feature("port10").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port10").set("PortType", "Rectangular");
    model.component("comp1").physics("acpr").feature("port10").set("m_rect", 2);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_min/5");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(4, 9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3, 1.03e3, 1.06e3, 1.09e3, 1.12e3, 1.15e3, 1.18e3, 1.22e3, 1.25e3, 1.28e3}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 114, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 114, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 114, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 73, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 73, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 73, 0);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6563\u5c04\u7cfb\u6570");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S11)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S11)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S21)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S21)", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S31)", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S31)", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S41)", 3);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S41)", 3);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S51)", 4);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S51)", 4);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S61)", 5);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S61)", 5);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S71)", 6);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S71)", 6);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S81)", 7);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S81)", 7);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S91)", 8);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S91)", 8);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(acpr.S10_1)", 9);
    model.result("pg4").feature("glob1").setIndex("descr", "abs(S10_1)", 9);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f20\u8f93\u635f\u8017");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4f20\u8f93\u635f\u8017 (dB)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("oct1", "OctaveBand");
    model.result("pg5").feature("oct1").set("quantity", "bandpower");
    model.result("pg5").feature("oct1").set("markerpos", "datapoints");
    model.result("pg5").feature("oct1").set("linewidth", "preference");
    model.result("pg5").feature("oct1").selection().geom("geom1");
    model.result("pg5").feature("oct1")
         .set("expr", "acpr.port1.P_in/(acpr.port6.P_out+acpr.port7.P_out+acpr.port8.P_out+acpr.port9.P_out+acpr.port10.P_out)");
    model.result("pg5").feature("oct1").set("exprtype", "transfer");
    model.result("pg5").feature("oct1").set("quantity", "continuous");
    model.result("pg5").feature("oct1").set("legend", true);
    model.result("pg5").feature("oct1").set("legendmethod", "manual");
    model.result("pg5").feature("oct1").setIndex("legends", "\u4f20\u8f93\u635f\u8017 - \u8fde\u7eed", 0);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("oct2", "oct1");
    model.result("pg5").run();
    model.result("pg5").feature("oct2").set("quantity", "bandaveragepsd");
    model.result("pg5").feature("oct2").set("bandtype", "octave3");
    model.result("pg5").feature("oct2").set("type", "outline");
    model.result("pg5").feature("oct2").setIndex("legends", "\u4f20\u8f93\u635f\u8017 - 1/3 \u500d\u9891\u5e26", 0);
    model.result("pg5").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().set(1);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f52\u4e00\u5316\u632f\u578b");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "acpr.port1.pn");
    model.result("pg6").feature("surf1").set("descr", "\u5f52\u4e00\u5316\u6a21\u5f0f\u538b\u529b");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("expr", "acpr.port2.pn");
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature("surf2").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").feature("trn1").set("move", new String[]{"1.1*W", "0"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").set("expr", "acpr.port3.pn");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").feature("trn1").set("move", new String[]{"0", "-1.1*H"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf4", "surf3");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").set("expr", "acpr.port4.pn");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").feature("trn1").set("move", new String[]{"1.1*W", "-1.1*H"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf5", "surf4");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").set("expr", "acpr.port5.pn");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").feature("trn1").set("move", new String[]{"2.2*W", "0"});
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u622a\u6b62\u9891\u7387");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"acpr.port1.fc"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u6a21\u5f0f\u622a\u6b62\u9891\u7387"});
    model.result().evaluationGroup("eg1").feature("gev1").set("unit", new String[]{"Hz"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6a21\u5f0f (0,0) \u622a\u6b62\u9891\u7387", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "acpr.port2.fc", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6a21\u5f0f (1,0) \u622a\u6b62\u9891\u7387", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "acpr.port3.fc", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6a21\u5f0f (0,1) \u622a\u6b62\u9891\u7387", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "acpr.port4.fc", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6a21\u5f0f (1,1) \u622a\u6b62\u9891\u7387", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "acpr.port5.fc", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6a21\u5f0f (2,0) \u622a\u6b62\u9891\u7387", 4);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5f52\u4e00\u5316\u7aef\u53e3\u51fa\u5c04\u529f\u7387");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("legendpos", "middleleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port1.P_out/acpr.port1.P_in", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7aef\u53e3 1", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port2.P_out/acpr.port1.P_in", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7aef\u53e3 2", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port3.P_out/acpr.port1.P_in", 2);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7aef\u53e3 3", 2);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port4.P_out/acpr.port1.P_in", 3);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7aef\u53e3 4", 3);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port5.P_out/acpr.port1.P_in", 4);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7aef\u53e3 5", 4);
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("expr", "acpr.port6.P_out/acpr.port1.P_in", 0);
    model.result("pg7").feature("glob2").setIndex("descr", "\u7aef\u53e3 6", 0);
    model.result("pg7").feature("glob2").setIndex("expr", "acpr.port7.P_out/acpr.port1.P_in", 1);
    model.result("pg7").feature("glob2").setIndex("descr", "\u7aef\u53e3 7", 1);
    model.result("pg7").feature("glob2").setIndex("expr", "acpr.port8.P_out/acpr.port1.P_in", 2);
    model.result("pg7").feature("glob2").setIndex("descr", "\u7aef\u53e3 8", 2);
    model.result("pg7").feature("glob2").setIndex("expr", "acpr.port9.P_out/acpr.port1.P_in", 3);
    model.result("pg7").feature("glob2").setIndex("descr", "\u7aef\u53e3 9", 3);
    model.result("pg7").feature("glob2").setIndex("expr", "acpr.port10.P_out/acpr.port1.P_in", 4);
    model.result("pg7").feature("glob2").setIndex("descr", "\u7aef\u53e3 10", 4);
    model.result("pg7").feature("glob2").set("linestyle", "dashed");
    model.result("pg7").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg7").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 93, 0);
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").setIndex("looplevel", 111, 0);
    model.result("pg3").setIndex("looplevel", 93, 0);

    model.title("\u5e26\u76f4\u89d2\u5f2f\u5934\u7684\u7ba1\u9053");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790\u5e26\u76f4\u89d2\u5f2f\u5934\u7684\u7ba1\u9053\u6216\u6ce2\u5bfc\u7684\u58f0\u5b66\u7279\u6027\uff0c\u5176\u4e2d\u5728\u5165\u53e3\u548c\u51fa\u53e3\u4f7f\u7528\u4e86\u7aef\u53e3\u8fb9\u754c\u6761\u4ef6\u3002\u7aef\u53e3\u53ef\u4ee5\u6355\u6349\u548c\u5904\u7406\u6ce2\u5bfc\u4e2d\u7684\u975e\u5e73\u9762\u4f20\u64ad\u6a21\u5f0f\uff0c\u5c06\u5206\u6790\u6269\u5c55\u5230\u7b2c\u4e00\u622a\u6b62\u9891\u7387\u4ee5\u4e0a\u3002\u901a\u8fc7\u8ba1\u7b97\u786e\u5b9a\u4e86\u7cfb\u7edf\u7684\u4f20\u8f93\u635f\u8017\u548c\u6563\u5c04\u7cfb\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("duct_right_angled_bend.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
