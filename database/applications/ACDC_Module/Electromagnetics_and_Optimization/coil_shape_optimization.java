/*
 * coil_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class coil_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.component("comp1").geom("geom1").insertFile("coil_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel3");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").set("f0", "1[kHz]");
    model.param("par2").descr("f0", "\u9891\u7387");
    model.param("par2").set("lastTurns", "0.5");
    model.param("par2").descr("lastTurns", "\u5916\u56de\u8def\u7535\u6d41\u56e0\u5b50");
    model.param("par2").set("dmax", "3[cm]");
    model.param("par2").descr("dmax", "\u6700\u5927\u7ebf\u5708\u5e73\u79fb");

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
    model.component("comp1").material("mat2").selection().named("geom1_unisel1_dom");

    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("geom1_copy2_dom");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "1[kA]");
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").selection().named("geom1_difsel3");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "1[kA]*lastTurns");
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").selection().named("geom1_difsel2");
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "-1[kA]*lastTurns");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_adjsel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("geom1_disksel2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("geom1_boxsel4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.004);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common("fsd1").selection().named("geom1_difsel1");
    model.component("comp1").common().create("tsf1", "Transformation");
    model.component("comp1").common("tsf1").selection().named("geom1_unisel1_dom");
    model.component("comp1").common("tsf1").setIndex("move_lock", false, 0);
    model.component("comp1").common("tsf1").setIndex("move_lBound", "-dmax", 0);
    model.component("comp1").common("tsf1").setIndex("move_uBound", "dmax", 0);
    model.component("comp1").common("tsf1").setIndex("move_lock", false, 2);
    model.component("comp1").common("tsf1").setIndex("move_lBound", "-coilSpace/4", 2);
    model.component("comp1").common("tsf1").setIndex("move_uBound", "coilSpace/4", 2);
    model.component("comp1").common("tsf1").set("scalingType", "No_scaling");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u5e73\u5747\u5185\u90e8\u78c1\u573a");
    model.component("comp1").probe("dom1").set("probename", "Bavg");
    model.component("comp1").probe("dom1").selection().named("geom1_r4_dom");
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").label("\u5e73\u5747\u503c\u7684\u5747\u65b9\u5dee");
    model.component("comp1").probe("dom2").set("probename", "Bdev_sq");
    model.component("comp1").probe("dom2").set("expr", "(mf.normB-Bavg)^2");
    model.component("comp1").probe().duplicate("dom3", "dom2");
    model.component("comp1").probe("dom3").label("\u5e73\u5747\u5916\u90e8\u78c1\u573a");
    model.component("comp1").probe("dom3").set("probename", "Bouter");
    model.component("comp1").probe("dom3").selection().named("geom1_r3_dom");
    model.component("comp1").probe("dom3").set("expr", "mf.normB");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("geom1_adjsel1");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "mma");
    model.study("std1").feature("opt").set("keepsolgb", "everynth");
    model.study("std1").feature("opt").set("nskipsols", 1000);
    model.study("std1").feature("opt").set("objectivescaling", "init");
    model.study("std1").feature("opt").set("optobj", new String[]{"comp1.Bdev_sq"});
    model.study("std1").feature("opt").set("descr", new String[]{"\u5e73\u5747\u503c\u7684\u5747\u65b9\u5dee"});
    model.study("std1").feature("opt").set("optobj", new String[]{"comp1.Bdev_sq", "comp1.Bouter"});
    model.study("std1").feature("opt")
         .set("descr", new String[]{"\u5e73\u5747\u503c\u7684\u5747\u65b9\u5dee", "\u5e73\u5747\u5916\u90e8\u78c1\u573a"});
    model.study("std1").feature("opt").setIndex("optobj", "sqrt(comp1.Bdev_sq)", 0);
    model.study("std1").feature("opt").setIndex("descr", "\u57df\u63a2\u9488 2", 0);
    model.study("std1").feature("opt").setIndex("pname", "coilHeight", 0);
    model.study("std1").feature("opt").setIndex("initval", "2[cm]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "coilHeight", 0);
    model.study("std1").feature("opt").setIndex("initval", "2[cm]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "lastTurns", 0);
    model.study("std1").feature("opt").setIndex("lbound", 0, 0);
    model.study("std1").feature("opt").setIndex("ubound", 1, 0);
    model.study("std1").feature("opt").set("probesel", "none");
    model.study("std1").label("\u5f62\u72b6\u4f18\u5316");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result().create("pg3", "PlotGroup2D");
    model.result().dataset().duplicate("dset1g1", "dset1");
    model.result().dataset("dset1g1").label("\u5f62\u72b6\u4f18\u5316/\u89e3 1 (1) - \u51e0\u4f55");
    model.result().dataset("dset1g1").set("frametype", "geometry");
    model.result("pg3").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("frametype", "geometry");
    model.result("pg3").set("edgecolor", "gray");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").create("arwp1", "ArrowPoint");
    model.result("pg3").feature("arwp1").label("\u5e73\u79fb (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp1").set("expr", new String[]{"tsf1.moveRg", "tsf1.moveZg"});
    model.result("pg3").feature("arwp1").set("arrowbase", "head");
    model.result("pg3").feature("arwp1").set("scaleactive", true);
    model.result("pg3").feature("arwp1").set("scale", "1");
    model.result("pg3").feature("arwp1").create("def1", "Deform");
    model.result("pg3").feature("arwp1").feature("def1")
         .set("expr", new String[]{"-tsf1.scaleRg-tsf1.rotateRg", "-tsf1.scaleZg-tsf1.rotateZg"});
    model.result("pg3").feature("arwp1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arwp1").feature("def1").set("scale", "1");
    model.result("pg3").feature("arwp1").create("col1", "Color");
    model.result("pg3").feature("arwp1").feature("col1").set("expr", "tsf1.rel_move");
    model.result("pg3").feature("arwp1").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwp1").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").create("arwp2", "ArrowPoint");
    model.result("pg3").feature("arwp2").label("\u7f29\u653e (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp2").set("expr", new String[]{"tsf1.scaleRg", "tsf1.scaleZg"});
    model.result("pg3").feature("arwp2").set("arrowbase", "head");
    model.result("pg3").feature("arwp2").set("scaleactive", true);
    model.result("pg3").feature("arwp2").set("scale", "1");
    model.result("pg3").feature("arwp2").set("inheritplot", "arwp1");
    model.result("pg3").feature("arwp2").create("def1", "Deform");
    model.result("pg3").feature("arwp2").feature("def1")
         .set("expr", new String[]{"-tsf1.rotateRg", "-tsf1.rotateZg"});
    model.result("pg3").feature("arwp2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arwp2").feature("def1").set("scale", "1");
    model.result("pg3").feature("arwp2").create("col1", "Color");
    model.result("pg3").feature("arwp2").feature("col1").set("expr", "tsf1.rel_scale");
    model.result("pg3").feature("arwp2").feature("col1").set("rangecoloractive", "on");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("arwp2").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp2").feature("col1").set("rangecolormax", 1);
    model.result("pg3").create("arwp3", "ArrowPoint");
    model.result("pg3").feature("arwp3").label("\u65cb\u8f6c (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp3").set("expr", new String[]{"tsf1.rotateRg", "tsf1.rotateZg"});
    model.result("pg3").feature("arwp3").set("arrowbase", "head");
    model.result("pg3").feature("arwp3").set("scaleactive", true);
    model.result("pg3").feature("arwp3").set("scale", "1");
    model.result("pg3").feature("arwp3").set("inheritplot", "arwp1");
    model.result("pg3").feature("arwp3").create("col1", "Color");
    model.result("pg3").feature("arwp3").feature("col1").set("expr", "tsf1.rel_rotate");
    model.result("pg3").feature("arwp3").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwp3").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp3").feature("col1").set("rangecolormax", 1);
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("text", "eval(lastTurns)");
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").feature("ann1").set("backgroundcolor", "gray");

    model.study("std1").feature("opt").set("plot", true);
    model.study("std1").feature("opt").set("plotgroup", "pg3");

    model.sol("sol1").feature("o1").set("movelimitactive", true);
    model.sol("sol1").feature("o1").set("mmamaxiteractive", true);
    model.sol("sol1").feature("o1").set("mmamaxiter", 50);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u4e0a\u7684\u573a");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("geom1_boxsel4");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "z");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u521d\u59cb\u8bbe\u8ba1", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("data", "dset1");
    model.result("pg4").feature("lngr2").setIndex("looplevelinput", "last", 1);
    model.result("pg4").feature("lngr2").setIndex("legends", "\u4f18\u5316\u8bbe\u8ba1", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr3").set("linewidth", "preference");
    model.result("pg4").feature("lngr3").selection().named("geom1_boxsel3");
    model.result("pg4").feature("lngr3").set("xdata", "expr");
    model.result("pg4").feature("lngr3").set("xdataexpr", "z");
    model.result("pg4").feature("lngr3").set("linecolor", "cyclereset");
    model.result("pg4").feature("lngr3").set("linewidth", 3);
    model.result("pg4").feature().duplicate("lngr4", "lngr3");
    model.result("pg4").run();
    model.result("pg4").feature("lngr4").set("data", "dset1");
    model.result("pg4").feature("lngr4").setIndex("looplevelinput", "last", 1);
    model.result("pg4").feature("lngr4").set("linecolor", "cycle");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("lngr5", "lngr3");
    model.result("pg4").feature().duplicate("lngr6", "lngr4");
    model.result("pg4").run();
    model.result("pg4").feature("lngr5").selection().named("geom1_boxsel1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr6").selection().named("geom1_boxsel1");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"Bdev_sq"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u5e73\u5747\u503c\u7684\u5747\u65b9\u5dee"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"Bdev_sq", "Bouter"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u5e73\u5747\u503c\u7684\u5747\u65b9\u5dee", "\u5e73\u5747\u5916\u90e8\u78c1\u573a"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "sqrt(Bdev_sq)", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u70b9\u4f4d\u79fb");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg2").feature("pev1").selection().named("geom1_disksel1");
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "r", 0);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "z", 1);
    model.result().evaluationGroup("eg2").run();

    model.title("\u7ebf\u5708\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u4f18\u5316\u6a21\u5757\u201d\u6765\u67e5\u627e\u7ebf\u5708\u51e0\u4f55\uff0c\u4ece\u800c\u5728\u8f74\u4e0a\u4ea7\u751f\u5747\u5300\u78c1\u573a\uff0c\u5e76\u5728\u8f74\u7aef\u9644\u8fd1\u4ea7\u751f\u6700\u5c0f\u78c1\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("coil_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
