/*
 * cascaded_cavity_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:35 by COMSOL 6.3.0.290. */
public class cascaded_cavity_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

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
    model.study("std1").feature("freq").set("plist", "range(3.3[GHz],2.5[MHz],3.38[GHz])");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d", "60[mil]", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("x_slot", "32[mm]", "\u69fd\u4f4d\u7f6e");
    model.param().set("w_slot", "3[mm]", "\u69fd\u5bbd\u5ea6");
    model.param().set("d_cavity", "50[mm]", "\u8f93\u5165\u8154\u5927\u5c0f");
    model.param().set("w_cavity", "100[mm]", "\u8f93\u5165\u8154\u957f\u5ea6");
    model.param().set("l_feed", "10[mm]", "\u9988\u7ebf\u957f\u5ea6");
    model.param().set("h_feed", "20[mm]", "\u9988\u7535\u7bb1\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u8154\u4f53 1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_cavity", "d_cavity", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "d_cavity", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"-w_cavity/2-w_cavity/8", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "d_cavity", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"w_cavity/3", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "d_cavity", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "d", 2);
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"-w_cavity-w_cavity/8", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-w_cavity/4", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "d_cavity*1.5", 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9988\u7535");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"l_feed+w_slot", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "3.2[mm]", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "d", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3")
         .set("pos", new String[]{"-w_cavity/2-w_cavity/8-x_slot-l_feed/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "d_cavity*1.5+d/2", 2);
    model.component("comp1").geom("geom1").run("blk3");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").label("\u9988\u76d2\u957f\u65b9\u4f53");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"w_cavity/3", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "d_cavity", 1);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "h_feed", 2);
    model.component("comp1").geom("geom1").feature("blk4").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk4")
         .set("pos", new String[]{"-w_cavity-w_cavity/8+w_cavity/6", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "d_cavity*1.5+h_feed/2", 2);
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "25[mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"3.5[mm]", "26.4[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-28.5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "75[mm]");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"w_slot", "45[mm]"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-w_cavity/2-w_cavity/8-x_slot", "0"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("blk1", "blk2", "blk3", "blk4", "wp1", "wp2");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk5", "blk1");
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new int[]{0, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(6, 16, 17, 22, 29, 36, 44, 52, 55, 58);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(14);
    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(59);

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
    model.component("comp1").material("mat2").selection().set(2, 4, 7, 9);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"3.38"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

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
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 37, 38, 40, 41, 42, 43, 45, 46, 48, 49, 50, 60, 61, 62);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 37, 38, 40, 41, 42, 43, 45, 46, 48, 49, 50, 60, 61, 62);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 37, 38, 40, 41, 42, 43, 45, 46, 48, 49, 50, 60, 61, 62);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(6, 16, 17, 22, 29, 36, 44, 52, 55, 58);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 37, 38, 40, 41, 42, 43, 45, 46, 48, 49, 50, 60, 61, 62);

    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(6, 16, 17, 22, 29, 36, 44, 52, 55, 58);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "chrome");
    model.result("pg4").feature("surf2").set("expr", "1");
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection()
         .set(9, 14, 15, 18, 19, 20, 21, 31, 39, 47, 51, 53, 54, 56, 57, 59);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-282.8920579993207, -377.1894637398098, 308.9790145210598});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 34.55868482589722);

    model.result("pg4").set("view", "view4");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "-d_cavity/2+0.1,d_cavity/2+0.1,d_cavity/2*3-0.1");
    model.result("pg1").feature("mslc1").set("rangecoloractive", true);
    model.result("pg1").feature("mslc1").set("rangecolormax", 800);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 17, 0);
    model.result("pg5").create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("number", 20);
    model.result("pg5").feature("iso1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("iso1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("iso1").feature("filt1").set("expr", "y>0");
    model.result("pg5").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u96c6\u603b\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(14);

    model.component("comp1").physics("emw").feature("lport1").selection().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u96c6\u603b\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(59);

    model.component("comp1").physics("emw").feature("lport2").selection().named("sel2");

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/emw", true);
    model.study("std2").create("frmod", "Frequencymodal");
    model.study("std2").feature("frmod").set("linpsolnum", "auto");
    model.study("std2").feature("frmod").set("solnum", "auto");
    model.study("std2").feature("frmod").set("notsolnum", "auto");
    model.study("std2").feature("frmod").set("outputmap", new String[]{});
    model.study("std2").feature("frmod").setSolveFor("/physics/emw", true);
    model.study("std2").feature("eig").set("shift", "3.3[GHz]");
    model.study("std2").feature("frmod").set("plist", "range(3.3[GHz],2.5[MHz]/50,3.38[GHz])");
    model.study("std2").feature("frmod").setEntry("outputmap", "emw", "selection");
    model.study("std2").feature("frmod").setEntry("outputselectionmap", "emw", "sel1;sel2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 1601, 0);
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
    model.result("pg7").feature("glob1").set("xdataexpr", "freq");
    model.result("pg7").feature("glob1").set("xdataunit", "GHz");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg8", "SmithGroup");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("rgr1", "ReflectionGraph");
    model.result("pg8").feature("rgr1").set("unit", new String[]{""});
    model.result("pg8").feature("rgr1").set("expr", new String[]{"emw.S11"});

    return model;
  }

  public static Model run2(Model model) {
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
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u9891\u57df\u6a21\u6001\u4e0e\u79bb\u6563\u626b\u63cf\u4e4b\u95f4\u7684 S \u53c2\u6570\u6bd4\u8f83");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset1");
    model.result("pg7").feature("glob2").set("linestyle", "dotted");
    model.result("pg7").feature("glob2").set("linemarker", "cycle");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("descr", "S11\uff0cFD \u6a21\u5f0f", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "S21\uff0cFD \u6a21\u5f0f", 1);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u5e26\u56fe\u5f62\u6807\u8bb0\u7684 S \u53c2\u6570");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"S11"});
    model.result("pg9").feature("glob1").setIndex("unit", "dB", 0);
    model.result("pg9").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg9").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").feature("gmrk1").set("display", "min");
    model.result("pg9").feature("glob1").feature("gmrk1").set("scope", "local");
    model.result("pg9").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg9").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg9").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg9").feature("glob1").feature("gmrk1").set("anchorpoint", "lowerleft");
    model.result("pg9").run();
    model.result("pg9").create("glob2", "Global");
    model.result("pg9").feature("glob2").set("markerpos", "datapoints");
    model.result("pg9").feature("glob2").set("linewidth", "preference");
    model.result("pg9").feature("glob2").set("expr", new String[]{"emw.S21dB"});
    model.result("pg9").feature("glob2").set("descr", new String[]{"S21"});
    model.result("pg9").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg9").feature("glob2").create("gmrk1", "GraphMarker");
    model.result("pg9").feature("glob2").feature("gmrk1").set("linewidth", "preference");
    model.result("pg9").run();
    model.result("pg9").feature("glob2").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg9").feature("glob2").feature("gmrk1").set("precision", 3);
    model.result("pg9").feature("glob2").feature("gmrk1").set("includeunit", true);
    model.result("pg9").feature("glob2").feature("gmrk1").set("showframe", true);

    model.title("\u7ea7\u8054\u77e9\u5f62\u8154\u6ee4\u6ce2\u5668");

    model
         .description("\u76f8\u6bd4\u4e8e\u5355\u8154\u6ee4\u6ce2\u5668\uff0c\u7ea7\u8054\u8154\u6ee4\u6ce2\u5668\u63d0\u4f9b\u4e86\u66f4\u597d\u7684\u5e26\u901a\u6ee4\u6ce2\u6027\u80fd\u3002\u672c\u4f8b\u901a\u8fc7\u69fd\u5c06\u4e09\u4e2a\u77e9\u5f62\u8154\u6ee4\u6ce2\u5668\u7ea7\u8054\u3002\u8ba1\u7b97\u5f97\u5230\u7684 S \u53c2\u6570\u663e\u793a\u51fa\u4f18\u5f02\u7684\u5e26\u5916\u6291\u5236\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("cascaded_cavity_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
