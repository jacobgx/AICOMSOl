/*
 * impedance_tube_parameter_estimation_data.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:41 by COMSOL 6.3.0.290. */
public class impedance_tube_parameter_estimation_data {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Parameter_Estimation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("L1", "30[cm]");
    model.param().descr("L1", "\u7ba1\u957f");
    model.param().set("D1", "10[cm]");
    model.param().descr("D1", "\u7ba1\u76f4\u5f84");
    model.param().set("L2", "3[cm]");
    model.param().descr("L2", "\u591a\u5b54\u533a\u57df\u957f\u5ea6");
    model.param().set("L3", "2[cm]");
    model.param().descr("L3", "\u9ea6\u514b\u98ce\u95f4\u8ddd");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u53c2\u6570");
    model.param("par2").set("Rf0", "87[kN*s/m^4]");
    model.param("par2").descr("Rf0", "\u6d41\u963b\u7387");
    model.param("par2").set("Lth0", "119[um]");
    model.param("par2").descr("Lth0", "\u70ed\u7279\u5f81\u957f\u5ea6");
    model.param("par2").set("Lv0", "37[um]");
    model.param("par2").descr("Lv0", "\u9ecf\u6ede\u7279\u5f81\u957f\u5ea6");
    model.param("par2").set("tau0", "2.52");
    model.param("par2").descr("tau0", "\u66f2\u6298\u56e0\u5b50");
    model.param("par2").set("epsilon0", "0.97");
    model.param("par2").descr("epsilon0", "\u5b54\u9699\u7387");
    model.param().create("par3");
    model.param("par3").label("\u6750\u6599\u53c2\u6570\uff08\u521d\u59cb\u731c\u6d4b\u503c\uff09");
    model.param("par3").set("Rf1", "100[kN*s/m^4]");
    model.param("par3").descr("Rf1", "\u6d41\u963b\u7387");
    model.param("par3").set("Lth1", "100[um]");
    model.param("par3").descr("Lth1", "\u70ed\u7279\u5f81\u957f\u5ea6");
    model.param("par3").set("Lv1", "100[um]");
    model.param("par3").descr("Lv1", "\u9ecf\u6ede\u7279\u5f81\u957f\u5ea6");
    model.param("par3").set("tau1", "1");
    model.param("par3").descr("tau1", "\u66f2\u6298\u56e0\u5b50");
    model.param("par3").set("epsilon1", "0.5");
    model.param("par3").descr("epsilon1", "\u5b54\u9699\u7387");
    model.param().create("par4");
    model.param("par4").set("error", "5e-3");
    model.param("par4").descr("error", "\u76f8\u5bf9\u6d4b\u91cf\u8bef\u5dee");
    model.param("par4").set("fmin", "500[Hz]");
    model.param("par4").descr("fmin", "\u6700\u5c0f\u9891\u7387");
    model.param("par4").set("fmax", "4[kHz]");
    model.param("par4").descr("fmax", "\u6700\u5927\u9891\u7387");
    model.param("par4").set("meshsz", "340[m/s]/fmax/12");
    model.param("par4").descr("meshsz", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"D1/2", "L1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "L2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D1/2", "L2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "D1/2", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L2+L3", 1);
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "D1/2", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "L2+2*L3", 1);
    model.component("comp1").geom("geom1").feature("pt2").set("selresult", true);
    model.component("comp1").geom("geom1").run("pt2");

    model.func().create("rn1", "Random");
    model.func("rn1").set("type", "normal");

