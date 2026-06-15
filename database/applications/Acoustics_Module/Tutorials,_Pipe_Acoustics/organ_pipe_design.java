/*
 * organ_pipe_design.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class organ_pipe_design {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pipe_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pafd", "FrequencyPipeAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/pafd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f", "440[Hz]", "\u97f3\u7b26 A4 \u7684\u9891\u7387");
    model.param().set("Lguess", "1/2*c0/f", "\u534a\u6ce2\u957f");
    model.param().set("L", "0.3805[m]", "\u7ba1\u957f");
    model.param().set("d", "3[cm]", "\u5185\u7ba1\u5f84");
    model.param().set("a", "d/2", "\u5185\u7ba1\u534a\u5f84");
    model.param().set("dw", "2[mm]", "\u7ba1\u58c1\u539a\u5ea6");
    model.param().set("Ew", "1e9[Pa]", "\u7ba1\u58c1\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nuw", "0.4", "\u7ba1\u58c1\u6cca\u677e\u6bd4");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("ka", "2*pi*f/c0*a", "\u6ce2\u6570\u4e58\u4ee5\u7ba1\u534a\u5f84");
    model.param().set("h_min", "c0/3000[Hz]/20", "3000 Hz \u65f6\u7684\u7f51\u683c\u5927\u5c0f");
    model.param().set("T0", "20[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("p0", "1[atm]", "\u73af\u5883\u538b\u529b");
    model.param().set("dL", "0.6*a", "\u7aef\u90e8\u4fee\u6b63");
    model.param().set("f_est", "c0/(2*(L+dL))", "\u9884\u4f30\u5171\u632f\u9891\u7387");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L", 1, 2);
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

    model.component("comp1").physics("pafd").feature("fp1").set("minput_temperature", "T0");
    model.component("comp1").physics("pafd").feature("fp1").set("minput_pressure", "p0");
    model.component("comp1").physics("pafd").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pafd").feature("pipe1").set("innerd", "d");
    model.component("comp1").physics("pafd").feature("pipe1").set("pipeModel", "AnchoredAtOneEnd");
    model.component("comp1").physics("pafd").feature("pipe1").set("E_mat", "userdef");
    model.component("comp1").physics("pafd").feature("pipe1").set("E", "Ew");
    model.component("comp1").physics("pafd").feature("pipe1").set("nu_mat", "userdef");
    model.component("comp1").physics("pafd").feature("pipe1").set("nu", "nuw");
    model.component("comp1").physics("pafd").feature("pipe1").set("dw", "dw");
    model.component("comp1").physics("pafd").create("endimp1", "EndImpedance", 0);
    model.component("comp1").physics("pafd").feature("endimp1").selection().set(2);
    model.component("comp1").physics("pafd").feature("endimp1").set("Type", "UnflangedPipeCircular");
    model.component("comp1").physics("pafd").create("pres1", "Pressure", 0);
    model.component("comp1").physics("pafd").feature("pres1").selection().set(1);
    model.component("comp1").physics("pafd").feature("pres1").set("p", 1);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h_min");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "h_min/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "range(f_est-30,0.5,f_est+30)");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "f", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "d", 0);
    model.study("std1").feature("param").setIndex("plistarr", "2[cm] 2.5[cm] 3[cm] 3.5[cm] 4[cm]", 0);
    model.study("std1").label("\u7814\u7a76 1 - \u5185\u7ba1\u5f84\u626b\u63cf");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b (pafd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").setIndex("looplevel", 5, 1);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ebf");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*pafd.dh");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("colortable", "Wave");
    model.result("pg1").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u58f0\u901f (pafd)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 121, 0);
    model.result("pg2").setIndex("looplevel", 5, 1);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u7ebf");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "u");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "0.5*pafd.dh");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/pafd", true);
    model.study("std2").feature("freq").set("plist", "range(f_est-30,0.5,f_est+30)");
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").setIndex("pname_aux", "f", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "f", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "dw", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "0.5[mm] 1[mm] 2[mm] 3[mm]", 0);
    model.study("std2").label("\u7814\u7a76 2 - \u7ba1\u58c1\u539a\u626b\u63cf");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/pafd", true);
    model.study("std3").feature("freq").set("plist", "range(100,10,3000)");
    model.study("std3").label("\u7814\u7a76 3 - \u6269\u5c55\u9891\u7387\u626b\u63cf");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u54cd\u5e94\uff1a\u4e0d\u540c\u7ba1\u5f84");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(2);
    model.result("pg3").feature("ptgr1").set("expr", "pafd.Lp");
    model.result("pg3").feature("ptgr1").set("descr", "\u58f0\u538b\u7ea7");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("autopoint", false);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u54cd\u5e94\uff1a\u4e0d\u540c\u58c1\u539a");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").feature("ptgr1").set("expr", "pafd.Lp");
    model.result("pg4").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset4");
    model.result("pg5").label("\u54cd\u5e94\uff1a\u7ba1\u8c10\u632f\u9891\u7387");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(2);
    model.result("pg5").feature("ptgr1").set("expr", "pafd.Lp");
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").run();

    model.title("\u7ba1\u98ce\u7434\u97f3\u7ba1\u8bbe\u8ba1");

    model
         .description("\u7ba1\u98ce\u7434\u97f3\u7ba1\u7684\u8bbe\u8ba1\u53d6\u51b3\u4e8e\u7ba1\u7684\u534a\u5f84\u3001\u957f\u5ea6\u3001\u6750\u6599\u548c\u58c1\u539a\u3002\n\n\u672c\u4f8b\u901a\u8fc7\u5206\u6790\u786e\u5b9a\u97f3\u7ba1\u7684\u56db\u5206\u4e4b\u4e00\u6ce2\u957f\u8c10\u632f\u9891\u7387\uff0c\u5e76\u786e\u5b9a\u4e86\u5305\u542b\u8c10\u6ce2\u7684\u9891\u7387\u54cd\u5e94\u3002\u5728\u6b64\u6a21\u578b\u7684 App \u7248\u672c\u4e2d\uff0c\u53ef\u4ee5\u64ad\u653e\u548c\u6536\u542c\u7531\u7ed9\u5b9a\u8bbe\u8ba1\u4ea7\u751f\u7684\u58f0\u97f3\uff0c\u540c\u65f6\u6f14\u793a\u201cApp \u5f00\u53d1\u5668\u201d\u7684\u4e00\u4e9b\u5176\u4ed6\u529f\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("organ_pipe_design.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
