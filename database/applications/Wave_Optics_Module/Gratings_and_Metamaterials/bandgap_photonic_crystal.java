/*
 * bandgap_photonic_crystal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:37 by COMSOL 6.3.0.290. */
public class bandgap_photonic_crystal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd", true);

    model.param().set("a", "375[nm]");
    model.param().descr("a", "\u539f\u80de\u8fb9\u957f");
    model.param().set("b", "70[nm]");
    model.param().descr("b", "\u7837\u5316\u9553\u6676\u67f1\u534a\u5f84");
    model.param().set("k", "0");
    model.param().descr("k", "\u6ce2\u77e2\u5e45\u503c\u5206\u6570");
    model.param().set("k1", "1");
    model.param().descr("k1", "\u6ce2\u65b9\u5411\u77e2\u91cf\u7684\u7b2c\u4e00\u4e2a\u5206\u91cf");
    model.param().set("k2", "1");
    model.param().descr("k2", "\u6ce2\u65b9\u5411\u77e2\u91cf\u7684\u7b2c\u4e8c\u4e2a\u5206\u91cf");
    model.param().set("a1x", "a");
    model.param().descr("a1x", "\u7b2c\u4e00\u6676\u683c\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("a1y", "0[nm]");
    model.param().descr("a1y", "\u7b2c\u4e00\u6676\u683c\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("a2x", "0[nm]");
    model.param().descr("a2x", "\u7b2c\u4e8c\u6676\u683c\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param().set("a2y", "a");
    model.param().descr("a2y", "\u7b2c\u4e8c\u6676\u683c\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param().set("band", "1");
    model.param().descr("band", "\u9891\u5e26\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("b1x", "2*pi*a2y/(a1x*a2y-a1y*a2x)");
    model.component("comp1").variable("var1")
         .descr("b1x", "\u7b2c\u4e00\u5012\u6613\u6676\u683c\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("b1y", "-2*pi*a2x/(a1x*a2y-a1y*a2x)");
    model.component("comp1").variable("var1")
         .descr("b1y", "\u7b2c\u4e00\u5012\u6613\u6676\u683c\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("b2x", "-2*pi*a1y/(a1x*a2y-a1y*a2x)");
    model.component("comp1").variable("var1")
         .descr("b2x", "\u7b2c\u4e8c\u5012\u6613\u6676\u683c\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("b2y", "2*pi*a1x/(a1x*a2y-a1y*a2x)");
    model.component("comp1").variable("var1")
         .descr("b2y", "\u7b2c\u4e8c\u5012\u6613\u6676\u683c\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("kx", "k*(k1*b1x+k2*b2x)");
    model.component("comp1").variable("var1").descr("kx", "Floquet \u77e2\u91cf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("ky", "k*(k1*b1y+k2*b2y)");
    model.component("comp1").variable("var1").descr("ky", "Floquet \u77e2\u91cf\uff0cy \u5206\u91cf");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "a");
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "b");
    model.component("comp1").geom("geom1").runPre("fin");
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

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "n_GaAs");
    model.component("comp1").func("an1").set("expr", "-3.3285e5[1/m]*c_const/f+3.8359");
    model.component("comp1").func("an1").set("args", "f");
    model.component("comp1").func("an1").setIndex("argunit", "Hz", 0);
    model.component("comp1").func("an1").set("fununit", "1");

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 4);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("ewfd").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc2").selection().set(2, 3);
    model.component("comp1").physics("ewfd").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc2").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("ewfd").create("wee2", "WaveEquationElectric", 2);
    model.component("comp1").physics("ewfd").feature("wee2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("wee2").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("wee2")
         .set("n", new String[]{"n_GaAs(freq)", "0", "0", "0", "n_GaAs(freq)", "0", "0", "0", "n_GaAs(freq)"});
    model.component("comp1").physics("ewfd").feature("wee2").set("ki_mat", "userdef");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(4);
    model.component("comp1").mesh("mesh1").create("cpe2", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").set(2);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").set(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 5);
    model.study("std1").feature("eig").set("shift", "550[THz]");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("eigref", "550[THz]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7279\u5f81\u9891\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.freq", "ewfd.Qfactor"});
    model.result().numerical("gev1").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/ewfd2", true);

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "freq1", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "1[V^2/m^2]-nEz", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "-imag(lambda)/(2*pi)", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u9891\u7387", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "frequency");
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "V^2/m^2", 0, 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq", "freq1", 0);
    model.component("comp1").physics("ewfd2").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd2").feature("init1").set("E2", new String[]{"0", "0", "ewfd.Ez"});
    model.component("comp1").physics("ewfd2").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd2").feature("pc1").selection().set(1, 4);
    model.component("comp1").physics("ewfd2").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd2").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("ewfd2").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd2").feature("pc2").selection().set(2, 3);
    model.component("comp1").physics("ewfd2").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd2").feature("pc2").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("ewfd2").create("wee2", "WaveEquationElectric", 2);
    model.component("comp1").physics("ewfd2").feature("wee2").selection().set(2);
    model.component("comp1").physics("ewfd2").feature("wee2").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd2").feature("wee2")
         .set("n", new String[]{"n_GaAs(freq1)", "0", "0", "0", "n_GaAs(freq1)", "0", "0", "0", "n_GaAs(freq1)"});
    model.component("comp1").physics("ewfd2").feature("wee2").set("ki_mat", "userdef");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1, 2);

    model.component("comp1").variable("var1").set("A", "intop1(1)");
    model.component("comp1").variable("var1").descr("A", "\u9762\u79ef");
    model.component("comp1").variable("var1").set("nEz", "intop1(realdot(ewfd2.Ez,ewfd2.Ez))/A");
    model.component("comp1").variable("var1").descr("nEz", "\u5f52\u4e00\u5316\u79ef\u5206");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ewfd2", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", "manual");
    model.study("std2").feature("stat").set("manualsolnum", "band");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "k", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.01,0.5)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("st1").set("splitcomplex", true);

    model.component("comp1").physics("ge").feature("ge1").set("valueType", "real");

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "band", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(1,1,5)", 0);

    model.sol("sol2").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol2").feature("s1").feature("p1").set("pinitstep", "0.0001");
    model.sol("sol2").feature("s1").feature("p1").set("pminstep", "0.0001");
    model.sol("sol2").feature("s1").feature("p1").set("pmaxstep", 0.01);
    model.sol("sol2").feature("s1").feature("p1").set("porder", "constant");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").set("expr", new String[]{"freq1"});
    model.result().numerical("gev2").set("descr", new String[]{"\u9891\u7387"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd2)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").setIndex("looplevel", 5, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ewfd2.normE");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").set("phasetype", "manual");
    model.result("pg2").set("phase", 180);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd2.Ez");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "band=1", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "band=2", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "band=3", 2);
    model.result("pg3").feature("glob1").setIndex("legends", "band=4", 3);
    model.result("pg3").feature("glob1").setIndex("legends", "band=5", 4);
    model.result("pg3").run();
    model.result("pg1").run();

    model.study("std1").feature("eig").setSolveFor("/physics/ge", false);
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd2", false);

    model.title("\u5149\u5b50\u6676\u4f53\u7684\u5e26\u9699\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u7814\u7a76\u7531\u7b49\u8ddd\u653e\u7f6e\u7684\u7837\u5316\u9553\u6676\u67f1\u6784\u6210\u7684\u5149\u5b50\u6676\u4f53\u4e2d\u6ce2\u7684\u4f20\u64ad\uff0c\u5176\u4e2d\u7684\u5149\u5b50\u6676\u4f53\u7ed3\u6784\u4e0e\u53e6\u4e00\u4e2a\u5149\u5b50\u6676\u4f53\u6a21\u578b\u4e2d\u4f7f\u7528\u7684\u7ed3\u6784\u76f8\u540c\uff0c\u4f46\u672c\u4f8b\u751f\u6210\u4e86\u6676\u4f53\u6700\u4f4e\u9891\u5e26\u7684\u80fd\u5e26\u56fe\u3002\u672c\u4f8b\u4f7f\u7528\u975e\u7ebf\u6027\u6c42\u89e3\u5668\u5e76\u7ed3\u5408\u989d\u5916\u7684\u5f52\u4e00\u5316\u65b9\u7a0b\u6c42\u89e3\u7279\u5f81\u503c\uff0c\u901a\u8fc7\u975e\u7ebf\u6027\u626b\u63cf\u6765\u6267\u884c\u7279\u5f81\u503c\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("bandgap_photonic_crystal.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
