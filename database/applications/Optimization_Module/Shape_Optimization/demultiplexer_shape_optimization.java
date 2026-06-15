/*
 * demultiplexer_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:42 by COMSOL 6.3.0.290. */
public class demultiplexer_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.param().set("f1", "5.5[kHz]");
    model.param().descr("f1", "\u9891\u7387 1");
    model.param().set("f2", "7.5[kHz]");
    model.param().descr("f2", "\u9891\u7387 2");
    model.param().set("df", "50[Hz]");
    model.param().set("dfN", "5");
    model.param().set("meshsz", "340[m/s]/f2/6");
    model.param().descr("meshsz", "\u7f51\u683c\u5927\u5c0f");
    model.param().set("Pc", "1[mW/m]");
    model.param().descr("Pc", "\u7279\u6027\u529f\u7387");

    model.component("comp1").geom("geom1").insertFile("demultiplexer_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("acpr").create("port1", "Port", 1);
    model.component("comp1").physics("acpr").feature("port1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Slit");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", 1);
    model.component("comp1").physics("acpr").create("port2", "Port", 1);
    model.component("comp1").physics("acpr").feature("port2").selection().named("geom1_boxsel2");
    model.component("comp1").physics("acpr").feature("port2").set("PortType", "Slit");
    model.component("comp1").physics("acpr").create("port3", "Port", 1);
    model.component("comp1").physics("acpr").feature("port3").selection().named("geom1_boxsel3");
    model.component("comp1").physics("acpr").feature("port3").set("PortType", "Slit");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").feature("freq").set("plist", "f1 f2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u7aef\u53e3\u529f\u7387");
    model.component("comp1").variable("var1").set("power2", "10*log10(acpr.port2.P_out/Pc)");
    model.component("comp1").variable("var1").descr("power2", "\u7aef\u53e3 2 \u529f\u7387");
    model.component("comp1").variable("var1").set("power3", "10*log10(acpr.port3.P_out/Pc)");
    model.component("comp1").variable("var1").descr("power3", "\u7aef\u53e3 3 \u529f\u7387");
    model.component("comp1").variable("var1")
         .set("power_rat", "log10(acpr.port2.P_out/Pc)-log10(acpr.port3.P_out/Pc)");
    model.component("comp1").variable("var1").descr("power_rat", "\u529f\u7387\u6bd4");
    model.component("comp1").variable("var1").set("obj", "if(2*freq<f1+f2,power_rat,-power_rat)/10");
    model.component("comp1").variable("var1").descr("obj", "\u76ee\u6807");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp1").common("fsb1").selection().named("geom1_disksel3");
    model.component("comp1").common("fsb1").set("maximumDisplacement", "0.5*Rhole");
    model.component("comp1").common("fsb1").set("filterRadiusType", "Medium");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u4f18\u5316");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("movelimitactive", false);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u76ee\u6807"});
    model.study("std2").feature("sho").set("objectivesolution", "max");
    model.study("std2").feature("freq")
         .set("plist", "range(f1-0.5*df,df/(dfN-1),f1+0.5*df) range(f2-0.5*df,df/(dfN-1),f2+0.5*df)");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b (acpr) 1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg5", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u7814\u7a76 2\uff1a\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg5").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "geometry");
    model.result("pg5").set("edgecolor", "gray");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "fromtheme");
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("data", "dset2g1");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"fsd1.dXg", "fsd1.dYg"});
    model.result("pg5").feature("arwl1").set("scaleactive", true);
    model.result("pg5").feature("arwl1").set("scale", "1");
    model.result("pg5").feature("arwl1").set("placement", "uniform");
    model.result("pg5").feature("arwl1").set("arrowcount", 200);
    model.result("pg5").feature("arwl1").create("col1", "Color");
    model.result("pg5").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg5").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg5").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg5").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg5").feature("arwl1").create("sel1", "Selection");
    model.result("pg5").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").set("placement", "elements");

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg5");
    model.study("std2").feature("sho").set("mmamaxiter", 25);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result().dataset("dset2").createDeformedConfig("deform1", "mesh2");

    model.component("comp1").mesh("mesh2").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "meshsz/4");
    model.component("comp1").mesh("mesh2").feature("size1").selection().geom("deform1", 1);
    model.component("comp1").mesh("mesh2").feature("size1").selection().named("geom1_disksel3");
    model.component("comp1").mesh("mesh2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("size1").set("hmax", "meshsz/4");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ref1").active(false);

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").set("plist", "f1 f2");
    model.study("std3").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u9a8c\u8bc1 (f1,f2)");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std4").feature("freq").set("plist", "range(f1-10*df,(f2+10*df-(f1-10*df))/400,f2+10*df)");
    model.study("std4").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std4").feature("freq").set("usesol", true);
    model.study("std4").feature("freq").set("notsolmethod", "sol");
    model.study("std4").feature("freq").set("notstudy", "std2");
    model.study("std4").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std4").feature("freq").setEntry("outputselectionmap", "acpr", "geom1_disksel2");
    model.study("std4").feature("freq").setEntry("outputmap", "frame:material1", "selection");
    model.study("std4").feature("freq").setEntry("outputselectionmap", "frame:material1", "geom1_disksel2");
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u9a8c\u8bc1 (mesh2)");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u9891\u8c31");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u529f\u7387 (dB)");
    model.result("pg6").set("legendpos", "lowermiddle");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"power2"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u7aef\u53e3 2 \u529f\u7387"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"power2", "power3"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u7aef\u53e3 2 \u529f\u7387", "\u7aef\u53e3 3 \u529f\u7387"});
    model.result("pg6").feature("glob1").set("xdataparamunit", "kHz");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "dset2");
    model.result("pg6").feature("glob2").setIndex("descr", "\u7aef\u53e3 2 (mesh1)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u7aef\u53e3 3 (mesh1)", 1);
    model.result("pg6").feature("glob2").set("linestyle", "none");
    model.result("pg6").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg6").feature("glob2").set("linemarker", "square");
    model.result("pg6").feature("glob2").set("legend", false);
    model.result("pg6").run();
    model.result().dataset("dset3").set("frametype", "spatial");
    model.result("pg3").run();
    model.result("pg3").label("\u52a8\u753b");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "with(1,acpr.p_t)");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("trn1").set("move", new String[]{"1.25*L", "0"});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -1);
    model.result("pg3").feature("surf1").set("rangecolormax", 1);
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("trn1").set("move", new String[]{"1.25*L", "0"});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").run();
    model.result().export("anim1").set("repeat", "forever");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("expr", "with(1,acpr.Lp_t)");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature("surf2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("trn1").set("move", new String[]{"1.25*L", "0"});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"acpr.Ix", "acpr.Iy"});
    model.result("pg4").feature("str1")
         .set("descr", "\u5f3a\u5ea6 \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg4").feature("str1").selection().named("geom1_boxsel1");
    model.result("pg4").feature("str1").set("color", "blue");
    model.result("pg4").feature().duplicate("str2", "str1");
    model.result("pg4").run();
    model.result("pg4").feature("str2").set("expr", new String[]{"with(1,acpr.Ix)", "acpr.Iy"});
    model.result("pg4").feature("str2").setIndex("expr", "with(1,acpr.Iy)", 1);
    model.result("pg4").feature("str2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("str2").feature("trn1").set("move", new String[]{"1.25*L", "0"});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("line1").feature("trn1").set("move", new String[]{"1.25*L", "0"});
    model.result("pg4").run();
    model.result("pg4").run();

    model.study("std1").feature("freq").setSolveFor("/frame/material1", false);

    model.result("pg4").run();
    model.result("pg4").set("data", "dset1");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").run();

    model.title("\u58f0\u4fe1\u53f7\u5206\u79bb\u5668\u7684\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5f62\u72b6\u4f18\u5316\u6765\u8bbe\u8ba1\u58f0\u4fe1\u53f7\u5206\u79bb\u5668\u3002\u4fe1\u53f7\u5206\u79bb\u5668\u662f\u4e00\u79cd\u6570\u636e\u5206\u914d\u8bbe\u5907\uff0c\u672c\u4f8b\u4e2d\u5b83\u5c06\u5206\u914d\u58f0\u80fd\u3002\u51e0\u4f55\u7ed3\u6784\u7531\u5177\u6709\u4e00\u4e2a\u8f93\u5165\u7aef\u53e3\u548c\u4e24\u4e2a\u8f93\u51fa\u7aef\u53e3\u7684\u5706\u5f62\u57df\u7ec4\u6210\u3002\u8be5\u57df\u5177\u6709\u58f0\u5b50\u6676\u4f53\u7684\u7ed3\u6784\uff0c\u5305\u542b 19\u00a0\u4e2a\u5706\u5f62\u8154\uff0c\u8fd9\u4e9b\u8154\u4f1a\u53d1\u751f\u53d8\u5f62\uff0c\u4ece\u800c\u4f7f\u80fd\u91cf\u5728\u4e00\u4e2a\u9891\u6bb5\u8fdb\u5165\u4e00\u4e2a\u8f93\u51fa\u7aef\u53e3\uff0c\u5e76\u5728\u53e6\u4e00\u4e2a\u9891\u6bb5\u8fdb\u5165\u53e6\u4e00\u4e2a\u8f93\u51fa\u7aef\u53e3\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("demultiplexer_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
