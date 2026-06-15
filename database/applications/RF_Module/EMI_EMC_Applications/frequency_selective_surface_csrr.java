/*
 * frequency_selective_surface_csrr.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:32 by COMSOL 6.3.0.290. */
public class frequency_selective_surface_csrr {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\EMI_EMC_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").set("plist", "range(3.8[GHz],0.1[GHz],5.4[GHz])");

    model.param().set("theta", "0[deg]");
    model.param().descr("theta", "\u4ef0\u89d2");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{15, 15, 45});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 3.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{4, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{15, 15, 2});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 0, -1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(9);
    model.component("comp1").physics("emw").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("emw").feature("pc1").selection().set(1, 4, 7, 15, 16, 17);
    model.component("comp1").physics("emw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("emw").feature("pc1").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("emw").create("pc2", "PeriodicCondition", 2);
    model.component("comp1").physics("emw").feature("pc2").selection().set(2, 5, 8, 11, 12, 13);
    model.component("comp1").physics("emw").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("emw").feature("pc2").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(10);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("emw").feature("port1").set("InputType", "H");
    model.component("comp1").physics("emw").feature("port1").set("Hampl", new int[]{0, 1, 0});
    model.component("comp1").physics("emw").feature("port1").set("alpha1_inc", "theta");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(3);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("emw").feature("port2").set("InputType", "H");
    model.component("comp1").physics("emw").feature("port2").set("Hampl", new int[]{0, 1, 0});

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
    model.component("comp1").material("mat2").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(7, 8);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(9);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 15, 16, 17);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(9);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(1, 2, 4, 5, 7, 8, 11, 12, 13, 15, 16, 17);
    model.result("pg4").feature("surf2").set("colortable", "Prism");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.7);
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().set(6, 14);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.3);
    model.result("pg4").create("surf4", "Surface");
    model.result("pg4").feature("surf4").set("expr", "emw.normE");
    model.result("pg4").feature("surf4").create("sel1", "Selection");
    model.result("pg4").feature("surf4").feature("sel1").selection().set(3, 10);
    model.result("pg4").feature("surf4").set("colortable", "Dipole");
    model.result("pg4").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf4").create("tran1", "Transparency");
    model.result("pg4").feature("surf4").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-58.42527970023777, -77.90038399074389, 58.42527970023777});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 51.18896484375);

    model.result("pg4").set("view", "view3");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u6781\u5316\u56fe (emw)");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", "1", 0);
    model.result("pg5").create("plz1", "Polarization");
    model.result("pg5").feature("plz1").set("linestyle", "solid");
    model.result("pg5").feature("plz1").set("linewidth", 2);
    model.result("pg5").feature("plz1").set("display", "0");
    model.result("pg5").feature("plz1").create("col1", "Color");
    model.result("pg5").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg5").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg5").feature("plz1").set("legend", true);
    model.result("pg5").feature("plz1").set("legendmethod", "manual");
    model.result("pg5").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg5").create("plz2", "Polarization");
    model.result("pg5").feature("plz2").set("linestyle", "dashed");
    model.result("pg5").feature("plz2").set("linewidth", 2);
    model.result("pg5").feature("plz2").set("display", "1");
    model.result("pg5").feature("plz2").create("col1", "Color");
    model.result("pg5").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg5").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("plz2").set("legend", true);
    model.result("pg5").feature("plz2").set("legendmethod", "manual");
    model.result("pg5").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", -1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "theta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("pname", "theta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0[deg],5[deg],85[deg])", 0);
    model.study("std2").feature("freq").set("plist", "4.6[GHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 18, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg6").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg7").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg7").label("S \u53c2\u6570 (emw) 1");
    model.result("pg7").feature("glob1").set("titletype", "none");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg7").feature("glob1").set("xdataexpr", "theta");
    model.result("pg7").feature("glob1").set("xdataunit", "rad");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg8", "SmithGroup");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("rgr1", "ReflectionGraph");
    model.result("pg8").feature("rgr1").set("unit", new String[]{""});
    model.result("pg8").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg8").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg8").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg8").feature("rgr1").set("titletype", "manual");
    model.result("pg8").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg8").feature("rgr1").set("linemarker", "point");
    model.result("pg8").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("rgr1").create("col1", "Color");
    model.result("pg8").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg8").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(9);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 15, 16, 17);

    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "emw.normE");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(9);
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg9").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg9").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("expr", "emw.normE");
    model.result("pg9").feature("surf2").create("sel1", "Selection");
    model.result("pg9").feature("surf2").feature("sel1").selection().set(1, 2, 4, 5, 7, 8, 11, 12, 13, 15, 16, 17);
    model.result("pg9").feature("surf2").set("colortable", "Prism");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf2").create("tran1", "Transparency");
    model.result("pg9").feature("surf2").feature("tran1").set("transparency", 0.7);
    model.result("pg9").create("surf3", "Surface");
    model.result("pg9").feature("surf3").set("expr", "emw.normE");
    model.result("pg9").feature("surf3").create("sel1", "Selection");
    model.result("pg9").feature("surf3").feature("sel1").selection().set(6, 14);
    model.result("pg9").feature("surf3").set("colortable", "Dipole");
    model.result("pg9").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf3").create("tran1", "Transparency");
    model.result("pg9").feature("surf3").feature("tran1").set("transparency", 0.3);
    model.result("pg9").create("surf4", "Surface");
    model.result("pg9").feature("surf4").set("expr", "emw.normE");
    model.result("pg9").feature("surf4").create("sel1", "Selection");
    model.result("pg9").feature("surf4").feature("sel1").selection().set(3, 10);
    model.result("pg9").feature("surf4").set("colortable", "Dipole");
    model.result("pg9").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf4").create("tran1", "Transparency");
    model.result("pg9").feature("surf4").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-58.42527970023777, -77.90038399074389, 58.42527970023777});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 12.926417589187622);

    model.result("pg9").set("view", "view4");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u6781\u5316\u56fe (emw) 1");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", "1", 0);
    model.result("pg10").create("plz1", "Polarization");
    model.result("pg10").feature("plz1").set("linestyle", "solid");
    model.result("pg10").feature("plz1").set("linewidth", 2);
    model.result("pg10").feature("plz1").set("display", "0");
    model.result("pg10").feature("plz1").create("col1", "Color");
    model.result("pg10").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg10").feature("plz1").set("legend", true);
    model.result("pg10").feature("plz1").set("legendmethod", "manual");
    model.result("pg10").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg10").create("plz2", "Polarization");
    model.result("pg10").feature("plz2").set("linestyle", "dashed");
    model.result("pg10").feature("plz2").set("linewidth", 2);
    model.result("pg10").feature("plz2").set("display", "1");
    model.result("pg10").feature("plz2").create("col1", "Color");
    model.result("pg10").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("plz2").set("legend", true);
    model.result("pg10").feature("plz2").set("legendmethod", "manual");
    model.result("pg10").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("xnumber", "0");
    model.result("pg6").feature("mslc1").set("ynumber", "0");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", -1);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("rgr1").feature("col1").set("expr", "theta");
    model.result("pg8").feature("rgr1").feature("col1").set("unit", "\u00b0");
    model.result("pg8").run();
    model.result("pg8").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe\uff1aS \u53c2\u6570\uff0c\u989c\u8272\uff1a\u4ef0\u89d2\uff08\u5ea6\uff09");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(10);

    model.component("comp1").physics("emw").feature("port1").selection().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(3);

    model.component("comp1").physics("emw").feature("port2").selection().named("sel2");

    model.study().create("std3");
    model.study("std3").create("frawe", "FrequencyAdaptive");
    model.study("std3").feature("frawe").set("plotgroup", "Default");
    model.study("std3").feature("frawe").set("solnum", "auto");
    model.study("std3").feature("frawe").set("notsolnum", "auto");
    model.study("std3").feature("frawe").set("outputmap", new String[]{});
    model.study("std3").feature("frawe").setSolveFor("/physics/emw", true);
    model.study("std3").feature("frawe").set("plist", "range(3.8[GHz],0.01[GHz],5.4[GHz])");
    model.study("std3").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "emw", "sel1;sel2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7535\u573a (emw) 2");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 161, 0);
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature().create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg11").feature("mslc1").set("smooth", "internal");
    model.result("pg11").feature("mslc1").set("data", "parent");
    model.result("pg11").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg11").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg12").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg12").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg12").label("S \u53c2\u6570 (emw) 2");
    model.result("pg12").feature("glob1").set("titletype", "none");
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg12").feature("glob1").set("xdataexpr", "freq");
    model.result("pg12").feature("glob1").set("xdataunit", "GHz");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg13", "SmithGroup");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").create("rgr1", "ReflectionGraph");
    model.result("pg13").feature("rgr1").set("unit", new String[]{""});
    model.result("pg13").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg13").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg13").label("\u53f2\u5bc6\u65af\u56fe (emw) 2");
    model.result("pg13").feature("rgr1").set("titletype", "manual");
    model.result("pg13").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg13").feature("rgr1").set("linemarker", "point");
    model.result("pg13").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg13").feature("rgr1").create("col1", "Color");
    model.result("pg13").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg13").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").label("\u6781\u5316\u56fe (emw) 2");
    model.result("pg14").set("data", "dset3");
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg14").setIndex("looplevelinput", "manual", 0);
    model.result("pg14").setIndex("looplevel", "1", 0);
    model.result("pg14").create("plz1", "Polarization");
    model.result("pg14").feature("plz1").set("linestyle", "solid");
    model.result("pg14").feature("plz1").set("linewidth", 2);
    model.result("pg14").feature("plz1").set("display", "0");
    model.result("pg14").feature("plz1").create("col1", "Color");
    model.result("pg14").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg14").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg14").feature("plz1").set("legend", true);
    model.result("pg14").feature("plz1").set("legendmethod", "manual");
    model.result("pg14").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg14").create("plz2", "Polarization");
    model.result("pg14").feature("plz2").set("linestyle", "dashed");
    model.result("pg14").feature("plz2").set("linewidth", 2);
    model.result("pg14").feature("plz2").set("display", "1");
    model.result("pg14").feature("plz2").create("col1", "Color");
    model.result("pg14").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg14").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg14").feature("plz2").set("legend", true);
    model.result("pg14").feature("plz2").set("legendmethod", "manual");
    model.result("pg14").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature().remove("mslc1");
    model.result("pg11").run();
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().set(3, 10);
    model.result("pg11").run();
    model.result("pg12").run();
    model.result("pg12").set("legendpos", "lowerright");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").setIndex("descr", "S11 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg12").feature("glob1").setIndex("descr", "S21 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 1);
    model.result("pg12").feature().duplicate("glob2", "glob1");
    model.result("pg12").run();
    model.result("pg12").feature("glob2").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg12").feature("glob2").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 1);
    model.result("pg12").feature("glob2").set("data", "dset1");
    model.result("pg12").feature("glob2").set("linestyle", "dotted");
    model.result("pg12").feature("glob2").set("linemarker", "cycle");
    model.result("pg12").run();
    model.result("pg13").run();
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").set("fullsize", new int[]{16, 16, 1});
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result("pg15").label("\u9635\u5217\u573a\u7ed8\u56fe");
    model.result("pg15").set("data", "arr1");
    model.result("pg15").setIndex("looplevel", 9, 0);
    model.result("pg15").set("titletype", "none");
    model.result("pg15").set("edges", false);
    model.result("pg15").set("showlegends", false);

    model.view("view5").set("showaxisorientation", false);

    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg15").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg15").feature("surf1").create("filt1", "Filter");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").feature("filt1").set("expr", "z>=-2.05[mm] && z<=0");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").create("def1", "Deform");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").feature("def1").set("expr", new String[]{"", "", "emw.normE"});
    model.result("pg15").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg15").feature("surf1").feature("def1").set("scale", "0.5E-5");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u5e26\u56fe\u5f62\u6807\u8bb0\u7684\u901a\u5e26");
    model.result("pg16").set("data", "dset3");
    model.result("pg16").create("glob1", "Global");
    model.result("pg16").feature("glob1").set("markerpos", "datapoints");
    model.result("pg16").feature("glob1").set("linewidth", "preference");
    model.result("pg16").feature("glob1").set("expr", new String[]{"emw.S21dB"});
    model.result("pg16").feature("glob1").set("descr", new String[]{"S21"});
    model.result("pg16").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg16").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg16").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg16").run();
    model.result("pg16").feature("glob1").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg16").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg16").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg16").feature("glob1").feature("gmrk1").set("showframe", true);

    model
         .title("\u9891\u7387\u9009\u62e9\u8868\u9762\uff0c\u5468\u671f\u6027\u4e92\u8865\u5f00\u53e3\u8c10\u632f\u73af");

    model
         .description("\u9891\u7387\u9009\u62e9\u8868\u9762 (FSS) \u662f\u4e00\u79cd\u5177\u6709\u5e26\u901a\u6216\u5e26\u963b\u9891\u7387\u54cd\u5e94\u7684\u5468\u671f\u6027\u7ed3\u6784\u3002\u672c\u4f8b\u8868\u660e\uff0c\u53ea\u6709\u63a5\u8fd1\u4e2d\u5fc3\u9891\u7387\u7684\u4fe1\u53f7\u624d\u80fd\u901a\u8fc7\u5468\u671f\u6027\u4e92\u8865\u5f00\u53e3\u8c10\u632f\u73af\u5c42\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("frequency_selective_surface_csrr.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
