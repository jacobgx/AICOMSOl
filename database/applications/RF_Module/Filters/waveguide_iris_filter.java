/*
 * waveguide_iris_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:38 by COMSOL 6.3.0.290. */
public class waveguide_iris_filter {

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
    model.study("std1").feature("freq").set("plist", "range(8.5[GHz],0.05[GHz],11.5[GHz])");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("w_wg", "2.286[cm]", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("h_wg", "1.016[cm]", "\u6ce2\u5bfc\u9ad8\u5ea6");
    model.param().set("d_iris", "0.3[cm]", "\u8679\u819c\u539a\u5ea6");
    model.param().set("l_iris1", "0.695[cm]", "\u8679\u819c\u957f\u5ea6 1");
    model.param().set("l_iris2", "0.53[cm]", "\u8679\u819c\u957f\u5ea6 2");
    model.param().set("spacing", "2.03[cm]", "\u8679\u819c\u95f4\u9694\u8ddd\u79bb");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("WR-90");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"12", "w_wg", "h_wg"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("Iris1");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"d_iris", "l_iris1", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "h_wg", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"spacing/2", "(w_wg-l_iris1)/2", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("Iris2");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"d_iris", "l_iris2", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "h_wg", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"spacing*1.42", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "(w_wg-l_iris2)/2", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk2", "blk3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("blk2", "blk3", "mir1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk2", "blk3", "mir1", "mir2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(1);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(38);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");

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

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
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
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(1, 38);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").camera()
         .set("position", new double[]{-14.395678976307746, -19.194238082222316, 14.395678976307746});
    model.component("comp1").view("view2").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 29.017720222473145);

