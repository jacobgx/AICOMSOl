/*
 * pipeline_insulation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:58 by COMSOL 6.3.0.290. */
public class pipeline_insulation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("nipfl", "NonisothermalPipeFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/nipfl", true);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "150e3", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 2);
    model.component("comp1").geom("geom1").run("pol1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_oil", "870[kg/m^3]", "\u6cb9\u7684\u5bc6\u5ea6");
    model.param().set("mu_oil", "1e-2[Pa*s]", "\u6cb9\u7684\u9ecf\u5ea6");
    model.param().set("Cp_oil", "2000[J/kg/K]", "\u6cb9\u7684\u70ed\u5bb9");
    model.param().set("k_oil", "0.1[W/m/K]", "\u6cb9\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("gamma_oil", "1", "\u6bd4\u70ed\u7387");
    model.param().set("v_air", "5[m/s]", "\u7ba1\u5916\u7a7a\u6c14\u6d41\u901f");
    model.param().set("T_in", "25[degC]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_ext", "-10[degC]", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("d_wall", "2[cm]", "\u7ba1\u58c1\u539a\u5ea6");
    model.param().set("k_wall", "45[W/m/K]", "\u7ba1\u58c1\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("d_ins", "3[cm]", "\u9694\u70ed\u5c42\u539a\u5ea6");
    model.param().set("k_ins", "0.025[W/m/K]", "\u9694\u70ed\u6750\u6599\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("oil_rate", "2500[m^3/h]", "\u6cb9\u7684\u4f53\u79ef\u6d41\u7387");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u539f\u6cb9");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_oil"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_oil"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_oil"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("ratioofspecificheat", new String[]{"gamma_oil"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_oil"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");

    model.component("comp1").physics("nipfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("nipfl").feature("pipe1").set("innerd", "70[cm]");
    model.component("comp1").physics("nipfl").feature("pipe1").set("frictionmodel", 3);
    model.component("comp1").physics("nipfl").feature("pipe1").set("roughness", 3);
    model.component("comp1").physics("nipfl").feature("temp1").set("Tin", "T_in");
    model.component("comp1").physics("nipfl").create("hofl1", "HeatOutflow", 0);
    model.component("comp1").physics("nipfl").feature("hofl1").selection().set(2);
    model.component("comp1").physics("nipfl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("nipfl").feature("inl1").selection().set(1);
    model.component("comp1").physics("nipfl").feature("inl1").set("spec", 1);
    model.component("comp1").physics("nipfl").feature("inl1").set("qv0", "oil_rate");
    model.component("comp1").physics("nipfl").create("wht1", "WallHeatTransfer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").selection().set(1);
    model.component("comp1").physics("nipfl").feature("wht1").set("Text", "T_ext");
    model.component("comp1").physics("nipfl").feature("wht1").create("intfilm1", "InternalFilmResistance", 1);
    model.component("comp1").physics("nipfl").feature("wht1").create("wall1", "WallLayer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").label("\u94a2\u7ba1\u58c1");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("kChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("k", "k_wall");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("deltawChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall1").set("item.deltaw", "d_wall");
    model.component("comp1").physics("nipfl").feature("wht1").create("wall2", "WallLayer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall2").label("\u9694\u70ed\u5c42");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall2").set("kChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall2").set("k", "k_ins");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall2").set("deltawChoice", "UserDefined");
    model.component("comp1").physics("nipfl").feature("wht1").feature("wall2").set("item.deltaw", "d_ins");
    model.component("comp1").physics("nipfl").feature("wht1").create("extfilm1", "ExternalFilmResistance", 1);
    model.component("comp1").physics("nipfl").feature("wht1").feature("extfilm1").set("externalMaterial", "mat2");
    model.component("comp1").physics("nipfl").feature("wht1").feature("extfilm1").set("u", "v_air");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"nipfl/wht1/wall2"});
    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u4e0d\u9694\u70ed");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "T");
    model.result("pg1").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg1").feature("lngr1").set("unit", "degC");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u4e0d\u9694\u70ed", 0);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/nipfl", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"nipfl/wht1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u5b8c\u5168\u9694\u70ed");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("data", "dset2");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u5b8c\u5168\u9694\u70ed", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("T_diff", "intop1((T_in-T)^2)");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/nipfl", true);
    model.study("std3").create("opt", "Optimization");
    model.study("std3").feature("opt").setIndex("optobj", "comp1.T_diff", 0);
    model.study("std3").feature("opt").setIndex("pname", "rho_oil", 0);
    model.study("std3").feature("opt").setIndex("initval", "870[kg/m^3]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "rho_oil", 0);
    model.study("std3").feature("opt").setIndex("initval", "870[kg/m^3]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "d_ins", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u9694\u70ed\u6750\u6599\u539a\u5ea6\u4f18\u5316");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("o1").run("compute");

    model.study("std3").feature("opt").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").set("data", "dset3");
    model.result("pg1").feature("lngr3")
         .setIndex("legends", "\u4f18\u5316\u7684\u9694\u70ed\u6750\u6599\u539a\u5ea6", 0);
    model.result("pg1").run();
    model.result().dataset().create("dset4nipfl", "Pipe");
    model.result().dataset("dset4nipfl").set("data", "dset4");
    model.result().dataset("dset4nipfl").set("physicsinterface", "nipfl");
    model.result().dataset("dset4nipfl").set("refinement", 1);
    model.result().dataset("dset4nipfl")
         .set("defaultPlotIDs", new String[]{"Pressure|nipfl", "Temperature|nipfl", "Temperature|nipfl|surf2", "Temperature|nipfl|surf3", "geometryOrientation|nipfl"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset4nipfl");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u4e09\u7ef4\u6e29\u5ea6 (nipfl)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"T"});
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").create("filt1", "Filter");
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "nipfl.isactive");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "dset4nipfl");
    model.result("pg2").feature("surf2").set("expr", new String[]{"nipfl.wht1.wall1.T_wl"});
    model.result("pg2").feature("surf2").create("filt1", "Filter");
    model.result("pg2").feature("surf2").feature("filt1").set("expr", "nipfl.wht1.wall1.isactive");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("data", "dset4nipfl");
    model.result("pg2").feature("surf3").set("expr", new String[]{"nipfl.wht1.wall2.T_wl"});
    model.result("pg2").feature("surf3").create("filt1", "Filter");
    model.result("pg2").feature("surf3").feature("filt1").set("expr", "nipfl.wht1.wall2.isactive");
    model.result("pg2").feature("surf3").set("inheritplot", "surf1");
    model.result("pg2").label("\u4e09\u7ef4\u6e29\u5ea6 (nipfl)");
    model.result("pg2").run();
    model.result().dataset("dset4nipfl").set("scale", "50e3");
    model.result("pg2").run();
    model.result("pg2").set("edges", true);
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();

    model.view().create("view2", 3);
    model.view("view2").label("\u7ba1\u6bb5\u89c6\u56fe");

    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u7ba1\u6bb5\u7edd\u70ed");

    model
         .description("\u5f53\u539f\u6cb9\u6d41\u7ecf\u7ba1\u9053\u65f6\uff0c\u7531\u4e8e\u9ecf\u6027\u52a0\u70ed\uff08\u5185\u8017\uff09\uff0c\u4f1a\u4ea7\u751f\u70ed\u91cf\u8017\u6563\u3002\u901a\u8fc7\u5bf9\u7ba1\u9053\u4fdd\u6e29\u8fdb\u884c\u4ed4\u7ec6\u4f18\u5316\uff0c\u53ef\u4ee5\u4f7f\u7528\u70ed\u91cf\u6765\u907f\u514d\u5bf9\u77f3\u6cb9\u8fdb\u884c\u9884\u70ed\uff0c\u5c3d\u7ba1\u5b83\u9700\u8981\u5728\u5bd2\u51b7\u7684\u73af\u5883\u4e2d\u957f\u8ddd\u79bb\u8fd0\u8f93\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u7ba1\u9053\u6d41\u6a21\u5757\u201d\u6765\u6a21\u62df\u7ba1\u9053\u4e2d\u7684\u77f3\u6cb9\u8fd0\u8f93\u3002\u901a\u8fc7\u6dfb\u52a0\u201c\u4f18\u5316\u201d\u7814\u7a76\uff0c\u53ef\u4ee5\u627e\u5230\u7406\u60f3\u7684\u7ba1\u9053\u4fdd\u6e29\u539a\u5ea6\uff0c\u4ece\u800c\u4f7f\u7ba1\u9053\u6cbf\u7ebf\u7684\u6e29\u5ea6\u4fdd\u6301\u6052\u5b9a\u3002");

    model.label("pipeline_insulation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