    model.component("comp1").probe().create("point1", "Point");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").probe("point1").label("\u9ea6\u514b\u98ce 1 (real)");
    model.component("comp1").probe("point1").set("probename", "mic1_real");
    model.component("comp1").probe("point1").selection().named("geom1_pt1_pnt");
    model.component("comp1").probe("point1").set("expr", "real(acpr.p_t)");
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").label("\u9ea6\u514b\u98ce 1 (imag)");
    model.component("comp1").probe("point2").set("probename", "mic1_imag");
    model.component("comp1").probe("point2").set("expr", "imag(acpr.p_t)");
    model.component("comp1").probe().duplicate("point3", "point1");
    model.component("comp1").probe("point3").label("\u9ea6\u514b\u98ce 2 (real)");
    model.component("comp1").probe("point3").set("probename", "mic2_real");
    model.component("comp1").probe("point3").selection().named("geom1_pt2_pnt");
    model.component("comp1").probe().duplicate("point4", "point3");
    model.component("comp1").probe("point4").label("\u9ea6\u514b\u98ce 2 (imag)");
    model.component("comp1").probe("point4").set("probename", "mic2_imag");
    model.component("comp1").probe("point4").set("expr", "imag(acpr.p_t)");

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
    model.component("comp1").material().create("mat2", "Common");

    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("pom1").selection().set(1);
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom1").set("SolidMaterial", "mat2");
    model.component("comp1").physics("acpr").create("nacc1", "NormalAcceleration", 1);
    model.component("comp1").physics("acpr").feature("nacc1").selection().set(5);
    model.component("comp1").physics("acpr").feature("nacc1").set("nacc", 1);

    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"epsilon0"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Lv", new String[]{"Lv0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Lth", new String[]{"Lth0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("tau", new String[]{"tau0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Rf", new String[]{"Rf0"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u751f\u6210\u5b9e\u9a8c\u6570\u636e");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "D1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "D1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "meshsz", 0);
    model.study("std1").feature("param").setIndex("plistarr", "7 3.5", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").feature("param").set("probesel", "none");
    model.study("std1").feature("freq").set("probesel", "none");
    model.study("std1").feature("freq")
         .set("plist", "{500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3, 1.03e3, 1.06e3, 1.09e3, 1.12e3, 1.15e3, 1.18e3, 1.22e3, 1.25e3, 1.28e3, 1.32e3, 1.36e3, 1.4e3, 1.45e3, 1.5e3, 1.55e3, 1.6e3, 1.65e3, 1.7e3, 1.75e3, 1.8e3, 1.85e3, 1.9e3, 1.95e3, 2e3, 2.06e3, 2.12e3, 2.18e3, 2.24e3, 2.3e3, 2.36e3, 2.43e3, 2.5e3, 2.58e3, 2.65e3, 2.72e3, 2.8e3, 2.9e3, 3e3, 3.07e3, 3.15e3, 3.25e3, 3.35e3, 3.45e3, 3.55e3, 3.65e3, 3.75e3, 3.87e3, 4e3}");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 73, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 73, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 73, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 73, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u5b9e\u9a8c\u6570\u636e");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mic1_real*(1+error*rn1(freq+0))", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u4f20\u611f\u5668 1\uff0c\u5b9e\u90e8", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mic1_imag*(1+error*rn1(freq+1))", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u4f20\u611f\u5668 1\uff0c\u865a\u90e8", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mic2_real*(1+error*rn1(2*freq+0))", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u4f20\u611f\u5668 2\uff0c\u5b9e\u90e8", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mic2_imag*(1+error*rn1(2*freq+1))", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u4f20\u611f\u5668 2\uff0c\u865a\u90e8", 3);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "first", 1);
    model.result().evaluationGroup("eg2").label("\u76f8\u5bf9\u8bef\u5dee\uff08\u7f51\u683c\uff09");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("expr", new String[]{"(sqrt(mic1_real^2+mic1_imag^2)-withsol('sol1',sqrt(mic1_real^2+mic1_imag^2),setval(freq,freq)))/sqrt(mic1_real^2+mic1_imag^2)", "(sqrt(mic2_real^2+mic2_imag^2)-withsol('sol1',sqrt(mic2_real^2+mic2_imag^2),setval(freq,freq)))/sqrt(mic2_real^2+mic2_imag^2)"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u4f20\u611f\u5668 1\uff0c\u5e45\u5ea6", "\u4f20\u611f\u5668 2\uff0c\u5e45\u5ea6"});
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg5", "PlotGroup1D");

    model.nodeGroup("grp1").add("plotgroup", "pg5");

    model.result("pg5").run();
    model.result("pg5").label("\u8bef\u5dee");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg5").feature("tblp1").set("xaxisdata", 2);
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{3, 4});
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result().export().create("tbl1", "Table");
    model.result().export("tbl1").set("source", "evaluationgroup");
    model.result().export("tbl1").set("filename", "impedance_tube_parameter_estimation_data.csv");
    model.result().export("tbl1").set("header", false);
    model.result().export("tbl1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").label("\u53c2\u6570\u4f30\u8ba1");
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").set("filename", "impedance_tube_parameter_estimation_data.csv");
    model.study("std2").feature("lsqo").importData();
    model.study("std2").feature("lsqo").setEntry("columnType", "col1", "freq");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col2", "comp1.mic1_real");
    model.study("std2").feature("lsqo").setEntry("variableName", "col2", "pressure1");
    model.study("std2").feature("lsqo").setEntry("unit", "col2", "Pa");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col3", "comp1.mic1_imag");
    model.study("std2").feature("lsqo").setEntry("variableName", "col3", "pressure2");
    model.study("std2").feature("lsqo").setEntry("unit", "col3", "Pa");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col4", "comp1.mic2_real");
    model.study("std2").feature("lsqo").setEntry("variableName", "col4", "pressure3");
    model.study("std2").feature("lsqo").setEntry("unit", "col4", "Pa");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col5", "comp1.mic2_imag");
    model.study("std2").feature("lsqo").setEntry("variableName", "col5", "pressure4");
    model.study("std2").feature("lsqo").setEntry("unit", "col5", "Pa");
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("lsqo").set("probesel", "none");
    model.study("std2").feature("lsqo").setIndex("pname", "D1", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "10[cm]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "D1", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "10[cm]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "epsilon0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 0.97, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "epsilon0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 0.97, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "epsilon1", 2);
    model.study("std2").feature("lsqo").setIndex("initval", 0.5, 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "epsilon1", 2);
    model.study("std2").feature("lsqo").setIndex("initval", 0.5, 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "error", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "5e-3", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "error", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "5e-3", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "fmax", 4);
    model.study("std2").feature("lsqo").setIndex("initval", "4[kHz]", 4);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 4);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("lsqo").setIndex("pname", "fmax", 4);
    model.study("std2").feature("lsqo").setIndex("initval", "4[kHz]", 4);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 4);
    model.study("std2").feature("lsqo").setIndex("pname", "", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "epsilon1", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "Lth0", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "epsilon0", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "Lth1", 1);
    model.study("std2").feature("lsqo").setIndex("scale", "100[um]", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "Lv0", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "Lv1", 2);
    model.study("std2").feature("lsqo").setIndex("scale", "100[um]", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "Rf0", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "Rf1", 3);
    model.study("std2").feature("lsqo").setIndex("scale", "100[kN*s/m^4]", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "tau0", 4);
    model.study("std2").feature("lsqo").setIndex("initval", "tau1", 4);
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("st1").set("splitcomplex", true);
    model.sol("sol5").feature("o1").set("gradientlm", "adjoint");

    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 73, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg6").feature("surf1").set("colortable", "Wave");
    model.result("pg6").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b (acpr) 1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 73, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev2");
    model.result("pg8").setIndex("looplevel", 73, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg8").feature("surf1").set("colortable", "Wave");
    model.result("pg8").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b, 3D (acpr) 1");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev2");
    model.result("pg9").setIndex("looplevel", 73, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u58f0\u538b\u7ea7, 3D (acpr) 1");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u53c2\u6570\u4f30\u8ba1");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u9891\u7387");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg10").feature("glob1").set("expr", new String[]{"opt.glsobj.pressure1.model"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg10").feature("glob1").set("xdata", "expr");
    model.result("pg10").feature("glob1").set("xdataexpr", "freq");
    model.result("pg10").create("glob2", "Global");
    model.result("pg10").feature("glob2").label("\u5217 3 (\u6a21\u578b)");
    model.result("pg10").feature("glob2").set("expr", new String[]{"opt.glsobj.pressure2.model"});
    model.result("pg10").feature("glob2").set("descr", new String[]{"\u5217 3 (\u6a21\u578b)"});
    model.result("pg10").feature("glob2").set("xdata", "expr");
    model.result("pg10").feature("glob2").set("xdataexpr", "freq");
    model.result("pg10").create("glob3", "Global");
    model.result("pg10").feature("glob3").label("\u5217 4 (\u6a21\u578b)");
    model.result("pg10").feature("glob3").set("expr", new String[]{"opt.glsobj.pressure3.model"});
    model.result("pg10").feature("glob3").set("descr", new String[]{"\u5217 4 (\u6a21\u578b)"});
    model.result("pg10").feature("glob3").set("xdata", "expr");
    model.result("pg10").feature("glob3").set("xdataexpr", "freq");
    model.result("pg10").create("glob4", "Global");
    model.result("pg10").feature("glob4").label("\u5217 5 (\u6a21\u578b)");
    model.result("pg10").feature("glob4").set("expr", new String[]{"opt.glsobj.pressure4.model"});
    model.result("pg10").feature("glob4").set("descr", new String[]{"\u5217 5 (\u6a21\u578b)"});
    model.result("pg10").feature("glob4").set("xdata", "expr");
    model.result("pg10").feature("glob4").set("xdataexpr", "freq");
    model.result("pg10").create("glob5", "Global");
    model.result("pg10").feature("glob5").label("\u5217 2 (\u6570\u636e)");
    model.result("pg10").feature("glob5").set("expr", new String[]{"opt.glsobj.pressure1.data"});
    model.result("pg10").feature("glob5").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg10").feature("glob5").set("xdata", "expr");
    model.result("pg10").feature("glob5").set("xdataexpr", "freq");
    model.result("pg10").feature("glob5").set("linestyle", "none");
    model.result("pg10").feature("glob5").set("linemarker", "point");
    model.result("pg10").feature("glob5").set("markerpos", "datapoints");
    model.result("pg10").feature("glob5").set("linecolor", "cyclereset");
    model.result("pg10").create("glob6", "Global");
    model.result("pg10").feature("glob6").label("\u5217 3 (\u6570\u636e)");
    model.result("pg10").feature("glob6").set("expr", new String[]{"opt.glsobj.pressure2.data"});
    model.result("pg10").feature("glob6").set("descr", new String[]{"\u5217 3 (\u6570\u636e)"});
    model.result("pg10").feature("glob6").set("xdata", "expr");
    model.result("pg10").feature("glob6").set("xdataexpr", "freq");
    model.result("pg10").feature("glob6").set("linestyle", "none");
    model.result("pg10").feature("glob6").set("linemarker", "point");
    model.result("pg10").feature("glob6").set("markerpos", "datapoints");
    model.result("pg10").create("glob7", "Global");
    model.result("pg10").feature("glob7").label("\u5217 4 (\u6570\u636e)");
    model.result("pg10").feature("glob7").set("expr", new String[]{"opt.glsobj.pressure3.data"});
    model.result("pg10").feature("glob7").set("descr", new String[]{"\u5217 4 (\u6570\u636e)"});
    model.result("pg10").feature("glob7").set("xdata", "expr");
    model.result("pg10").feature("glob7").set("xdataexpr", "freq");
    model.result("pg10").feature("glob7").set("linestyle", "none");
    model.result("pg10").feature("glob7").set("linemarker", "point");
    model.result("pg10").feature("glob7").set("markerpos", "datapoints");
    model.result("pg10").create("glob8", "Global");
    model.result("pg10").feature("glob8").label("\u5217 5 (\u6570\u636e)");
    model.result("pg10").feature("glob8").set("expr", new String[]{"opt.glsobj.pressure4.data"});
    model.result("pg10").feature("glob8").set("descr", new String[]{"\u5217 5 (\u6570\u636e)"});
    model.result("pg10").feature("glob8").set("xdata", "expr");
    model.result("pg10").feature("glob8").set("xdataexpr", "freq");
    model.result("pg10").feature("glob8").set("linestyle", "none");
    model.result("pg10").feature("glob8").set("linemarker", "point");
    model.result("pg10").feature("glob8").set("markerpos", "datapoints");
    model.result("pg6").run();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").label("\u53c2\u6570\u4f30\u8ba1");

    model.result("pg10").run();
    model.result("pg10").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u76f8\u5bf9\u8bef\u5dee");
    model.result().evaluationGroup("eg3").set("data", "dset3");
    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "abs(Rf0-withsol('sol1',Rf0))/withsol('sol1',Rf0)", 0);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "abs(Lth0-withsol('sol1',Lth0))/withsol('sol1',Lth0)", 1);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "abs(Lv0-withsol('sol1',Lv0))/withsol('sol1',Lv0)", 2);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "abs(tau0-withsol('sol1',tau0))/withsol('sol1',tau0)", 3);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("unit", 1, 3);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "abs(epsilon0-withsol('sol1',epsilon0))/withsol('sol1',epsilon0)", 4);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("unit", 1, 4);
    model.result().evaluationGroup("eg3").run();

    model.title("\u963b\u6297\u7ba1\u53c2\u6570\u4f30\u8ba1\u548c\u6570\u636e\u751f\u6210");

    model
         .description("\u963b\u6297\u7ba1\u7528\u4e8e\u4f30\u8ba1\u5404\u79cd\u6837\u54c1\u7684\u8868\u9762\u963b\u6297\uff0c\u4f8b\u5982\u7528\u4e8e\u9694\u97f3\u7684\u591a\u5b54\u6750\u6599\u5c42\u7b49\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6839\u636e\u4e24\u4e2a\u6d4b\u91cf\u9ea6\u514b\u98ce\u7684\u538b\u529b\u6765\u4f30\u8ba1 Johnson-Champoux-Allard \u6a21\u578b\u7684\u4e94\u4e2a\u6750\u6599\u53c2\u6570\u3002\u5176\u4e2d\u5305\u542b\u4e00\u4e2a\u521d\u59cb\u7814\u7a76\uff0c\u53ef\u7528\u4e8e\u751f\u6210\u7efc\u5408\u6d4b\u91cf\u6570\u636e\u3002\u5728\u5bfc\u51fa\u6570\u636e\u4e4b\u524d\uff0c\u6211\u4eec\u5728\u5176\u4e2d\u6dfb\u52a0\u4e86\u566a\u58f0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("impedance_tube_parameter_estimation_data.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