    model.result("pg4").set("view", "view2");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();

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
    model.study("std2").feature("eig").set("shift", "9.5[GHz]");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 3);
    model.study("std2").feature("frmod").set("plist", "range(8.5[GHz],0.01[GHz],11.5[GHz])");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").physics("emw").feature("port1").selection().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(38);

    model.component("comp1").physics("emw").feature("port2").selection().named("sel2");

    model.study("std2").feature("frmod").setEntry("outputmap", "emw", "selection");
    model.study("std2").feature("frmod").setEntry("outputselectionmap", "emw", "sel1;sel2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (emw) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 301, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg5").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg6").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg6").label("S \u53c2\u6570 (emw) 1");
    model.result("pg6").feature("glob1").set("titletype", "none");
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg6").feature("glob1").set("xdataexpr", "freq");
    model.result("pg6").feature("glob1").set("xdataunit", "GHz");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg7", "SmithGroup");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("rgr1", "ReflectionGraph");
    model.result("pg7").feature("rgr1").set("unit", new String[]{""});
    model.result("pg7").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg7").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg7").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg7").feature("rgr1").set("titletype", "manual");
    model.result("pg7").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg7").feature("rgr1").set("linemarker", "point");
    model.result("pg7").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("rgr1").create("col1", "Color");
    model.result("pg7").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg7").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "-6 6");
    model.result("pg5").feature("mslc1").set("ynumber", "0");
    model.result("pg5").feature("mslc1").set("znumber", "0");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 151, 0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("descr", "S11 \u9891\u57df\u6a21\u6001", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "S21 \u9891\u57df\u6a21\u6001", 0);
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").set("data", "dset1");
    model.result("pg6").feature("glob2").set("expr", new String[]{"emw.S11dB"});
    model.result("pg6").feature("glob2").set("descr", new String[]{"S11"});
    model.result("pg6").feature("glob2").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg6").feature("glob2").set("descr", new String[]{"S11", "S21"});
    model.result("pg6").feature("glob2").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg6").feature("glob2").set("linestyle", "dashed");
    model.result("pg6").feature("glob2").set("linemarker", "circle");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u9891\u57df\u6a21\u6001\u6cd5\u5f97\u51fa\u7684 S \u53c2\u6570\u4e0e\u5e38\u89c4\u79bb\u6563\u626b\u63cf\u5f97\u5230\u7684 S \u53c2\u6570\u6bd4\u8f83");
    model.result("pg7").run();

    model.study().create("std3");
    model.study("std3").create("frawe", "FrequencyAdaptive");
    model.study("std3").feature("frawe").set("plotgroup", "Default");
    model.study("std3").feature("frawe").set("solnum", "auto");
    model.study("std3").feature("frawe").set("notsolnum", "auto");
    model.study("std3").feature("frawe").set("outputmap", new String[]{});
    model.study("std3").feature("frawe").setSolveFor("/physics/emw", true);
    model.study("std3").feature("frawe").set("plist", "range(8.5[GHz],0.01[GHz],11.5[GHz])");
    model.study("std3").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "emw", "sel1;sel2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u573a (emw) 2");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevel", 301, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg8").feature("mslc1").set("smooth", "internal");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg8").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg9").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg9").label("S \u53c2\u6570 (emw) 2");
    model.result("pg9").feature("glob1").set("titletype", "none");
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg9").feature("glob1").set("xdataexpr", "freq");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("glob1").set("xdataunit", "GHz");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg10", "SmithGroup");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").create("rgr1", "ReflectionGraph");
    model.result("pg10").feature("rgr1").set("unit", new String[]{""});
    model.result("pg10").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg10").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg10").label("\u53f2\u5bc6\u65af\u56fe (emw) 2");
    model.result("pg10").feature("rgr1").set("titletype", "manual");
    model.result("pg10").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg10").feature("rgr1").set("linemarker", "point");
    model.result("pg10").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("rgr1").create("col1", "Color");
    model.result("pg10").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg10").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 151, 0);
    model.result("pg8").run();
    model.result("pg8").feature().remove("mslc1");
    model.result("pg8").run();
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().set(1, 38);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "manual");
    model.result("pg9")
         .set("title", "\u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf\u5f97\u5230\u7684 S \u53c2\u6570\u4e0e\u5e38\u89c4\u79bb\u6563\u626b\u63cf\u5f97\u5230\u7684 S \u53c2\u6570\u6bd4\u8f83");
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("descr", "S11 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "S21 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg9").feature().duplicate("glob2", "glob1");
    model.result("pg9").run();
    model.result("pg9").feature("glob2").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg9").feature("glob2").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg9").feature("glob2").set("data", "dset1");
    model.result("pg9").feature("glob2").set("linestyle", "dotted");
    model.result("pg9").feature("glob2").set("linemarker", "cycle");
    model.result("pg9").run();
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u5e26\u56fe\u5f62\u6807\u8bb0\u7684 S \u53c2\u6570");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"S11"});
    model.result("pg11").feature("glob1").setIndex("unit", "dB", 0);
    model.result("pg11").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg11").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").feature("gmrk1").set("display", "min");
    model.result("pg11").feature("glob1").feature("gmrk1").set("scope", "local");
    model.result("pg11").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg11").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg11").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg11").feature("glob1").feature("gmrk1").set("anchorpoint", "lowerleft");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").feature("filt1").set("expr", "emw.freq>9.2[GHz] && emw.freq<10.8[GHz]");
    model.result("pg11").run();
    model.result("pg11").create("glob2", "Global");
    model.result("pg11").feature("glob2").set("markerpos", "datapoints");
    model.result("pg11").feature("glob2").set("linewidth", "preference");
    model.result("pg11").feature("glob2").set("expr", new String[]{"emw.S21dB"});
    model.result("pg11").feature("glob2").set("descr", new String[]{"S21"});
    model.result("pg11").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg11").feature("glob2").create("gmrk1", "GraphMarker");
    model.result("pg11").feature("glob2").feature("gmrk1").set("linewidth", "preference");
    model.result("pg11").run();
    model.result("pg11").feature("glob2").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg11").feature("glob2").feature("gmrk1").set("precision", 3);
    model.result("pg11").feature("glob2").feature("gmrk1").set("includeunit", true);
    model.result("pg11").feature("glob2").feature("gmrk1").set("showframe", true);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("glob2").feature().copy("filt1", "pg11/glob1/filt1");
    model.result("pg11").run();

    model.title("\u6ce2\u5bfc\u8679\u819c\u5e26\u901a\u6ee4\u6ce2\u5668");

    model
         .description("\u4e00\u79cd\u5bfc\u7535\u9694\u819c\u79f0\u4e3a\u8679\u819c\uff0c\u6a2a\u5411\u653e\u7f6e\u5728\u6ce2\u5bfc\u5b54\u524d\uff0c\u4ea7\u751f\u4e0d\u8fde\u7eed\u6027\u548c\u5e76\u8054\u7535\u6297\u3002\u53ef\u4ee5\u901a\u8fc7\u4e0e\u8fd9\u79cd\u53cd\u5e94\u5355\u5143\u7ed3\u5408\u7684\u7ea7\u8054\u7a7a\u8154\u8c10\u632f\u5668\u5b9e\u73b0\u5e26\u901a\u9891\u7387\u54cd\u5e94\uff0c\u8fd9\u53ef\u4ee5\u901a\u8fc7\u5728\u6ce2\u5bfc\u4e2d\u63d2\u5165\u4e00\u7cfb\u5217\u8679\u819c\u5355\u5143\u6765\u5b9e\u73b0\u3002\u672c\u4f8b\u7531 WR90 X \u5e26\u6ce2\u5bfc\u548c\u5bf9\u79f0\u7684\u611f\u5e94\u9694\u819c\uff08\u8679\u819c\uff09\u7ec4\u6210\u3002\u8ba1\u7b97\u5f97\u5230\u7684 S \u53c2\u6570\u663e\u793a\u51fa\u5f88\u597d\u7684\u5e26\u901a\u54cd\u5e94\u548c\u5e26\u5916\u53cd\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("waveguide_iris_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
