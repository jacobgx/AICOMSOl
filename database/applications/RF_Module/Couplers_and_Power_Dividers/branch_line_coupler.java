/*
 * branch_line_coupler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:31 by COMSOL 6.3.0.290. */
public class branch_line_coupler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Couplers_and_Power_Dividers");

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
    model.study("std1").feature("freq").set("plist", "range(1[GHz],100[MHz],5[GHz])");

    model.param().set("thickness", "60[mil]");
    model.param().descr("thickness", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("l_s", "40[mm]");
    model.param().descr("l_s", "\u957f\u5ea6\uff0c\u57fa\u677f");
    model.param().set("w_line2", "5[mm]");
    model.param().descr("w_line2", "\u5bbd\u5ea6\uff0c\u7ebf 2");
    model.param().set("l_line2", "13[mm]");
    model.param().descr("l_line2", "\u957f\u5ea6\uff0c\u7ebf 2");
    model.param().set("l_line1", "(l_s-l_line2)/2");
    model.param().descr("l_line1", "\u957f\u5ea6\uff0c\u7ebf 1");
    model.param().set("w_line1", "3.2[mm]");
    model.param().descr("w_line1", "\u5bbd\u5ea6\uff0c\u7ebf 1");
    model.param().set("w_line3", "3[mm]");
    model.param().descr("w_line3", "\u5bbd\u5ea6\uff0c\u7ebf 3");
    model.param().set("l_line3", "13.6[mm]");
    model.param().descr("l_line3", "\u957f\u5ea6\uff0c\u7ebf 3");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"2*w_line1+l_line3", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "l_s", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"w_line2*2+l_line3", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "l_line2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"l_line3", "l_line2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"-l_line3/2", "l_line2/2+w_line3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("linearsize", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "-l_line2-w_line3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "thickness", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l_s", "l_s", "thickness"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "thickness/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "ext1");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u5c01\u88c5");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"l_s", "l_s+l_s/8", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "thickness*5", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "thickness*5/2"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideEntities().create("hide1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 4);

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(13);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(24);
    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(25);
    model.component("comp1").physics("emw").create("lport3", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport3").selection().set(15);
    model.component("comp1").physics("emw").create("lport4", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport4").selection().set(14);

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
    model.component("comp1").material("mat2").label("\u57fa\u677f");
    model.component("comp1").material("mat2").selection().named("geom1_uni1_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"3.38"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

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
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB", "emw.S41dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S31", "S41"});
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
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 10, 11, 12, 17, 19, 21, 27, 30, 31);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 10, 11, 12, 17, 19, 21, 27, 30, 31);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 10, 11, 12, 17, 19, 21, 27, 30, 31);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(13);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 10, 11, 12, 17, 19, 21, 27, 30, 31);

    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(13);
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
         .set(6, 8, 9, 14, 15, 16, 18, 20, 22, 23, 24, 25, 26, 28, 29);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-54.571762084960945, -72.7623516580333, 54.903066883916445});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 30.869452953338623);

    model.result("pg4").set("view", "view3");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "thickness");
    model.result("pg1").feature("mslc1").set("rangecoloractive", true);
    model.result("pg1").feature("mslc1").set("rangecolormax", 1000);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5931\u8861");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u8026\u5408\u7aef\u53e3\u7684\u5e45\u503c\u548c\u76f8\u4f4d\u5931\u8861");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "emw.S21dB-emw.S31dB", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "dB", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5e45\u503c\u5dee", 0);
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "arg(emw.S21)-arg(emw.S31)", 0);
    model.result("pg5").feature("glob2").setIndex("unit", "deg", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u76f8\u4f4d\u5dee", 0);
    model.result("pg5").feature("glob2").set("unwrapphase", true);
    model.result("pg5").run();
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg5").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{21}, 0);
    model.result().numerical("gev1").setIndex("expr", "arg(emw.S21)-arg(emw.S31)", 0);
    model.result().numerical("gev1").setIndex("unit", "\u00b0", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e26\u56fe\u50cf\u6807\u8bb0\u7684 S \u53c2\u6570");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"S11"});
    model.result("pg6").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg6").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg6").feature("glob1").feature("gmrk1").set("rangetype", "widthoutside");
    model.result("pg6").feature("glob1").feature("gmrk1").set("cutoffvalabs", -10);
    model.result("pg6").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg6").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg6").feature("glob1").feature("gmrk1").set("showframe", true);

    model.param().set("w_line2", "5.2[mm]");

    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "thickness+0.635"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "l_s", 1);
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"l_s", "l_s", "20"});
    model.component("comp1").geom("geom1").run("blk2");
    model.geom()
         .load(new String[]{"part1"}, "RF_Module\\Connectors\\SMA_Connectors\\connector_sma_flange2.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_dielectric", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_pin", "1[mm]");
    model.component("comp1").geom("geom1").feature("pi1")
         .set("displ", new String[]{"-8.4", "-l_s/2", "thickness+0.635"});
    model.component("comp1").geom("geom1").feature("pi1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 16.8);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("copy1", "pi1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "0 180");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("fin", 6, 44, 153, 156, 158, 319);
    model.component("comp1").geom("geom1").run("cmf1");

    model.component("comp1").physics("emw").feature("pec2").selection().set(6, 44);
    model.component("comp1").physics("emw").feature("lport1").selection().set(234);
    model.component("comp1").physics("emw").feature("lport1").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").feature("lport2").selection().set(243);
    model.component("comp1").physics("emw").feature("lport2").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").feature("lport3").selection().set(80);
    model.component("comp1").physics("emw").feature("lport3").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").feature("lport4").selection().set(71);
    model.component("comp1").physics("emw").feature("lport4").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec3").selection().named("geom1_pi1_sel2");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("geom1_pi1_sel3");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();

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
    model.study("std2").feature("freq").set("plist", "range(1[GHz],100[MHz],5[GHz])");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (emw) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 41, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg7").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg8").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB", "emw.S41dB"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"S11", "S21", "S31", "S41"});
    model.result("pg8").label("S \u53c2\u6570 (emw) 1");
    model.result("pg8").feature("glob1").set("titletype", "none");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg8").feature("glob1").set("xdataexpr", "freq");
    model.result("pg8").feature("glob1").set("xdataunit", "GHz");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg9", "SmithGroup");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("rgr1", "ReflectionGraph");
    model.result("pg9").feature("rgr1").set("unit", new String[]{""});
    model.result("pg9").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg9").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg9").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg9").feature("rgr1").set("titletype", "manual");
    model.result("pg9").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg9").feature("rgr1").set("linemarker", "point");
    model.result("pg9").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("rgr1").create("col1", "Color");
    model.result("pg9").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg9").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24, 25, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 66, 67, 68, 89, 110, 111, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 136, 137, 140, 141, 142, 143, 144, 145, 146, 147, 148, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 184, 186, 188, 189, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 226, 227, 229, 230, 231, 252, 273, 274, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 299, 300, 303, 304, 305, 306, 307, 308, 309, 310, 311, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24, 25, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 66, 67, 68, 71, 80, 89, 110, 111, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 136, 137, 140, 141, 142, 143, 144, 145, 146, 147, 148, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 184, 186, 188, 189, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 226, 227, 229, 230, 231, 234, 243, 252, 273, 274, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 299, 300, 303, 304, 305, 306, 307, 308, 309, 310, 311, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341);

    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "emw.normE");
    model.result("pg10").feature("surf1").create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24, 25, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 66, 67, 68, 89, 110, 111, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 136, 137, 140, 141, 142, 143, 144, 145, 146, 147, 148, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 184, 186, 188, 189, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 226, 227, 229, 230, 231, 252, 273, 274, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 299, 300, 303, 304, 305, 306, 307, 308, 309, 310, 311, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341);
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf1").create("tran1", "Transparency");
    model.result("pg10").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg10").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg10").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg10").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(6, 18, 19, 21, 23, 26, 27, 44, 62, 65, 69, 70, 72, 73, 75, 76, 78, 79, 83, 85, 87, 88, 90, 91, 92, 93, 97, 98, 99, 100, 101, 102, 104, 105, 106, 107, 112, 113, 114, 115, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 138, 139, 149, 150, 151, 156, 182, 183, 185, 187, 190, 191, 225, 228, 232, 233, 235, 236, 238, 239, 241, 242, 246, 248, 250, 251, 253, 254, 255, 256, 260, 261, 262, 263, 264, 265, 267, 268, 269, 270, 275, 276, 277, 278, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 301, 302, 312, 313, 314, 317);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24, 25, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 66, 67, 68, 71, 80, 89, 110, 111, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 136, 137, 140, 141, 142, 143, 144, 145, 146, 147, 148, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 184, 186, 188, 189, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 226, 227, 229, 230, 231, 234, 243, 252, 273, 274, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 299, 300, 303, 304, 305, 306, 307, 308, 309, 310, 311, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341);

    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("expr", "emw.normE");
    model.result("pg10").feature("surf2").create("sel1", "Selection");
    model.result("pg10").feature("surf2").feature("sel1").selection()
         .set(6, 18, 19, 21, 23, 26, 27, 44, 62, 65, 69, 70, 72, 73, 75, 76, 78, 79, 83, 85, 87, 88, 90, 91, 92, 93, 97, 98, 99, 100, 101, 102, 104, 105, 106, 107, 112, 113, 114, 115, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 138, 139, 149, 150, 151, 156, 182, 183, 185, 187, 190, 191, 225, 228, 232, 233, 235, 236, 238, 239, 241, 242, 246, 248, 250, 251, 253, 254, 255, 256, 260, 261, 262, 263, 264, 265, 267, 268, 269, 270, 275, 276, 277, 278, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 301, 302, 312, 313, 314, 317);
    model.result("pg10").feature("surf2").set("colortable", "Dipole");
    model.result("pg10").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf2").create("tran1", "Transparency");
    model.result("pg10").feature("surf2").feature("tran1").set("transparency", 0.38);
    model.result("pg10").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg10").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("surf2").feature("mtrl1").set("family", "chrome");
    model.result("pg10").feature("surf2").set("expr", "1");
    model.result("pg10").create("surf3", "Surface");
    model.result("pg10").feature("surf3").set("expr", "emw.normE");
    model.result("pg10").feature("surf3").create("sel1", "Selection");
    model.result("pg10").feature("surf3").feature("sel1").selection()
         .set(9, 74, 77, 81, 82, 84, 86, 94, 95, 96, 103, 108, 109, 152, 153, 154, 155, 157, 237, 240, 244, 245, 247, 249, 257, 258, 259, 266, 271, 272, 315, 316, 318);
    model.result("pg10").feature("surf3").set("colortable", "Dipole");
    model.result("pg10").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf3").create("tran1", "Transparency");
    model.result("pg10").feature("surf3").feature("tran1").set("transparency", 0.3);
    model.result("pg10").create("surf4", "Surface");
    model.result("pg10").feature("surf4").set("expr", "emw.normE");
    model.result("pg10").feature("surf4").create("sel1", "Selection");
    model.result("pg10").feature("surf4").feature("sel1").selection().set(71, 80, 234, 243);
    model.result("pg10").feature("surf4").set("colortable", "Dipole");
    model.result("pg10").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf4").create("tran1", "Transparency");
    model.result("pg10").feature("surf4").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view5", "geom1");
    model.component("comp1").view("view5").camera()
         .set("position", new double[]{-54.571762084960945, -72.7623516580333, 54.903066883916445});
    model.component("comp1").view("view5").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view5").camera().set("zoomanglefull", 30.869452953338623);

    model.result("pg10").set("view", "view5");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg10").run();

    model.title("\u5206\u652f\u7ebf\u8026\u5408\u5668");

    model
         .description("\u5206\u652f\u7ebf\u8026\u5408\u5668\u4e5f\u79f0\u4e3a\u6b63\u4ea4\uff0890 \u5ea6\uff09\u6df7\u5408\u5668\uff0c\u662f\u4e00\u79cd\u56db\u7aef\u53e3\u7f51\u7edc\u8bbe\u5907\uff0c\u5305\u542b\u4e00\u4e2a\u8f93\u5165\u7aef\uff0c\u4e24\u4e2a\u76f8\u5dee 90 \u5ea6\u7684\u8f93\u51fa\u7aef\uff0c\u4ee5\u53ca\u4e00\u4e2a\u9694\u79bb\u7aef\u3002\u7531\u4e8e\u5bf9\u79f0\u6027\uff0c\u4efb\u610f\u4e00\u4e2a\u7aef\u53e3\u90fd\u53ef\u4ee5\u4f5c\u4e3a\u8f93\u5165\u7aef\u3002\u672c\u4f8b\u7684\u76ee\u6807\u662f\u8ba1\u7b97 S \u53c2\u6570\uff0c\u5e76\u89c2\u5bdf\u5de5\u4f5c\u9891\u7387\u65f6\u7684\u5339\u914d\u3001\u9694\u79bb\u548c\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("branch_line_coupler.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
